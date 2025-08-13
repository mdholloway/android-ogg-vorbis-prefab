#!/usr/bin/env bash
set -euo pipefail

NDK="$HOME/Android/Sdk/ndk/27.0.12077973"
ABIS=("arm64-v8a" "armeabi-v7a" "x86_64")
API=24
ROOT="$(cd "$(dirname "$0")" && pwd)"

build_one() {
  local ABI="$1"

  echo "==> Building Ogg for $ABI"
  cmake "$ROOT/sources/ogg" \
    -B "$ROOT/build/$ABI/ogg" \
    -G Ninja \
    -DCMAKE_TOOLCHAIN_FILE="$NDK/build/cmake/android.toolchain.cmake" \
    -DANDROID_ABI="$ABI" \
    -DANDROID_PLATFORM="android-$API" \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_INSTALL_PREFIX="$ROOT/install/$ABI" \
    -DANDROID_ARM_NEON=TRUE
  cmake --build "$ROOT/build/$ABI/ogg" --target install

  echo "==> Building Vorbis for $ABI"
  cmake "$ROOT/sources/vorbis" \
    -B "$ROOT/build/$ABI/vorbis" \
    -G Ninja \
    -DCMAKE_TOOLCHAIN_FILE="$NDK/build/cmake/android.toolchain.cmake" \
    -DANDROID_ABI="$ABI" \
    -DANDROID_PLATFORM="android-$API" \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_INSTALL_PREFIX="$ROOT/install/$ABI" \
    -DCMAKE_PREFIX_PATH="$ROOT/install/$ABI" \
    -DCMAKE_POLICY_DEFAULT_CMP0057=NEW \
    -DCMAKE_FIND_PACKAGE_PREFER_CONFIG=ON \
    -DOgg_DIR="$ROOT/install/$ABI/lib/cmake/Ogg" \
    -DANDROID_ARM_NEON=TRUE
  cmake --build "$ROOT/build/$ABI/vorbis" --target install
}

for abi in "${ABIS[@]}"; do
  build_one "$abi"
done

echo "Done. Staged outputs under $ROOT/install/<abi>."


# Ogg + Vorbis Prefab for Android

This project provides **Android Prefab AARs** for [libogg](https://xiph.org/ogg) and [libvorbis](https://xiph.org/vorbis), making it easy to consume these native libraries in Android projects via Gradle and CMake.

It builds Ogg and Vorbis as shared libraries, wraps them in AARs with Prefab metadata, and publishes them to Maven for use in consuming apps.

## Features
* **Prefab support:** Libraries ship with CMake config files (`oggConfig.cmake`, `vorbisConfig.cmake`, etc.) so you can `find_package()` them in your consuming project.
* **Multi-ABI builds:** Prebuilt for `arm64-v8a`, `armeabi-v7a`, and `x86_64`.

## Usage
1.	Add dependencies in your consumer app’s `build.gradle.kts`:
```gradle
dependencies {
    implementation("org.mdholloway:ogg:1.0.0")
    implementation("org.mdholloway:vorbis:1.0.0")
}
```
2.	Enable Prefab in your consumer app’s Gradle config:
```gradle
android {
    buildFeatures {
        prefab = true
    }
}
```
3.	Use CMake to find and link the libraries:
```cmake
find_package(ogg REQUIRED CONFIG)
find_package(vorbis REQUIRED CONFIG)
find_package(vorbisfile REQUIRED CONFIG)

target_link_libraries(your_target
    PRIVATE
    ogg::ogg
    vorbis::vorbis
    vorbis::vorbisfile
)
```

## Building and Publishing Locally

To build the AARs and publish them to your local Maven repository:

```sh
./gradlew :ogg:publishToMavenLocal
./gradlew :vorbis:publishToMavenLocal
```

Then add them to your consuming project as shown above.

## License

Ogg and Vorbis are licensed under the **BSD license**.
This project’s build scripts are licensed under the MIT license. See `LICENSE` for details.

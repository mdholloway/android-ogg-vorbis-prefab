import com.android.build.gradle.internal.cxx.configure.CmakeProperty

plugins {
  alias(libs.plugins.android.library)
}

android {
  namespace = "org.mdholloway.oggvorbis"
  compileSdk=36

  defaultConfig {
    minSdk = 24
    externalNativeBuild {
      cmake {
        arguments += listOf(
          "-DANDROID_STL=c++_shared",
          "-DCMAKE_POLICY_DEFAULT_CMP0057=NEW",
        )
      }
    }
    ndk {
      abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86_64")
    }
  }

  buildFeatures {
    prefabPublishing = true
  }

  prefab {
    create("ogg") {
      headers = "src/main/prefab-export/ogg/include"
    }
    create("vorbis") {
      headers = "src/main/prefab-export/vorbis/include"
    }
  }

  externalNativeBuild {
    cmake {
      path = file("CMakeLists.txt")
      version = "3.28.0+"
    }
  }
}

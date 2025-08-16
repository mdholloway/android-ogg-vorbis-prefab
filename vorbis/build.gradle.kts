plugins {
  alias(libs.plugins.android.library)
  id("maven-publish")
}

android {
  namespace = "org.mdholloway.vorbis"
  compileSdk=36

  defaultConfig {
    minSdk = 24
    externalNativeBuild {
      cmake {
        arguments += listOf(
          "-DBUILD_SHARED_LIBS=ON",
          "-DCMAKE_POLICY_DEFAULT_CMP0057=NEW",
	      "-DANDROID_STL=none",
	      "-DCMAKE_IGNORE_PATH=/usr;/usr/local",
        )
      }
    }
    ndk {
      abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86_64")
    }
  }

  buildFeatures {
    prefab = true
    prefabPublishing = true
  }

  prefab {
    create("vorbis") {
      headers = "src/main/prefab-export/vorbis/include"
    }
    create("vorbisfile") {
      headers = "src/main/prefab-export/vorbis/include"
    }
    create("vorbisenc") {
      headers = "src/main/prefab-export/vorbis/include"
    }
  }

  externalNativeBuild {
    cmake {
      path = file("CMakeLists.txt")
      version = "3.22.1"
    }
  }

  packaging {
    jniLibs {
      useLegacyPackaging = false
    }
  }

  publishing {
    singleVariant("debug")
    singleVariant("release")
  }
}

dependencies {
  api("org.mdholloway:ogg:1.0.0")
}

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("debugAar") {
        from(components["debug"])
        groupId = "org.mdholloway"
        artifactId = "vorbis"
        version = "1.0.0-debug"
      }
      create<MavenPublication>("releaseAar") {
        from(components["release"])
        groupId = "org.mdholloway"
        artifactId = "vorbis"
        version = "1.0.0"
      }
    }
    repositories {
      mavenLocal()
    }
  }
}

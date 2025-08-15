plugins {
  alias(libs.plugins.android.library)
  id("maven-publish")
}

android {
  namespace = "org.mdholloway.ogg"
  compileSdk=36

  defaultConfig {
    minSdk = 24
    externalNativeBuild {
      cmake {
        arguments += listOf(
          "-DBUILD_SHARED_LIBS=ON",
	  "-DANDROID_STL=none",
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
  }

  externalNativeBuild {
    cmake {
      path = file("CMakeLists.txt")
      version = "3.22.1"
    }
  }

  publishing {
    singleVariant("release")
  }
}

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("releaseAar") {
        from(components["release"])
        groupId = "org.mdholloway"
        artifactId = "ogg"
        version = "1.0.0"
      }
    }
    repositories {
      mavenLocal()
    }
  }
}

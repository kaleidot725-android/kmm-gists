plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.activity:activity-compose:1.3.0-beta02")
    implementation("androidx.compose.ui:ui:1.0.0-beta09")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta09")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta09")
    implementation("androidx.compose.material:material:1.0.0-beta09")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta09")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta09")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta09")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "jp.kaleidot725.githubclient.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta09"
    }
}
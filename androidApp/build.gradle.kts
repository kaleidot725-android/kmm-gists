plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "jp.kaleidot725.githubclient.android"
        minSdk = 21
        targetSdk = 30
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.5.10"
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }
}

dependencies {
    val koinVersion = "3.1.2"

    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.activity:activity-ktx:1.2.3")
    implementation("androidx.core:core-ktx:1.7.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    implementation("androidx.compose.runtime:runtime:1.0.0-rc01")
    implementation("androidx.compose.ui:ui:1.0.0-rc01")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-rc01")
    implementation("androidx.compose.foundation:foundation:1.0.0-rc01")
    implementation("androidx.compose.material:material:1.0.0-rc01")
    implementation("androidx.compose.material:material-icons-core:1.0.0-rc01")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-rc01")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-rc01")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha04")
}

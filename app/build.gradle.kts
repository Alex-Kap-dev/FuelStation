import io.grpc.internal.SharedResourceHolder.release
import org.gradle.kotlin.dsl.release

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}

android {
    namespace = "com.epc_it.fuelstations"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.epc_it.fuelstations"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.21"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create("release") {
            storeFile =
                file("F:\\Облачные хранилища\\Neom13 YandexDisk\\Приложение FuelStations\\ks.jks")
            storePassword = "13%+F@a7K0"
            keyAlias = "key0"
            keyPassword = "13%+F@a7K0"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dependencies {
        // Core Android Dependencies
        implementation("androidx.core:core-ktx:1.16.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.activity:activity-compose:1.8.2")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
        // Compose
        implementation(platform("androidx.compose:compose-bom:2025.05.01"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")

        // Navigation
        implementation("androidx.navigation:navigation-compose:2.7.7")

        // OSMDroid для OpenStreetMap
        implementation("org.osmdroid:osmdroid-android:6.1.17")

        // Загрузка изображений
        implementation("io.coil-kt:coil-compose:2.5.0")

        // Парсинг JSON
        implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2025.05.01"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")

        // Debug
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }
}
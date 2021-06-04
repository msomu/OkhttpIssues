plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.msomu.squareissues"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.msomu.squareissues.HiltTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.32"
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha01")

    implementation("com.google.accompanist:accompanist-insets:0.10.0")
    implementation("com.google.accompanist:accompanist-coil:0.10.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.2.3")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra["hilt_version"]}")

    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")

    // KTOR
    implementation("io.ktor:ktor-client-okhttp:${rootProject.extra["ktor_version"]}")
    implementation("io.ktor:ktor-client-json:${rootProject.extra["ktor_version"]}")
    implementation("io.ktor:ktor-client-gson:${rootProject.extra["ktor_version"]}")
    implementation("io.ktor:ktor-client-logging-jvm:${rootProject.extra["ktor_version"]}")
    implementation("com.github.mrmike:ok2curl:0.7.0")

    // Room
    implementation("androidx.room:room-runtime:2.4.0-alpha02")
    kapt("androidx.room:room-compiler:2.4.0-alpha02")
    implementation("androidx.room:room-ktx:2.4.0-alpha02")

    //test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    testImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.35.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.35.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")

}
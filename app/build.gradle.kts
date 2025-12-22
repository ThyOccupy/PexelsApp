import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.pexelsapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.pexelsapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //Putting a key to BuildConfig
        buildConfigField("String", "API_KEY", "\"${getLocalProperty("API_KEY")}\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

// Reading the key from local.properties
fun getLocalProperty(key: String, file: String = "local.properties"): String {
    val properties = Properties()
    val localProperties = File(rootProject.projectDir, file)
    if (localProperties.exists()) {
        localProperties.inputStream().use { properties.load(it) }
    } else {
        logger.warn("File $file didn't found.")
    }
    return properties.getProperty(key, "")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //Splash Screen API
    implementation(libs.androidx.splashscreen)

    //Compose Navigation
    implementation(libs.androidx.compose.navigation)

    //Coil
    implementation(libs.coil)

    //Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.paging.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //Okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    //Retrofit
    implementation(libs.retrofit)
    //Moshi
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)

    //Room + Room Coroutines support + Paging 3 Integration
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.paging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
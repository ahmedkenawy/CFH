plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.ahmedkenawy.cfhtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ahmedkenawy.cfhtest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    android {
        buildTypes {
            debug {
                buildConfigField("String", "FOURSQUARE_CLIENT_ID", "\"${project.findProperty("FOURSQUARE_CLIENT_ID")}\"")
                buildConfigField("String", "FOURSQUARE_CLIENT_SECRET", "\"${project.findProperty("FOURSQUARE_CLIENT_SECRET")}\"")
                buildConfigField("String", "FOURSQUARE_VERSION", "\"${project.findProperty("VERSION")}\"")
                buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL")}\"")
            }
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "FOURSQUARE_CLIENT_ID", "\"${project.findProperty("FOURSQUARE_CLIENT_ID")}\"")
                buildConfigField("String", "FOURSQUARE_CLIENT_SECRET", "\"${project.findProperty("FOURSQUARE_CLIENT_SECRET")}\"")
                buildConfigField("String", "FOURSQUARE_VERSION", "\"${project.findProperty("VERSION")}\"")
                buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL")}\"")

            }

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    //datastore
    implementation(libs.androidx.datastore.preferences)
    // coroutine
    implementation(libs.kotlinx.coroutines.android)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.retrofit2.converter.scalars)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    // locations
    implementation(libs.play.services.location)
    //Toasty
    implementation (libs.toasty)
    //navigation
    implementation(libs.androidx.navigation.ui.ktx)
    //fragment ktx
    implementation(libs.androidx.navigation.fragment.ktx)
    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //livedata
    implementation(libs.androidx.lifecycle.livedata.ktx)
    //Glide
    implementation (libs.glide)
    kapt(libs.glide.kapt)


}

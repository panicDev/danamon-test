plugins {
    alias(libs.plugins.panicDev.android.application)
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.panicDev.android.hilt)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.danamon.test"

    defaultConfig {
        applicationId = "com.danamon.test"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures{
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.bundles.androidx.full)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.lifecycle.full)

    implementation(libs.glide)
    kapt(libs.glide.compiler)
}

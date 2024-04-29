plugins {
    alias(libs.plugins.panicDev.android.library)
    alias(libs.plugins.panicDev.android.hilt)
}

android.namespace = "com.danamon.domain"
android.buildFeatures.buildConfig = true

dependencies {
    implementation(project(":core:common"))

    implementation(libs.kotlin.coroutines)
    api(libs.bundles.square.retrofit2)
}

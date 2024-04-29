plugins {
    alias(libs.plugins.panicDev.android.library)
    alias(libs.plugins.panicDev.android.hilt)
}

android.namespace = "com.danamon.local"
android.buildFeatures.buildConfig = true

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    implementation(libs.kotlin.coroutines)
}

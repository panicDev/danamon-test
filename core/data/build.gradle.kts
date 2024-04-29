plugins {
    alias(libs.plugins.panicDev.android.library)
    alias(libs.plugins.panicDev.android.room)
    alias(libs.plugins.panicDev.android.hilt)

}

android.namespace = "com.danamon.data"
android.buildFeatures.buildConfig = true

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.square.retrofit2)
}

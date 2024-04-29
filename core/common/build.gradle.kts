plugins {
    alias(libs.plugins.panicDev.android.library)
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.panicDev.android.hilt)
}

android.namespace = "com.danamon.common"
android.buildFeatures.buildConfig = true
android.buildFeatures.viewBinding = true

dependencies {
    implementation(libs.bundles.androidx.full)
    implementation(libs.bundles.lifecycle.full)
    implementation(libs.bundles.navigation)
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.square.retrofit2)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckerNoOp)
}

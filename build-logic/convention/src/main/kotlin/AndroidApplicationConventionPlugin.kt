import com.panicDev.android.configureKotlinAndroid
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("panicDev.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                buildFeatures {
                    buildConfig = true
                    viewBinding = true
                }

                defaultConfig {
                    targetSdk = 34

                    buildTypes {
                        release {
                            isMinifyEnabled = true
                            isShrinkResources = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )

                            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")

                            packaging.resources.excludes += "DebugProbesKt.bin"
                        }

                        debug {
                            isMinifyEnabled = false
                            isShrinkResources = false
                            isDebuggable = true

                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )

                            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")

                            packaging.resources.excludes += "DebugProbesKt.bin"
                        }
                    }
                }

                packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
                packaging.jniLibs.excludes += "META-INF/LICENSE.md"
            }
        }
    }
}

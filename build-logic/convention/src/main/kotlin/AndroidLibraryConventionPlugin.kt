import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.panicDev.android.configureKotlinAndroid
import com.panicDev.android.libs
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                compileSdk = 34

                defaultConfig {
                    buildTypes {
                        release {
                            isMinifyEnabled = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }

                buildFeatures {
                    viewBinding = true
                    dataBinding = true
                }
                packaging.jniLibs.excludes += "META-INF/LICENSE.md"
            }

            dependencies {
                add("implementation", libs.findBundle("androidx.full").get())
                add("implementation", libs.findBundle("lifecycle.full").get())
                add("implementation", libs.findLibrary("kotlin.coroutines").get())
            }
        }
    }
}

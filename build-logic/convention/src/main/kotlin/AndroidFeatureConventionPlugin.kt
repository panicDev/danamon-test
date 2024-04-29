import com.panicDev.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("panicDev.android.library")
                apply("androidx.navigation.safeargs.kotlin")
                apply("panicDev.android.hilt")
            }

            dependencies {
                add("implementation", libs.findBundle("androidx.full").get())
                add("implementation", libs.findBundle("lifecycle.full").get())
                add("implementation", libs.findBundle("navigation").get())
                add("implementation", libs.findLibrary("kotlin.coroutines").get())

                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}

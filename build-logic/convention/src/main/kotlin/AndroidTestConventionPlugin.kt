import com.android.build.gradle.TestExtension
import com.rodolfo.stocksearch.convention.configureKotlinAndroid
import com.rodolfo.stocksearch.convention.libs
import com.rodolfo.stocksearch.convention.utils.DefaultSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = DefaultSdk.TARGET_SDK
            }

            dependencies {
                add("testImplementation", libs.findLibrary("org.mockito.kotlin").get())
            }
        }
    }

}

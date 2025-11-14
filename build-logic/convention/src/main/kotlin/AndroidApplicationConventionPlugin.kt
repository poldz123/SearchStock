import com.android.build.api.dsl.ApplicationExtension
import com.rodolfo.stocksearch.convention.configureCommonAndroid
import com.rodolfo.stocksearch.convention.configureKotlinAndroid
import com.rodolfo.stocksearch.convention.utils.DefaultSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureCommonAndroid()
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = DefaultSdk.TARGET_SDK
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }
        }
    }

}

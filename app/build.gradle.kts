plugins {
    alias(libs.plugins.stocksearch.android.application)
    alias(libs.plugins.stocksearch.android.application.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rodolfo.stocksearch"

    defaultConfig {
        applicationId = "com.rodolfo.stocksearch"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.search)

    implementation(projects.data.network)
    implementation(projects.data.repository)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.startup)
    implementation(libs.timber)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.compose.material3)

    testImplementation(libs.kotlin.test)
}
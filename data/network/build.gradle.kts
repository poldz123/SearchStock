plugins {
    alias(libs.plugins.stocksearch.android.library)
    id("kotlinx-serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.rodolfo.stocksearch.data.network"
}

dependencies {
    implementation(projects.domain.api)
    implementation(projects.domain.model)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.android)
    implementation(libs.kotlinx.datetime)
}
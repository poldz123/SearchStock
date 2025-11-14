plugins {
    alias(libs.plugins.stocksearch.android.library)
}

android {
    namespace = "com.rodolfo.stocksearch.data.repository"
}

dependencies {
    implementation(projects.domain.api)
    implementation(projects.domain.model)
    implementation(projects.data.network)

    implementation(libs.retrofit.core)
    implementation(libs.koin.android)

    testImplementation(libs.org.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
}
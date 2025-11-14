plugins {
    alias(libs.plugins.stocksearch.android.feature)
    alias(libs.plugins.stocksearch.android.library.compose)
}

android {
    namespace = "com.rodolfo.search.feature.search"
}

dependencies {
    implementation(projects.data.repository)

    implementation(projects.domain.api)
    implementation(projects.domain.model)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.navigationSuite)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.util)
}
plugins {
    alias(libs.plugins.stocksearch.jvm.library)
}

dependencies {
    api(projects.domain.model)

    implementation(libs.kotlinx.coroutines.core)
}
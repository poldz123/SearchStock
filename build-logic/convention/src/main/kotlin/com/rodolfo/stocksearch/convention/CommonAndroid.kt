package com.rodolfo.stocksearch.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCommonAndroid() {
    dependencies {
        add("implementation", libs.findLibrary("timber").get())
    }
}

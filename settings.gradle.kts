pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "stocksearch"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":data:network")
include(":data:repository")
include(":app")
include(":domain:api")
include(":domain:model")
include(":feature:search")
 

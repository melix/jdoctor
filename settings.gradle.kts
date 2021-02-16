rootProject.name = "jdoctor"

includeBuild("build-logic")

include("jdoctor-core")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
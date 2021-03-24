rootProject.name = "jdoctor"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("jdoctor-build-logic")

include("jdoctor-bom")
include("jdoctor-core")
include("jdoctor-utils")


dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
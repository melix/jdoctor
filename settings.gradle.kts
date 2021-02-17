rootProject.name = "jdoctor"

includeBuild("build-logic")

include("jdoctor-bom")
include("jdoctor-core")
include("jdoctor-utils")


dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
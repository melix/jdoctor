rootProject.name = "jdoctor"

includeBuild("build-logic")

include("jdoctor-bom")
include("jdoctor-core")


dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
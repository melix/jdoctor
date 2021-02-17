plugins {
    groovy
    id("me.champeau.java-module")
    id("me.champeau.publishing")
    `java-test-fixtures`
}

description = "The core model of jdoctor"

dependencies {
    api(platform(project(":jdoctor-bom")))
    testImplementation(libs.bundles.commonTestLibraries)
    testFixturesImplementation(libs.groovy)
}

val javaComponent = components["java"] as AdhocComponentWithVariants
javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }

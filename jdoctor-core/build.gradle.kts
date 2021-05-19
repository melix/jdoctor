plugins {
    groovy
    id("me.champeau.java-module")
    id("me.champeau.publishing")
    `java-test-fixtures`
}

description = "The core model of jdoctor"

dependencies {
    api(platform(project(":jdoctor-bom")))
    testImplementation(libs.bundles.test.commonLibraries)
    testFixturesImplementation(libs.groovy.core)
}

javaLibrary {
    doNotPublishTestFixtures()
}
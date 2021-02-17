plugins {
    groovy
    id("me.champeau.java-module")
    id("me.champeau.publishing")
    `java-test-fixtures`
}

dependencies {
    api(platform(project(":jdoctor-bom")))
    testImplementation("org.codehaus.groovy:groovy:2.5.13")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
    testImplementation("junit:junit:4.13")

    testFixturesImplementation("org.codehaus.groovy:groovy:2.5.13")
}

val javaComponent = components["java"] as AdhocComponentWithVariants
javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }

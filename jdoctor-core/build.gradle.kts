plugins {
    groovy
    `maven-publish`
    id("me.champeau.java-module")
}

group = "me.champeau.jdoctor"
version = "0.1-SNAPSHOT"

dependencies {
    testImplementation("org.codehaus.groovy:groovy:2.5.13")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
    testImplementation("junit:junit:4.13")

    testFixturesImplementation("org.codehaus.groovy:groovy:2.5.13")
}

publishing {
    repositories {
        maven {
            name = "projectLocal"
            url = uri("${buildDir}/repo")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
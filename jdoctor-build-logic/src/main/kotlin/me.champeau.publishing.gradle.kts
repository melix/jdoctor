import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.get
import org.gradle.api.publish.maven.MavenPom

/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    `maven-publish`
}

group = "me.champeau.jdoctor"

publishing {
    repositories {
        maven {
            name = "projectLocal"
            url = uri("${rootProject.buildDir}/repo")
        }
    }
    publications {
        plugins.withId("java-library") {
            create<MavenPublication>("maven") {
                from(components["java"])
                pom.addRequiredMetadataForPublicationOnMavenCentral()
            }
        }
        plugins.withId("java-platform") {
            create<MavenPublication>("maven") {
                from(components["javaPlatform"])
                pom.addRequiredMetadataForPublicationOnMavenCentral()
            }
        }
    }
}

// Follow the requirements described at https://central.sonatype.org/pages/requirements.html
// (which assume that POM is the king)
fun MavenPom.addRequiredMetadataForPublicationOnMavenCentral() {
    name.set(providers.provider { "${project.group}:${project.name}" })
    description.set(providers.provider { project.description })
    url.set("https://github.com/melix/jdoctor")
    licenses {
        license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }

    developers {
        developer {
            name.set("Cédric Champeau")
            email.set("cedric.champeau@gmail.com")
            organization.set("Personal")
            organizationUrl.set("https://github.com/melix")
        }
    }

    scm {
        connection.set("scm:git:git://github.com/melix/jdoctor.git")
        developerConnection.set("scm:git:ssh://github.com/melix/jdoctor.git")
        url.set("https://github.com/melix/jdoctor/tree/master")
    }
}
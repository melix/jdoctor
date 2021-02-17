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
    groovy
    id("me.champeau.java-module")
    id("me.champeau.publishing")
}

dependencies {
    api(platform(project(":jdoctor-bom")))
    api(project(":jdoctor-core"))

    testImplementation("org.codehaus.groovy:groovy:2.5.13")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
    testImplementation("junit:junit:4.13")

    testImplementation(testFixtures(project(":jdoctor-core")))
}
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
    `java-library`
    `java-test-fixtures`
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

val java11 by sourceSets.creating {
    java {
        srcDir("src/main/java11")
    }
}

tasks.named<JavaCompile>("compileJava11Java") {
    javaCompiler.set(javaToolchains.compilerFor {
        // We use a LTS version of Java for building
        // but use target 9 for maximum compatibility
        languageVersion.set(JavaLanguageVersion.of(11))
    })
    options.release.set(9)
}

tasks.named<Jar>("jar") {
    from(java11.output) {
        into("META-INF/versions/9")
        include("module-info.class")
    }
}
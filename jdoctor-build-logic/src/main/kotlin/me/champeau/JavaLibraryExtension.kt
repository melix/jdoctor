package me.champeau

import org.gradle.api.Project
import org.gradle.api.component.AdhocComponentWithVariants
import org.gradle.kotlin.dsl.get
import javax.inject.Inject

open class JavaLibraryExtension(@Inject val project: Project) {
    fun doNotPublishTestFixtures() = project.run {
        val javaComponent = components["java"] as AdhocComponentWithVariants
        javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
        javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }
    }
}
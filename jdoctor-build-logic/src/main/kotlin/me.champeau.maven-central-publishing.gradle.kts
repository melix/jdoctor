plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(providers.systemProperty("central.username").forUseAtConfigurationTime())
            password.set(providers.systemProperty("central.password").forUseAtConfigurationTime())
        }
    }
}
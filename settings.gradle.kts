rootProject.name = "morphe-patches-template"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/MorpheApp/registry")
            val gprUser = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GPR_USER")
            val gprKey = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GPR_KEY")
            if (gprUser != null && gprKey != null) {
                credentials {
                    username = gprUser
                    password = gprKey
                }
            }
        }
        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    id("app.morphe.patches") version "1.3.3"
}

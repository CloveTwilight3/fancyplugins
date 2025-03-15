plugins {
    id("java-library")
    id("maven-publish")
    id("com.gradleup.shadow")
}

val minecraftVersion = "1.19.4"

dependencies {
    compileOnly("io.papermc.paper:paper-api:$minecraftVersion-R0.1-SNAPSHOT")

    compileOnly("de.oliver:FancyLib:35")
    compileOnly("de.oliver.FancyAnalytics:logger:0.0.6")

    implementation("org.lushplugins:ChatColorHandler:5.1.2")
}

tasks {
    shadowJar {
        relocate("org.lushplugins.chatcolorhandler", "de.oliver.fancyholograms.libs.chatcolorhandler")

        archiveClassifier.set("")
    }

    publishing {
        repositories {
            maven {
                name = "fancypluginsReleases"
                url = uri("https://repo.fancyplugins.de/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }

            maven {
                name = "fancypluginsSnapshots"
                url = uri("https://repo.fancyplugins.de/snapshots")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                groupId = "de.oliver"
                artifactId = "FancyHolograms"
                version = getFHVersion()
                from(project.components["java"])
            }
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
}

fun getFHVersion(): String {
    return file("../VERSION").readText()
}
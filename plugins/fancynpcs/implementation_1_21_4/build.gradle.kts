plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev")
}

val minecraftVersion = "1.21.4"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    paperweight.paperDevBundle("$minecraftVersion-R0.1-SNAPSHOT")

    compileOnly(project(":plugins:fancynpcs:api"))
    compileOnly(project(":libraries:common"))
    compileOnly("org.lushplugins:ChatColorHandler:5.1.3")
}


tasks {
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 21
    }
}
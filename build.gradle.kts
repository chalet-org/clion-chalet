plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.11.0"
    id("org.jetbrains.kotlin.jvm") version "1.7.22"
}

group = "com.chalet"
version = "0.0.1"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

intellij {
    version.set("2022.3") // Target IDE Version
    type.set("CL") // Target IDE Platform

    plugins.set(listOf("org.jetbrains.plugins.terminal"))
}

tasks {
    buildSearchableOptions {
        enabled = false
    }

    patchPluginXml {
        version.set("${project.version}")
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    // https://plugins.jetbrains.com/docs/intellij/plugin-signing.html
    //
    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    // https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html
    //
    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

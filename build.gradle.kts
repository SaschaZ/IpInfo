@file:Suppress("PropertyName", "HasPlatformType", "EXPERIMENTAL_API_USAGE")

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {

    kotlin("multiplatform") version "1.3.71"
    id("kotlinx-serialization") version "1.3.71"
    application
}

group = "dev,zieger"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://kotlin.bintray.com/kotlin-dev")
    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers")
    jcenter()
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    mavenCentral()
}


val ktor_version = "1.3.1"
val coroutines_version = "1.3.5"
val logback_version = "1.2.3"
val serialization_version = "0.20.0"

kotlin {
    jvm() {
        withJava()
    }
    js {
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
    }
    sourceSets {
        val commonMain by getting() {
            dependencies {
                implementation(kotlin("stdlib-common"))

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")
            }
        }
        val commonTest by getting() {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting() {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))

                implementation("io.ktor:ktor-server-netty:$ktor_version")
                implementation("io.ktor:ktor-html-builder:$ktor_version")
                implementation("io.ktor:ktor-html-builder:$ktor_version")

                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutines_version")

                implementation("ch.qos.logback:logback-classic:$logback_version")
            }
        }
        val jvmTest by getting() {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting() {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation(npm("text-encoding", "0.7.0"))
                implementation(npm("abort-controller"))

                implementation("io.ktor:ktor-client-js:$ktor_version")
                implementation("io.ktor:ktor-client-json-js:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-js:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serialization_version")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutines_version")
            }
        }
        val jsTest by getting() {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

application {
    mainClassName = "ServerKt"
}

val jvmProcessResources by tasks.withType(ProcessResources::class).getting {
    val jsWebpackTaskName =
        if (project.findProperty("production") == "true")
            "jsBrowserProductionWebpack"
        else
            "jsBrowserDevelopmentWebpack"
    val jsWebpackTask = tasks.withType(KotlinWebpack::class).named(jsWebpackTaskName)
    from(jsWebpackTask.map { project.files(it.destinationDirectory) })
}
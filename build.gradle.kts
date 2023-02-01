import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://repo.powbot.org/releases")
    }
}

dependencies {
    implementation("org.powbot:client-sdk:1.+")
    implementation("org.powbot:client-sdk-loader:1.+")
    implementation("com.google.guava:guava:31.1-jre")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
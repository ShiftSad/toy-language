/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.org.apache.commons.commons.lang3)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.api)
    compileOnly(libs.org.projectlombok.lombok)
}

group = "org.example"
version = "0.0.10"
description = "toy-language"
java.sourceCompatibility = JavaVersion.VERSION_11

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

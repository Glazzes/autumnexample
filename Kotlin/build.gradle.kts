import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.7.22"
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

application {
    mainClass.set("MainExampleKt")
}

dependencies {
    implementation("com.glaze:autumn:0.1")
    implementation(kotlin("stdlib"))
}

tasks.withType<ShadowJar>().configureEach {
    // Java is un-capable of running this jar file as long as this file exists
    exclude("META-INF/versions/9/module-info.class")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

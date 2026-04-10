plugins {
    kotlin("jvm") version "2.3.0"
}

group = "org.iesra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.ajalt.clikt:clikt:4.4.0") // Verifica la última versión

}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.jar.JarFile

plugins {
    kotlin("jvm") version "1.7.0"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.beryx.jlink") version "2.25.0"
}

java {
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
}

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

compileJava.destinationDir = compileKotlin.destinationDirectory.get().asFile

group = "io.github.usbharu"
version = "1.0.1"

val jar by tasks.getting(Jar::class){
//    enabled = false
//    dependsOn("shadowJar")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.github.weisj:darklaf-core:3.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("io.github.usbharu.switchbot_on_pc.MainKt")
    mainModule.set("SwitchBotOnPc")
}

jlink{
    jpackage{
        installerName = "SwitchBotOnPC"
        appVersion = "1.0.1"
        icon = "src/main/resources/switchbot.ico"
        if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
            installerType = "msi"
            installerOptions.addAll(arrayOf("--win-menu-group","SwitchBot","--win-menu","--win-shortcut","--vendor","usbharu","--win-per-user-install"))
        }
    }

}

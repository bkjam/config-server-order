plugins {
    base
    kotlin("jvm") version "1.5.32" apply false
    kotlin("plugin.spring") version "1.5.32" apply false
}

allprojects {
    group = "com.example"
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/milestone") }
    }
}

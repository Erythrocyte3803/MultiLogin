plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
    id("com.github.johnrengelman.shadow") version "8.1.0" apply false
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://repo1.maven.org/maven2")
    maven("https://libraries.minecraft.net")
}



allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        // sql
        testImplementation(compileOnly("com.zaxxer", "HikariCP", "5.0.1"))
        testImplementation(compileOnly("org.ktorm", "ktorm-core", "3.6.0"))
        testImplementation(compileOnly("mysql", "mysql-connector-java", "8.0.33"))

        // config
        testImplementation(compileOnly("org.spongepowered", "configurate-yaml", "4.1.2"))
        testImplementation(compileOnly("org.spongepowered", "configurate-core", "4.1.2"))
        testImplementation(compileOnly("org.yaml", "snakeyaml", "1.33"))

        testImplementation(compileOnly("com.google.code.gson", "gson", "2.8.8"))
        testImplementation(compileOnly("com.squareup.okhttp3", "okhttp", "4.10.0"))
        testImplementation(compileOnly("com.mojang", "brigadier", "1.0.18"))


        testImplementation("junit:junit:4.13.1")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

subprojects {
    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()

    }


}

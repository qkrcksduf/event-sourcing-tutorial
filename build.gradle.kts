plugins {
    id("java")
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

group = "io.wisoft.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenCentral()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks.withType<JavaCompile> {
    options.apply {
        encoding = "UTF-8"
        compilerArgs = listOf("-Xlint:all", "-Xlint:-processing")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.axonframework:axon-spring-boot-starter:4.3.3") {
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

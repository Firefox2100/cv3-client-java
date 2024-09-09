plugins {
    id("java")
}

group = "org.cafevariome"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // SLF4J API
    implementation("org.slf4j:slf4j-api:2.0.16")

    // Logback Classic (SLF4J implementation)
    implementation("ch.qos.logback:logback-classic:1.5.7")

    // Jackson for JSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")

    // Jsoup for HTML parsing
    implementation("org.jsoup:jsoup:1.18.1")

    // JUnit for testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

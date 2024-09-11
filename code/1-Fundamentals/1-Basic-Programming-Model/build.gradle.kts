plugins {
    id("java")
}

group = "com.segarciat.algs4e"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // See: https://stackoverflow.com/questions/57706525/unable-to-run-kotlin-scratch-file
    // See also: https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("kapt") version "1.9.23"

    id("org.openapi.generator") version "7.2.0"
}

group = "com.gmail.marcosav2010"
version = "0.0.1-SNAPSHOT"

val springBootVersion = "3.2.4"
val springDocVersion = "2.3.0"
val jacksonVersion = "2.17.0"
val mapstructVersion = "1.5.5.Final"
val kotlinVersion = "1.9.23"
val myfitnesspalApiVersion = "0.5.4"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()

    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/marcosav/myfitnesspal-api")
        credentials(PasswordCredentials::class)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("com.gmail.marcosav2010:myfitnesspal-api:$myfitnesspalApiVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

tasks.withType<KotlinCompile> {
    dependsOn(tasks.openApiGenerate)
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openApiGenerate {
    inputSpec = "${rootDir}/src/main/resources/openapi.yaml"
    apiPackage = "com.gmail.marcosav2010.mfp.api.controller"
    modelPackage = "com.gmail.marcosav2010.mfp.api.dto"

    generatorName = "kotlin-spring"
    globalProperties = mapOf(
        "generateSupportingFiles" to "false"
    )
    typeMappings = mapOf(
        "date" to "Date"
    )
    importMappings = mapOf(
        "Date" to "java.util.Date"
    )
    configOptions = mapOf(
        "delegatePattern" to "false",
        "useSpringBoot3" to "true",
        "interfaceOnly" to "true",
        "skipDefaultInterface" to "true",
        "useTags" to "true"
    )
}

sourceSets {
    main {
        java {
            srcDir("$rootDir/build/generate-resources/main/src/main/kotlin")
        }
    }
}
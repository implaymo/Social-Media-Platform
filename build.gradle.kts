plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.4.2")
	implementation("org.hibernate.validator:hibernate-validator:9.0.0.CR1")
	implementation("jakarta.validation:jakarta.validation-api:3.1.1")
	implementation("com.auth0:java-jwt:4.5.0")
	implementation("io.github.cdimascio:java-dotenv:5.2.2")
	implementation("org.springframework:spring-webmvc:6.2.3")
	testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.18.1")
	implementation("org.springframework.boot:spring-boot-starter-websocket:3.4.3")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.4.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

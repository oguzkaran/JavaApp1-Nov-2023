plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "org.csystem"
version = "1.0.0"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	mavenLocal()
	maven {
		url = uri("https://raw.github.com/oguzkaran/javaapp1-nov-2023-maven-repo/main")
	}

	maven {
		url = uri("https://raw.github.com/oguzkaran/javaapp1-nov-2023-karandev-maven-repo/main")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("com.karandev:com-karandev-util-console:11.2.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

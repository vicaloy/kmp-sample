import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinApollo)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

mavenPublishing {
    // Define coordinates for the published artifact
    coordinates(
        groupId = "io.github.vicaloy",
        artifactId = "shared",
        version = "1.0.5"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("Math KMP Library")
        description.set("Sample Kotlin MultiPlatform Library Test")
        inceptionYear.set("2024")
        url.set("https://github.com/vicaloy/KmpSharedGuide")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("vicaloy")
                name.set("Victoria Aloy")
                email.set("vic.aloy@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/vicaloy/KmpSharedGuide")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}

kotlin {

    task("testClasses")

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    js {
        browser()
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.apollo.runtime)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.apollo.normalized.cache)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.apollo.mockserver)
            implementation(libs.apollo.testing.support)
        }

        androidMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }

        iosMain.dependencies {
            implementation(libs.apollo.normalized.cache.sqlite)
        }
    }
}

android {
    namespace = "io.github.vicaloy"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

apollo {
    service("service") {
        packageName.set("com.valoy.events")
        codegenModels.set("operationBased")
        generateSchema.set(true)
        generateDataBuilders.set(true)
        introspection {
            endpointUrl.set("http://10.0.2.2:8081/graphql")
            schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
        }
    }
}
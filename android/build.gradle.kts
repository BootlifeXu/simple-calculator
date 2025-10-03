allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Custom build directory inside the project (safe from permission issues)
val newBuildDir: Directory =
    rootProject.layout.projectDirectory
        .dir("custom_build")
        .get()

rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

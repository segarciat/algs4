# Project Setup

This file details the steps I took in order to set up this Gradle project. It exists solely for my own
reference.

## Install Gradle Locally

Step 0 of the tutorial involves [installing Gradle](https://docs.gradle.org/current/userguide/installation.html#ex-installing-    with-a-package-manager)
This local installation is only used to initialize a Gradle project, after which the Gradle wrapper
is used for all other interactions. Gradle uses the [SDKMAN!](http://sdkman.io/) tool for managing
versions, which should be preferred to using a distribution-specific package manager such as `apt`.
From their site, you can run:

```bash
curl -s "https://get.sdkman.io" | bash
```

The download in my case said to open a new terminal afterward. Then we can install Gradle:

```bash
sdk install gradle
# Check it was installed by verifying the version
gradle -v
```

## Initializing the Project

To create a project, I followed the  instructions in the
[official Gradle 8.10.1 tutorial](https://docs.gradle.org/current/userguide/quick_start.html):

```bash
# Create gradle project folder from root of repository
mkdir algs4sgt
cd algs4sgt

gradle init --type java-application --dsl kotlin
```

I accepted the defaults. Namely:

- Use Java version 21.
- Use a single application project.
- Use JUnit Jupiter as the test framework.
- Opt out of new APIs and behavior when generating builds (these may change across minor releases).

From here on, the `gradle` command is no longer used. Instead, we use the Gradle wrapper script,
namely, `./gradlew`.

## Configuring the Application plugin

To enable me to easily run class files from the command-line, I modified the `build.gradle.kts` script
to configure the
[Application plugin](https://docs.gradle.org/current/userguide/application_plugin.html#application_plugin)
that was preconfigured during the `gradle init`. Namely, I made the following addition to the
`application` configuration block as suggested by [First Zero on StackOverflow](https://stackoverflow.com/a/21360609)

```kotlin
application {
    mainClass = project.properties.getOrDefault("mainClass", "NULL").toString()
}
```
Note that the StackOverflow answer is specific to the Groovy DSL, but my project uses the Kotlin DSL, so
I modified it accordingly.

## Configuring the `run (JavaExec)` task

Occasionally my programs need to read data from standard input. According to
[Rene on StackOverflow](https://stackoverflow.com/a/13172566), `System.in` in the Gradle build is not
wired to the `System.in` of the `run` (`JavaExec`) task. Rene proposes the following addition to
`build.gradle.kts`:

```bash
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
```

## Running Program from a Class

With the modifications so far, we can run the code in the `org.example.App` class
by using the `-P` option to set the `mainClass` field to this full-qualified path,
and then using the `run` command:

```bash
./gradlew -PmainClass=org.example.App run
```

## Using `algs4.jar`

The authors of *Algorithms* provide `algs4.jar` which I often use in the exercises.
I began by creating a `libs` directory and added this JAR there:

```bash
mkdir -p app/libs
mv ~/Downloads/algs4.jar app/libs
```

The following line in `build.gradle.kts` arranges for programs to have access to
this library:

```kotlin
dependencies {
    implementation(files("libs/algs4.jar"))
}
```

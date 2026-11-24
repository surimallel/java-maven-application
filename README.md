# Student Grade Tracker

A small Java 17 command-line application for practicing classes, objects,
encapsulation, collections, and unit testing. Students can have multiple scores;
the application displays their average and letter grade.

## Requirements

- JDK 17 or newer
- Apache Maven 3.8 or newer

## Build and test

```shell
mvn clean verify
```

## Run

```shell
mvn exec:java
```

The sample output lists each student's scores, average, and letter grade.
Scores must be whole numbers from 0 to 100. Letter grades are A (90+), B (80+),
C (70+), D (60+), and F (below 60).

## Project layout

- `Student` stores a student's name and scores and calculates the average and
  letter grade.
- `GradeBook` manages a collection of students.
- `Main` creates sample students and prints a report.
- JUnit 5 tests cover score validation, calculations, and grade-book behavior.

## Jenkins

The root [Jenkinsfile](Jenkinsfile) checks out the repository, then builds and
uploads the Maven artifact to JFrog Artifactory with `mvn -B clean deploy` using
the `distributionManagement` configuration in [pom.xml](pom.xml). The pipeline
expects a Jenkins "Username with password" credential with ID
`artifactory-student-9` (matching the server ID in `pom.xml`) and generates a
temporary Maven `settings.xml` during the deploy step. Ensure the Jenkins agent
has a JDK 17+ and Maven 3.8+ on its `PATH`. Surefire test reports from
`target/surefire-reports/*.xml` are published when available.

## Web calculator

A simple calculator web application is available in `web-calculator/index.html`
and is served by Nginx when built with `Dockerfile.calculator`.

```shell
docker build -f Dockerfile.calculator -t calculator-app:1 .
docker run -d -p 8094:80 --name calculator-app calculator-app:1
```

Open `http://localhost:8094` in a web browser to use the calculator. When using
the Jenkins pipeline, the image is tagged with the Jenkins build number instead
of `1`.
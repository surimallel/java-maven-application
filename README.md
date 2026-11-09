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

The root `Jenkinsfile` runs `mvn -B clean verify` and publishes the Surefire
JUnit test reports. Ensure the Jenkins agent uses JDK 17 or newer for both
`java` and `javac`, and has Maven 3.8+ on its `PATH`. The pipeline checks the
Java compiler's Java 17 release support before building. Configure the agent's
`JAVA_HOME` and `PATH` to use JDK 17 or newer; if using a Jenkins-managed JDK,
select it in the agent configuration. The pipeline does not depend on a
globally named Jenkins JDK tool installation.
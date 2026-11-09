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
JUnit test reports. In Jenkins, configure a JDK installation named `JDK17`
(version 17 or newer) under **Manage Jenkins → Tools**. Ensure Maven 3.8+ is
available on the agent's `PATH`, then create a Pipeline job pointing at this
repository. The JDK tool setting makes Jenkins use the right compiler; without
it, an older `javac` can report `release version 17 not supported`.
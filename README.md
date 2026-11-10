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

The root `Jenkinsfile` runs Maven's `clean`, `compile`, `test`, `package`, and
`verify` goals in separate stages, publishes the Surefire JUnit test reports,
then runs the sample application so its student report appears in the Jenkins
job's **Console Output**. Maven lifecycle goals include all earlier phases, so
the later stages repeat some earlier build work. Ensure the Jenkins agent has
a **full JDK 17 or newer**
(not only a Java runtime/JRE) and Maven 3.8+ on its `PATH`. Set `JAVA_HOME` to
the JDK installation and include its `bin` directory on `PATH`, so both
`java` and `javac` are available. The pipeline checks for `javac` and Java 17
release support before building and reports a setup error if either is missing.
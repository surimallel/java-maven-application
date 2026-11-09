package com.example.grades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

class StudentTest {
    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Student("  "));
    }

    @Test
    void acceptsMinimumAndMaximumScores() {
        Student student = new Student("Maya");

        student.addScore(0);
        student.addScore(100);

        assertEquals(50.0, student.getAverage());
    }

    @Test
    void rejectsScoresOutsideRange() {
        Student student = new Student("Maya");

        assertThrows(IllegalArgumentException.class, () -> student.addScore(-1));
        assertThrows(IllegalArgumentException.class, () -> student.addScore(101));
    }

    @Test
    void requiresScoresBeforeCalculatingAverage() {
        Student student = new Student("Maya");

        assertThrows(IllegalStateException.class, student::getAverage);
    }

    @Test
    void calculatesAverageAndLetterGrade() {
        Student student = new Student("Maya");
        student.addScore(92);
        student.addScore(88);

        assertEquals(90.0, student.getAverage());
        assertEquals("A", student.getLetterGrade());
    }

    @ParameterizedTest
    @CsvSource({
        "100, A",
        "90, A",
        "89, B",
        "80, B",
        "79, C",
        "70, C",
        "69, D",
        "60, D",
        "59, F",
        "0, F"
    })
    void assignsLetterGradeAtEachBoundary(int score, String expectedGrade) {
        Student student = new Student("Maya");
        student.addScore(score);

        assertEquals(expectedGrade, student.getLetterGrade());
    }
}

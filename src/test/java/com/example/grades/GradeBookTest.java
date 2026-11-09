package com.example.grades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GradeBookTest {
    @Test
    void storesStudentsInInsertionOrder() {
        GradeBook gradeBook = new GradeBook();
        Student maya = new Student("Maya");
        Student leo = new Student("Leo");

        gradeBook.addStudent(maya);
        gradeBook.addStudent(leo);

        assertEquals(2, gradeBook.getStudents().size());
        assertEquals(maya, gradeBook.getStudents().get(0));
        assertEquals(leo, gradeBook.getStudents().get(1));
    }

    @Test
    void doesNotAllowNullStudents() {
        GradeBook gradeBook = new GradeBook();

        assertThrows(NullPointerException.class, () -> gradeBook.addStudent(null));
    }
}

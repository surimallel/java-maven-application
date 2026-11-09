package com.example.grades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GradeBook {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(Objects.requireNonNull(student, "Student must not be null"));
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }
}

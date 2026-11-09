package com.example.grades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Student {
    private final String name;
    private final List<Integer> scores = new ArrayList<>();

    public Student(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name must not be blank");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        scores.add(score);
    }

    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public double getAverage() {
        if (scores.isEmpty()) {
            throw new IllegalStateException("Cannot calculate an average without scores");
        }
        return scores.stream().mapToInt(Integer::intValue).average().orElseThrow();
    }

    public String getLetterGrade() {
        double average = getAverage();
        if (average >= 90) {
            return "A";
        }
        if (average >= 80) {
            return "B";
        }
        if (average >= 70) {
            return "C";
        }
        if (average >= 60) {
            return "D";
        }
        return "F";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Student student)) {
            return false;
        }
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

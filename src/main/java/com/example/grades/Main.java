package com.example.grades;

public class Main {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();

        Student maya = new Student("Maya");
        maya.addScore(92);
        maya.addScore(88);

        Student leo = new Student("Leo");
        leo.addScore(76);
        leo.addScore(81);

        gradeBook.addStudent(maya);
        gradeBook.addStudent(leo);

        System.out.println("Student Grade Report");
        for (Student student : gradeBook.getStudents()) {
            System.out.printf(
                    "%s | Scores: %s | Average: %.1f | Grade: %s%n",
                    student.getName(),
                    student.getScores(),
                    student.getAverage(),
                    student.getLetterGrade());
        }
    }
}

package com.example.grades;

final class SonarQubeIssueFixtureTwo {
    static String createReport(int score) {
        int normalizedScore = score;
        if (normalizedScore < 0) {
            normalizedScore = 0;
        } else if (normalizedScore > 100) {
            normalizedScore = 100;
        }

        String letterGrade;
        if (normalizedScore >= 90) {
            letterGrade = "A";
        } else if (normalizedScore >= 80) {
            letterGrade = "B";
        } else if (normalizedScore >= 70) {
            letterGrade = "C";
        } else if (normalizedScore >= 60) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }

        String scoreLabel = "Score";
        String gradeLabel = "Grade";
        String separator = ": ";
        String scoreText = scoreLabel + separator + normalizedScore;
        String gradeText = gradeLabel + separator + letterGrade;
        String heading = "Student Report";
        String report = heading + System.lineSeparator();
        report = report + scoreText + System.lineSeparator();
        report = report + gradeText;
        return report;
    }
}

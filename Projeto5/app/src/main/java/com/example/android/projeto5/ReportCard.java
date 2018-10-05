package com.example.android.projeto5;

/**
 * Created by Pedro on 15/11/2017.
 */

public class ReportCard {

    private String localCourse;
    private String localModule;
    private double localGrade;
    private int localStudentID;

    public ReportCard(String course, String module, double grade, int studentID) {
        this.localCourse = course;
        this.localModule = module;
        this.localGrade = grade;
        this.localStudentID = studentID;

    }

    public String getCourse() {
        return localCourse;
    }

    public void setCourse(String course) {
        this.localCourse = course;
    }

    public String getModule() {
        return localModule;
    }

    public void setModule(String semester) {
        this.localModule = semester;
    }

    public double getGrade() {
        return localGrade;
    }

    public void setGrade(int grade) {
        this.localGrade = grade;
    }

    public int getStudentID() {
        return localStudentID;
    }

    public void setStudentID(int id) {
        localStudentID = id;
    }

    public String getResult() {
        String text;

        if (localGrade > 5) {
            text = "Approved";
        } else if (localGrade > 4 && localGrade < 5) {
            text = "Recovery";
        } else {
            text = "Disapproved";
        }

        return text;
    }

    @Override
    public String toString() {
        return "Student ID: " + localStudentID + " | " +
                "Course: " + localCourse + " | " +
                "Module: " + localModule + " | " +
                "Grade: " + localGrade + " | " +
                "Result: " + getResult();
    }
}
package models;

public class Schooling {
    private int id;
    private String schoolName;
    private String fieldOfStudy;
    private String degree;
    private String start;
    private String end;
    private double grade;
    private String description;
    private String activities;

    public Schooling(int id, String schoolName, String fieldOfStudy, String degree, String start, String end, double grade, String description, String activities) {
        this.id = id;
        this.schoolName = schoolName;
        this.fieldOfStudy = fieldOfStudy;
        this.degree = degree;
        this.start = start;
        this.end = end;
        this.grade = grade;
        this.description = description;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
}
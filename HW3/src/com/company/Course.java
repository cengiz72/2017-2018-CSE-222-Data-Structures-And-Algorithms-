package com.company;

public class Course {
    private int semester;
    private String courseCode;
    public Course(){
        semester = 0;
        courseCode = "";
    }
    public  Course(int semester,String courseCode) {
        this.semester = semester;
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {

       return (semester + " " + courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            if (((Course) obj).getSemester() == semester &&
                ((Course) obj).getCourseCode().equals(courseCode))
                return true;
        }
            return false;
    }

    public void setSemester(String semester) {
        this.semester = Integer.parseInt(semester);
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public  int getSemester() {return  semester;}

    public String getCourseCode() {
        return courseCode;
    }
}

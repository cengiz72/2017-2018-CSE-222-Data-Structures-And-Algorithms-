package com.company;

import org.junit.Assert;

import java.util.ArrayList;

public class Part1Test {

    private Part1 list = new Part1("gtuCourses");
    @org.junit.Test
    public void getByCode() {
        Course expected = new Course(1,"CSE 101");
        System.out.println("The test of getByCode :");
        Assert.assertEquals(expected, list.getByCode("CSE 101"));
        System.out.println("The end of testing of getByCode :");
    }

    @org.junit.Test
    public void listSemesterCourses() {
        ArrayList<Course> expected = list.listSemesterCourses(8);
        System.out.println("The test of getByCode :");
        Assert.assertEquals(expected,list.listSemesterCourses(8));
        System.out.println("The end of testing of getByCode :");
    }

    @org.junit.Test
    public void getByRange() {
        System.out.println("The test of getByCode :");
        ArrayList<Course> expected = null;
        try {
            expected = list.getByRange(-2,45);
            Assert.assertEquals(expected,list.getByRange(-2,45));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Part1 {
    private Course course;
    private LinkedList <Course> courses; //for holding all courses together.
    public LinkedList<Course> getCourses() {
        return courses;
    }
    public Part1(String filename){
        courses = new LinkedList < >();
        course = new Course();
        takeTheDataFromFile(filename);
    }
    /**
     * This is helper function and reads strings from file.
     * @param filename file that will be read
     */
    private void takeTheDataFromFile(String filename){
        BufferedReader file;
        String line;
        String[] token;
        String COMMA = ",";
        int i = 0;
        try {
            file = new BufferedReader(new FileReader(filename));
            // first row is unnecessary
            file.readLine();
            while((line=file.readLine())!= null) {
                token = line.split(COMMA);
                course = new Course();
                course.setSemester(token[0]);
                course.setCourseCode(token[1]);
                courses.add(course);
                ++i;
            }

            file.close();
        } catch (IOException param) {
            param.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This function return a course with respect to given course code.
     * @param code Course code.
     * @return return course if code course is available ,if not , throw exception.
     * @throws NoSuchFieldError
     */
    public Course getByCode (String code) throws NoSuchFieldError{

        ListIterator<Course> it = courses.listIterator(0);
        Course temp = new Course();
        while (it.hasNext()) {
          temp = it.next();
          if (code.equals(temp.getCourseCode()))
              return temp;
        }

        throw  new NoSuchFieldError("There is no course named this code!");
    }

    /**
     * This function return all courses in the same semester.
     * @param semester Semester no in the academic calendar.
     * @return return all courses in the same semester.
     * @throws IndexOutOfBoundsException
     */
    public ArrayList<Course> listSemesterCourses (int semester) throws IndexOutOfBoundsException {

        ArrayList<Course> coursesInTheSameSemester = new ArrayList<>();
        Course temp = new Course();
        int i = 0;
       if (semester < 1 || semester > 8)
        throw new IndexOutOfBoundsException("There is no such an semester!");

        ListIterator<Course> it = courses.listIterator(0);
       while (it.hasNext()) {
           temp = it.next();
           int id = temp.getSemester();
           if (semester == id) {
               coursesInTheSameSemester.add(temp);
           }

       }
       System.out.printf("The items of %d. semester   :\n",semester);
       while (i < coursesInTheSameSemester.size()) {
           System.out.println(coursesInTheSameSemester.get(i));
           ++i;
       }
       return coursesInTheSameSemester;
    }

    /**
     * This function return all courses between the start index and last index.
     * @param start_index indexed of desired first element.
     * @param last_index  indexed of desired last element.
     * @return return all courses between the start_index and last_index.
     * @throws Exception in case of invalid indexes.
     */
    public ArrayList<Course> getByRange(int start_index, int last_index)
            throws  Exception {

        int i = start_index ;
        ArrayList<Course> desiredCourses = new ArrayList<>();
        if (start_index < 0 || start_index >= courses.size()
            || last_index < 0 || last_index >= courses.size())
            throw new IndexOutOfBoundsException("Invalid indexes!");
        else if (start_index > last_index)
            throw new Exception("Last index must be bigger than or equal to start index!");
        while ( i <= last_index) {
            desiredCourses.add(courses.get(i));
            ++i;
        }
        System.out.printf("The items between %d. and %d. index   :\n",start_index,last_index);
        for (int j = 0;  j < desiredCourses.size(); ++j)
            System.out.println(desiredCourses.get(j).toString());
        return desiredCourses;
    }
}

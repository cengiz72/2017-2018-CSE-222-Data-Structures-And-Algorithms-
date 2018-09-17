package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Cengiz Toprak 161044087
 */
public class Main {

    public static void main(String[] args) {

        Part1 list1 = new Part1("gtuCourses");
        Part2<Course> list2 = new Part2<>();
        Part3 list3 = new Part3();
        String[] outputs = new String[3];
        /*
            for part 1 results ===> outputs[0];
            for part 2 results ===> outputs[1];
            for part 3 results ===> outputs[2];
         */
        for (int i = 0; i < 3; i++)
            outputs[i] = "output" + (i + 1) + ".txt";
        try {
            System.setOut(new PrintStream(new FileOutputStream(outputs[2])));
        } catch (IOException param) {
            param.printStackTrace();
            System.exit(-1);
        }
        /* The begin of part 1*/
/*
        System.out.println("Part1 results :");
        System.out.println("Results for getByCode(String courseCode) :");
        try {
            System.out.println(list1.getByCode("CSE 222").toString());
            System.out.println(list1.getByCode("CSE 101").toString());
            //System.out.println(list1.getByCode("CSE 500").toString()); //throws exception no course named CSE 500
        } catch (Exception param) {
            System.out.println(param.getMessage());
            param.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Results for  listSemesterCourses(int semester) :");
        try {
            list1.listSemesterCourses(1);
            list1.listSemesterCourses(8);
            //list1.listSemesterCourses(20); throws exception because no semester indexed 20.
        } catch (Exception param) {
            System.out.println(param.getMessage());
            param.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Results for  getByRange(int start_index, int last_index) :");
        try {
            list1.getByRange(10,30);
            list1.getByRange(0,15);
            //list1.getByRange(0,80);; throws exception because invalid index
            //list1.getByRange(-1,15);; throws exception becausebecause invalid index
        } catch (Exception param) {
            System.out.println(param.getMessage());
            param.printStackTrace();
            System.exit(-1);
        }
*/
        /* The end of part 1 */
        /* The begin of part 2 */
/*
        System.out.println("PART2 RESULTS :");
        System.out.println();
        list2.add(new Course(1,"MATH 101"));
        list2.add(new Course(4,"MATH 217"));
        list2.add(new Course(1,"CSE 101"));
        list2.add(new Course(1,"CSE 107"));
        list2.add(new Course(4,"CSE 234"));
        list2.add(new Course(1,"PHYS 121"));
        list2.add(new Course(5,"CSE 321"));
        list2.add(new Course(1,"PHYS 151"));
        list2.add(new Course(6,"CSE 396"));
        list2.add(new Course(1,"SSTR 101"));
        list2.add(new Course(2,"TUR 102"));
        list2.add(new Course(4,"CSE 232"));
        System.out.println("RESULTS FOR disable(Course item)");
        list2.disable(new Course(4,"MATH 217"));
        System.out.println("<<<=====================================>>>>");
        list2.disable(new Course(6,"CSE 396"));
        System.out.println("<<<=====================================>>>>");
        list2.disable(new Course(5,"CSE 321"));
        System.out.println("<<<=====================================>>>>");
        list2.disable(new Course(4,"MATH 217"));
        System.out.println("<<<=====================================>>>>");
        list2.disable(new Course(4,"CSE 232"));
        System.out.println("<<<=====================================>>>>");
        list2.disable(new Course(4,"CSE 896"));
        System.out.println("<<<=====================================>>>>");
        list2.remove(new Course(4,"MATH 217"));
        System.out.println("<<<=====================================>>>>");
        list2.remove(1);
        System.out.println("<<<=====================================>>>>");
        list2.set(1,new Course(1,"SSTR 101"));
        System.out.println("<<<=====================================>>>>");
        System.out.println("The total elements count : 12");
        System.out.printf("The Enable elements counts: %d \n",list2.size());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list2.get(1));
        System.out.println("<<<=====================================>>>>");
        list2.showDisable();
        System.out.println("<<<=====================================>>>>");
        list2.enable(new Course(5,"CSE 321"));
        System.out.println("<<<=====================================>>>>");
        list2.showDisable();
        System.out.println("<<<=====================================>>>>");
        list2.enable(new Course(5,"CSE 107"));
        System.out.println("<<<=====================================>>>>");
        list2.enable(new Course(5,"PHYS 107"));
        System.out.println("<<<=====================================>>>>");
*/
        /* The end of part 2*/


        /* The begin of part 3*/
/*
       System.out.println("PART3 RESULTS :");
       System.out.println(" RESULTS add(Course item) :");
        list3.add(new Course(1,"MATH 101"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(4,"MATH 217"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(1,"CSE 101"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(1,"CSE 107"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(4,"CSE 234"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(1,"PHYS 121"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(5,"CSE 321"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(1,"PHYS 151"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(6,"CSE 396"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(1,"SSTR 101"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(2,"TUR 102"));
        System.out.println("<<<=====================================>>>>");
        list3.add(new Course(4,"CSE 232"));
        System.out.println("<<<=====================================>>>>");
        System.out.println();
        System.out.println("RESULTS nextInSemester(int semester) :");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(1));
        System.out.println("<<<=====================================>>>>");
        //System.out.println(list3.nextInSemester(23)); throws exception because of invalid semester
        //System.out.println("<<<=====================================>>>>");
        System.out.println(list3.nextInSemester(8)); // return null because elements belonged to 8. semester not added yet.
        System.out.println("<<<=====================================>>>>");
        System.out.println();
        System.out.println("RESULTS remove(Course item) :");
        System.out.println(list3.remove(new Course(4,"CSE 232")));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.remove(new Course(4,"CSE 234")));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.remove(new Course(1,"MATH 101")));
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.remove(new Course(4,"CSE 234")));
        System.out.println("<<<=====================================>>>>");
        System.out.println();
        System.out.println("RESULTS next() :");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println(list3.next());
        System.out.println("<<<=====================================>>>>");
        System.out.println();
        System.out.println("RESULT size() :");
        System.out.printf("The size of list : %d\n",list3.size());
        System.out.println("<<<=====================================>>>>");
*/
        /* The end of part 3*/
    }
}
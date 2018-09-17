package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Part3Test {
    private Part3 list = new Part3();
    @Test
    public void remove() {
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(4,"MATH 217"));
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(4,"CSE 234"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(5,"CSE 321"));
        list.add(new Course(1,"PHYS 151"));
        list.add(new Course(6,"CSE 396"));
        list.add(new Course(1,"SSTR 101"));
        list.add(new Course(2,"TUR 102"));
        list.add(new Course(4,"CSE 232"));
        //remove first element from the list.
        Assert.assertEquals(new Course(1,"MATH 101"),list.remove(new Course(1,"MATH 101")));
        //remove last element from the list.
        Assert.assertEquals(new Course(4,"CSE 232"),list.remove(new Course(4,"CSE 232")));
        //remove element between head and end of list.
        Assert.assertEquals(new Course(4,"CSE 234"),list.remove(new Course(4,"CSE 234")));
        //return null because CSE 234 is not available in list anymore.
        Assert.assertEquals(null,list.remove(new Course(4,"CSE 234")));
        //return null because SDF 324 does not exist in list
        Assert.assertEquals(null,list.remove(new Course(9,"SDF 234")));


    }

    @Test
    public void size() {
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(4,"MATH 217"));
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(4,"CSE 234"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(5,"CSE 321"));
        list.add(new Course(1,"PHYS 151"));
        list.add(new Course(6,"CSE 396"));
        list.add(new Course(1,"SSTR 101"));
        list.add(new Course(2,"TUR 102"));
        list.add(new Course(4,"CSE 232"));
        //size of list is 12
        Assert.assertEquals(12,list.size());
    }

    @Test
    public void next() {
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(4,"MATH 217"));
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(4,"CSE 234"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(5,"CSE 321"));
        list.add(new Course(1,"PHYS 151"));
        list.add(new Course(6,"CSE 396"));
        list.add(new Course(1,"SSTR 101"));
        list.add(new Course(2,"TUR 102"));
        list.add(new Course(4,"CSE 232"));
        //expected values.
        Assert.assertEquals(new Course(1,"MATH 101"),list.next());
        Assert.assertEquals(new Course(4,"MATH 217"),list.next());
        Assert.assertEquals(new Course(1,"CSE 101"),list.next());
        Assert.assertEquals(new Course(1,"CSE 107"),list.next());
        Assert.assertEquals(new Course(4,"CSE 234"),list.next());
        Assert.assertEquals(new Course(1,"PHYS 121"),list.next());
        Assert.assertEquals(new Course(5,"CSE 321"),list.next());
        Assert.assertEquals(new Course(1,"PHYS 151"),list.next());
        Assert.assertEquals(new Course(6,"CSE 396"),list.next());
        Assert.assertEquals(new Course(1,"SSTR 101"),list.next());
        Assert.assertEquals(new Course(2,"TUR 102"),list.next());
        Assert.assertEquals(new Course(4,"CSE 232"),list.next());
        Assert.assertEquals(null,list.next());
    }

    @Test
    public void nextInSemester() {
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(4,"MATH 217"));
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(4,"CSE 234"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(5,"CSE 321"));
        list.add(new Course(1,"PHYS 151"));
        list.add(new Course(6,"CSE 396"));
        list.add(new Course(1,"SSTR 101"));
        list.add(new Course(2,"TUR 102"));
        list.add(new Course(4,"CSE 232"));
        //expected outputs.
        Assert.assertEquals(new Course(1,"MATH 101"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"CSE 101"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"CSE 107"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"PHYS 121"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"PHYS 151"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"SSTR 101"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"MATH 101"),list.nextInSemester(1));
        Assert.assertEquals(new Course(1,"CSE 101"),list.nextInSemester(1));
        Assert.assertEquals(new Course(4,"MATH 217"),list.nextInSemester(4));
        Assert.assertEquals(new Course(4,"CSE 234"),list.nextInSemester(4));
        Assert.assertEquals(new Course(4,"CSE 232"),list.nextInSemester(4));
        Assert.assertEquals(new Course(4,"MATH 217"),list.nextInSemester(4));
        Assert.assertEquals(new Course(4,"CSE 234"),list.nextInSemester(4));
        //In the list , elements of 8. semester do not exist.
        Assert.assertEquals(null,list.nextInSemester(8));
    }
}
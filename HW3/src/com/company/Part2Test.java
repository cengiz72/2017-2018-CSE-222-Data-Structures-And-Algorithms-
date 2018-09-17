package com.company;

import org.junit.Assert;
import org.junit.Test;


public class Part2Test {
    private Part2<Course> list = new Part2<>();
    @Test
    public void disable() {
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(1,"PHYS 151"));
        //return true because MATH 101 is enable before.
        Assert.assertEquals(true,list.disable(new Course(1,"MATH 101")));
        System.out.println("====================================================================");
        //return false because MATH 101 is already disable.
        Assert.assertEquals(false,list.disable(new Course(1,"MATH 101")));
        System.out.println("====================================================================");
        //return false because there is no course named DSE 205.
        Assert.assertEquals(false,list.disable(new Course(1,"DSE 205")));
        System.out.println("====================================================================");
        //return null because 2.indexed element is MATH 101 and not visible.

        Assert.assertEquals(null,list.get(2));
        System.out.println("====================================================================");
        //return null because 2.indexed element is MATH 101 and not visible.
        Assert.assertEquals(null,list.set(2,new Course(5,"CSE 321")));
        System.out.println("====================================================================");
        //return null because 2.indexed element is MATH 101 and not visible.
        Assert.assertEquals(null,list.remove(2));
        System.out.println("====================================================================");
        //return false because MATH 101 is not visible.
        Assert.assertEquals(false,list.remove(new Course(1,"MATH 101")));
        System.out.println("====================================================================");
        //Normally,it has 5 element.
        Assert.assertEquals(4,list.size());
    }

    @Test
    public void enable() {
        list.add(new Course(1,"CSE 101"));
        list.add(new Course(1,"CSE 107"));
        list.add(new Course(1,"MATH 101"));
        list.add(new Course(1,"PHYS 121"));
        list.add(new Course(1,"PHYS 151"));
        list.disable(new Course(1,"CSE 101"));
        list.disable(new Course(1,"PHYS 151"));
        Assert.assertEquals(true,list.enable(new Course(1,"PHYS 151")));
        Assert.assertEquals(false,list.enable(new Course(1,"PHYS 151")));
        Assert.assertEquals(false,list.enable(new Course(1,"DEf 151")));
    }
}

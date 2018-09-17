package com.company;

import org.junit.Assert;
import org.junit.Test;


public class Part2Test {
    private Part2<MustBeComparable<Integer>> tree2 = new Part2<>();
    public void fillTheTree() {
        MustBeComparable<Integer> A = new MustBeComparable<>(10,20,30);
        MustBeComparable<Integer> B = new MustBeComparable<>(11,25,31);
        MustBeComparable<Integer> C = new MustBeComparable<>(9,18,40);
        MustBeComparable<Integer> D = new MustBeComparable<>(-9,-96,0);
        MustBeComparable<Integer> E = new MustBeComparable<>(7,15,33);
        MustBeComparable<Integer> F = new MustBeComparable<>(32,52,23);
        MustBeComparable<Integer> G = new MustBeComparable<>(110,20,45);
        MustBeComparable<Integer> H = new MustBeComparable<>(0,23,37);
        MustBeComparable<Integer> K = new MustBeComparable<>(8,17,99);
        tree2.add(A);
        tree2.add(B);
        tree2.add(C);
        tree2.add(D);
        tree2.add(E);
        tree2.add(F);
        tree2.add(G);
        tree2.add(H);
        tree2.add(K);
    }
    @Test
    public void add() {
        MustBeComparable<Integer> A = new MustBeComparable<>(10,20,30);
        MustBeComparable<Integer> B = new MustBeComparable<>(11,25,31);
        MustBeComparable<Integer> C = new MustBeComparable<>(9,18,40);
        MustBeComparable<Integer> D = new MustBeComparable<>(-9,-96,0);
        MustBeComparable<Integer> E = new MustBeComparable<>(7,15,33);
        MustBeComparable<Integer> F = new MustBeComparable<>(32,52,23);
        MustBeComparable<Integer> G = new MustBeComparable<>(110,20,45);
        MustBeComparable<Integer> H = new MustBeComparable<>(0,23,37);
        MustBeComparable<Integer> K = new MustBeComparable<>(8,17,99);
        System.out.println("Tests for add method :");
        Assert.assertEquals(true,tree2.add(A));
        Assert.assertEquals(true,tree2.add(B));
        Assert.assertEquals(true,tree2.add(C));
        Assert.assertEquals(true,tree2.add(D));
        Assert.assertEquals(true,tree2.add(E));
        Assert.assertEquals(true,tree2.add(F));
        Assert.assertEquals(true,tree2.add(G));
        Assert.assertEquals(true,tree2.add(H));
        Assert.assertEquals(true,tree2.add(K));
        Assert.assertEquals(false,tree2.add(E));

    }

    @Test
    public void contains() {
        fillTheTree();
        System.out.println("Firstly filled the tree.");
        System.out.println("Tests for contains :");
        Assert.assertEquals(true,tree2.contains(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(true,tree2.contains(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(false,tree2.contains(new MustBeComparable<>(50,18,29)));

    }

    @Test
    public void find() {
        fillTheTree();
        System.out.println("Firstly filled the tree.");
        System.out.println("Tests for find :");
        Assert.assertEquals(new MustBeComparable<>(9,18,40),tree2.find(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(new MustBeComparable<>(0,23,37),tree2.find(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(null,tree2.find(new MustBeComparable<>(50,18,29)));
    }

    @Test
    public void delete() {
        fillTheTree();
        System.out.println("Firstly filled the tree.");
        System.out.println("Tests for delete :");
        Assert.assertEquals(new MustBeComparable<>(9,18,40),tree2.delete(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(false,tree2.contains(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(new MustBeComparable<>(0,23,37),tree2.delete(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(false,tree2.contains(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(null,tree2.delete(new MustBeComparable<>(50,18,29)));

    }

    @Test
    public void remove() {
        fillTheTree();
        System.out.println("Firstly filled the tree.");
        System.out.println("Tests for remove :");
        Assert.assertEquals(true,tree2.remove(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(false,tree2.contains(new MustBeComparable<>(9,18,40)));
        Assert.assertEquals(true,tree2.remove(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(false,tree2.contains(new MustBeComparable<>(0,23,37)));
        Assert.assertEquals(false,tree2.remove(new MustBeComparable<>(50,18,29)));
    }
}
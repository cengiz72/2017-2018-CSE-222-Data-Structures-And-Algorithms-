package com.company;

import org.junit.Assert;


public class Part1Test {
    private Part1<String> tree = new Part1<>("william");
    private Part1<String> tree1 = new Part1<>("george");

    @org.junit.Test
    public void add() {
        System.out.println("add method used the level order search:");
        System.out.println();
        Assert.assertEquals(true,tree.add("william","jack",0));
        Assert.assertEquals(true,tree.add("jack","victory",0));
        Assert.assertEquals(true,tree.add("william","ariana",0));
        Assert.assertEquals(true,tree.add("william","rebeca",0));
        Assert.assertEquals(true,tree.add("rebeca","henry",0));
        Assert.assertEquals(true,tree.add("rebeca","fiona",0));
        Assert.assertEquals(true,tree.add("jack","adam",0));
        Assert.assertEquals(true,tree.add("victory","luccia",0));
        Assert.assertEquals(true,tree.add("victory","raymen",0));
        Assert.assertEquals(true,tree.add("ariana","ella",0));
        Assert.assertEquals(true,tree.add("ariana","carter",0));
        Assert.assertEquals(true,tree.add("adam","david",0));
        System.out.println("Result  of add(logan,hercule,0) is false because logan is not present in tree.");
        Assert.assertEquals(false,tree.add("logan","hercule",0));
        System.out.println();
        System.out.println("add method used the post order search:");
        Assert.assertEquals(true,tree1.add("george","jaxon",1));
        Assert.assertEquals(true,tree1.add("jaxon","hazel",1));
        Assert.assertEquals(true,tree1.add("george","audrey",1));
        Assert.assertEquals(true,tree1.add("george","alice",1));
        Assert.assertEquals(true,tree1.add("alice","hunter",1));
        Assert.assertEquals(true,tree1.add("alice","hailey",1));
        Assert.assertEquals(true,tree1.add("jaxon","hudson",1));
        Assert.assertEquals(true,tree1.add("hazel","willow",1));
        Assert.assertEquals(true,tree1.add("hazel","parker",1));
        Assert.assertEquals(true,tree1.add("audrey","piper",1));
        Assert.assertEquals(true,tree1.add("audrey","robert",1));
        System.out.println();
        System.out.println("Result  of add(tyler,jane,1) is false because tyler is not present in tree.");
        Assert.assertEquals(false,tree.add("tyler","jane",1));
        System.out.println();
    }

    @org.junit.Test
    public void levelOrderSearch() {
        tree1.add("george","jaxon",1);
        tree1.add("jaxon","hazel",1);
        tree1.add("george","audrey",1);
        tree1.add("george","alice",1);
        tree1.add("alice","hunter",1);
        tree1.add("alice","hailey",1);
        tree1.add("jaxon","hudson",1);
        tree1.add("hazel","willow",1);
        tree1.add("hazel","parker",1);
        tree1.add("audrey","piper",1);
        tree1.add("audrey","robert",1);
        System.out.println();
        Assert.assertEquals("piper",tree1.levelOrderSearch("piper",1).data);
        System.out.println();
        Assert.assertEquals("hudson",tree1.levelOrderSearch("hudson",1).data);
        System.out.println();
        Assert.assertEquals(null,tree1.levelOrderSearch("hilary",1));
    }

    @org.junit.Test
    public void postOrderSearch() {
        tree.add("william","jack",0);
        tree.add("jack","victory",0);
        tree.add("william","ariana",0);
        tree.add("william","rebeca",0);
        tree.add("rebeca","henry",0);
        tree.add("rebeca","fiona",0);
        tree.add("jack","adam",0);
        tree.add("victory","luccia",0);
        tree.add("victory","raymen",0);
        tree.add("ariana","ella",0);
        tree.add("ariana","carter",0);
        tree.add("adam","david",0);
        System.out.println();
        Assert.assertEquals("fiona",tree.postOrderSearch("fiona",1).data);
        System.out.println();
        Assert.assertEquals("ella",tree.postOrderSearch("ella",1).data);
        System.out.println();
        Assert.assertEquals(null,tree.postOrderSearch("obama",1));
    }
}
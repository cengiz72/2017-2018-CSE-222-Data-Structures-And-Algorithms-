package com.company.part1;
import com.company.sourcecode.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class part1 {
    public static void main(String[] args) {
        AbstractGraph graph = new ListGraph(11,true);
        graph.insert(new Edge(0,1,41));
        graph.insert(new Edge(1,2,10));
        graph.insert(new Edge(2,3,15));
        graph.insert(new Edge(2,10,20));
        graph.insert(new Edge(3,4,5));
        graph.insert(new Edge(4,5,30));
        graph.insert(new Edge(5,10,45));
        graph.insert(new Edge(5,6,80));
        graph.insert(new Edge(5,9,56));
        graph.insert(new Edge(6,7,3));
        graph.insert(new Edge(7,8,60));
        graph.insert(new Edge(8,9,55));
        graph.insert(new Edge(1,10,23));
        graph.insert(new Edge(3,10,21));
        graph.insert(new Edge(4,10,1));
        graph.insert(new Edge(1,5,57));
        graph.insert(new Edge(9,10,100));
        graph.insert(new Edge(1,9,81));
        graph.insert(new Edge(2,5,43));
        graph.insert(new Edge(6,8,71));
        graph.insert(new Edge(1,8,19));
        Methods.plot_graph(graph);
        if (Methods.is_undirected(graph) == false)
            System.out.println("The graph is directed.");
        else
            System.out.println("The graph is undirected.");
        if (Methods.is_acyclic_graph(graph))
            System.out.println("The graph is acyclic.");
        else
            System.out.println("The graph is cyclic.");
        Vector<Integer> path;
        if ((path = Methods.shortest_path(graph,3,10)) != null) {
            int i = 0;
            ListIterator<Integer> iter = path.listIterator(path.size());
            System.out.print("The shortest path between 3 and 10 : ");
             while (iter.hasPrevious()) {

                 System.out.print(iter.previous());
                if (i < path.size()-1)
                    System.out.print(" -> ");
                ++i;
            }
            System.out.println();
        }
        else
            System.out.println("There is no shortest path between 3 and 10.");

        if ((path = Methods.shortest_path(graph,2,5)) != null) {
            int i = 0;
            ListIterator<Integer> iter = path.listIterator(path.size());
            System.out.print("The shortest path between 2 and 5 : ");
            while (iter.hasPrevious()) {

                System.out.print(iter.previous());
                if (i < path.size()-1)
                    System.out.print(" -> ");
                ++i;
            }
            System.out.println();
        }
        else
            System.out.println("There is no shortest path between 3 and 10.");
        if ((path = Methods.shortest_path(graph,6,9)) != null) {
            int i = 0;
            ListIterator<Integer> iter = path.listIterator(path.size());
            System.out.print("The shortest path between 6 and 9 : ");
            while (iter.hasPrevious()) {

                System.out.print(iter.previous());
                if (i < path.size()-1)
                    System.out.print(" -> ");
                ++i;
            }
            System.out.println();
        }
        else
            System.out.println("There is no shortest path between 3 and 10.");
        if ((path = Methods.shortest_path(graph,1,13)) != null) {
            int i = 0;
            ListIterator<Integer> iter = path.listIterator(path.size());
            System.out.print("The shortest path between 1 and 13 : ");
            while (iter.hasPrevious()) {

                System.out.print(iter.previous());
                if (i < path.size()-1)
                    System.out.print(" -> ");
                ++i;
            }
            System.out.println();
        }
        else
            System.out.println("There is no shortest path between 1 and 13.");
    }
}

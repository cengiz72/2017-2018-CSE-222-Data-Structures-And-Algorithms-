package com.company.part2;
import com.company.sourcecode.AbstractGraph;
import com.company.sourcecode.Edge;
import com.company.sourcecode.ListGraph;
import com.company.sourcecode.Methods;
public class part2 {
    public static void main(String[] args) {
        AbstractGraph graph = new ListGraph(19,false);
        graph.insert(new Edge(0,2));
        graph.insert(new Edge(1,2));
        graph.insert(new Edge(2,3));
        graph.insert(new Edge(3,4));
        graph.insert(new Edge(14,13));
        graph.insert(new Edge(15,13));
        graph.insert(new Edge(13,12));
        graph.insert(new Edge(12,11));
        graph.insert(new Edge(11,10));
        graph.insert(new Edge(5,6));
        graph.insert(new Edge(5,9));
        graph.insert(new Edge(15,9));
        graph.insert(new Edge(6,7));
        graph.insert(new Edge(6,8));
        graph.insert(new Edge(7,8));
        graph.insert(new Edge(8,9));
        graph.insert(new Edge(9,12));
        graph.insert(new Edge(4,15));
        graph.insert(new Edge(15,5));
        graph.insert(new Edge(16,17));
        graph.insert(new Edge(17,18));
        Methods.plot_graph(graph);
        if (Methods.is_undirected(graph))
            System.out.println("The graph is undirected.");
        else
            System.out.println("The graph is directed.");
        if (Methods.is_acyclic_graph(graph))
            System.out.println("The graph is acyclic.");
        else
            System.out.println("The graph is cyclic.");
        if (Methods.is_connected(graph,1,8))
            System.out.println("1 and 8 is connected.");
        else
            System.out.println("1 and 8 is not connected.");
        if (Methods.is_connected(graph,5,14))
            System.out.println("5 and 14 is connected.");
        else
            System.out.println("5 and 14 is not connected.");
        if (Methods.is_connected(graph,1,18))
            System.out.println("1 and 18 is  connected.");
        else
            System.out.println("1 and 18 is not connected.");
    }
}

package com.company.part3;
import com.company.sourcecode.*;

public class part3 {
   public static void main(String[] args) {
       AbstractGraph graph = new ListGraph(16,false);
       graph.insert(new Edge(0,1));
       graph.insert(new Edge(1,2));
       graph.insert(new Edge(2,3));
       graph.insert(new Edge(3,0));
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
       //graph.insert(new Edge(15,1));

       Methods.plot_graph(graph);
       if (Methods.is_undirected(graph))
           System.out.println("The graph is undirected.");
       else
           System.out.println("The graph is directed.");
       if (Methods.is_acyclic_graph(graph))
           System.out.println("The graph is acyclic.");
       else
           System.out.println("The graph is cyclic.");

       DepthFirstSearch depth = new DepthFirstSearch(graph);
       int parent[] = depth.getParent();
       System.out.println("DFS spanning tree : ");
       System.out.print("childs  : ");
       for (int i = 0; i < parent.length; i++)
           System.out.print(" " + i + " ");
       System.out.println();
       System.out.print("parents :");
       for (int i = 0; i < parent.length; i++)
           System.out.print(" " + parent[i] + " ");
       System.out.println("\nBFS spanning tree : ");
       parent = BreadthFirstSearch.breadthFirstSearch(graph,5);
       System.out.println();
       System.out.print("childs  : ");
       for (int i = 0; i < parent.length; i++)
           System.out.print(" " + i + " ");
       System.out.println();
       System.out.print("parents :");
       for (int i = 0; i < parent.length; i++)
           System.out.print(" " + parent[i] + " ");
   }
}

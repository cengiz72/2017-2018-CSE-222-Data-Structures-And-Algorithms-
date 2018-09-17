package com.company.sourcecode;
import java.util.*;

public class Methods {
    /**
     * This function check whether graph is undirected or not.
     * @param graph parameter for method.
     * @return true if graph is undirected ,otherwise false.
     */
    public static boolean is_undirected(Graph graph) {
        for (int i = 0; i < graph.getNumV(); i++) {
            Iterator<Edge> iter = graph.edgeIterator(i);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                if (!graph.isEdge(edge.getDest(),edge.getSource())) return false;
            }
        }

        return true;
    }

    /**
     * This function check the whether there is or not path between v1 and v2 vertexes in graph
     * @param graph
     * @param v1 start source vertex.
     * @param v2 destination vertex.
     * @return true if it exists a path between v1 and v2 ,otherwise false.
     */
    public static boolean is_connected(Graph graph,int v1,int v2) {
        if (graph instanceof ListGraph) {
            if (v1 >= graph.getNumV() || v2 >= graph.getNumV()) return false;
            Iterator<Edge> iter1 = graph.edgeIterator(v1);
            Iterator<Edge> iter2 = graph.edgeIterator(v2);
            if (!iter1.hasNext() || !iter2.hasNext()) return false;
            LinkedList<Integer> visited = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            Edge start = iter1.next();
            stack.push(start.getSource());
            do
            {
                int source = stack.pop();
                visited.add(source);
                Iterator<Edge> iter = graph.edgeIterator(source);
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    if (edge.getSource() == v2) return true; //found.
                    if (!visited.contains(edge.getDest())) {
                        stack.push(edge.getDest());
                    }
                }

            }while (!stack.empty());

            return false; // not found
        }
        return false;
    }

    /**
     * This function check the whether graph has or does not has cycle or cycles.
     * @param graph parameter for method.
     * @return true if graph has cycle or cycles,otherwise false.
     */
    public static boolean is_acyclic_graph(Graph graph) {
        LinkedList<Integer> visited = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        Iterator<Edge> iter;
        for (int i = 0; i < graph.getNumV(); i++) {
            iter = graph.edgeIterator(i);
            if (iter.hasNext()) {
                //visited.add(i);
                queue.offer(iter.next().getDest());
                while (!queue.isEmpty()) {
                    if (!iter.hasNext()) {
                        int child = queue.poll();
                        visited.add(child);
                        iter = graph.edgeIterator(child);
                    }
                    if (iter.hasNext()) {
                        while (iter.hasNext()) {
                            Edge edge = iter.next();
                            if (edge.getDest() == i && !visited.contains(edge.getDest()))
                            {
                                return false;
                            }
                            if (!visited.contains(edge.getDest()))
                                queue.offer(edge.getDest());
                        }
                    }
                }
            }
            visited.clear();
        }
        return true;
    }

    /**
     * This function plots the given graph
     * @param graph plotted graph.
     */
    public static void plot_graph(Graph graph) {
        System.out.println("List representation of graph :");
        for (int i = 0; i < graph.getNumV(); i++) {
            Iterator<Edge> iter = graph.edgeIterator(i);
            if (iter.hasNext()) {
                Edge edge = iter.next();
                //System.out.println(edge);
                System.out.print(edge.getSource() + " -> " + edge.getDest());
            }
            while (iter.hasNext()) {
                Edge edge = iter.next();
                //System.out.println(edge);
                System.out.print(" -> " + edge.getDest());
            }
            System.out.println();
        }
    }

    /**
     * This function check the whether there is  or not a shortest path from v1 to v2
     * @param graph parameter for method.
     * @param v1 source vertex.
     * @param v2 destination vertex.
     * @return vector of vertexes which are in order from v1 to v2 if
     *          there is a shortest path from v1 to v2 ,otherwise null
     */
    public static Vector<Integer> shortest_path(Graph graph,int v1,int v2) {

        if (v1 < 0 || v2 < 0 || v1 >= graph.getNumV() || v2 >= graph.getNumV())
            return null;
        int[] parent = new int[graph.getNumV()];
        double[] distance = new double[graph.getNumV()];
        boolean flag = true;
        DijkstrasAlgorithm.dijkstrasAlgorithm(graph,v1,parent,distance);
        if (distance[v2] == Double.POSITIVE_INFINITY)
            return null;
        int i = parent[v2];
        Vector<Integer> path = new Vector<>();
        path.add(v2);
        path.add(i);
        while (flag) {
            if (i == v1)
                flag=false;
            else {
                i = parent[i];
                path.add(i);
            }
        }
        return path;
    }
    public static void main(String[] args) {

    }
}

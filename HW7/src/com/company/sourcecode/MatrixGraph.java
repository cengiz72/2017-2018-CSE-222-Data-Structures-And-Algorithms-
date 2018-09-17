/*This source file taken from text book*/
package com.company.sourcecode;

import java.util.Iterator;

public class MatrixGraph
    extends AbstractGraph {

  /** The two dimensional array to represent an edge */
  private double[][] edges;

  /** Construct a graph with the specified number of
      vertices and directionality
      @param numV - The number of vertices
      @param directed - The directionality flag
   */
  public MatrixGraph(int numV, boolean directed) {
    super(numV, directed);
    edges = new double[numV][];
    if (!directed) {
      for (int i = 0; i != numV; ++i) {
        edges[i] = new double[numV];
        for (int j = 0; j != numV; ++j) {
          edges[i][j] = Double.POSITIVE_INFINITY;
        }
      }
    }
    else {
      for (int i = 0; i != numV; ++i) {
        edges[i] = new double[i + 1];
        for (int j = 0; j != i + 1; ++j) {
          edges[i][j] = Double.POSITIVE_INFINITY;
        }
      }
    }
  }

  public boolean vertexAvailable(int index) {
    if (index < 0 || index >= getNumV()) return false;
    return true;
  }
  /** Insert a new edge into the graph
      @param edge - The new edge
   */
  public void insert(Edge edge) {
    setEdgeValue(edge.getSource(), edge.getDest(),
                 edge.getWeight());
  }

  /** Determine if an edge exists
      @param startPoint - The source vertix
      @param endPoint - The destination vertix
      @return true if there is an edge from u to v
   */
  public boolean isEdge(int startPoint, int endPoint) {
    return Double.POSITIVE_INFINITY != getEdgeValue(startPoint, endPoint);
  }

  /** Get the edge between two vertices. If an
      edge does not exist, an Edge with a weight
      of POSITIVE_INFINITY is returned.
      @param startPoint - The source
      @param endPoint - The destination
      @return the edge between these two vertices
   */
  public Edge getEdge(int startPoint, int endPoint) {
    return new Edge(startPoint, endPoint,
                    getEdgeValue(startPoint, endPoint));
  }

  /** Return an iterator to the edges connected
      to a given vertix.
      @param startPoint - The source vertix
      @return an EdgeIterator to the vertices
      contcted to source
   */
  public Iterator < Edge > edgeIterator(int startPoint) {
    return new Iter(startPoint);
  }

  /** Method to set the value of an edge
      @param startPoint - The source vertix
      @param endPoint - The destination vertix
      @param weight - The weight
   */
  private void setEdgeValue(int startPoint, int endPoint, double weight) {
    if (isDirected() || startPoint <= endPoint) {
      edges[startPoint][endPoint] = weight;
    }
    else {
      edges[endPoint][startPoint] = weight;
    }
  }

  /** Method to get the value of an edge
   *  @param startPoint - The source vertix
   *  @param endPoint - The destination vertix
   *  @return The weight of this edge or
   *  POSITIVE_INFINITY if no edge exists
   */
  private double getEdgeValue(int startPoint, int endPoint) {
    if (isDirected() | startPoint <= endPoint) {
      return edges[startPoint][endPoint];
    }
    else {
      return edges[endPoint][startPoint];
    }
  }

  // Iter class
  /** An iterator to the edges.  An Edge iterator is
   *  similar to an Iterator except that its
   *  next method will always return an edge
   */
  private class Iter
      implements Iterator < Edge > {
    // Data fields
    /** The source vertix for this iterator */
    private int source;

    /** The current index for this iterator */
    private int index;

    // Constructor
    /** Construct an EdgeIterator for a given vertix
     *  @param source - The source vertix
     */
    public Iter(int source) {
      this.source = source;
      index = -1;
      advanceIndex();
    }

    /** Return true if there are more edges
     *  @return true if there are more edges
     */
    public boolean hasNext() {
      return index != getNumV();
    }

    /** Return the next edge if there is one
     *  @throws NoSuchElementException there are no
     *  more edges
     *  @return the next Edge in the iteration
     */
    public Edge next() {
      if (index == getNumV()) {
        throw new java.util.NoSuchElementException();
      }
      Edge returnValue = new Edge(source, index,
                                  getEdgeValue(source, index));
      advanceIndex();
      return returnValue;
    }

    /** Advance the index to the next edge */
    private void advanceIndex() {
      do {
        index++;
      }
      while (index != getNumV()
             && Double.POSITIVE_INFINITY == getEdgeValue(source, index));
    }
  }
}

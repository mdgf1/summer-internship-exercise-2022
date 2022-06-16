package com.premiumminds.internship.screenlocking;

import java.util.Arrays;
import java.lang.Math;

/**
 * contains:
 *  value for id,
 *  array with used nodes,
 *  size of used nodes array
 */
class Node {
  /**
   * Node value(position in the matrix)
   */
  private int value;
  /**
   * bollean array each index is a position(1-10)
   * containing used nodes
   * index 0 is ignored to correspond the index to node value
   */
  private boolean[] usedNodes;
  /**
   * size of used nodes array
   */
  private int numUsed;

  /**
   * contructor
   * @param value Node value(position in the matrix)
   * @param numberOfNodes number of nodes in matrix
   */
  public Node(int value, int numberOfNodes){
    this.value = value;
    usedNodes = new boolean[numberOfNodes+1];
    Arrays.fill(usedNodes, false);
    usedNodes[value]=true;
    numUsed = 1;
  }

  /**
   * contructor
   * @param value Node value(position in the matrix)
   * @param previous previous node
   * @param numberOfNodes number of nodes in matrix
   */
  public Node(int value, Node previous, int numberOfNodes){
    this(value, numberOfNodes);
    usedNodes = previous.getUsedCopy();
    usedNodes[value] = true;
    this.numUsed = previous.getNumUsed()+1;
  }

  /**
   * getter for Node Value
   * @return value of node
   */
  public int getValue(){
    return value;
  }

  /**
   * getter for usedNodes array
   * @return usedNodes of node
   */
  public boolean[] getUsed(){
    return usedNodes;
  }

  /**
   * getter for number of used
   * @return size of usedNodes array
   */
  public int getNumUsed(){
    return numUsed;
  }

  /**
   * copies usedNodes array since array is mutable
   * @return unlinked copy of usedNodes
   */
  public boolean[] getUsedCopy(){
    boolean[] newUsed = new boolean[usedNodes.length];
    System.arraycopy(usedNodes, 0, newUsed, 0, usedNodes.length);
    return newUsed;
  }
  
  
}

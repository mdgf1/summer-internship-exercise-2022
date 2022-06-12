package com.premiumminds.internship.screenlocking;

import java.util.Arrays;

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
   * @param numOfNodes number of total nodes in the matrix
   */
  public Node(int value, int numOfNodes){
    this.value = value;
    usedNodes = new boolean[numOfNodes];
    Arrays.fill(usedNodes, false);
    usedNodes[value]=true;
    numUsed = 1;

  }

  /**
   * contructor
   * @param value Node value(position in the matrix)
   * @param usedNodes array with path history
   */
  public Node(int value, boolean[] used, int numUsed, int numOfNodes){
    this(value, numOfNodes);
    usedNodes = used;
    usedNodes[value]=true;
    
    this.numUsed = numUsed;

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
   * copys usedNodes array since array is mutable
   * @return unlinked copy of usedNodes
   */
  public boolean[] getUsedCopy(){
    boolean[] newUsed = new boolean[usedNodes.length];
    System.arraycopy(usedNodes, 0, newUsed, 0, usedNodes.length);
    return newUsed;
  }
  
  
}

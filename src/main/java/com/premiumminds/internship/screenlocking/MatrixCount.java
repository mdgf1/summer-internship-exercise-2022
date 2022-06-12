package com.premiumminds.internship.screenlocking;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.ArrayList;

/**
 * class needs to exist since it's
 * working with the Future interface,
 * this will be the executable/callable element
 */
class MatrixCount implements Callable {
  /**
   * constant for number of nodes in the matrix
   * if changed with getJump() function appropriately
   * gives solution of 4x4/5x5 matrixs
   */
  final private int numOfNodes;

  /**
   * first key being pressed already inside
   * a Node class
   */
  private Node firstNode;
  /**
   * array list of edge Nodes in bfs 
   */
  private ArrayList<Node> edges;
  /**
   * size of pattern being generated
   */
  private int length;
  /**
   * final count to be returned by call()
   */
  private Integer count;


  /**
   * contructor
   * @param firstPoint starting node
   * @param length max size of lock
   * @param numOfNodes number of total nodes in the matrix
   */
  public MatrixCount(int firstPoint, int length, int numOfNodes){
    this.length = length;
    this.numOfNodes = numOfNodes+1;
    this.firstNode = new Node(firstPoint, this.numOfNodes);
    this.edges = new ArrayList<Node>();
    edges.add(firstNode);
    this.count = 0;
  }

  /**
   * bfs counting number of possible connections
   */
  public int bfs(){
    Node current;
    //bfs stop condition
    while(!edges.isEmpty()){
      //pop first node in edges
      current = edges.remove(0);

      //check if the bfs is over
      if(current.getNumUsed() == length){
        count += 1;
        continue;
      }
      //we try to connect all nodes to the current node n
      for(int i = 1; i < numOfNodes; i++){
        //checking if nodes are used/
        //if there is a node in the way
        if(!current.getUsed()[i] && !getJump(current, i)){
          //copying the whole array this way is necessary
          //because arrays are mutable
          boolean[] newUsed = current.getUsedCopy();
          //push new node into edges
          edges.add(new Node(i, newUsed, current.getNumUsed()+1, numOfNodes));
        }
      }
    }
    return count;
  }


  /**
   * identifies conflicts when connecting
   * nodes that pass through other unused nodes
   * 
   * only function needed to change along with
   * the numOfNodes constant in order get solution
   * for more nodes
   * @param current node currently being explored
   * @param i possibly next node being explored
   * @return true if there is a node between currentNode and i
   */
  public boolean getJump(Node current, int i){
    
    int[][] conflicts = new int[numOfNodes][numOfNodes];

    //horizontal conflicts
    conflicts[1][3] = conflicts[3][1] = 2;
    conflicts[4][6] = conflicts[6][4] = 5;
    conflicts[7][9] = conflicts[9][7] = 8;
    //vertical conflicts
    conflicts[1][7] = conflicts[7][1] = 4;
    conflicts[2][8] = conflicts[8][2] = 5;
    conflicts[3][9] = conflicts[9][3] = 6;
    //diagonal conflicts
    conflicts[1][9] = conflicts[9][1] = 5;
    conflicts[3][7] = conflicts[7][3] = 5;
    //...
    //possible other conflicts although this number
    //grows exponentially with the value n on nxn matrixs
    //with bigged matrixs this if statement would have to be 
    //altered because 1 link could lead to multiple conflicts
    int u = conflicts[current.getValue()][i];
    if(u != 0 && !current.getUsed()[u]){
      return true;
    }
    return false;
  }


 /**
   * Necessary override since 
   * we're implementing Callable interface
   * @return final possible combinations result 
   * in count variable
   */
  @Override
  public Integer call() throws Exception{
    
    return bfs();
  };
}

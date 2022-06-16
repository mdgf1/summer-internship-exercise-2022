package com.premiumminds.internship.screenlocking;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.lang.Math;

/**
 * class needs to exist since it's
 * working with the Future interface,
 * this will be the executable/callable element
 */
class MatrixCount implements Callable {
  /**
   * constant for number of nodes in row
   * gives solution of 4x4/5x5 matrixs
   */
  final private int sizeOfMatrix;
  /**
   * sizeOfMatrix squared
   */
  private int numberOfNodes;

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
   * matrix sizeOfMatrix by sizeOfMatrix containing all values
   */
  private int[][] table;
  /**
   * matrix numberOfNodes by numberOfNodes containing all conflicts
   */
  private ArrayList<ArrayList<ArrayList<Integer>>> conflicts;

  /**
   * contructor
   * @param firstPoint starting node
   * @param length max size of lock
   * @param sizeOfMatrix size of matrix
   */
  public MatrixCount(int firstPoint, int length, int sizeOfMatrix){
    this.length = length;
    this.sizeOfMatrix = sizeOfMatrix;
    this.numberOfNodes = (int)Math.pow(sizeOfMatrix, 2);
    this.firstNode = new Node(firstPoint, numberOfNodes);
    this.edges = new ArrayList<Node>();
    edges.add(firstNode);
    this.count = 0;
    initMatrix();
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
      for(int i = 1; i <= numberOfNodes; i++){
        //checking if nodes are used/
        //if there is a node in the way
        if(!current.getUsed()[i] && !getJump(current, i)){
          //push new node into edges
          edges.add(new Node(i, current, numberOfNodes));
        }
      }
    }
    return count;
  }

  /**
   * a matrix containing all conflicts beetween two numbers in an ArrayList
   * exemple in 3x3:
   * conflicts.get(1).get(3) == conflicts.get(3)(1) == 2
   */
  public void initMatrix(){
    //matrix containing all conflicts
    ArrayList<ArrayList<ArrayList<Integer>>> conflicts = new ArrayList<ArrayList<ArrayList<Integer>>>(numberOfNodes+1);
    for(int i = 0; i <= numberOfNodes; i++){
      conflicts.add(new ArrayList<ArrayList<Integer>>(numberOfNodes+1));
      for(int u = 0; u <= numberOfNodes; u++){
        conflicts.get(i).add(new ArrayList<Integer>(0));
      }
    }
    this.conflicts = conflicts;

    //matrix containing all numbers in their row/column index
    //0 index is ignored
    int[][] matrix = new int[sizeOfMatrix+1][sizeOfMatrix+1];
    for(int i = 0; i < numberOfNodes; i++){
      //generating matrix for later generation of conflicts
      matrix[i/sizeOfMatrix+1][i%sizeOfMatrix+1] = i+1;
    }
    this.table = matrix;
  }

  /**
   * identifies conflicts when connecting
   * nodes that pass through other unused nodes
   * 
   * only function needed to change along with
   * the sizeOfMatrix constant in order get solution
   * for more nodes
   * @param current node currently being explored
   * @param possible possibly next node being explored
   * @return true if there is a node between currentNode and i
   */
  public boolean getJump(Node current, int possible){
    ArrayList<Integer> next = this.conflicts.get(current.getValue()).get(possible);

    for(int w = 0; w < next.size(); w++){
      if(next.get(w) != 0 && !current.getUsed()[next.get(w)]){
        return true;
      }
    }
    //generates actual conflicts
    //index of columns/row being analized
    int cur1=((current.getValue()-1)/sizeOfMatrix)+1;
    int cur2=((current.getValue()-1)%sizeOfMatrix)+1;
    int num1=(possible-1)/sizeOfMatrix+1;
    int num2=(possible-1)%sizeOfMatrix+1;
    int val = current.getValue();

    int i = Math.min(num1, cur1)+1;
    int u = Math.min(num2, cur2)+1;
    double slope = (double)Math.abs(cur1-num1)/(double)Math.abs(cur2-num2);

    //vertical conflicts
    if(cur2 == num2){
      if(Math.abs(cur1-num1) > 1){
        for(; i < Math.max(num1, cur1); i++){
          this.conflicts.get(val).get(possible).add(table[i][cur2]);
          this.conflicts.get(possible).get(val).add(table[i][cur2]);
          if(!current.getUsed()[table[i][cur2]]){
            return true;
          }
        }
      }
    }else if(cur1 == num1){//horizontal conflicts
      if(Math.abs(cur2-num2) > 1){  
        for(; u < Math.max(num2, cur2); u++){
          this.conflicts.get(val).get(possible).add(table[cur1][u]);
          this.conflicts.get(possible).get(val).add(table[cur1][u]);
          if(!current.getUsed()[table[cur1][u]]){
            return true;
          }
        }
      }
    }else{//diagonal
      for(; u < Math.max(num2, cur2); u++){
        for(; i < Math.max(num1, cur1); i++){
          if(slope == (double)Math.abs(cur1-i)/(double)Math.abs(cur2-u)){
            this.conflicts.get(val).get(possible).add(table[i][u]);
            this.conflicts.get(possible).get(val).add(table[i][u]);
            if(!current.getUsed()[table[i][u]]){
              return true;
            }
          }
        }
      }
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

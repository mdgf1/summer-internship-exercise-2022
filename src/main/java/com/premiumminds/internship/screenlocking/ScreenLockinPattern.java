package com.premiumminds.internship.screenlocking;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import com.premiumminds.internship.screenlocking.exceptions.InvalidLength;
import com.premiumminds.internship.screenlocking.exceptions.InvalidFirst;

import java.lang.Math;


/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

 /**
  * Method to count patterns from firstPoint with the length
  * @param firstPoint initial matrix position
  * @param length the number of points used in pattern
  * @return number of patterns
  */
  public Future<Integer> countPatternsFrom(int firstPoint,int length, int sizeOfMatrix) throws InvalidFirst, InvalidLength{
    if(1 > firstPoint || Math.pow(sizeOfMatrix, 2) < firstPoint){
      throw new InvalidFirst(firstPoint);
    }
    if(1 > length || Math.pow(sizeOfMatrix, 2) < length){
      throw new InvalidLength(length);
    }
    ExecutorService ex = Executors.newSingleThreadExecutor();
    Future<Integer> future = ex.submit(new MatrixCount(firstPoint, length, sizeOfMatrix));
    return future;
  };
}

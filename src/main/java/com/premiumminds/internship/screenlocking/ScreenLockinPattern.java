package com.premiumminds.internship.screenlocking;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

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
  public Future<Integer> countPatternsFrom(int firstPoint,int length) {
    ExecutorService ex = Executors.newSingleThreadExecutor();
    Future<Integer> future = ex.submit(new MatrixCount(firstPoint, length, 9));
    return future;
  };
}

package com.premiumminds.internship.screenlocking;

import com.premiumminds.internship.screenlocking.exceptions.*;
import java.util.concurrent.Future;

/**
  * Created by aamado on 05-05-2022.
  */
interface IScreenLockinPattern {
  /**
    * Method to count patterns from firstPoint with the length
    * @param firstPoint initial matrix position
    * @param length the number of points used in pattern
    * @param sizeOfMatrix size of matrix (3x3/4x4/5x5)
    * @return number of patterns
    */
  public Future<Integer> countPatternsFrom(int firstPoint,int length, int sizeOfMatrix) throws InvalidFirst, InvalidLength;
}

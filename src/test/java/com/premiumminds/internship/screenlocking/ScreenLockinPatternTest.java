package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2022.
 */
@RunWith(JUnit4.class)
public class ScreenLockinPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public ScreenLockinPatternTest() {
  };

  @Test
  public void ScreenLockinPatternTestAll()  throws InterruptedException, ExecutionException, TimeoutException {
    int sum = 0;
    int[] results = {56, 320, 1624, 7152,26016,72912,140704, 140704};
    for(int u = 2; u <=9; u++){
      for(int i = 1; i <= 9; i++){
        Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, u);
        Integer result = count.get(10, TimeUnit.SECONDS);
        sum += result.intValue();
      }
      assertEquals(sum, results[u-2]);
      sum=0;
    }
  }

  @Test
  public void ScreenLockinPatternTestAlmostAll()  throws InterruptedException, ExecutionException, TimeoutException {
    int sum = 0;
    for(int i = 1; i <= 9; i++){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2);
      Integer result = count.get(10, TimeUnit.SECONDS);
      sum += result.intValue();
    }
    assertEquals(sum, 56);
  }

  @Test
  public void ScreenLockinPatternTestLength2Test1_3_7_9()  throws InterruptedException, ExecutionException, TimeoutException {
    int[] toTest = {1,3,7,9};
    for(int i: toTest){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(result.intValue(), 5);
    }
  }

  @Test
  public void ScreenLockinPatternTestLength2Test2_4_6_8()  throws InterruptedException, ExecutionException, TimeoutException {
    int[] toTest = {2,4,6,8};
    for(int i: toTest){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(result.intValue(), 7);
    }
  }

  @Test
  public void ScreenLockinPatternTestLength2Test5()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(5, 2);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(result.intValue(), 8);
  }
}
package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.premiumminds.internship.screenlocking.exceptions.InvalidLength;
import com.premiumminds.internship.screenlocking.exceptions.InvalidFirst;

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
  public void ScreenLockinPatternTestAll()  throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int sum = 0;
    int[] results = {56, 320, 1624, 7152,26016,72912,140704, 140704};
    for(int u = 2; u <=9; u++){
      for(int i = 1; i <= 9; i++){
        Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, u, 3);
        Integer result = count.get(10, TimeUnit.SECONDS);
        sum += result.intValue();
      }
      assertEquals(sum, results[u-2]);
      sum=0;
    }
  }

  @Test
  public void ScreenLockinPatternTestAlmostAll() throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int sum = 0;
    for(int i = 1; i <= 9; i++){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2, 3);
      Integer result = count.get(10, TimeUnit.SECONDS);
      sum += result.intValue();
    }
    assertEquals(sum, 56);
  }

  @Test
  public void ScreenLockinPatternTestLength2Test1_3_7_9()  throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int[] toTest = {1,3,7,9};
    for(int i: toTest){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2, 3);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(result.intValue(), 5);
    }
  }

  @Test
  public void ScreenLockinPatternTestLength2Test2_4_6_8()  throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int[] toTest = {2, 4, 6, 8};
    for(int i: toTest){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 2, 3);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(result.intValue(), 7);
    }
  }

  @Test
  public void ScreenLockinPatternTestLength2Test5()  throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(5, 2, 3);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(result.intValue(), 8);
  }
  
  @Test
  public void ScreenLockinPatternTestAlmostAll4x4() throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int sum = 0;
    for(int i = 1; i <= 4*4; i++){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 5, 4);
      Integer result = count.get(10, TimeUnit.SECONDS);
      sum += result.intValue();
    }
    assertEquals(sum, 155896);
  }

  @Test
  public void ScreenLockinPatternTestAlmostAll5x5() throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int sum = 0;
  
    for(int i = 1; i <= 5*5; i++){
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, 4, 5);
      Integer result = count.get(10, TimeUnit.SECONDS);
      sum += result.intValue();
    }
    assertEquals(sum, 101152);
  
  }

  @Test
  public void ScreenLockinPatternTestAnyAlmostAll() throws InterruptedException, ExecutionException, TimeoutException, InvalidLength, InvalidFirst {
    int[] results = {140704, 16946, 101152, 20324, 52256, 121562, 254516, 495246, 902802};
    //these values aren't as big as they could be to make this test not freeze for some time
    int[] sizes = {9, 4, 4, 3, 3, 3, 3, 3, 3};
    int sum = 0;
    int n = 3;
    int maxSize = 3;
    for(int s: sizes){
      sum = 0;
      for(int i = 1; i <=n*n; i++){
        Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(i, s, n);
        Integer result = count.get(10, TimeUnit.SECONDS);
        sum += result.intValue();

      }
      assertEquals(sum, results[n-3]);
      n++;
    }
  }
}
/**
 * Assume you are given the following code to add to the Math class. They will be accessed similar
 * to how Math.sqrt or Math.abs is used, in that they are static methods that do not require
 * instantiating an object.
 *
 * <p>You are given an implementation that computes the sum of the number from 1 to n, where n is an
 * input to the function. The given implementation uses a closed form solution for the problem. You
 * are required to give a recursive implementation.
 *
 * <p>SumSequenceTest.java gives you a unit test that you can use to test your code.
 */
public class SumSequence {

  /**
   * The static method, sumSequenceEquation uses the closed form solution for computing the sum of
   * all integers from 1 to n. For example, if n is 3, the correct output is 1 + 2 + 3 = 6. The
   * closed form solution computes (3*4)/2 = 6.
   *
   * @param n the highest integer in the sequence
   * @return the sum of all integers from 1 to n
   */
  public static int sumSequenceEquation(int n) {
    int total = (n * (n + 1)) / 2;
    return total;
  }

  /**
   * A recursive implementation of sumSequence, or summing all the integers between 1 and n.
   *
   * @param n the highest integer in the sequence.
   * @return the sum of all integers from 1 to n.
   */
  public static int sumSequenceRecursive(int n) {
    if (n == 1) {
      return n;
    } else {
      return n + sumSequenceRecursive(n - 1);
    }
  }

}

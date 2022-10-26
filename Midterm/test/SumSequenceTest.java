import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for sumSequence class.
 */
public class SumSequenceTest {

  /**
   * Testing the recursive formulation for sumSequence. This assumes
   * sumSequence.sumSequenceEquation(n) works correctly, and compares it to the results of the
   * recursive formulation.
   */
  @Test
  public void testSumSequence() {

    for (int input = 1; input < 10000; input++) {
      int expected = SumSequence.sumSequenceEquation(input);
      int actual = SumSequence.sumSequenceRecursive(input);

      assertEquals(expected, actual);
    }
  }

}

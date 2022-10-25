import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class sumSequenceTest {

  /**
   * Testing the recursive formulation for sumSequence.
   * This assumes sumSequence.sumSequenceEquation(n)
   * works correctly, and compares it to the results of the 
   * recursive formulation. 
   */
  @Test
  public void testSumSequence() {
    
    for (int input = 1; input<10000; input++) {
      int expected = sumSequence.sumSequenceEquation(input);
      int actual = sumSequence.sumSequenceRecursive(input);
    
      assertEquals(expected,actual);
    }
  }

}

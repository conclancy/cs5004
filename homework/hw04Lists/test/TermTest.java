import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TermTest {
  private Term simpleTerm;
  private Term complexTerm;
  private Term zeroTerm;

  @Before
  public void init() {
    simpleTerm = new Term(2,1);
    complexTerm = new Term(3, 4);
    zeroTerm = new Term(2,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    Term failedTerm = new Term(-3, 0);
  }

  @Test
  public void testGetPower() {
    assertEquals(2, simpleTerm.getPower());
    assertEquals(3, complexTerm.getPower());
    assertEquals(2, zeroTerm.getPower());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(1, simpleTerm.getCoefficient());
    assertEquals(4, complexTerm.getCoefficient());
    assertEquals(0, zeroTerm.getCoefficient());
  }

  @Test
  public void testEvaluate() {

    // Test `zero` cases
    assertEquals(0.0, zeroTerm.evaluate(10.0), .01);
    assertEquals(0.0, zeroTerm.evaluate(0.0), .01);

    // Test simple
    assertEquals(4, simpleTerm.evaluate(2), .01);
    assertEquals(9, simpleTerm.evaluate(3), .01);
    assertEquals(144, simpleTerm.evaluate(12), .01);
    assertEquals(42.25, simpleTerm.evaluate(6.5), .01);

    for (int input = 1; input < 10000; input++) {
      double expected = Math.pow(input,2);
      double actual = simpleTerm.evaluate(input);

      assertEquals(expected, actual, .01);
    }

    // Test complex
    for (int input = 1; input < 10000; input++) {
      double expected = Math.pow(input, 3) * complexTerm.getCoefficient();
      double actual = complexTerm.evaluate(input);

      assertEquals(expected, actual, .01);
    }
  }

  @Test
  public void testToString() {
    assertEquals("1x^2", simpleTerm.toString());
    assertEquals("4x^3", complexTerm.toString());
    assertEquals("0x^2", zeroTerm.toString());

    // Test empty Term behavior
    Term empty = new Term();
    assertEquals("0", empty.toString());
  }

}

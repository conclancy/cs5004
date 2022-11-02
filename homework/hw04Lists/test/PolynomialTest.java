import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for the Polynomial implementation.
 */
public class PolynomialTest {

  private PolynomialImpl emptyPoly;
  private PolynomialImpl singleTerm;
  private PolynomialImpl termToAdd;

  @Before
  public void init() {
    emptyPoly = new PolynomialImpl();
    singleTerm = new PolynomialImpl(4, 4);
    termToAdd = new PolynomialImpl(3, 2);
  }

  @Test
  public void testAddTerm() {
    String expected = "4x^4 3x^2";
    assertEquals(expected, singleTerm.addTerm(3, 2).toString());
  }

  @Test
  public void testToString() {
    assertEquals("2x^3", termToAdd.toString());
    assertEquals("4x^4", singleTerm.toString());
    assertEquals("0", emptyPoly.toString());
  }

}

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
  private Polynomial complexPoly;

  @Before
  public void init() {
    emptyPoly = new PolynomialImpl();
    singleTerm = new PolynomialImpl(4, 4);
    termToAdd = new PolynomialImpl(3, 2);

    // complex term
    complexPoly = new PolynomialImpl(3, 8);
    complexPoly.addTerm(-2, 2);
    complexPoly.addTerm(1, 22);
  }

  @Test
  public void testStringConstructor() {
    Polynomial polyString = new PolynomialImpl("1x^22 8x^3 -2x^2");
    assertEquals(complexPoly.toString(), polyString.toString());

    Polynomial polyStringDuplicate = new PolynomialImpl("1x^22 2x^2 8x^3 -2x^2");
    assertEquals("1x^22 8x^3", polyStringDuplicate.toString());

    Polynomial polyStringHuge = new PolynomialImpl("1x^22 2x^22 8x^3 -2x^2");
    assertEquals("3x^22 8x^3 -2x^2", polyStringHuge.toString());

    Polynomial constant = new PolynomialImpl("2");
    assertEquals("2x^0", constant.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadString() {
    Polynomial fail = new PolynomialImpl("Connor");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadStringX() {
    Polynomial fail = new PolynomialImpl("2xx^2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadStringEmpty() {
    Polynomial fail = new PolynomialImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadStringNull() {
    Polynomial fail = new PolynomialImpl(null);
  }


  @Test
  public void testAddTerm() {
    String expected = "4x^4 3x^2";
    assertEquals(expected, singleTerm.addTerm(3, 2).toString());

    // Test the @Before for the complex term
    String expectedComplex = "1x^22 8x^3 -2x^2";
    assertEquals(expectedComplex, complexPoly.toString());
  }

  @Test
  public void testToString() {
    assertEquals("2x^3", termToAdd.toString());
    assertEquals("4x^4", singleTerm.toString());
    assertEquals("0", emptyPoly.toString());
    assertEquals("1x^0", new TermNode(0, 1).toString());
  }

  @Test
  public void testRemoveTerm() {
    Polynomial testRemove = complexPoly.removeTerm(3);
    assertEquals("1x^22 -2x^2", testRemove.toString());

    // Should return the same as above since 3 was already removed
    testRemove = complexPoly.removeTerm(3);
    assertEquals("1x^22 -2x^2", testRemove.toString());

    testRemove = complexPoly.removeTerm(2);
    assertEquals("1x^22", testRemove.toString());

    testRemove = complexPoly.removeTerm(22);
    assertEquals("0", testRemove.toString());
  }

  @Test
  public void testGetDegree() {
    assertEquals(22, complexPoly.getDegree());
    assertEquals(4, singleTerm.getDegree());
    assertEquals(3, termToAdd.getDegree());
    assertEquals(0, emptyPoly.getDegree());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(1, complexPoly.getCoefficient(22));
    assertEquals(-2, complexPoly.getCoefficient(2));
    assertEquals(8, complexPoly.getCoefficient(3));

    // Non-existent power
    assertEquals(0, complexPoly.getCoefficient(4));

    // Empty node
    assertEquals(0, emptyPoly.getCoefficient(0));
    assertEquals(0, emptyPoly.getCoefficient(100));
  }

  @Test
  public void testEvaluate() {
    assertEquals(0, emptyPoly.evaluate(4), .01);
    assertEquals(7, complexPoly.evaluate(1), .01);
    assertEquals(4194360, complexPoly.evaluate(2), .01);
    assertEquals(3.456, termToAdd.evaluate(1.2), .0001);
    assertEquals(0, termToAdd.evaluate(0), .01);
  }
}

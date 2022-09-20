import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Vector3D class.
 */
public class Vector3DTest {

  private Vector3D vectorZero;
  private Vector3D vectorOne;
  private Vector3D vectorTwo;
  private Vector3D vectorThree;


  @Before
  public void setUp() {

    // Test 0 vector
    vectorZero = new Vector3D(0, 0, 0);

    // Test simple integers
    vectorOne = new Vector3D(1, 2, 3);

    // Test simple decimals
    vectorTwo = new Vector3D(1.1, 2.2, 3.3);

    // Test negatives
    vectorThree = new Vector3D(-1, -2, -3);
  }

  @Test
  public void testGetX() {
    assertEquals(1.00, vectorOne.getX(), .01);
    assertEquals(1.10, vectorTwo.getX(), .01);
    assertEquals(-1.0, vectorThree.getX(), .01);
  }

  @Test
  public void testGetY() {
    assertEquals(2.00, vectorOne.getY(), .01);
    assertEquals(2.20, vectorTwo.getY(), .01);
    assertEquals(-2.0, vectorThree.getY(), .01);
  }

  @Test
  public void testGetZ() {
    assertEquals(3.00, vectorOne.getZ(), .01);
    assertEquals(3.30, vectorTwo.getZ(), .01);
    assertEquals(-3.0, vectorThree.getZ(), .01);
  }

  @Test
  public void testToString() {
    // Affirmative tests
    assertEquals("(1.00,2.00,3.00)", vectorOne.toString());
    assertEquals("(1.10,2.20,3.30)", vectorTwo.toString());

    // Negative tests
    assertNotEquals("(1,2,3)", vectorOne.toString());
    assertNotEquals("(1.1,2.2,3.3)", vectorTwo.toString());

    // Large decimal tests
    Vector3D vectorD = new Vector3D(1, 2, 3.456);
    assertEquals("(1.00,2.00,3.46)", vectorD.toString());
  }

  @Test
  public void testGetMagnitude() {
    // Test greater than 0
    assertEquals(3.7416, vectorOne.getMagnitude(), .001);
    assertEquals(4.1158, vectorTwo.getMagnitude(), .001);

    // Test for magnitude of 0
    assertEquals(0, vectorZero.getMagnitude(), .001);

  }

  @Test
  public void testNormalize() {

    // vectorOne
    Vector3D normalOne = vectorOne.normalize();
    assertEquals(0.2673, normalOne.getX(), .001);
    assertEquals(0.5345, normalOne.getY(), .001);
    assertEquals(0.8018, normalOne.getZ(), .001);

    // vectorTwo
    Vector3D normalTwo = vectorOne.normalize();
    assertEquals(0.2672, normalTwo.getX(), .001);
    assertEquals(0.5345, normalTwo.getY(), .001);
    assertEquals(0.8018, normalTwo.getZ(), .001);
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalStateExceptionNormalize() {
    Vector3D testVector = new Vector3D(0, 0, 0).normalize();
  }

  @Test
  public void testAdd() {

    // add vectors one and two
    Vector3D addOne = vectorOne.add(vectorTwo);
    assertEquals(2.1, addOne.getX(), .01);
    assertEquals(4.2, addOne.getY(), .01);
    assertEquals(6.3, addOne.getZ(), .01);

    // add vectors two and three
    Vector3D addTwo = vectorTwo.add(vectorThree);
    assertEquals(.1, addTwo.getX(), .01);
    assertEquals(.2, addTwo.getY(), .01);
    assertEquals(.3, addTwo.getZ(), .01);

    // add vectors one and three
    Vector3D addThree = vectorOne.add(vectorThree);
    assertEquals(0, addThree.getX(), .01);
    assertEquals(0, addThree.getY(), .01);
    assertEquals(0, addThree.getZ(), .01);

    // add zero vector
    Vector3D vectorNoChange = vectorOne.add(vectorZero);
    assertEquals(1, vectorNoChange.getX(), .01);
    assertEquals(2, vectorNoChange.getY(), .01);
    assertEquals(3, vectorNoChange.getZ(), .01);
  }

  @Test
  public void testMultiply() {

    // multiply vectors one and two
    Vector3D multiOne = vectorOne.multiply(1.0);
    assertEquals(1, multiOne.getX(), .01);
    assertEquals(2, multiOne.getY(), .01);
    assertEquals(3, multiOne.getZ(), .01);

    // multiply vectors two and three
    Vector3D multiTwo = vectorTwo.multiply(2.0);
    assertEquals(2.2, multiTwo.getX(), .01);
    assertEquals(4.4, multiTwo.getY(), .01);
    assertEquals(6.6, multiTwo.getZ(), .01);

    // multiply vectors one and three
    Vector3D multiThree = vectorThree.multiply(3.0);
    assertEquals(-3.0, multiThree.getX(), .01);
    assertEquals(-6.0, multiThree.getY(), .01);
    assertEquals(-9.0, multiThree.getZ(), .01);

    // multiply 0
    Vector3D multiZero = vectorOne.multiply(0);
    assertEquals(0, multiZero.getX(), .01);
    assertEquals(0, multiZero.getY(), .01);
    assertEquals(0, multiZero.getZ(), .01);
  }

  @Test
  public void testDotProduct() {
    assertEquals(15.4, vectorOne.dotProduct(vectorTwo), .01);
    assertEquals(-15.4, vectorTwo.dotProduct(vectorThree), .01);
    assertEquals(-14, vectorOne.dotProduct(vectorThree), .01);
  }

  @Test
  public void testAngleBetween() {

    // Test standard vectors
    assertEquals(0, vectorOne.angleBetween(vectorTwo), .01);
    assertEquals(180, vectorTwo.angleBetween(vectorThree), .01);
    assertEquals(180, vectorOne.angleBetween(vectorThree), .01);

    // Test 90 degree vector
    Vector3D vectorAngleOne = new Vector3D(1, 0, 0);
    Vector3D vectorAngleTwo = new Vector3D(0, 1, 0);
    assertEquals(90, vectorAngleOne.angleBetween(vectorAngleTwo), .01);
  }


  @Test(expected = IllegalStateException.class)
  public void testIllegalStateExceptionAngleBetween() {
    Vector3D vectorA = new Vector3D(0, 0, 0);
    Vector3D vectorB = new Vector3D(0, 0, 0);

    double vectorFinal;
    vectorFinal = vectorA.angleBetween(vectorB);
  }
}
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Vector3D class.
 */
public class Vector3DTest {

  private Vector3D vectorOne;
  private Vector3D vectorTwo;
  private Vector3D vectorThree;

  @Before
  public void setUp() {

    // Test simple integers
    vectorOne = new Vector3D(1, 2, 3);

    // Test simple decimals
    vectorTwo = new Vector3D(1.1, 2.2, 3.3);

    // Test negatives
    vectorThree = new Vector3D(-1, -2, -3);

    // Test negatives
    // Test mix of positives and negatives
    // Test mix of ints and doubles
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
  }

  @Test
  public void testGetMagnitude() {
    assertEquals(3.7416, vectorOne.getMagnitude(), .001);
    assertEquals(4.1158, vectorTwo.getMagnitude(), .001);
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

  @Test
  public void testAdd() {

    // add vectors one and two
    Vector3D addOne = vectorOne.add(vectorTwo);
    assertEquals(2.1, addOne.getX(), .01);
    assertEquals(4.2, addOne.getY(), .01);
    assertEquals(6.3, addOne.getZ(), .01);
  }
}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for the node class.
 */
public class TermNodeTest {

  private TermNode simpleTermNode;
  private TermNode complexTermNode;
  private TermNode zeroTermNode;

  @Before
  public void init() {
    simpleTermNode = new TermNode(2, 1, new EmptyTermNode());
    complexTermNode = new TermNode(3, 4, new EmptyTermNode());
    zeroTermNode = new TermNode(2, 0, new EmptyTermNode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    TermNode failedTermNode = new TermNode(-3, 0, new EmptyTermNode());
  }

  @Test
  public void testGetPower() {
    assertEquals(2, simpleTermNode.getPower());
    assertEquals(3, complexTermNode.getPower());
    assertEquals(2, zeroTermNode.getPower());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(1, simpleTermNode.getCoefficient());
    assertEquals(4, complexTermNode.getCoefficient());
    assertEquals(0, zeroTermNode.getCoefficient());
  }

  @Test
  public void testEquals() {
    assertEquals(true, simpleTermNode.equals(new TermNode(2, 1, new EmptyTermNode())));
    assertEquals(false, simpleTermNode.equals(new TermNode(2, 2, new EmptyTermNode())));
  }

  @Test
  public void testEvaluate() {

    // Test `zero` cases
    assertEquals(0.0, zeroTermNode.evaluate(10.0), .01);
    assertEquals(0.0, zeroTermNode.evaluate(0.0), .01);

    // Test simple
    assertEquals(4, simpleTermNode.evaluate(2), .01);
    assertEquals(9, simpleTermNode.evaluate(3), .01);
    assertEquals(144, simpleTermNode.evaluate(12), .01);
    assertEquals(42.25, simpleTermNode.evaluate(6.5), .01);

    for (int input = 1; input < 10000; input++) {
      double expected = Math.pow(input, 2);
      double actual = simpleTermNode.evaluate(input);

      assertEquals(expected, actual, .01);
    }

    // Test complex
    for (int input = 1; input < 10000; input++) {
      double expected = Math.pow(input, 3) * complexTermNode.getCoefficient();
      double actual = complexTermNode.evaluate(input);

      assertEquals(expected, actual, .01);
    }

    // Test 3 term evaluate
    Node temp = simpleTermNode.addNode(complexTermNode);
    Node threeTerm = temp.addNode(new TermNode(1, 3, new EmptyTermNode()));
    assertEquals("4x^3 1x^2 3x^1", threeTerm.toString());
    assertEquals(42, threeTerm.evaluate(2), .01);

  }

  @Test
  public void testToString() {
    assertEquals("1x^2", simpleTermNode.toString());
    assertEquals("4x^3", complexTermNode.toString());
    assertEquals("0x^2", zeroTermNode.toString());
  }

  @Test
  public void testAddNode() {

    // Test higher power adding smaller power
    assertEquals("4x^3 1x^2", complexTermNode.addNode(simpleTermNode).toString());

    // Test smaller power adding larger power
    Node temp = simpleTermNode.addNode(complexTermNode);
    assertEquals("4x^3 1x^2", temp.toString());

    // Test multiple nodes
    Node powerOne = new TermNode(1, 3, new EmptyTermNode());
    assertEquals("4x^3 1x^2 3x^1", temp.addNode(powerOne).toString());

    // Test adding two nodes with the same coefficient
    assertEquals("1x^2", simpleTermNode.addNode(zeroTermNode).toString());

    // Test adding the zeroNode
    assertEquals("4x^3 0x^2", complexTermNode.addNode(zeroTermNode).toString());
  }

  @Test
  public void testRemoveNode() {
    // Test smaller power adding larger power
    Node temp = simpleTermNode.addNode(complexTermNode);
    assertEquals("4x^3 1x^2", temp.toString());

    assertEquals("4x^3", temp.removeNode(2).toString());
    assertEquals("1x^2", temp.removeNode(3).toString());

    // Test larger Polynomial
    Node threeTerm = temp.addNode(new TermNode(1, 3, new EmptyTermNode()));
    assertEquals("4x^3 1x^2 3x^1", threeTerm.toString());

    assertEquals("1x^2 3x^1", threeTerm.removeNode(3).toString());
    assertEquals("4x^3 3x^1", threeTerm.removeNode(2).toString());
  }

  @Test
  public void restFindCoefficient() {

    // set up multi term polynomial
    Node temp = simpleTermNode.addNode(complexTermNode);
    assertEquals("4x^3 1x^2", temp.toString());

    Node threeTerm = temp.addNode(new TermNode(1, 3, new EmptyTermNode()));
    assertEquals("4x^3 1x^2 3x^1", threeTerm.toString());

    // standard tests
    assertEquals(4, threeTerm.findCoefficient(3));
    assertEquals(1, threeTerm.findCoefficient(2));
    assertEquals(3, threeTerm.findCoefficient(1));

    // term not found; should return 0.
    assertEquals(0, threeTerm.findCoefficient(5));
    assertEquals(0, threeTerm.findCoefficient(0));
  }

}

/**
 * A special type of node which acts as a recursive base case.
 */
public class EmptyTermNode extends AbstractNode {

  /**
   * Constructor for an empty node.
   */
  public EmptyTermNode() {
    super();
  }

  /**
   * Add a new node to the list.
   *
   * @param other The new node to be added to the existing node.
   * @return A new node containing the new element.
   */
  @Override
  public Node addNode(Node other) {
    return null;
  }

  @Override
  public Node addNodeHelper(Node other) {
    return new TermNode(other.getDegree(), other.getCoefficient(), this);
  }

  /**
   * Remove a node with the given power.
   *
   * @param power The power node to be removed.  If the node is not found, the returned node will
   *              not be changed.
   * @return A new node without the passed node.
   */
  @Override
  public Node removeNode(int power) {
    return null;
  }

  /**
   * Calculate the value of the node given an input term.
   *
   * @param x The term to be evaluated.
   * @return The value of the node, as a double.
   */
  @Override
  public double evaluate(double x) {
    return 0;
  }


  /**
   * Helps generate the toString() method return.
   *
   * @param str The string to be concatenated.
   * @return The concatenated string.
   */
  @Override
  public String toStringHelper(String str) {
    return str.substring(0, str.length() - 1);
  }
}

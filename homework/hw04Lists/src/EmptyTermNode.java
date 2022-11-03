import java.util.Objects;

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
    return this.addNodeHelper(other);
  }

  /**
   * Helps add a new node to an existing node.
   *
   * @param other The node to be added.
   * @return A new node containing the original and added node.
   */
  @Override
  public Node addNodeHelper(Node other) {
    return new TermNode(other.getDegree(), other.getCoefficient(), this);
  }

  /**
   * Get the highest power being raised in the list of terms.
   *
   * @return The highest power as an int.
   */
  @Override
  public int getDegree() {
    return 0;
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
    return this.removeNodeHelper(power);
  }

  /**
   * Helps removeNode method to remove a term with a given power from the node.
   *
   * @param power The power to be removed, as an int.
   * @return A node without the term containing the power. If no node with the given power is found
   *         the Node is returned unchanged.
   */
  @Override
  public Node removeNodeHelper(int power) {
    return this;
  }

  /**
   * Find the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int findCoefficient(int power) {
    return 0;
  }

  /**
   * Helps the findCoefficient method search for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int findCoefficientHelper(int power) {
    return 0;
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
   * Help the evaluate() method to evaluate the value of a term given a value.
   *
   * @param x The term to be evaluated.
   * @return The evaluated value of the node, as a double.
   */
  @Override
  public double evaluateHelper(double x) {
    return this.evaluate(x);
  }

  /**
   * Return a string interpolation of the node.
   *
   * @return The node in string form.
   */
  @Override
  public String toString() {
    return "0";
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

  /**
   * Determines if two objects are logically equivalent.
   *
   * @param other the other object to test for equality.
   * @return true if the objects are logically equivalent.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof EmptyTermNode)) {
      return false; //other cannot be equal to this as it is not a Person object!
    }
    Node otherNode = (EmptyTermNode) other;
    return true;
  }

  /**
   * Create a hash table of the object.
   *
   * @return the object as an integer hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.coefficient, this.power);
  }
}

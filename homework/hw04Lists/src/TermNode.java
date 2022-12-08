import java.util.Objects;

/**
 * A special type of node that acts a term in a polynomial.
 */
public class TermNode extends AbstractNode {

  /**
   * Constructor for the AbstractNode class that instantiates a node with a power and coefficient
   * and additional terms in the list.
   *
   * @param power       The power being raised by this node.
   * @param coefficient The coefficient of this node.
   * @param rest        The other elements of this node.
   * @throws IllegalArgumentException Powers cannot be entered that are less than 0.
   */
  public TermNode(int power, int coefficient, Node rest) throws IllegalArgumentException {
    super(power, coefficient, rest);
  }

  /**
   * Constructor for the AbstractNode class that instantiates a node with a power and coefficient
   * and additional terms in the list.
   *
   * @param power       The power being raised by this node.
   * @param coefficient The coefficient of this node.
   * @throws IllegalArgumentException Powers cannot be entered that are less than 0.
   */
  public TermNode(int power, int coefficient) throws IllegalArgumentException {
    super(power, coefficient, new EmptyTermNode());
  }

  /**
   * Constructor for an empty node.
   */
  public TermNode() {
    super();
  }

  /**
   * Calculate the value of the node given an input term.
   *
   * @param x The term to be evaluated.
   * @return The value of the node, as a double.
   */
  @Override
  public double evaluate(double x) {
    return this.evaluateHelper(x);
  }

  /**
   * Help the evaluate() method to evaluate the value of a term given a value.
   *
   * @param x The term to be evaluated.
   * @return The evaluated value of the node, as a double.
   */
  @Override
  public double evaluateHelper(double x) {
    double eval = Math.pow(x, super.power) * super.coefficient;
    return eval + this.rest.evaluateHelper(x);
  }

  /**
   * Get the highest power being raised in the list of terms.
   *
   * @return The highest power as an int.
   */
  @Override
  public int getDegree() {
    return super.getPower();
  }

  /**
   * Return a string interpolation of the node.
   *
   * @return The node in string form.
   */
  @Override
  public String toString() {
    return this.toStringHelper("");
  }

  /**
   * Helps generate the toString() method return.
   *
   * @param str The string to be concatenated.
   * @return The concatenated string.
   */
  @Override
  public String toStringHelper(String str) {

    if (super.coefficient == 0) {
      return this.rest.toStringHelper(str);
    } else {
      String thisString = String.format("%dx^%d", super.coefficient, super.power);
      return this.rest.toStringHelper(str + thisString + " ");
    }


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
  public Node addNodeHelper(Node other) {

    if (this.getDegree() > other.getDegree()) {
      // return new TermNode(super.getPower(), super.getCoefficient(), other);
      return new TermNode(this.power, this.coefficient, this.rest.addNodeHelper(other));
    }

    // Smaller adding larger
    else if (this.getDegree() < other.getDegree()) {
      //return other.addNode(this);
      return new TermNode(other.getDegree(), other.getCoefficient(), this);
    }

    // Equal powers
    else {
      return new TermNode(super.getPower(), super.getCoefficient() + other.getCoefficient(),
            other.getRest());
      }
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
    if (super.getPower() == power) {
      return super.getRest();
    } else {
      return new TermNode(super.power, super.coefficient, super.rest.removeNodeHelper(power));
    }
  }

  /**
   * Find the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int findCoefficient(int power) {
    return this.findCoefficientHelper(power);
  }

  /**
   * Helps the findCoefficient method search for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int findCoefficientHelper(int power) {
    if (super.getPower() == power) {
      return super.getCoefficient();
    } else {
      return super.rest.findCoefficientHelper(power);
    }
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
    if (!(other instanceof TermNode)) {
      return false; //other cannot be equal to this as it is not a Person object!
    }
    Node otherNode = (TermNode) other; //this will work

    return this.getDegree() == otherNode.getDegree()
        && this.getCoefficient() == otherNode.getCoefficient()
        && this.rest.equals(otherNode.getRest());
  }

  /**
   * Create a hash table of the object.
   *
   * @return the object as an integer hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.coefficient, this.power, this.rest);
  }
}
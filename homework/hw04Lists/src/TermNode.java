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
    return Math.pow(x, this.power) * coefficient;
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
    String thisString = String.format("%dx^%d", super.coefficient, super.power);
    return this.rest.toStringHelper(str + thisString + " ");
  }

  /**
   * Add a new node to the list.
   *
   * @param other The new node to be added to the existing node.
   * @return A new node containing the new element.
   */
  @Override
  public Node addNode(Node other) {
    if (this.getDegree() > other.getDegree()) {
      return new TermNode(super.getPower(), super.getCoefficient(), other);
    } else if (this.getDegree() < other.getDegree()) {
      return other.addNode(this);
    } else {
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
    return null;
  }
}
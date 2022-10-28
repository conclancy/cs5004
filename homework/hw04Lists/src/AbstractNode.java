/**
 * Represents nodes in a linked list.
 */
public abstract class AbstractNode implements Node {

  protected int power;
  protected int coefficient;
  protected Node rest;

  /**
   * Constructor for the AbstractNode class that instantiates a node with a power and coefficient
   * and additional terms in the list.
   *
   * @param power       The power being raised by this node.
   * @param coefficient The coefficient of this node.
   * @param rest        The other elements of this node.
   * @throws IllegalArgumentException Powers cannot be entered that are less than 0.
   */
  public AbstractNode(int power, int coefficient, Node rest) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power must not be negative.");
    }

    this.power = power;
    this.coefficient = coefficient;
    this.rest = rest;
  }

  /**
   * Constructor for an empty node.
   */
  public AbstractNode() {
    this.power = 0;
    this.coefficient = 0;
    this.rest = null;
  }

  /**
   * Get the power of this node.
   *
   * @return the power for this node, as an int.
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Get the coefficient value for the node.
   *
   * @return The coefficient, as an int.
   */
  @Override
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Get the rest of the nodes after this node.
   *
   * @return The remaining nodes.
   */
  @Override
  public Node getRest() {
    return this.rest;
  }

  /**
   * Get the highest power being raised in the list of terms.
   *
   * @return The highest power as an int.
   */
  @Override
  public int getDegree() {
    return Math.max(this.getPower(), rest.getDegree());
  }


}

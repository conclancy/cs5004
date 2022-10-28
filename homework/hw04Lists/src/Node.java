import java.util.function.Predicate;

public interface Node {

  /**
   * Get the highest power being raised in the list of terms.
   *
   * @return The highest power as an int.
   */
  int getDegree();

  /**
   * Get the coefficient value for the node.
   *
   * @return The coefficient, as an int.
   */
  int getCoefficient();

  /**
   * Get the rest of the nodes after this node.
   *
   * @return The remaining nodes.
   */
  Node getRest();

  /**
   * Return a string interpolation of the node.
   *
   * @return The node in string form.
   */
  String toString();

  /**
   * Add a new node to the list.
   *
   * @param other The new node to be added to the existing node.
   * @return A new node containing the new element.
   */
  Node addNode(Node other);

  /**
   * Remove a node with the given power.
   *
   * @param power The power node to be removed.  If the node is not found, the returned node will
   *              not be changed.
   * @return A new node without the passed node.
   */
  Node removeNode(int power);

  /**
   * Calculate the value of the node given an input term.
   *
   * @param x The term to be evaluated.
   * @return The value of the node, as a double.
   */
  double evaluate(double x);

  /**
   * Helps generate the toString() method return.
   *
   * @param str The string to be concatenated.
   * @return The concatenated string.
   */
  String toStringHelper(String str);

  /**
   * Helps add a new node to an existing node.
   *
   * @param other The node to be added.
   * @return A new node containing the original and added node.
   */
  Node addNodeHelper(Node other);
}

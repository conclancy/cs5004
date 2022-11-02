/**
 * A class representing a node in a linked-list.
 */
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
   * Help the evaluate() method to evaluate the value of a term given a value.
   *
   * @param x The term to be evaluated.
   * @return The evaluated value of the node, as a double.
   */
  double evaluateHelper(double x);

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

  /**
   * Helps removeNode method to remove a term with a given power from the node.
   *
   * @param power The power to be removed, as an int.
   * @return A node without the term containing the power. If no node with the given power is found
   *         the Node is returned unchanged.
   */
  Node removeNodeHelper(int power);

  /**
   * Find the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  int findCoefficient(int power);

  /**
   * Helps the findCoefficient method search for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  int findCoefficientHelper(int power);
}

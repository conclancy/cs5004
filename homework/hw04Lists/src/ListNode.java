import java.util.function.Predicate;

public class ListNode<T> extends AbstractNode<T> {

  public ListNode(T data, Node rest) {
    super(data, rest);
  }

  /**
   * Add the given node as a child to a node in this tree identified by the predicate. If no node is
   * identified by the predicate, the tree remains unchanged.
   *
   * @param identifier A predicate that is used to identify the node.
   * @param newNode    The node that must be added as a child node.
   * @return the resulting hierarchy starting at this node.
   */
  @Override
  public Node<T> addChild(Predicate<T> identifier, Node<T> newNode) {
    if (identifier.test(this.data)) {
      this.rest.add(newNode);
    }
  }

  /**
   * Remove the give node in the tree identified by the predicate. If no node is identified by the
   * predcate, the tree remains unchanged.
   *
   * @param identifier A predicate that is used to identify the node.
   * @param newNode    The node that must be added as a child node.
   * @return the resulting hierarchy starting at this node.
   */
  @Override
  public Node<T> removeChild(Predicate<T> identifier, Node<T> newNode) {
    return null;
  }
}

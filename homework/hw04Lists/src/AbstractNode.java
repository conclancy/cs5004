public abstract class AbstractNode<T> implements Node<T> {
  protected T data;
  protected Node rest;

  /**
   * Constructor for the Abstract Node class.
   *
   * @param data a data object which will be stored in the node.
   */
  public AbstractNode(T data, Node rest) {
    this.data = data;
    this.rest = rest;
  }
}

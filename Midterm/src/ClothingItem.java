/**
 * Clothing item is a specific type of item that belongs to a collection.
 */
public class ClothingItem extends AbstractItem {

  private Collection collection;

  /**
   * Construct a clothing item.
   *
   * @param price        The cost of the item, as a double.
   * @param size         The size of the item, as an integer.
   * @param collection   The collection of an item, as WINTER, SPRING, SUMMER, or FALL.
   * @param initialStock The number of items starting in stock, as an integer.
   */
  public ClothingItem(double price, double size, Collection collection, int initialStock) {
    super(price, initialStock, size);
    this.collection = collection;
  }

  /**
   * Get the size of the current item.
   *
   * Functionally the same as the getWeight() method from the super class, but here to ensure the
   * caller does not get caught up on the semantics of weight vs. size.
   *
   * @return The size of the item as a double.
   */
  public double getSize() {
    return super.getWeight();
  }

  /**
   * Get the collection of the current item.
   *
   * @return The collection of an item, as WINTER, SPRING, SUMMER, or FALL.
   */
  public Collection getCollection() {
    return collection;
  }


}

/**
 * Kitchen item is a specific type of item.
 */
public class KitchenItem extends AbstractItem {

  /**
   * Construct a kitchen item.
   *
   * @param price        The cost of the item, as a double.
   * @param initialStock The number of items starting in stock, as an integer.
   * @param weight       The weight of a single unit of the item, as a double.
   */
  public KitchenItem(double price, int initialStock, double weight) {
    super(price, initialStock, weight);
  }
}

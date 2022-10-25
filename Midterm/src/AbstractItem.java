/**
 * This represents an abstract item that will be in a web store.
 * You are a developer for the web store and noticed that 
 * the original developer did not use proper abstraction.
 * You need to correct this problem.
 * You need to look at the specific items:
 * KitchenItem
 * ClothingItem
 * Book Item
 * 
 * and determine should be moved into the abstract class.
 * Then properly add javadoc to all methods in the 
 * abstract class and subclasses. 
 * You will need to turn in this file, along with the properly
 * modified version of the other files so that they can make use
 * of all the fields and methods of this AbstractClass. 
 * 
 *
 */
public abstract class AbstractItem {
  protected double price;
  protected int nInStock;
  protected double weight;

  /**
   * Construct an item.
   *
   * @param price The cost of the item, as a double.
   * @param initialStock The number of items starting in stock, as an integer.
   * @param weight The weight or size of a single unit of the item, as a double.
   */
  public AbstractItem(double price, int initialStock, double weight) {
    this.price = price;
    this.nInStock = initialStock;
    this.weight = weight;
  }

  /**
   * Get the price of the item.
   *
   * @return The price of the item, as a double.
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Determine if the item is in stock.
   *
   * @return True if the item is in stock.
   */
  public boolean inStock() {
    return this.nInStock>0;
  }

  /**
   * Get the weight of the item.
   *
   * @return The weight of the item, as a double.
   */
  public double getWeight() {
    return this.weight;
  }

  /**
   * Get the value of the inventory.
   *
   * @return The value of the items, as a double.
   */
  public double valueOfStock() {
    return this.price * this.nInStock;
  }

  /**
   * Invoked to reduce the number of an item in stock.
   *
   * @param nSold The number of items sold, as an integer.
   * @throws IllegalArgumentException if the number sold is less than the in stock number.
   */
  public void sellItem(int nSold) throws IllegalArgumentException {
    if (nSold > this.nInStock) {
      throw new IllegalArgumentException("We cannot sell more than are in stock.");
    }
    this.nInStock = this.nInStock - nSold;
  }
}

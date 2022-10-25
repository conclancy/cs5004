
public class ClothingItem {
  private double price;
  private double size;
  private int numberAvailable;
  private Collection collection;


  public ClothingItem(double price, double size, Collection collection, int initialStock) {
    this.price = price;
    this.numberAvailable = initialStock;
    this.size = size;
    this.collection = collection;
  }

  public double getPrice() {
    return this.price;
  }

  public double getWeight() {
    return this.size;
  }


  public boolean inStock() {
    return numberAvailable>0;
  }

  public void sellItem(int nSold) throws IllegalArgumentException {
    if (nSold > this.numberAvailable) {
      throw new IllegalArgumentException("We cannot sell more than are in stock.");
    }
    this.numberAvailable = this.numberAvailable - nSold;
  }

  public Collection getCollection() {
    return collection;
  }



}

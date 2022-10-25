
public class KitchenItem {
  
  private double price;
  private double weight;
  private int nInStock;
  
  public KitchenItem(double price, int initialStock, double weight) {
    this.price = price;
    this.nInStock = initialStock;
    this.weight = weight;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public double getWeight() {
    return this.weight;
  }
  
  public boolean inStock() {
    return nInStock>0;
  }
  
  public void sellItem(int nSold) throws IllegalArgumentException {
    if (nSold > this.nInStock) {
      throw new IllegalArgumentException("We cannot sell more than are in stock.");
    }
    this.nInStock = this.nInStock - nSold;
  }
  
  public double valueOfStock() {
    return price*nInStock;
  }
  

}

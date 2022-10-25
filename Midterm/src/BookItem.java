
public class BookItem {
  
  private String title;
  private String author;
  private double price;
  private double weight;
  private int length;
  private int nInStock;
  
  public BookItem(String author, String title, int length, double price, int initialStock, double weight) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.nInStock = initialStock;
    this.weight = weight;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getAuthor() {
    return this.author;
  }
  
  public int getLenght() {
    return this.length;
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

/**
 * Book item is a specific type of item that has a title, author, and length.
 */
public class BookItem extends AbstractItem {

  private String title;
  private String author;
  private int length;

  /**
   * Construct a book item.
   *
   * @param author       The author of the book, as a String.
   * @param title        The title of the book, as a String.
   * @param length       The length of the book, as an integer.
   * @param price        The price of the book, as a double.
   * @param initialStock The initial amount of stock, as an integer.
   * @param weight       The weight of a single unit of the book, as a double.
   */
  public BookItem(String author, String title, int length, double price, int initialStock,
      double weight) {
    super(price, initialStock, weight);
    this.title = title;
    this.author = author;
    this.length = length;
  }

  /**
   * Get the title of the book.
   *
   * @return The title of the book,  as a String.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Get the author of the book.
   *
   * @return The author of the book, as a String.
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * Get the length of the book.
   *
   * @return The length of the book, as an int.
   */
  public int getLenght() {
    return this.length;
  }


}

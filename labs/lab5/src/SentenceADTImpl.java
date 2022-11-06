import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceADTImpl<T> implements SentenceADT<T> {
  private List<T> sentence;

  /**
   * Constructor to create an empty sentence.
   */
  public SentenceADTImpl() {
    this.sentence = new LinkedList<>();
  }

  /**
   * Constructor to create a sentence from an existing list.
   *
   * @param sentence the list containing the objects for the sentence.
   */
  public SentenceADTImpl(List<T> sentence) {
    this.sentence = sentence;
  }

  /**
   * Constructor to create a sentence from an existing sentence string.
   *
   * @param sentence the String containing the words for the sentence.
   */
  public SentenceADTImpl(String sentence) {
    // TODO use higher order function to implement.
  }

  /**
   * Add a new object to the front of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  @Override
  public void addFront(T s) {

  }

  /**
   * Add a new object to the final position of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  @Override
  public void addBack(T s) {

  }

  /**
   * Add a new object at a specific index in the sentence.
   *
   * @param index the position of the object to be added, as an int.
   * @param s     the object to be added to the sentence.
   */
  @Override
  public void add(int index, T s) {

  }

  /**
   * Get the number of words in the current sentence, as an int.
   *
   * @return the count of the number of words in the sentence, as an int.
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Remove a specific object from the sentence.
   *
   * @param s the object to be removed.
   */
  @Override
  public void remove(T s) {

  }

  /**
   * Get the object at a specific point in the sentence.
   *
   * @param index the index of the desired object, as an int starting at 0.
   * @return the object.
   * @throws IllegalArgumentException if the given index is out of range for the sentence.
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    return null;
  }

  /**
   * A general purpose map function that can be used to return the sentence as a corresponding list
   * of generic type R.
   *
   * @param converter the function that converts objects of T to objects of R.
   * @return the list converted to objects of type R.
   */
  @Override
  public <R> SentenceADT<R> map(Function<T, R> converter) {
    return null;
  }

  /**
   * Return the longest word in the sentence.
   *
   * @return the longest word in the sentence, as a String.
   */
  @Override
  public String longestWord() {
    return null;
  }

  /**
   * Create an identical copy of this Sentence.
   *
   * @return A Sentence object that is a clone of the original sentence object.
   */
  @Override
  public SentenceADT<T> clone() {
    return null;
  }

  /**
   * Combine two Sentence objects into a new single sentence.
   *
   * @param other the other object to be merged.
   * @return A new sentence object.
   */
  @Override
  public SentenceADT<T> merge(SentenceADT<T> other) {
    return null;
  }
}

import java.util.List;
import java.util.function.Function;

/**
 * This interface represents a sentence.  Separating from previous Sentence interface to remove the
 * necessity to extend cloneable.
 */
public interface SentenceADT<T> {

  /**
   * Add a new object to the front of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  void addFront(T s);

  /**
   * Add a new object to the final position of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  void addBack(T s);

  /**
   * Add a new object at a specific index in the sentence.
   *
   * @param index the position of the object to be added, as an int.
   * @param s the object to be added to the sentence.
   */
  void add(int index, T s) throws IllegalArgumentException;

  /**
   * Get the number of words in the current sentence, as an int.
   *
   * @return the count of the number of words in the sentence, as an int.
   */
  int getNumberOfWords();

  /**
   * Get the number of punctuation marks in the current sentence, as an int.
   *
   * @return the count of the number of punctuation marks in the sentence, as an int.
   */
  int getNumberOfPunctuation();

  /**
   * Count the number of words that contain the letter 'Z'.
   *
   * @return the number of words in the sentence that contain at least one 'z', as an int.
   */
  int getNumberOfWordsWithZ();

  /**
   * Remove a specific object from the sentence.
   *
   * @param s the object to be removed.
   */
  void remove(T s);

  /**
   * Get the object at a specific point in the sentence.
   *
   * @param index the index of the desired object, as an int starting at 0.
   * @return the object.
   * @throws IllegalArgumentException if the given index is out of range for the sentence.
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * Get all elements of the sentence as a list.
   *
   * @return the elements of the sentence as a list.
   */
  List<T> getSentenceList();


  /**
   * Return the longest word in the sentence.
   *
   * @return the longest word in the sentence, as a String.
   */
  String longestWord();

  /**
   * Create an identical copy of this Sentence.
   *
   * @return A Sentence object that is a clone of the original sentence object.
   */
  SentenceADT<T> clone();

  /**
   * Combine two Sentence objects into a new single sentence.
   *
   * @param other the other object to be merged.
   * @return A new sentence object.
   */
  SentenceADT<T> merge(SentenceADT<T> other);

  /**
   * Converts an English sentence into pig latin and back to english.
   */
  void pigLatin(boolean state);

  /**
   * Returns a sentence in its pig latin form.
   *
   * @return a sentence in its pig latin form.
   */
  String getPigLatinString();
}

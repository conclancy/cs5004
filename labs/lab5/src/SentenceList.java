import java.util.List;

/**
 * This interface represents a sentence.  Separating from previous Sentence interface to remove the
 * necessity to extend cloneable.
 */
public interface SentenceList {

  /**
   * Return the sentence as a linked list.
   *
   * @return the sentence, as a linked list.
   */
  List<String> getSentence();

  /**
   * Return the number of words in the sentence (excluding punctuation).
   *
   * @return number of words in the sentence
   */
  int getNumberOfWords();

  /**
   * The longest word in the sentence by number of characters.
   *
   * @return longest word in the sentence
   */
  String longestWord();

  /**
   * Returns the entire sentence as a single string (including punctuation).
   *
   * @return singular string representing the sentence
   */
  String toString();

  /**
   * Returns a clone of this sentence.
   *
   * @return cloned identical sentence
   */
  SentenceList clone();

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @return combination of two sentences
   */
  SentenceList merge(SentenceList other);
}

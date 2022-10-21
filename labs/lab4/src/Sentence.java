/**
 * This interface represents a sentence.
 */
public interface Sentence extends Cloneable {

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
   * Combine two sentences into a singular new sentence.
   *
   * @return combination of two sentences
   */
  Sentence merge(Sentence other);
}

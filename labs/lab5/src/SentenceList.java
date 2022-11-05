/**
 * This interface represents a sentence.  Separating from previous Sentence interface to remove the
 * necessity to extend cloneable.
 */
public interface SentenceList {

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
  Sentence clone();

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @return combination of two sentences
   */
  Sentence merge(Sentence other);

  /**
   * Helper for the getNumberOfWords method.
   *
   * @param acc accumulator value
   * @return the value accumulated
   */
  int getNumberOfWordsHelp(int acc);

  /**
   * Helper method for the toString method.
   *
   * @return accumulated toString value
   */
  String toStringHelp(String str);
}

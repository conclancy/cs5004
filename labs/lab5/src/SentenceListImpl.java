public class SentenceListImpl implements SentenceList{

  /**
   * Return the number of words in the sentence (excluding punctuation).
   *
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * The longest word in the sentence by number of characters.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    return null;
  }

  /**
   * Returns a clone of this sentence.
   *
   * @return cloned identical sentence
   */
  @Override
  public Sentence clone() {
    return null;
  }

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @param other
   * @return combination of two sentences
   */
  @Override
  public Sentence merge(Sentence other) {
    return null;
  }

  /**
   * Helper for the getNumberOfWords method.
   *
   * @param acc accumulator value
   * @return the value accumulated
   */
  @Override
  public int getNumberOfWordsHelp(int acc) {
    return 0;
  }

  /**
   * Helper method for the toString method.
   *
   * @param str
   * @return accumulated toString value
   */
  @Override
  public String toStringHelp(String str) {
    return null;
  }
}

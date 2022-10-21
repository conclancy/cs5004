public class PunctuationNode extends AbstractSentence {

  /**
   * Abstract constructor for the sentence class.
   *
   * @param word the string held within this node
   * @param rest the previous nodes in the sentence
   */
  public PunctuationNode(String word, Sentence rest) {
    super(word, rest);
  }

  /**
   * Return the number of words in the sentence (excluding punctuation).
   *
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWords() {
    return rest.getNumberOfWords();
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
   * Returns an exact replica of the sentence.
   *
   * @return a duplicate of the sentence
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
}

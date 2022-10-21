public class EmptyNode extends AbstractSentence {

  /**
   * Constructor for an empty node.
   */
  public EmptyNode() {
    super("",null);
  }

  /**
   * Return 0 to initiate the accumulation process.
   *
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Return the empty string to initiate the accumulation process.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    return null;
  }

  /**
   * Returns the new empty node for the duplicate sentence.
   *
   * @return a duplicate of the sentence
   */
  @Override
  public Sentence clone() {
    return new EmptyNode();
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

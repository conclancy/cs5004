/**
 * Abstraction of the Sentence implementation.
 */
public abstract class AbstractSentence implements Sentence, Cloneable {
  protected String word;
  protected Sentence rest;

  /**
   * Abstract constructor for the sentence class.
   *
   * @param word the string held within this node
   * @param rest the previous nodes in the sentence
   */
  public AbstractSentence(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
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
   * Helper for the getNumberOfWords() method.
   *
   * @param acc accumulator value
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWordsHelp(int acc) {
    return acc;
  }

  /**
   * Creates a clone of the sentence.
   *
   * @return a new sentence object with the same nodes.
   * @throws CloneNotSupportedException if the clone cannot be created.
   */
  @Override
  public Sentence clone() throws CloneNotSupportedException {
    return null;
  }
}

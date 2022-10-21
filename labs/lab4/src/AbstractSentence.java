public abstract class AbstractSentence implements Sentence {
    public String word;
    public Sentence rest;

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
}

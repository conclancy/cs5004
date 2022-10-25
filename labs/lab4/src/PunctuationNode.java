/**
 * Represents a punctuation mark in a sentence.
 */
public class PunctuationNode extends AbstractSentence implements Cloneable {

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
   * The longest word in the sentence by number of characters.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    return rest.longestWord();
  }

  /**
   * Returns an exact replica of the sentence.
   *
   * @return a duplicate of the sentence
   */
  @Override
  public Sentence clone() throws CloneNotSupportedException {
    try {
      return new PunctuationNode(super.word, super.rest.clone());
    } catch (Exception e) {
      throw new CloneNotSupportedException("This punctuation node cannot be cloned");
    }
  }

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @param other the other sentence
   * @return combination of two sentences
   */
  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(super.word, super.rest.merge(other));
  }

  /**
   * Return the entire sentence as a single string object.
   *
   * @return a string representation of the sentence
   */
  @Override
  public String toString() {
    return this.toStringHelp("");
  }


  /**
   * Helper method for the toString() method.
   *
   * @param str string to be returned
   * @return the accumulated string
   */
  @Override
  public String toStringHelp(String str) {
    return this.rest.toStringHelp(str + this.word);
  }
}

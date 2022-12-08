/**
 * Represents a word in a sentence.
 */
public class WordNode extends AbstractSentence implements Cloneable {

  /**
   * Abstract constructor for the sentence class.
   *
   * @param word the string held within this node
   * @param rest the previous nodes in the sentence
   */
  public WordNode(String word, Sentence rest) {
    super(word, rest);
  }

  /**
   * Return the number of words in the sentence (excluding punctuation).
   *
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWords() {
    return this.getNumberOfWordsHelp(0);
  }

  /**
   * Helper method for the getNumberOfWords() method.
   *
   * @param acc accumulator value
   * @return incremented value
   */
  @Override
  public int getNumberOfWordsHelp(int acc) {
    return this.rest.getNumberOfWordsHelp(1 + acc);
  }

  /**
   * The longest word in the sentence by number of characters.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    if (super.word.length() > rest.longestWord().length()) {
      return super.word;
    } else {
      return rest.longestWord();
    }
  }

  /**
   * Returns a clone of this sentence.
   *
   * @return cloned identical sentence
   * @throws IllegalArgumentException if the clone cannot be created.
   */
  @Override
  public Sentence clone() throws IllegalArgumentException {
    try {
      return new WordNode(super.word, super.rest.clone());
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Cloning this object not possible");
    }

  }

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @param other the other sentence.
   * @return combination of two sentences
   */
  @Override
  public Sentence merge(Sentence other) {
    return new WordNode(super.word, super.rest.merge(other));
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
    if (this.getNumberOfWords() == 1) {
      return this.rest.toStringHelp(str + this.word);
    } else {
      return this.rest.toStringHelp(str + this.word + " ");
    }
  }
}

import java.util.regex.Pattern;

/**
 * Class representing the end of a sentence.
 */
public class EmptyNode extends AbstractSentence implements Cloneable {

  /**
   * Constructor for an empty node.
   */
  public EmptyNode() {
    super("",null);
  }

  /**
   * Return the empty string to initiate the accumulation process.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    return super.word;
  }

  /**
   * Returns the new empty node for the duplicate sentence.
   *
   * @return a duplicate of the sentence
   */
  @Override
  public Sentence clone() throws CloneNotSupportedException {
    try {
      return new EmptyNode();
    }
    catch (Exception e) {
      throw new CloneNotSupportedException("This sentence node cannot be cloned.");
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
    // replace the empty node with a space between sentences.
    return new WordNode("", other);
  }

  /**
   * Return the entire sentence as a single string object.
   *
   * @return a string representation of the sentence
   */
  @Override
  public String toString() {
    return "";
  }


  /**
   * Helper method for the toString() method.
   *
   * @param str string to be returned
   * @return the accumulated string
   */
  @Override
  public String toStringHelp(String str) {
    String last = str.substring(str.length() - 1);
    if (Pattern.matches("\\p{Punct}", last)) {
      return str;
    } else {
      return str + ".";
    }
  }
}

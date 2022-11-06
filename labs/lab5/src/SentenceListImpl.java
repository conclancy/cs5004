import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceListImpl implements SentenceList {

  private List<String> sentence;

  /**
   * Constructor for a Sentence that takes in a list of words and punctuation marks as strings.
   *
   * @param sentence a list of strings, representing a sentence.
   */
  public SentenceListImpl(List<String> sentence) {
    this.sentence = sentence;
  }

  /**
   * Constructor for a Sentence that initializes and empty sentence ready to take in new strings to
   * build a sentence from scratch.
   */
  public SentenceListImpl() {
    this.sentence = new LinkedList<String>();
  }

  public List<String> getSentence() {
    return this.sentence;
  }

  /**
   * Return the number of words in the sentence (excluding punctuation).
   *
   * @return number of words in the sentence
   */
  @Override
  public int getNumberOfWords() {

    List<String> wordCount = this.sentence.stream()
        .filter(s -> !Pattern.matches("\\p{IsPunctuation}", s))
        .collect(Collectors.toList());

    return wordCount.size();
  }

  /**
   * The longest word in the sentence by number of characters.
   *
   * @return longest word in the sentence
   */
  @Override
  public String longestWord() {
    return this.sentence.stream()
        .max(Comparator.comparingInt(String::length)).get();
  }

  /**
   * Returns a clone of this sentence.
   *
   * @return cloned identical sentence
   */
  @Override
  public SentenceList clone() {
    return new SentenceListImpl(this.sentence);
  }

  /**
   * Combine two sentences into a singular new sentence.
   *
   * @param other the other Sentence object.
   * @return combination of two sentences.
   */
  @Override
  public SentenceList merge(SentenceList other) {
    List<String> merged = new LinkedList<>();
    merged.addAll(this.sentence);
    merged.addAll(other.getSentence());
    return new SentenceListImpl(merged);
  }
}

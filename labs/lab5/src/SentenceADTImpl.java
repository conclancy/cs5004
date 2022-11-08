import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the Sentence interface.
 *
 * @param <T> Object type for the components of the sentence.
 */
public class SentenceADTImpl<T> implements SentenceADT<T> {

  private List<T> sentence;

  /**
   * Constructor to create an empty sentence.
   */
  public SentenceADTImpl() {
    this.sentence = new LinkedList<>();
  }

  /**
   * Constructor to create a sentence from an existing list.
   *
   * @param sentence the list containing the objects for the sentence.
   */
  public SentenceADTImpl(List<T> sentence) {
    this.sentence = sentence;
  }

  /**
   * Constructor to create a sentence from an existing sentence string.
   *
   * @param sentence the String containing the words for the sentence.
   */
  public SentenceADTImpl(String sentence) throws IllegalArgumentException {
    this.sentence = new LinkedList<>();
    String[] sentenceArray = sentence
        .replaceAll("(?<=\\S)(?:(?<=\\p{Punct})|(?=\\p{Punct}))(?=\\S)", " ")
        .split("\\s");

    for (String word : sentenceArray) {
      try {
        this.addBack((T) word);
      } catch (Exception e) {
        throw new IllegalArgumentException(
            "Element '" + word + "' cannot be added to sentence" + e);
      }

    }
  }

  /**
   * Add a new object to the front of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  @Override
  public void addFront(T s) {
    this.sentence.add(0, s);
  }

  /**
   * Add a new object to the final position of the sentence.
   *
   * @param s the object to be added to the sentence.
   */
  @Override
  public void addBack(T s) {
    this.sentence.add(s);
  }

  /**
   * Add a new object at a specific index in the sentence.
   *
   * @param index the position of the object to be added, as an int.
   * @param s     the object to be added to the sentence.
   */
  @Override
  public void add(int index, T s) throws IllegalArgumentException {
    try {
      this.sentence.add(index, s);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Must add at valid index between 0 and "
          + this.sentenceSize());
    }

  }

  /**
   * Get the number of words in the current sentence, as an int.
   *
   * @return the count of the number of words in the sentence, as an int.
   */
  @Override
  public int getNumberOfWords() {
    return this.sentence.stream()
        .map(e -> {
              if (!(Pattern.matches("\\p{Punct}", e.toString()))) {
                return 1;
              } else {
                return 0;
              }
            }
        )
        .reduce(0, Integer::sum);
  }

  /**
   * Get the number of punctuation marks in the current sentence, as an int.
   *
   * @return the count of the number of punctuation marks in the sentence, as an int.
   */
  @Override
  public int getNumberOfPunctuation() {
    return this.sentence.stream()
        .map(e -> {
              if ((Pattern.matches("\\p{Punct}", e.toString()))) {
                return 1;
              } else {
                return 0;
              }
            }
        )
        .reduce(0, Integer::sum);
  }

  /**
   * Count the number of words that contain the letter 'Z'.
   *
   * @return the number of words in the sentence that contain at least one 'z', as an int.
   */
  @Override
  public int getNumberOfWordsWithZ() {
    return this.sentence.stream()
        .map(e -> {
              if (e.toString().toLowerCase().contains("z")) {
                return 1;
              } else {
                return 0;
              }
            }
        )
        .reduce(0, Integer::sum);
  }

  /**
   * Remove a specific object from the sentence.
   *
   * @param s the object to be removed.
   */
  @Override
  public void remove(T s) {
    this.sentence = this.sentence.stream()
        .filter(e -> !e.equals(s)).collect(Collectors.toList());
  }

  /**
   * Get the object at a specific point in the sentence.
   *
   * @param index the index of the desired object, as an int starting at 0.
   * @return the object at the given index.
   * @throws IllegalArgumentException if the given index is out of range for the sentence.
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index - 1 > this.sentence.size()) {
      throw new IllegalArgumentException("That index does not exit.  Sentence only contains " +
          this.sentenceSize() + " elements");
    } else if (index < 0) {
      throw new IllegalArgumentException("Index must be 0 or higher");
    }

    return this.sentence.get(index);
  }

  /**
   * Get all elements of the sentence as a list.
   *
   * @return the elements of the sentence as a list.
   */
  @Override
  public List<T> getSentenceList() {
    return this.sentence;
  }

  /**
   * Return the longest word in the sentence.  In the case of a tie, the word appear first will be
   * returned.
   *
   * @return the longest word in the sentence, as a String.
   * @throws NoSuchElementException Exception thrown if there is not a longest element.
   */
  @Override
  public String longestWord() throws NoSuchElementException {
    return this.sentence.stream()
        .map(Object::toString)
        .max(Comparator.comparing(String::length))
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * Create an identical copy of this Sentence.
   *
   * @return A Sentence object that is a clone of the original sentence object.
   */
  @Override
  public SentenceADT<T> clone() {
    return new SentenceADTImpl<>(this.sentence);
  }

  /**
   * Combine two Sentence objects into a new single sentence.
   *
   * @param other the other object to be merged.
   * @return A new sentence object.
   */
  @Override
  public SentenceADT<T> merge(SentenceADT<T> other) {
    return new SentenceADTImpl<>(Stream
        .concat(this.sentence.stream(), other.getSentenceList().stream())
        .collect(Collectors.toList()));
  }

  /**
   * Converts an English sentence of Strings into pig latin.  Sentences not of type String will
   * return the sentence unchanged as a String.
   *
   * @return a String sentence in its pig latin form.
   */
  @Override
  public String pigLatin() {
    List<String> pigList = this.sentence.stream()
        .map(e -> {
              if (e instanceof String) {
                if (Pattern.matches("\\p{Punct}", e.toString())) {
                  return e.toString();
                } else if (this.isVowel(e.toString().charAt(0))) {
                  return e.toString() + "way";
                } else {
                  return e.toString().substring(1) + e.toString().charAt(0) + "ay";
                }
              } else {
                return e.toString();
              }
            }
        )
        .collect(Collectors.toList());

    SentenceADT<String> pigSentence = new SentenceADTImpl<>(pigList);

    return pigSentence.toString();
  }

  /**
   * Takes in a character to determine if it is a vowel.  Y is not considered a vowel when using
   * this function.
   *
   * @param c letter to test, as a char.
   * @return true if the character passed is a vowel.
   */
  private boolean isVowel(char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }

  /**
   * Returns the Sentence in its current string form.  If the sentence is in English, the method
   * will return an English sentence; if the sentence is in Pig Latin, the method will return the
   * sentence in Pig Latin.
   *
   * @return the sentence as a String in English.
   */
  @Override
  public String toString() {
    String newString = this.sentence.stream()
        .map(Object::toString)
        .collect(Collectors.joining(" "));
    return newString.trim().replaceAll("\\s+(?=\\p{Punct})", "");
  }

  /**
   * Method for getting the sentence size as a String.
   *
   * @return the size of the sentence, as a String.
   */
  private String sentenceSize() {
    Integer size = this.sentence.size();
    return size.toString();
  }
}

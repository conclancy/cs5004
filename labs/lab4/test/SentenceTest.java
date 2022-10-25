import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Sentence class.
 */
public class SentenceTest {

  private EmptyNode firstEmptyNode;
  private PunctuationNode firstEnd;
  private WordNode firstSentFirstWord;
  private WordNode firstSentSecondWord;
  private WordNode firstSentThirdWord;
  private WordNode sentenceOne;
  private EmptyNode secondEmptyNode;
  private PunctuationNode secondEnd;
  private WordNode secondSentFirstWord;
  private WordNode secondSentSecondWord;
  private WordNode sentenceTwo;

  /**
   * Initialize objects for JUnit tests.
   */
  @Before
  public void init() {

    // first sentence.  This is a sentence.
    firstEmptyNode = new EmptyNode();
    firstEnd = new PunctuationNode(".", firstEmptyNode);
    firstSentFirstWord = new WordNode("sentence", firstEnd);
    firstSentSecondWord = new WordNode("a", firstSentFirstWord);
    firstSentThirdWord = new WordNode("is", firstSentSecondWord);
    sentenceOne = new WordNode("This", firstSentThirdWord);

    // second sentence. And another sentence!
    secondEmptyNode = new EmptyNode();
    secondEnd = new PunctuationNode("!", secondEmptyNode);
    secondSentFirstWord = new WordNode("sentence", secondEnd);
    secondSentSecondWord = new WordNode("another", secondSentFirstWord);
    sentenceTwo = new WordNode("And", secondSentSecondWord);

  }

  /**
   * Test to ensure getNumberOfWords returns the correct number of words in the sentence.
   */
  @Test
  public void testGetNumberOfWords() {
    assertEquals(0, firstEmptyNode.getNumberOfWords());
    assertEquals(0, firstEnd.getNumberOfWords());
    assertEquals(0, secondEmptyNode.getNumberOfWords());
    assertEquals(0, secondEnd.getNumberOfWords());
    assertEquals(1, firstSentFirstWord.getNumberOfWords());
    assertEquals(4, sentenceOne.getNumberOfWords());
    assertEquals(1, secondSentFirstWord.getNumberOfWords());
    assertEquals(2, secondSentSecondWord.getNumberOfWords());
  }

  /**
   * Test to ensure the method is able to find the longest word in the sentence.
   */
  @Test
  public void testLongestWord() {
    assertEquals("sentence", sentenceOne.longestWord());
    assertEquals("", firstEmptyNode.longestWord());
    assertEquals("", firstEnd.longestWord());
  }

  /**
   * Test to ensure we can properly convert the sentence into a string.
   */
  @Test
  public void testToString() {
    assertEquals("", firstEmptyNode.toString());
    assertEquals(".", firstEnd.toString());
    assertEquals("sentence.", firstSentFirstWord.toString());
    assertEquals("a sentence.", firstSentSecondWord.toString());
    assertEquals("is a sentence.", firstSentThirdWord.toString());
    assertEquals("This is a sentence.", sentenceOne.toString());
    assertEquals("And another sentence!", sentenceTwo.toString());
  }

  /**
   * Test to ensure the toString() method adds a period if no punctuation is present at the end of
   * the sentence.
   */
  @Test
  public void testToStringNoPunct() {
    Sentence noPunct = new WordNode("No punctuation", new EmptyNode());
    assertEquals("No punctuation.", noPunct.toString());
  }

  /**
   * Test to ensure the implementation of Cloneable is working correctly.
   */
  @Test
  public void testClone() {

    // set up clone variables
    Sentence sentenceClone;
    try {
      sentenceClone = sentenceOne.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }

    // actual clone test
    assertEquals("This is a sentence.", sentenceClone.toString());
  }

  /**
   * Test cloning an empty node.
   */
  @Test
  public void testCloneEmpty() {

    // set up clone variables
    Sentence empty = new EmptyNode();
    Sentence emptyClone;

    try {
      emptyClone = empty.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }

    // actual tests
    assertEquals("", emptyClone.toString());
  }

  /**
   * Test cloning a punctuation node.
   */
  @Test
  public void testClonePunct() {

    // set up clone variables
    Sentence punct = new PunctuationNode("!", new EmptyNode());
    Sentence punctClone;

    try {
      punctClone = punct.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }

    // actual test
    assertEquals("!", punctClone.toString());
  }

  /**
   * Test the merge() method.
   */
  @Test
  public void testMerge() {
    Sentence merged = sentenceOne.merge(sentenceTwo);
    assertEquals("This is a sentence. And another sentence!", merged.toString());
  }
}

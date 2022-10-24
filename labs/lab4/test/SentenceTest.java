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

  @Before
  public void init() {

    // first sentence.  This is a sentence.
    firstEmptyNode = new EmptyNode();
    firstEnd = new PunctuationNode(".", firstEmptyNode);
    firstSentFirstWord = new WordNode("sentence", firstEnd);
    firstSentSecondWord = new WordNode("a", firstSentFirstWord);
    firstSentThirdWord = new WordNode("is", firstSentSecondWord);
    sentenceOne = new WordNode("This", firstSentThirdWord);

  }

  @Test
  public void testGetNumberOfWords () {
    assertEquals(0, firstEmptyNode.getNumberOfWords());
    assertEquals(0, firstEnd.getNumberOfWords());
    assertEquals(1, firstSentFirstWord.getNumberOfWords());
    assertEquals(4, sentenceOne.getNumberOfWords());
  }

  @Test
  public void testLongestWord() {
    assertEquals("sentence", sentenceOne.longestWord());
    assertEquals("", firstEmptyNode.longestWord());
    assertEquals("", firstEnd.longestWord());
  }

  @Test
  public void testToString() {
    assertEquals("", firstEmptyNode.toString());
    assertEquals(".", firstEnd.toString());
    assertEquals("sentence.", firstSentFirstWord.toString());
    assertEquals("a sentence.", firstSentSecondWord.toString());
    assertEquals("is a sentence.", firstSentThirdWord.toString());
    assertEquals("This is a sentence.", sentenceOne.toString());
  }

  @Test
  public void testToStringNoPunct() {
    Sentence noPunct = new WordNode("No punctuation", new EmptyNode());
    assertEquals("No punctuation.", noPunct.toString());
  }

  @Test
  public void testCloneEmptyNode() {
  }
}

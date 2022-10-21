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
    assertEquals(4, sentenceOne.getNumberOfWords());
  }
}

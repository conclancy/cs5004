import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Sentence class.
 */
public class SentenceADTTest {

  private SentenceADTImpl sentenceOne;
  private SentenceADTImpl sentenceOneWPunct;

  @Before
  public void init() {
    sentenceOne = new SentenceADTImpl((List.of("This", "is", "a", "sentence")));
    sentenceOneWPunct = new SentenceADTImpl((List.of("This", "is", "a", "sentence", "!")));
  }

  @Test
  public void testGetNumberOfWords() {
    assertEquals(4, sentenceOne.getNumberOfWords());
    assertEquals(4, sentenceOneWPunct.getNumberOfWords());
  }

  @Test
  public void testLongestWord() {
    assertEquals("sentence", sentenceOne.longestWord());
  }

}

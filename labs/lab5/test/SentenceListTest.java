import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Sentence class.
 */
public class SentenceListTest {

  private SentenceListImpl sentenceOne;
  private SentenceListImpl sentenceOneWPunct;

  @Before
  public void init() {
    sentenceOne = new SentenceListImpl((List.of("This", "is", "a", "sentence")));
    sentenceOneWPunct = new SentenceListImpl((List.of("This", "is", "a", "sentence", "!")));
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

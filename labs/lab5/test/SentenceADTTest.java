import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit tests for the Sentence class.
 */
public class SentenceADTTest {

  private SentenceADTImpl<String> emptySentence;
  private SentenceADTImpl<String> stringSentence;
  private SentenceADTImpl<String> greeting;
  private SentenceADTImpl<String> listSentence;
  private SentenceADTImpl<Integer> numberSentence;
  private SentenceADTImpl<String> zSentence;
  private SentenceADTImpl<String> chipotle;

  @Before
  public void init() {
    emptySentence = new SentenceADTImpl<>();
    stringSentence = new SentenceADTImpl<>("This is a sentence from a string.");
    greeting = new SentenceADTImpl<>("Hello! Welcome to our new program.");
    listSentence = new SentenceADTImpl<>(
        List.of("Am", "I", "a", "sentence", "made", "from", "a", "list", "?"));
    numberSentence = new SentenceADTImpl<>(List.of(1, 2, 3, 4));
    zSentence = new SentenceADTImpl<>("The zany zebra zombie was filled with zeal.");
    chipotle = new SentenceADTImpl<>("making a pig deal about pig latin");
  }

  @Test
  public void testAddIndexError() {
    SentenceADTImpl<Integer> testErrors = new SentenceADTImpl<>();
    int i = 10;

    while (i >= 0) {
      try {
        testErrors.add(i, i);
      } catch (IllegalArgumentException e) {
        assert true;
      }

      i--;
    }

    assertEquals("0", testErrors.toString());
  }

  @Test
  public void testAddRemove() {
    emptySentence.addBack("test");
    assertEquals("test", emptySentence.toString());

    emptySentence.addBack("sentence");
    assertEquals("test sentence", emptySentence.toString());

    emptySentence.addBack(".");
    assertEquals("test sentence.", emptySentence.toString());

    emptySentence.addFront("a");
    assertEquals("a test sentence.", emptySentence.toString());

    emptySentence.addFront("This");
    assertEquals("This a test sentence.", emptySentence.toString());

    emptySentence.add(1, "is");
    assertEquals("This is a test sentence.", emptySentence.toString());

    emptySentence.remove("sentence");
    assertEquals("This is a test.", emptySentence.toString());

    emptySentence.remove(".");
    assertEquals("This is a test", emptySentence.toString());

    SentenceADTImpl<Integer> numberRemove = new SentenceADTImpl<>(List.of(1, 2, 3, 4));
    numberRemove.remove(1);
    assertEquals("2 3 4", numberRemove.toString());
    numberRemove.remove(3);
    assertEquals("2 4", numberRemove.toString());
    numberRemove.remove(4);
    assertEquals("2", numberRemove.toString());
  }

  @Test
  public void testToString() {
    assertEquals("This is a sentence from a string.", stringSentence.toString());
    assertEquals("Hello! Welcome to our new program.", greeting.toString());
    assertEquals("Am I a sentence made from a list?", listSentence.toString());
    assertEquals("1 2 3 4", numberSentence.toString());
    assertEquals("The zany zebra zombie was filled with zeal.", zSentence.toString());
  }

  @Test
  public void testGetNumberOfWords() {
    assertEquals(7, stringSentence.getNumberOfWords());
    assertEquals(6, greeting.getNumberOfWords());
    assertEquals(8, listSentence.getNumberOfWords());
    assertEquals(4, numberSentence.getNumberOfWords());
    assertEquals(8, zSentence.getNumberOfWords());
  }

  @Test
  public void testGetNumberOfPunctuation() {
    assertEquals(1, stringSentence.getNumberOfPunctuation());
    assertEquals(2, greeting.getNumberOfPunctuation());
    assertEquals(1, listSentence.getNumberOfPunctuation());
    assertEquals(0, numberSentence.getNumberOfPunctuation());
    assertEquals(1, zSentence.getNumberOfPunctuation());
  }

  @Test
  public void testNumberOfWordsWithZ() {
    assertEquals(0, stringSentence.getNumberOfWordsWithZ());
    assertEquals(0, greeting.getNumberOfWordsWithZ());
    assertEquals(0, listSentence.getNumberOfWordsWithZ());
    assertEquals(0, numberSentence.getNumberOfWordsWithZ());
    assertEquals(4, zSentence.getNumberOfWordsWithZ());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThanZero() {
    numberSentence.get(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfRange() {
    numberSentence.get(10);
  }

  @Test
  public void testGet() {

    // pre-declaration of variables required to prevent type confusion during test.
    int firstTest = 1;
    int results = numberSentence.get(0);
    assertEquals(firstTest, results);

    assertEquals("This", stringSentence.get(0));
    assertEquals("is", stringSentence.get(1));
    assertEquals("string", stringSentence.get(6));
    assertEquals(".", stringSentence.get(7));
  }

  @Test
  public void testLongestWord() {
    assertEquals("sentence", stringSentence.longestWord());

    SentenceADT<Integer> doubleDigits = new SentenceADTImpl<>(List.of(1, 2, 3, 4, 12));
    assertEquals("12", doubleDigits.longestWord());

    assertEquals("Welcome", greeting.longestWord());
    assertNotEquals("program", greeting.longestWord());
  }

  @Test
  public void testClone() {
    SentenceADT<Integer> numberClone = numberSentence.clone();
    assertEquals(numberSentence.toString(), numberClone.toString());
    assertNotEquals(numberClone, numberSentence);

    SentenceADT<String> greetingClone = greeting.clone();
    assertEquals(greeting.toString(), greetingClone.toString());
    assertNotEquals(greetingClone, greeting);
  }

  @Test
  public void testMerge() {
    SentenceADT<String> mergedSentence = stringSentence.merge(listSentence);
    assertEquals("This is a sentence from a string. Am I a sentence made from a list?",
        mergedSentence.toString());

    SentenceADT<Integer> mergedInt = numberSentence.merge(
        new SentenceADTImpl<>(List.of(5, 6, 7, 8)));
    assertEquals("1 2 3 4 5 6 7 8", mergedInt.toString());
  }

  @Test
  public void testPigLatin() {
    String pigLatin = "akingmay away igpay ealday aboutway igpay atinlay";
    assertEquals(pigLatin, chipotle.pigLatin());

    SentenceADT<String> pigPunc = chipotle.clone();
    pigPunc.addBack("!");
    pigLatin = "akingmay away igpay ealday aboutway igpay atinlay!";
    assertEquals(pigLatin, pigPunc.pigLatin());

    assertEquals("1 2 3 4", numberSentence.pigLatin());
  }
}

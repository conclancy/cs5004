import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Marble Solitaire model.
 */
public class MarbleSolitaireModelTest {

  private MarbleSolitaireModelImpl three;
  private MarbleSolitaireModelImpl threeOffCenter;
  private MarbleSolitaireModelImpl five;
  private MarbleSolitaireModelImpl threeCustom;

  @Before
  public void init() {
    three = new MarbleSolitaireModelImpl();
    threeOffCenter = new MarbleSolitaireModelImpl(2,2);
    threeCustom = new MarbleSolitaireModelImpl(3,0,4);
    five = new MarbleSolitaireModelImpl(5);
  }

  // Test for arm thickness < 3
  @Test(expected = IllegalArgumentException.class)
  public void smallGame() {
    new MarbleSolitaireModelImpl(1);
  }

  // Test for arm thickness < 0
  @Test(expected = IllegalArgumentException.class)
  public void negGame() {
    new MarbleSolitaireModelImpl(-1);
  }

  // Test for even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void evenGame() {
    new MarbleSolitaireModelImpl(4);
  }

  // Test for zero arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void zeroGame() {
    new MarbleSolitaireModelImpl(0);
  }

  // Test for game without empty space
  @Test(expected = IllegalArgumentException.class)
  public void noEmptyGame() {
    new MarbleSolitaireModelImpl(0,0);
  }

  // Test for game with negative empty space
  @Test(expected = IllegalArgumentException.class)
  public void negEmptyGame() {
    new MarbleSolitaireModelImpl(-1,-1);
  }

  // Test for game with negative empty space
  @Test(expected = IllegalArgumentException.class)
  public void negFiveVoid() {
    new MarbleSolitaireModelImpl(5,0,3);
  }

  @Test
  public void testState() {
    String threeString = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n    "
        + "O O O\n    O O O";
    assertEquals(threeString, three.getGameState());

    String threeOffCenterString = "    O O O\n    O O O\nO O _ O O O O\nO O O O O O O\n"
        + "O O O O O O O\n    O O O\n    O O O";
    assertEquals(threeOffCenterString, threeOffCenter.getGameState());

    String threeCustomString = "    O O _\n    O O O\nO O O O O O O\nO O O O O O O\n"
        + "O O O O O O O\n    O O O\n    O O O";
    assertEquals(threeCustomString, threeCustom.getGameState());

    String fiveString = "        O O O O O\n        O O O O O\n        O O O O O\n"
        + "        O O O O O\nO O O O O O O O O O O O O\nO O O O O _ O O O O O O O"
        + "\nO O O O O O O O O O O O O\nO O O O O O O O O O O O O\nO O O O O O O O O O O O O\n"
        + "        O O O O O\n        O O O O O\n        O O O O O\n        O O O O O";
    assertEquals(fiveString, five.getGameState());
  }

  @Test
  public void testMove() {

    // Vertical Move
    three.move(1,3,3,3);
    String threeString = "    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\nO O O O O O O\n    "
        + "O O O\n    O O O";
    assertEquals(threeString, three.getGameState());

    // Horizontal Move
    three.move(2,1,2,3);
    threeString = "    O O O\n    O _ O\nO _ _ O O O O\nO O O O O O O\nO O O O O O O\n    "
        + "O O O\n    O O O";
    assertEquals(threeString, three.getGameState());
  }

  // Test moving from negative row
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRow() {
    MarbleSolitaireModelImpl genericGame = new MarbleSolitaireModelImpl();
    genericGame.move(-1,0,2,3);
  }

  // Test moving from negative row
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCol() {
    MarbleSolitaireModelImpl genericGame = new MarbleSolitaireModelImpl();
    genericGame.move(1,3,-1,3);
  }


  // Test moving from void space
  @Test(expected = IllegalArgumentException.class)
  public void testVoidSpace() {
    MarbleSolitaireModelImpl genericGame = new MarbleSolitaireModelImpl();
    genericGame.move(0,0,2,3);
  }

  @Test
  public void testIsGameOver() {
    MarbleSolitaireModelImpl genericGame = new MarbleSolitaireModelImpl();
    genericGame.move(3, 1, 3, 3);
    genericGame.move(1, 2, 3, 2);
    genericGame.move(2, 4, 2, 2);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 6, 2, 4);
    genericGame.move(4, 6, 2, 6);
    genericGame.move(4, 5, 2, 5);
    genericGame.move(3, 3, 3, 5);
    genericGame.move(2, 5, 4, 5);
    genericGame.move(1, 4, 3, 4);
    genericGame.move(0, 2, 2, 2);
    genericGame.move(0, 3, 2, 3);
    genericGame.move(2, 2, 2, 4);
    genericGame.move(3, 4, 1, 4);
    genericGame.move(0, 4, 2, 4);
    genericGame.move(5, 2, 3, 2);
    genericGame.move(5, 3, 3, 3);
    genericGame.move(5, 4, 3, 4);
    genericGame.move(2, 0, 2, 2);
    genericGame.move(4, 0, 2, 0);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 4, 4, 4);
    genericGame.move(4, 5, 4, 3);
    genericGame.move(4, 3, 2, 3);

    assertEquals(true, genericGame.isGameOver());
  }

  @Test
  public void testGetScore() {
    assertEquals(32, threeOffCenter.getScore());

    MarbleSolitaireModelImpl genericGame = new MarbleSolitaireModelImpl();
    genericGame.move(3, 1, 3, 3);
    genericGame.move(1, 2, 3, 2);
    genericGame.move(2, 4, 2, 2);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 6, 2, 4);
    genericGame.move(4, 6, 2, 6);
    genericGame.move(4, 5, 2, 5);
    genericGame.move(3, 3, 3, 5);
    genericGame.move(2, 5, 4, 5);
    genericGame.move(1, 4, 3, 4);
    genericGame.move(0, 2, 2, 2);
    genericGame.move(0, 3, 2, 3);
    genericGame.move(2, 2, 2, 4);
    genericGame.move(3, 4, 1, 4);
    genericGame.move(0, 4, 2, 4);
    genericGame.move(5, 2, 3, 2);
    genericGame.move(5, 3, 3, 3);
    genericGame.move(5, 4, 3, 4);
    genericGame.move(2, 0, 2, 2);
    genericGame.move(4, 0, 2, 0);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 4, 4, 4);
    genericGame.move(4, 5, 4, 3);
    genericGame.move(4, 3, 2, 3);

    assertEquals(8, genericGame.getScore());
  }
}

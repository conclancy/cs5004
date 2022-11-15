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
  private MarbleSolitaireModelImpl genericGame;

  @Before
  public void init() {
    three = new MarbleSolitaireModelImpl();
    threeOffCenter = new MarbleSolitaireModelImpl(2,2);
    threeCustom = new MarbleSolitaireModelImpl(3,0,4);
    five = new MarbleSolitaireModelImpl(5);
  }

  @Test
  public void testState() {
    String threeString = "  OOO  \n  OOO  \nOOOOOOO\nOOO_OOO\nOOOOOOO\n  OOO  \n  OOO  ";
    assertEquals(threeString, three.getGameState());

    String threeOffCenterString = "  OOO  \n  OOO  \nOO_OOOO\nOOOOOOO\nOOOOOOO\n  OOO  \n  OOO  ";
    assertEquals(threeOffCenterString, threeOffCenter.getGameState());

    String threeCustomString = "  OO_  \n  OOO  \nOOOOOOO\nOOOOOOO\nOOOOOOO\n  OOO  \n  OOO  ";
    assertEquals(threeCustomString, threeCustom.getGameState());

    String fiveString = "   OOOOO   \n   OOOOO   \n   OOOOO   \nOOOOOOOOOOO\nOOOOOOOOOOO"
        + "\nOOOOO_OOOOO\nOOOOOOOOOOO\nOOOOOOOOOOO\n   OOOOO   \n   OOOOO   \n   OOOOO   ";
    assertEquals(fiveString, five.getGameState());
  }

  @Test
  public void testMove() {
    three.move(1,3,3,3);
    String threeString = "  OOO  \n  O_O  \nOOO_OOO\nOOOOOOO\nOOOOOOO\n  OOO  \n  OOO  ";
    assertEquals(threeString, three.getGameState());
  }

  @Test
  public void testIsGameOver() {
    genericGame = new MarbleSolitaireModelImpl();
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

  }
}

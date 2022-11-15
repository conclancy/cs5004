import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}

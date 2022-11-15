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
    five = new MarbleSolitaireModelImpl(5);
    threeCustom = new MarbleSolitaireModelImpl(3,0,4);
  }

  @Test
  public void firstTest() {

  }

}

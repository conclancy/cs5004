import cs5004.tictactoeLab6.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test to ensure the {@link Player} {@code toString} returns the proper String values.
 */
public class PlayerTest6 {

  @Test
  public void testX() {
    assertEquals("X", Player.X.toString());
  }

  @Test
  public void testY() {
    assertEquals("O", Player.O.toString());
  }

}

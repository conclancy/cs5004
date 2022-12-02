import cs5004.tictactoe.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test to ensure the {@link Player} {@code toString} returns the proper String values.
 */
public class PlayerTest {

  @Test
  public void testX() {
    assertEquals("X", Player.X.toString());
  }

  @Test
  public void testY() {
    assertEquals("O", Player.O.toString());
  }

}

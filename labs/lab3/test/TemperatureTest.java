import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature cTemp;
  private Temperature fTemp;

  @Before
  public void init() {
    cTemp = new CelsiusTemperature(100);
    fTemp = new FahrenheitTemperature(100, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp() {
    new FahrenheitTemperature(-1000);
  }

  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(212, cTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp.inKelvin(), 0.001);
  }

  @Test
  public void testInF() {
    assertEquals(212, fTemp.inFahrenheit(), 0.001);
  }

  @Test
  public void testAdd() {
    // test your add() method here according to its specification
  }

  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("212.0° Fahrenheit", fTemp.toString());
  }
}
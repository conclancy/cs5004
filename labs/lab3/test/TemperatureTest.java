import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature cTemp;
  private Temperature fTemp;

  /**
   * Set up test instances.
   */
  @Before
  public void init() {
    cTemp = new CelsiusTemperature(100);
    fTemp = new FahrenheitTemperature(100, true);
  }

  /**
   * Test to make sure an illegal argument is thrown if the temperature is below absolute zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp() {
    new FahrenheitTemperature(-1000);
  }

  /**
   * Test to make sure an illegal argument is thrown if the temperature is below absolute zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidCTemp() {
    new CelsiusTemperature(-1000);
  }

  /**
   * Test to make sure conversions are happening correctly.
   */
  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(212, cTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp.inKelvin(), 0.001);
  }

  /**
   * Test the inFahrenheit is working.
   */
  @Test
  public void testInF() {
    assertEquals(212, fTemp.inFahrenheit(), 0.001);
  }

  /**
   * Test the ability to add temperatures together.
   */
  @Test
  public void testAdd() {
    CelsiusTemperature newTemp = (CelsiusTemperature) cTemp.add(fTemp);
    assertEquals(200, newTemp.inCelsius(), .01);
    assertEquals(392, newTemp.inFahrenheit(), .01);
  }

  /**
   * Test the toString functionality.
   */
  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("212.0° Fahrenheit", fTemp.toString());
  }

  /**
   * Test compareTo method from abstract class.
   */
  @Test
  public void testCompareTo() {
    // Test equality
    assertEquals(0, cTemp.compareTo(fTemp));
    assertEquals(0, fTemp.compareTo(cTemp));

    // Test non-equal temps
    Temperature smallTemp =  new CelsiusTemperature(-273);
    assertEquals(1, cTemp.compareTo(smallTemp));
    assertEquals(-1, smallTemp.compareTo(cTemp));
  }
}
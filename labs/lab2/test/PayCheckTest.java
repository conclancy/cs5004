import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the PayCheck class.
 */
public class PayCheckTest {

  private PayCheck pc20;
  private PayCheck pc40;
  private PayCheck pcOvertime;

  /**
   * Set up the objects for testing.
   */
  @Before
  public void setUp() {
    pc20 = new PayCheck("Test20", 10, 20);
    pc40 = new PayCheck("Test40", 18.5, 40);
    pcOvertime = new PayCheck("Overtime", 20.3, 50);
  }

  /**
   * Test the getTotalPay method.
   */
  @Test
  public void testGetTotalPay() {
    assertEquals(200.0, pc20.getTotalPay(), .001);
    assertEquals(740, pc40.getTotalPay(), .001);
    assertEquals(1116.5, pcOvertime.getTotalPay(), .001);
  }

  /**
   * Test the toString method.
   */
  @Test
  public void testToString() {
    assertEquals("$200.00", pc20.toString());
    assertEquals("$740.00", pc40.toString());
    assertEquals("$1116.50", pcOvertime.toString());
  }

  /**
   * Test the getNames method.
   */
  @Test
  public void testGetName() {
    assertEquals("Test20", pc20.getName());
    assertEquals("Test40", pc40.getName());
    assertEquals("Overtime", pcOvertime.getName());
  }

  /**
   * Test the getRate method.
   */
  @Test
  public void testGetRate() {
    assertEquals(10.0, pc20.getRate(), .01);
    assertEquals(18.5, pc40.getRate(), .01);
    assertEquals(20.3, pcOvertime.getRate(), .01);
  }

  /**
   * Test the getHoursWorked method.
   */
  @Test
  public void testGetHoursWorked() {
    assertEquals(20.0, pc20.getHoursWorked(), .01);
    assertEquals(40.0, pc40.getHoursWorked(), .01);
    assertEquals(50.0, pcOvertime.getHoursWorked(), .01);
  }
}

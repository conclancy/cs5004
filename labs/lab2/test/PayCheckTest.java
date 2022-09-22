import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PayCheckTest {

  private PayCheck pc20;
  private PayCheck pc40;
  private PayCheck pcOvertime;

  @Before
  public void setUp() {
    pc20 = new PayCheck("Test20", 10, 20);
    pc40 = new PayCheck("Test40", 18.5, 40);
    pcOvertime = new PayCheck("Overtime", 20.3, 50);
  }

  @Test
  public void testGetTotalPay() {
    assertEquals(200.0, pc20.getTotalPay(), .001);
    assertEquals(740, pc40.getTotalPay(), .001);
    assertEquals(1116.5, pcOvertime.getTotalPay(), .001);
  }

  @Test
  public void testToString() {
    assertEquals("$200.00", pc20.toString());
    assertEquals("$740.00", pc40.toString());
    assertEquals("$1116.50", pcOvertime.toString());
  }
}

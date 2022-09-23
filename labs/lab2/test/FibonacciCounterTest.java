import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the FibonacciCounter class
 */
public class FibonacciCounterTest {

  private FibonacciCounter fib0;
  private FibonacciCounter fib1;
  private FibonacciCounter fib2;
  private FibonacciCounter fib3;
  private FibonacciCounter fib22;
  private FibonacciCounter fibNeg1;

  @Before
  public void setUp() {
    fib0 = new FibonacciCounter(0);
    fib1 = new FibonacciCounter(1);
    fib2 = new FibonacciCounter(2);
    fib3 = new FibonacciCounter(3);
    fib22 = new FibonacciCounter(22);
    fibNeg1 = new FibonacciCounter(-1);
  }

  @Test
  public void testGetCount() {
    assertEquals(0, fib0.getCount());
    assertEquals(1, fib1.getCount());
    assertEquals(2, fib2.getCount());
    assertEquals(22, fib22.getCount());
    assertEquals(-1, fibNeg1.getCount());
  }

  @Test
  public void testGetFibonacciNumber() {
    // https://www.goldennumber.net/fibonacci-series/

    assertEquals(0, fib0.getFibonacciNumber());
    assertEquals(1, fib1.getFibonacciNumber());
    assertEquals(1, fib2.getFibonacciNumber());
    assertEquals(2, fib3.getFibonacciNumber());
    assertEquals(17711, fib22.getFibonacciNumber());
    assertEquals(0, fibNeg1.getFibonacciNumber());
  }

  @Test
  public void testIncrement() {
    // todo
  }

  @Test
  public void testDecrement() {
    // todo
  }
}

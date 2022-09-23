/**
 * This class can store and represent an element of the fibonacci sequence.
 */
public class FibonacciCounter {

  private int count;
  private int value;

  /**
   * Class constructor.
   *
   * @param count the sequence number to initiate at
   */
  public FibonacciCounter(int count) {
    this.count = count;
    this.value = fibonacciCounter(count);
  }

  private int fibonacciCounter(int count) {
    if (count < 1) {
      return 0;
    } else if (count == 1) {
      return 1;
    } else {
      double firstValue = Math.pow(((1 + Math.sqrt(5)) / 2), count);
      double secondValue = Math.pow(((1 - Math.sqrt(5)) / 2), count);
      double mean = firstValue - secondValue;
      return (int) ((1 / (Math.sqrt(5))) * mean);
    }
  }

  /**
   * Increment to the next number in the sequence and return itself.
   *
   * @return incremented FibonacciCounter object.
   */
  public FibonacciCounter increment() {
    this.count++;
    this.value = fibonacciCounter(this.count);
    return this;
  }

  /**
   * Decrement to the preceding number in the sequence and return itself.
   *
   * @return decremented FibonacciCounter object.
   */
  public FibonacciCounter decrement() {
    if (count > 0) {
      this.count--;
      this.value = fibonacciCounter(this.count);
    }
    return this;
  }

  /**
   * Returns the current count of the sequence.
   *
   * @return value of the counter.
   */
  public int getCount() {
    return this.count;
  }

  /**
   * Returns the current value of the current Fibonacci Number.
   *
   * @return int value of the Fibonacci number.
   */
  public int getFibonacciNumber() {
    return this.value;
  }
}

/**
 * Abstract class representing the temperature implementation.
 */
public abstract class AbstractTemperature implements Temperature {

  protected double temperature;

  /**
   * Ensures the temperature is not below absolute zero degrees celsius.
   *
   * @throws IllegalArgumentException if the temp is below absolute zero
   */
  protected void isValidTemperature() {
    if (this.inKelvin() < 0) {
      throw new IllegalArgumentException("Invalid temperature");
    }
  }

  /**
   * Returns the temperature in degrees Kelvin.
   *
   * @return temperature in degrees Kelvin
   */
  public double inKelvin() {
    return this.inCelsius() - ABS_ZERO_C;
  }

  /**
   * Compare two temperature objects.
   *
   * @param other temperature to compare to.
   * @return comparison evaluation of the two objects.
   */
  @Override
  public int compareTo(Temperature other) {
    double tempKThis = this.inKelvin();
    double tempKOther = other.inKelvin();

    if (tempKThis < tempKOther) {
      return -1;
    } else if (tempKOther < tempKThis) {
      return 1;
    } else {
      return 0;
    }
  }
}

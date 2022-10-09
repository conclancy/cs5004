/**
 * A class representing a fahrenheit temperature.
 */
public class FahrenheitTemperature extends AbstractTemperature {

  /**
   * Constructor for the FahrenheitTemperature class.
   *
   * @param temperature in degrees Fahrenheit
   */
  public FahrenheitTemperature(double temperature) {
    this.temperature = temperature;
    this.isValidTemperature();
  }

  /**
   * Constructor for the FahrenheitTemperature class.
   *
   * @param temperature in degrees Fahrenheit
   * @param isCelsius must always be 'true' or the temperature will be treated as Fahrenheit
   */
  public FahrenheitTemperature(double temperature, boolean isCelsius) {
    if (isCelsius) {
      CelsiusTemperature temp = new CelsiusTemperature(temperature);
      this.temperature = temp.inFahrenheit();
    } else {
      this.temperature = temperature;
    }
  }

  /**
   * Returns the string representation of the temperature.
   *
   * @return string representation of the temperature
   */
  @Override
  public String toString() {
    return String.format("%.1f", this.temperature) + "° Fahrenheit";
  }

  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */
  @Override
  public double inCelsius() {
    return ((this.temperature - 32) * (5.0 / 9.0));
  }

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  @Override
  public double inFahrenheit() {
    return this.temperature;
  }

  /**
   * Add two temperatures together and return the resulting temperature.
   *
   * @param t other temperature to be added.
   * @return the new temperature
   */
  @Override
  public Temperature add(Temperature t) {
    return new FahrenheitTemperature(this.temperature + t.inFahrenheit());
  }
}

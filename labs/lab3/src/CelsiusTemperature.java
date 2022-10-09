/**
 * A class representing a celsius temperature.
 */
public class CelsiusTemperature extends AbstractTemperature {

  /**
   * Constructor for the CelsiusTemperature class.
   *
   * @param temperature in degrees Celsius
   */
  public CelsiusTemperature(double temperature) {
    this.temperature = temperature;
    this.isValidTemperature();
  }

  /**
   * Constructor for the CelsiusTemperature class that takes in a Fahrenheit value.
   *
   * @param temperature  in degrees Fahrenheit
   * @param isFahrenheit must always be 'true' or the temperature will be treated as celsius
   */
  public CelsiusTemperature(double temperature, boolean isFahrenheit) {
    if (isFahrenheit) {
      FahrenheitTemperature temp = new FahrenheitTemperature(temperature);
      this.temperature = temp.inCelsius();
    }
    this.temperature = temperature;
    this.isValidTemperature();
  }

  /**
   * Returns the string representation of the temperature.
   *
   * @return string representation of the temperature
   */
  @Override
  public String toString() {
    return String.format("%.1f", this.temperature) + "° Celsius";
  }

  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */
  @Override
  public double inCelsius() {
    return this.temperature;
  }

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  @Override
  public double inFahrenheit() {
    return (this.temperature * (9.0 / 5.0)) + 32;
  }

  /**
   * The temperature in degrees Kelvin.
   *
   * @return the temperature in degrees Kelvin
   */
  @Override
  public double inKelvin() {
    return this.temperature - ABS_ZERO_C;
  }

  /**
   * Add two temperatures together and return the resulting temperature.
   *
   * @param t instance of temperature
   * @return the new temperature
   */
  @Override
  public Temperature add(Temperature t) {
    return new CelsiusTemperature(this.temperature + t.inCelsius());
  }
}

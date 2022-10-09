public class FahrenheitTemperature implements Temperature{
  private double temperature;

  public FahrenheitTemperature(double temperature) {
    this.temperature = temperature;
    Temperature.isValidTemperature(this);
  }

  public FahrenheitTemperature(double temperature, boolean isCelsius) {
    if(isCelsius) {
      CelsiusTemperature temp = new CelsiusTemperature(temperature);
      this.temperature = temp.inFahrenheit();
    }
    else{
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
    return ((this.temperature - 32) * (5.0/9.0));
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
   * The temperature in degrees Kelvin.
   *
   * @return the temperature in degrees Kelvin
   */
  @Override
  public double inKelvin() {
    CelsiusTemperature celsius = new CelsiusTemperature(this.inCelsius());
    return celsius.inKelvin();
  }

  /**
   * Add two temperatures together and return the resulting temperature.
   *
   * @param t
   * @return the new temperature
   */
  @Override
  public Temperature add(Temperature t) {
    return new FahrenheitTemperature(this.temperature + t.inFahrenheit());
  }

  /**
   * @param temperature
   * @return
   */
  @Override
  public int compareTo(Temperature temperature) {
    return 0;
  }
}

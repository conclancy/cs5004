public class Term {

  private int power;
  private int coefficient;

  public Term(int power, int coefficient) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power must not be negative.");
    }

    this.power = power;
    this.coefficient = coefficient;
  }

  public Term() {
  }

  public int getPower() {
    return this.power;
  }

  public int getCoefficient() {
    return this.coefficient;
  }

  public double evaluate(double x) {
    return Math.pow(x, this.power) * coefficient;
  }

  @Override
  public String toString() {
    if (this.coefficient == 0 && this.power == 0) {
      return "0";
    } else {
      return String.format("%dx^%d", this.coefficient, this.power);
    }
  }
}
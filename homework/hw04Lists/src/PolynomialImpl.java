public class PolynomialImpl implements Polynomial {

  /**
   * Adds a new term to the polynomial.
   *
   * @param coefficient The coefficient of the new term, as an int.
   * @param power       The power of the new term, as an int.
   * @return A new polynomial object.
   * @throws IllegalArgumentException Thrown if the power passed is less than 0.
   */
  @Override
  public Polynomial addTerm(int coefficient, int power) throws IllegalArgumentException {
    return null;
  }

  /**
   * Remove any and all terms in the polynomial with the passed power.
   *
   * @param power The power of the term(s) to be removed, as an int.
   * @return A new polynomial without a given term.
   */
  @Override
  public Polynomial removeTerm(int power) {
    return null;
  }

  /**
   * Get the degree of the polynomial.
   *
   * @return The degree of the polynomial, as an int.
   */
  @Override
  public int getDegree() {
    return 0;
  }

  /**
   * Get the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  /**
   * Evaluate the value of the polynomial given an input value, x.
   *
   * @param x The value to evaluate the polynomial to, as a double.
   * @return The value of the evaluated polynomial, as a double.
   */
  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    return null;
  }
}

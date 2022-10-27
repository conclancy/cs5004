/**
 * This interface represents a polynomial.
 */
public interface Polynomial {

  /**
   * Adds a new term to the polynomial.
   *
   * @param coefficient The coefficient of the new term, as an int.
   * @param power The power of the new term, as an int.
   * @return A new polynomial object.
   * @throws IllegalArgumentException Thrown if the power passed is less than 0.
   */
  Polynomial addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Remove any and all terms in the polynomial with the passed power.
   *
   * @param power The power of the term(s) to be removed, as an int.
   * @return A new polynomial without a given term.
   */
  Polynomial removeTerm(int power);

  /**
   * Get the degree of the polynomial.
   *
   * @return The degree of the polynomial, as an int.
   */
  int getDegree();

  /**
   * Get the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  int getCoefficient(int power);

  /**
   * Evaluate the value of the polynomial given an input value, x.
   *
   * @param x The value to evaluate the polynomial to, as a double.
   * @return The value of the evaluated polynomial, as a double.
   */
  double evaluate(double x);
  Polynomial add(Polynomial other) throws IllegalArgumentException;


}

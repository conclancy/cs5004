import java.util.Objects;

/**
 * Implementation of the polynomial class.
 */
public class PolynomialImpl implements Polynomial {

  private Node poly;

  /**
   * Constructor for the PolynomialImpl class that takes in a power and a coefficient.
   *
   * @param power       The power of the polynomial, as an int.
   * @param coefficient The coefficient of the polynomial, as an int.
   * @throws IllegalArgumentException Powers not be negative.
   */
  public PolynomialImpl(int power, int coefficient) throws IllegalArgumentException {
    try {
      if (coefficient == 0) {
        this.poly = new EmptyTermNode();
      }
      this.poly = new TermNode(power, coefficient, new EmptyTermNode());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }

  }

  /**
   * Instantiates an empty polynomial that evaluates to 0.
   */
  public PolynomialImpl() {
    this.poly = new EmptyTermNode();

  }

  /**
   * Create a new polynomial from a string.
   *
   * @param stringPolynomial the string representation of a polynomial, terms seperated by space.
   * @throws IllegalArgumentException if the format is not correct (e.g. 3x^2)
   */
  public PolynomialImpl(String stringPolynomial) throws IllegalArgumentException {
    String[] termList = stringPolynomial.split(" ");
    this.poly = new EmptyTermNode();
    int tempCoefficient;
    int tempPower;
    int i;


    for (i = 0; i < termList.length; i++) {
      String[] currentTerm = termList[i].split("x\\^");

      if (currentTerm.length > 2) {
        throw new IllegalArgumentException("Polynomial format is incorrect");
      } else if (currentTerm.length == 1) {
        tempPower = 0;
        tempCoefficient = Integer.parseInt(currentTerm[0]);
      } else {
        tempPower = Integer.parseInt(currentTerm[1]);
        tempCoefficient = Integer.parseInt(currentTerm[0]);
      }

      this.addTerm(tempCoefficient, tempPower);
    }
  }

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
    this.poly = this.poly.addNode(new TermNode(power, coefficient));
    return this;
  }

  /**
   * Remove any and all terms in the polynomial with the passed power.
   *
   * @param power The power of the term(s) to be removed, as an int.
   * @return A new polynomial without a given term.
   */
  @Override
  public Polynomial removeTerm(int power) {
    this.poly = this.poly.removeNode(power);
    return this;
  }

  /**
   * Get the degree of the polynomial.
   *
   * @return The degree of the polynomial, as an int.
   */
  @Override
  public int getDegree() {
    return this.poly.getDegree();
  }

  /**
   * Get the coefficient for the term with to given power.
   *
   * @param power The power of the desired coefficient, as an int.
   * @return The coefficient, as an int
   */
  @Override
  public int getCoefficient(int power) {
    return this.poly.findCoefficient(power);
  }

  /**
   * Evaluate the value of the polynomial given an input value, x.
   *
   * @param x The value to evaluate the polynomial to, as a double.
   * @return The value of the evaluated polynomial, as a double.
   */
  @Override
  public double evaluate(double x) {
    return this.poly.evaluate(x);
  }

  /**
   * Combine two polynomials into a single mathematical expression.
   *
   * @param other The other polynomial.
   * @return A new polynomial which is a combination of the two inputs.
   * @throws IllegalArgumentException if the object passed is not of type polynomial.
   */
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    Polynomial combined = this;
    int currentCoefficient;
    int currentPower;

    // if other isn't polynomial throw error
    while(!Objects.isNull(other)) {
      currentPower = other.getDegree();
      currentCoefficient = other.getCoefficient(currentPower);

      combined.addTerm(currentCoefficient, currentPower);
      other.removeTerm(currentPower);
    }

    return combined;

  }

  /**
   * Return a string interpolation of the node.
   *
   * @return The node in string form.
   */
  @Override
  public String toString() {
    return poly.toString();
  }
}

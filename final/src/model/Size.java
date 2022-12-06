package model;

import java.util.Objects;

public class Size {

  private final double dimensionOne;
  private final double dimensionTwo;

  /**
   * Constructor for the Size class.  Used to resize {@link IShape} objects. This constructor takes
   * two arguments for shapes that have different size proportions (ovals, rectangles, etc.).
   *
   * @param dimensionOne the first dimension to be stored, as a double.
   * @param dimensionTwo the second dimension to be stored, as a double.
   */
  public Size(double dimensionOne, double dimensionTwo) {
    this.dimensionOne = this.validateDimension(dimensionOne);
    this.dimensionTwo = this.validateDimension(dimensionTwo);
  }

  /**
   * Constructor for the Size class.  Used to resize {@link IShape} objects. This constructor takes
   * one argument for shapes that have matching size proportions (circles, squares, etc.).
   *
   * @param dimension the shape's dimension, as a double.
   */
  public Size(double dimension) {
    this.dimensionOne = this.validateDimension(dimension);
    this.dimensionTwo = dimension;
  }

  /**
   * Get the first dimension.
   *
   * @return the first dimension, as a double.
   */
  public double getDimensionOne() {
    return this.dimensionOne;
  }

  /**
   * Get the second dimension.
   *
   * @return the second dimension, as a double.
   */
  public double getDimensionTwo() {
    return this.dimensionTwo;
  }

  /**
   * Get the dimension if both dimensions are identical.
   *
   * @return the dimension of the shape, as a double.
   * @throws IllegalStateException if the two dimensions stored within the size are not equal.
   */
  public double getDimension() throws IllegalStateException {
    if (this.dimensionOne != this.dimensionTwo) {
      throw new IllegalStateException(
          "This size instance has two different dimensions.  Use the getDimensionOne and "
              + "getDimensionTwo methods to access the dimensions.");
    }

    return this.dimensionOne;
  }

  /**
   * Validate that a valid dimension is being passed to the object.
   *
   * @param dimension the dimension, as a double.
   * @return the valid dimension, as a double.
   * @throws IllegalArgumentException if the dimension is equal to or less than 0.
   */
  private double validateDimension(double dimension) throws IllegalArgumentException {
    if (dimension <= 0) {
      throw new IllegalArgumentException("Dimensions must be greater than 0.");
    }

    return dimension;
  }

  /**
   * Determines if two Size objects are logically equal.
   *
   * @param other the other object to test.
   * @return boolean if the objects are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Size)) {
      return false;
    }

    Size otherSize = (Size) other;

    return this.getDimensionOne() == otherSize.getDimensionOne()
        && this.getDimensionTwo() == otherSize.getDimensionTwo();
  }

  /**
   * Hash code for the Size object.
   *
   * @return the dimensions of the Size object, as a hashCode integer.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.dimensionOne, ",", this.dimensionTwo);
  }

  /**
   * Create a toString method that returns the dimensions of the object at its new size.
   *
   * @return dimensions of the new object, as a floating point string with two decimal places.
   */
  @Override
  public String toString() {
    return String.format("(dimension one: %.2f, dimension two: %.2f)", dimensionOne, dimensionTwo);
  }

}

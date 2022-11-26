package model;

import java.util.Objects;

/**
 * This class represents a 2D point. This point is denoted in Cartesian coordinates as (x,y).
 */
public class Point2D {

  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Compute and return the Euclidean distance of this point to the origin
   *
   * @return the euclidean distance
   */
  public double distToOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Return the x-coordinate of this point
   *
   * @return x-coordinate of this point
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y-coordinate of this point
   *
   * @return y-coordinate of this point
   */
  public double getY() {
    return y;
  }

  /**
   * Determines if two Point2D objects are equal to one another.
   *
   * @param other the other object to be compared.
   * @return true if the objects are the same
   */
  @Override
  public boolean equals(Object other) {
    if (this==other) {
      return true;
    }
    if (!(other instanceof Point2D other2D)) {
      return false; //other cannot be equal to this as it is not a Person object!
    }

    return this.getX() == other2D.getX()
        && this.getY() == other2D.getY();
  }

  /**
   * Create a hash table of a Point2D object.
   *
   * @return Point2D hash table.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, ",", this.y);
  }
}
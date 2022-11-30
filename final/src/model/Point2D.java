package model;

import java.util.Objects;

/**
 * This class represents a 2D point. This point is denoted in Cartesian coordinates as (x,y).
 */
public class Point2D {

  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Compute and return the Euclidean distance of this point to the origin.
   *
   * @return the euclidean distance
   */
  public double distToOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return x-coordinate of this point.
   */
  public double getX() {
    return x;
  }

  /**
   * Setter method to change the Point's x coordinate.
   *
   * @param x the horizontal coordinate of the point, as an int.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return y-coordinate of this point.
   */
  public double getY() {
    return y;
  }

  /**
   * Setter method to change the Point's y coordinate.
   *
   * @param y the vertical coordinate of the point, as an int.
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Determines if two Point2D objects are equal to one another.
   *
   * @param other the other object to be compared.
   * @return true if the objects are the same.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Point2D)) {
      return false;
    }

    Point2D other2D = (Point2D) other;

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
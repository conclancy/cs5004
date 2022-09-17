import java.lang.Math;

/**
 * This class represents a 3D vector with an x, y, and z coordinate.
 */
public class Vector3D {
  private double x;
  private double y;
  private double z;

  /**
   * Constructs a Vector3D object and initializes the x, y, and z coordinates.
   * @param x the x coordinate of the vector.
   * @param y the y coordinate of the vector.
   * @param z the z coordinate of the vector,
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Getter method for the x coordinate.
   * @return the value of the x coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Getter method for the y coordinate.
   * @return the value of the y coordinate.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Getter method for the z coordinate.
   * @return the value of the z coordinate.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Returns the coordinates of the vector as a string.
   * @return (x,y,z) coordinates of the vector.
   */
  public String toString() {
    String xString = String.format("%.2f", this.x);
    String yString = String.format("%.2f", this.y);
    String zString = String.format("%.2f", this.z);

    return "(" + xString + "," + yString + "," + zString + ")";
  }

  /**
   * Returns the magnitude of the vector.
   * @return magnitude of the vector.
   */
  public double getMagnitude() {

    double xSquare = this.x * this.x;
    double ySquare = this.y * this.y;
    double zSquare = this.z * this.z;

    double magnitude = Math.sqrt(xSquare + ySquare + zSquare);

    return magnitude;
  }

  public Vector3D normalize() throws IllegalStateException {

    double magnitude = this.getMagnitude();

    if (magnitude == 0.0) {
      throw new IllegalStateException("Vector with a magnitude of 0 cannot be normalized");
    }

    double normalX = (x / magnitude);
    double normalY = (y / magnitude);
    double normalZ = (z / magnitude);

    return new Vector3D(normalX, normalY, normalZ);
  }

}

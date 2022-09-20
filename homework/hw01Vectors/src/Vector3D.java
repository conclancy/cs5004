/**
 * This class represents a 3D vector with an x, y, and z coordinate.
 */
public class Vector3D {

  private double x;
  private double y;
  private double z;

  /**
   * Constructs a Vector3D object and initializes the x, y, and z coordinates.
   *
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
   *
   * @return the value of the x coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Getter method for the y coordinate.
   *
   * @return the value of the y coordinate.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Getter method for the z coordinate.
   *
   * @return the value of the z coordinate.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Returns the coordinates of the vector as a string.
   *
   * @return (x, y, z) coordinates of the vector.
   */
  public String toString() {
    String xString = String.format("%.2f", this.x);
    String yString = String.format("%.2f", this.y);
    String zString = String.format("%.2f", this.z);

    return "(" + xString + "," + yString + "," + zString + ")";
  }

  /**
   * Returns the magnitude of the vector.
   *
   * @return magnitude of the vector.
   */
  public double getMagnitude() {

    double xSquare = this.x * this.x;
    double ySquare = this.y * this.y;
    double zSquare = this.z * this.z;

    double magnitude = Math.sqrt(xSquare + ySquare + zSquare);

    return magnitude;
  }

  /**
   * Normalizes the vector so that all dimensions equal one when summed.
   *
   * @return normalized vector.
   * @throws IllegalStateException when the vector has a magnitude of 0.
   */
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

  /**
   * Add two vectors together.
   *
   * @param other vector to be added.
   * @return the summation of the two vectors.
   */
  public Vector3D add(Vector3D other) {
    return new Vector3D(
        this.getX() + other.getX(),
        this.getY() + other.getY(),
        this.getZ() + other.getZ()
    );
  }

  /**
   * Multiply two vectors.
   *
   * @param other vector to be multiplied.
   * @return the product of the two vectors.
   */
  public Vector3D multiply(Vector3D other) {
    return new Vector3D(
        this.getX() * other.getX(),
        this.getY() * other.getY(),
        this.getZ() * other.getZ()
    );
  }

  /**
   * Calculate the dot products of the two vectors.
   *
   * @param other vector for calculation
   * @return dot product of the two vectors
   */
  public double dotProduct(Vector3D other) {
    double dotX = this.getX() * other.getX();
    double dotY = this.getY() * other.getY();
    double dotZ = this.getZ() * other.getZ();

    return dotX + dotY + dotZ;
  }

  /**
   * Calculate the angle between two vectors.
   *
   * @param other vector for calculation.
   * @return the angle in degrees
   * @throws IllegalStateException when either magnitude is 0.
   */
  public double angleBetween(Vector3D other) throws IllegalStateException {

    if (this.getMagnitude() == 0.0 || other.getMagnitude() == 0.0) {
      throw new IllegalStateException("Vectors with a magnitude of 0 do not have an angle.");
    }

    double theta = this.dotProduct(other) / (this.getMagnitude() * other.getMagnitude());
    return Math.toDegrees(Math.acos(theta));
  }
}

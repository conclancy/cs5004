package cs5004.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * This class represents an Ellipse shape. Ellipses care shapes that can be traced by a point moving
 * in a plane so that the sum of its distances from two other points (the foci) is constant, or
 * resulting when a cone is cut by an oblique plane which does not intersect the base
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructs an Ellipse with data inputs for all Shape fields.
   */
  public Ellipse(int width, int height, Point2D coordinatePosition,
      int rotationDegree, Color color, String name) {
    super(width, height, coordinatePosition, rotationDegree, color, name);
    super.shapeType = "Ellipse";
  }

  /**
   * Constructor for an Ellipse, that creates an "empty" shape at (0,0) with no height, width, or
   * color.
   */
  public Ellipse(String name) {
    super(name);
    this.shapeType = "Ellipse";
  }

  /**
   * Allows for the conversion of the Ellipse to a {@link Graphics2D} object for display on a GUI.
   *
   * @param g the graphics variable to display the shape, as a {@link Graphics2D} .
   */
  @Override
  public void draw(Graphics2D g) {
    if (this.degrees == 0) {
      g.fillOval((int) this.reference.getX(), (int) this.reference.getY(),
          this.width, this.height);
    } else {
      Graphics2D gg = (Graphics2D) g.create();
      gg.rotate(Math.toRadians(this.degrees), this.reference.getX()
              + ((double) this.width) / 2,
          this.reference.getY() + ((double) this.height) / 2);
      gg.fillOval((int) this.reference.getX(), (int) this.reference.getY(),
          this.width, this.height);
      gg.dispose();
    }
  }

  /**
   * Get a deep copy of the Ellipse.  This allows access to a shape without risking the changing of
   * the underlying shape attributes.
   *
   * @return an exact replica of the Ellipse, as a new {@link Ellipse} object.
   */
  @Override
  public IShape getCopy() {
    return new Ellipse(super.width, super.height,
        new Point2D.Double(super.reference.getX(), super.reference.getY()),
        super.degrees, new Color(super.color.getRGB()), super.name);
  }
}

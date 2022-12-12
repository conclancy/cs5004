package cs5004.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/**
 * This class represents a rectangle shape.  Rectangles have four sides that meet at four 90 degree
 * angles.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs a rectangle with data inputs for all Shape fields.
   */
  public Rectangle(int width, int height, Point2D reference, int degrees, Color color,
      String name) {
    super(width, height, reference, degrees, color, name);
    this.shapeType = "Rectangle";
  }

  /**
   * Constructor for a Rectangle, that creates an "empty" shape at (0,0) with no height, width, or
   * color.
   */
  public Rectangle(String name) {
    super(name);
    this.shapeType = "Rectangle";
  }

  /**
   * Allows for the conversion of the Rectangle to a {@link Graphics2D} object for display on a
   * GUI.
   *
   * @param g the graphics variable to display the shape, as a {@link Graphics2D} .
   */
  @Override
  public void draw(Graphics2D g) {
    if (this.degrees == 0) {
      g.fillRect((int) this.reference.getX(), (int) this.reference.getY(),
          this.width, this.height);
    } else {
      Graphics2D gg = (Graphics2D) g.create();
      gg.rotate(Math.toRadians(this.degrees), this.reference.getX()
              + ((double) this.width) / 2,
          this.reference.getY() + ((double) this.height) / 2);
      gg.fillRect((int) this.reference.getX(), (int) this.reference.getY(),
          this.width, this.height);
      gg.dispose();
    }
  }

  /**
   * Get a deep copy of the Rectangle.  This allows access to a shape without risking the changing
   * of the underlying shape attributes.
   *
   * @return an exact replica of the Rectangle, as a new {@link Rectangle} object.
   */
  @Override
  public IShape getCopy() {
    return new Rectangle(super.width, super.height,
        new Point2D.Double(super.reference.getX(), super.reference.getY()),
        super.degrees, new Color(super.color.getRGB()), super.name);
  }
}
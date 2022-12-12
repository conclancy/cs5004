package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.awt.Graphics2D;
import java.awt.Graphics;

import java.util.List;
import javax.swing.JPanel;

/**
 * Class represents the panel for drawing the animation using the draw() method.
 */
public class PaintPanel extends JPanel implements IPaintPanel {

  private List<IShape> shapes;

  /**
   * This constructor creates a drawing panel for all calls.
   */
  public PaintPanel() {
    this.shapes = null;
  }

  /**
   * Paints a {@link Graphics} component on the {@link JPanel}.
   *
   * @param g the {@link Graphics} object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.shapes != null) {
      this.paintComponentHelper(g);
    }
  }

  /**
   * Method that paints the list of shapes onto the screen.
   *
   * @param shapes the shapes to be painted on to the screen, passed as a List.
   */
  @Override
  public void paint(List<IShape> shapes) {
    this.shapes = shapes;
    this.repaint();
  }

  /**
   * Sets the color of a shape and draws it on the board.
   *
   * @param g the {@link Graphics} object to drawn.
   */
  private void paintComponentHelper(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    for ( IShape shape : this.shapes ) {
      g2D.setColor(shape.getColor());
      shape.draw(g2D);
    }
  }
}

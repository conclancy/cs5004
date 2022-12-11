package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.awt.Graphics2D;
import java.awt.Graphics;

import java.util.List;
import javax.swing.JPanel;

/**
 * This class represents an interface for a drawing panel for the GUI view.
 */
public class PaintPanel extends JPanel implements IPaintPanel {

  private List<IShape> shapes;

  /**
   * This constructor creates a drawing panel for all calls.
   */
  public PaintPanel() {
    this.shapes = null;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.shapes != null) {
      this.drawShapesOn(g);
    }
  }

  @Override
  public void paint(List<IShape> shapes) {
    this.shapes = shapes;
    this.repaint();
  }

  private void drawShapesOn(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    for ( IShape shape : this.shapes ) {
      g2D.setColor(shape.getColor());
      shape.draw(g2D);
    }
  }
}

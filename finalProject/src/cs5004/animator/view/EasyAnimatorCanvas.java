package cs5004.animator.view;

import cs5004.animator.controller.IController;
import cs5004.animator.model.IShape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class represents a Canvas for painting and displaying objects for the ViewGUI's animation.
 * This class can convert {@link IShape} objects into awt {@link Graphics} objects for animation
 * generation.
 */
public class EasyAnimatorCanvas extends JPanel implements ActionListener {

  IController controller;
  Timer timer;
  int time;
  int delay;
  int timeDelta;

  public EasyAnimatorCanvas(IController controller, int x, int y, int width, int height,
      int speed) {
    super(true);
    super.setBackground(Color.WHITE);
    super.setSize(new Dimension(width, height));
    super.setLocation(new Point(x, y));

    this.controller = controller;
    time = 0;

    this.delay = 1000 / speed;
    this.timeDelta = this.delay / 1000;
    timer = new Timer(1000 / speed, this);
    timer.setInitialDelay(0);
    timer.start();
  }

  /**
   * Paint each of the shapes from the model on the Canvas for display on the ViewGUI.
   *
   * @param g java awt {@link Graphics} object.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    List<IShape> shapes = controller.getShapesAtTick(time);

    for (IShape shape : shapes) {

      g.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
          shape.getColor().getBlue()));

      if (shape.getShapeType().equals("RECTANGLE")) {
        g.fillRect(shape.getReference().getX(), shape.getReference().getY(), shape.getWidth(),
            shape.getHeight());
      } else {
        g.fillOval(shape.getReference().getX(), shape.getReference().getY(), shape.getWidth(),
            shape.getHeight());
      }
    }
  }

  /**
   * Preform actions fed into the {@link ActionListener}.
   *
   * @param actionEvent java awt {@link ActionEvent} representing an action in the animation.
   */
  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    while (time <= this.controller.getEndTick()) {
      super.repaint();
      this.time += this.timeDelta;
    }

    timer.stop();
  }
}

package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.IShape;

/**
 * Interface represents the panel for drawing the animation using the draw() method.
 */
public interface IPaintPanel {

  /**
   * Method that paints the list of shapes onto the screen.
   *
   * @param shapes the shapes to be painted on to the screen, passed as a List.
   */
  void paint(List<IShape> shapes);
}

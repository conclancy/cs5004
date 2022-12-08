package cs5004.animator.view;

import java.awt.Graphics;
import javax.swing.JList;

public interface IViewGUI extends IView {

  /**
   * Get the X coordinate recorded in the view.
   *
   * @return the X coordinate from the view.
   */
  double getCoordinateX();

  /**
   * Get the current speed of the animation.
   *
   * @return tick speed, as a double
   */
  double getSpeed();

  /**
   * Set the speed of the animation.
   *
   * @param speed tick speed, as a double;
   */
  void setSpeed(double speed);

  /**
   * Display a message to the end user.
   *
   * @param m the message to be displayed, as a String.
   */
  void showMessage(String m);

  /**
   * Setter method for the shapes list.
   *
   * @param shapes a list of Swing graphics.
   */
  void setShapes(JList<Graphics> shapes);
}

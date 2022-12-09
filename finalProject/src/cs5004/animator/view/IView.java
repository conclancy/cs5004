package cs5004.animator.view;

import cs5004.animator.controller.IController;

/**
 * The view interface for the EasyAutomation program.
 */
public interface IView {

  /**
   * Display the view at the provided speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  void display(int speed);

  /**
   * Give the view access to the controller.
   *
   * @param controller for the EasyAutomation program.
   */
  void addController(IController controller);
}

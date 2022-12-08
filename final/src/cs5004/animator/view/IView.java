package cs5004.animator.view;

import cs5004.animator.controller.ControllerGUI;
import cs5004.animator.controller.IController;

/**
 * This interface represents a generic view for the EasyAnimation program.
 */
public interface IView {

  /**
   * Display the EasyAnimation using the view.
   */
  void display(ControllerGUI controller);

}

package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * This interface listens for shape change events that happen throughout the Easy Automation GUI
 * view.
 */
public interface IShapeChangeListener extends ActionListener {

  /**
   * Initiate a change in a shape when a {@link IShapeChangeEvent} occurs within the
   * automation.
   */
  void shapeChanged(IShapeChangeEvent event);
}

package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * Interface for shape change {@link ActionListener} object in the {@link ViewGUIEditor}.
 */
public interface IShapeChangeListener extends ActionListener {

  /**
   * Initiate a change in a shape when a {@link IShapeChangeEvent} occurs within the automation.
   */
  void shapeChanged(IShapeChangeEvent event);
}

package cs5004.animator.view;

import java.awt.Component;
import java.awt.Color;
import java.beans.PropertyChangeListener;

/**
 * This interface handles inputs from {@link IView} pop-ups presented to the end user.  This class
 * allows users to export their animation as an SVG file and allows users to change shapes and
 * events that occur within the automation.
 */
public interface IUserInputHandler {

  /**
   * Add a listener that receives a {@link IShapeChangeEvent} objects and adds them to the animation
   * program.
   */
  void addShapeChangeListener(IShapeChangeListener listener);

  /**
   * Add a listener that receives a {@link IFrameChangeEvent} objects and adds them to the animation
   * program.
   */
  void addFrameChangeListener(IFrameChangeListener listener);

  /**
   * Add a listener that receives a {@link PropertyChangeListener} objects and adds them to the
   * animation program.
   */
  void addExportListener(PropertyChangeListener listener);

  /**
   * Export the passed {@link Component} to an SVG file. Method creates a pop-up in the
   * {@link IViewGUI} that allows user to specify path.
   */
  void exportToSVG(Component component);

  /**
   * Change an existing shape within the automation.
   *
   * @param component the component being changed.
   * @param type      the type of shape being changed.
   * @param name      the name of the shape being changed.
   */
  void changeShape(Component component, EShapeChangeType type, String name);

  /**
   * Allows for ADDING or DELETING an existing frame with a given name and tick location.
   *
   * @param component  the component to be set.
   * @param changeType the {@link EFrameChangeType} changeType occurring
   * @param name       the name of the frame to be changed.
   * @param tick       the tick location of the change.
   */
  void changeFrame(Component component, EFrameChangeType changeType, String name, int tick);

  /**
   * Allows for EDITING an existing frame within an animation.
   *
   * @param component  the component to be edited.
   * @param changeType the {@link EFrameChangeType} type occurring
   * @param name       the name of the frame being changed.
   * @param tick       the tick location of the change.
   * @param x          the X coordinate of the shape.
   * @param y          the Y coordinate of the shape.
   * @param width      the width of the shape.
   * @param height     the height of the shape.
   * @param degrees    the degrees of rotation from the shape's original starting orientation.
   * @param color      the {@link Color} of the shape.
   */
  void changeFrame(Component component, EFrameChangeType changeType, String name, int tick, int x,
      int y, int width, int height, int degrees, Color color);
}

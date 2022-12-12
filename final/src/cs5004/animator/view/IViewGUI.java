package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.controller.IFrame;

/**
 * This GUI editor view class creates an interactive window for users to control an animation. This
 * will allow users to scroll through the animation, set the animation speed, play, pause, restart,
 * repeat, and export to the visual to an SVG file.
 */
public interface IViewGUI extends IView {


  /**
   * Display a list of {@link IShape} objects.
   *
   * @param shapes the list of {@link IShape} to be displayed in the GUI.
   */
  void display(List<IShape> shapes);

  /**
   * Get the shapes for the animation.
   *
   * @return a list of {@link ShapeCell}
   */
  List<IShapeCell> getShapes();

  /**
   * Add {@link IShape} objects to the view.
   *
   * @param shapes all the shapes in the animation.
   */
  void setShapes(Map<String, IShape> shapes);

  /**
   * Get the animation frames for the visual.
   */
  Map<String, List<IFrame>> getFrame();

  /**
   * Sets frames for automation.
   *
   * @param frames the shape name, as a String, and its associated {@link IFrame} objects as a
   *               {@link LinkedHashMap}.
   */
  void setFrames(Map<String, List<IFrame>> frames);

  /**
   * Set the width of the GUI.
   *
   * @param width the width of the GUI, as an int.
   */
  void setWidth(int width);


  /**
   * Set the height of the GUI.
   *
   * @param height the height of the GUI, as an int.
   */
  void setHeight(int height);

  /**
   * Add a {@link ActionListener} that receives action events from button presses.
   *
   * @param listener the listener being passed.
   */
  void addButtonListener(ActionListener listener);

  /**
   * Add a {@link PropertyChangeListener} that receives property change events.
   *
   * @param listener the listener being passed.
   */
  void addPropertyListener(PropertyChangeListener listener);

  /**
   * Add a {@link IShapeChangeListener} that receives shape change events.
   *
   * @param listener the listener being passed.
   */
  void addShapeChangeListener(IShapeChangeListener listener);

  /**
   * Adds a {@link IFrameChangeListener} that receives frame change events.
   *
   * @param listener the listener being passed.
   */
  void addFrameChangeListener(IFrameChangeListener listener);

  /**
   * Displays an error in the view indicating to the user an error occurred.
   *
   * @param error the error message to be displayed, as a String.
   */
  void displayError(String error);


  /**
   * Set the current state of the GUI playback slider.
   *
   * @param tick to set the slider at.
   */
  void setSlider(double tick);
}

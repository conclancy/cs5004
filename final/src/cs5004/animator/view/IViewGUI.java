package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import cs5004.animator.controller.IFrame;

/**
 * This interface represents a playback view that uses a gui for the user.
 */
public interface IViewGUI extends IView {

  /**
   * This method will display a list of shapes onto the graphical interface for the user.
   */
  void display(List<IShape> shapes);

  /**
   * Retrieves the shapes to be displayed.
   *
   * @return a list of shapes
   */
  List<IShapeCell> getShapes();

  /**
   * Places a map of all shapes in animation in the view.
   *
   * @param shapes all the shapes in the animation.
   */
  void setShapes(Map<String, IShape> shapes);

  /**
   * Retrieves all keyframes.
   */
  Map<String, List<IFrame>> getKeyframes();

  /**
   * Sets all keyframes.
   */
  void setKeyframes(Map<String, List<IFrame>> keyframes);

  /**
   * sets the width of a shape.
   */
  void setWidth(int width);

  /**
   * sets the height of a shape.
   */
  void setHeight(int height);

  /**
   * Adds a listener that can receive action events.
   */
  void addButtonListener(ActionListener listener);

  /**
   * Adds a listener that can receive property change events.
   */
  void addPropertyListener(PropertyChangeListener listener);

  /**
   * Adds a listener that receives a SHAPE CHANGE event.
   */
  void addShapeChangeListener(IShapeChangeListener listener);

  /**
   * Adds a listener that receives a frame change event.
   */
  void addFrameChangeListener(IFrameChangeListener listener);

  /**
   * Displays an error on the screen indicating to the user an error occured.
   */
  void displayError(String s);


  /**
   * Method that allows that pushes an update to the slider for the given tick.
   */
  void setSlider(double tick);
}

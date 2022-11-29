package model;

import java.util.HashMap;
import java.util.List;

/**
 * The main entrypoint into the Easy Automation model.  One instance of this class represents a
 * single automation.  The interface allows input for {@link IShape} and takes {@link IAction}
 * events to modify the shapes throughout the process.
 */
public interface IEasyAutomation {

  /**
   * Add a new shape to the automation program.
   *
   * @param shape the shape object to be added to the program.
   */
  void addShape(String shapeName, IShape shape);

  /**
   * Set the speed of the automation.  Default speed is 1.0, speed greater than this will increase
   * speed, and speeds lower than 1.0 will decrease speed.
   *
   * @param speed the speed at which the automation will play back.
   * @throws IllegalArgumentException if the {@param speed} is less than or equal to 0.
   */
  void setSpeed(double speed) throws IllegalArgumentException;

  /**
   * Create or update an action on a given shape.
   *
   * @param shapeName the name of the shape that is being automated.
   * @param action    the action object being applied to the shape.
   * @throws IllegalArgumentException if the {@param shapeName} provided does not exist.
   */
  void setAction(String shapeName, IAction action) throws IllegalArgumentException;

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return shapes available in the automation, as a List of IShape objects.
   */
  List<IShape> getShapes();

  /**
   * Get the length of the automation as an int.  The
   *
   * @return the length of the automation, as an int.
   */
  int getLength();

  /**
   * Returns a list of hash maps that contain the shape's name as a string and the action state this
   * shape will be in at this index.
   *
   * @return a full list of the shapes and their actions at each index of the automation list.
   */
  List<HashMap<String, IAction>> playback();

}

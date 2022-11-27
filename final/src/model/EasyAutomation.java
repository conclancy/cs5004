package model;

import java.util.HashMap;
import java.util.List;

public class EasyAutomation implements IEasyAutomation {

  private List<HashMap<String, List<IShape>>> animation;
  private double speed;

  /**
   * Add a new shape to the automation program.
   *
   * @param shape the shape object to be added to the program.
   */
  @Override
  public void addShape(IShape shape) {
    // TODO
    // add a HashMap key in the `animation` list
    // scale the list to have enough IShapes to list the longest length
  }

  private int automationLength() {
    // TODO
    // return the length of the longest list in `animation`
    // implement a check to ensure all the lists in `animation` are this length
    return 0;
  }

  /**
   * Set the speed of the automation.  Default speed is 1.0, speed greater than this will increase
   * speed, and speeds lower than 1.0 will decrease speed.
   *
   * @param speed the speed at which the automation will play back.
   * @throws IllegalArgumentException if the {@param speed} is less than or equal to 0.
   */
  @Override
  public void setSpeed(double speed) throws IllegalArgumentException {

    if(speed <= 0) {
      throw new IllegalArgumentException("Speed must be greater than 0.");
    }

    this.speed = speed;
  }

  /**
   * Create or update an action on a given shape.
   *
   * @param shapeName the name of the shape that is being automated.
   * @param action    the action object being applied to the shape.
   * @throws IllegalArgumentException if the {@param shapeName} provided does not exist.
   * @throws IndexOutOfBoundsException if the {@param action} appears or disappears does not exist in the `animationLength()`dsaaa
   */
  @Override
  public void setAction(String shapeName, IAction action) throws IllegalArgumentException, IndexOutOfBoundsException {
    // TODO
    // Validate that the shape name exists
    //
  }

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return shapes available in the automation, as a List of IShape objects.
   */
  @Override
  public List<IShape> getShapes() {
    return null;
  }

  /**
   * Get the length of the automation as an int.  The
   *
   * @return the length of the automation, as an int.
   */
  @Override
  public int getLength() {
    return 0;
  }

  /**
   * Returns a list of hash maps that contain the shape's name as a string and the action state this
   * shape will be in at this index.
   *
   * @return a full list of the shapes and their actions at each index of the automation list.
   */
  @Override
  public List<HashMap<String, IAction>> playback() {
    return null;
  }
}

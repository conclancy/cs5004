package cs5004.animator.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This interface represent the model for the actual animation.
 */
public interface IModel {

  /**
   * This method creates a list of the shapes at the state of the animation at the given timestamp.
   *
   * @param time which is represented in ticks which is unit less.
   */
  List<IShape> getState(int time);

  /**
   * Get a list of Shapes within the model.
   *
   * @return the list of shapes in the model.
   */
  List<IShape> getShapeList();

  /**
   * Get a shape from the model with a specific name.
   *
   * @param name the name of the shape to be retrieved.
   * @return the shape object, as an {@link IShape} object.
   * @throws NoSuchElementException if the name passed does not correspond to a shape in the model.
   */
  IShape getShape(String name) throws NoSuchElementException;

  /**
   * This method creates all the processes from the model.
   */
  LinkedHashMap<String, List<IAnimation>> getProcesses();

  /**
   * This method will retrieve the x of the model.
   */
  int getX();

  /**
   * This method will retrieve the y of the model.
   */
  int getY();

  /**
   * This method will retrieve the height of the model.
   */
  int getHeight();

  /**
   * This method will retrieve the width of the model.
   */
  int getWidth();

  /**
   * This method will retrieve the last tick of the model.
   **/
  int getLastTick();

  /**
   * Formats the given information as the XML language that converts the list of processes and
   * shapes into XML to be turned into an .svg file.
   */
  String getSVGTags(int speed);

}

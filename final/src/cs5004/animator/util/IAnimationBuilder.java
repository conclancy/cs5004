package cs5004.animator.util;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents the interface for playbackBuilder that creates an animation with the specified fields.
 */
public interface IAnimationBuilder {
  /**
   * Constructs a final document.
   * @return the newly constructed document
   */
  IModel build();


  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * Adds a new shape to the growing document.
   *
   * @param name The unique name of the shape to be added.  
   *             No shape with this name should already exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added.  
   *             The set of supported shapes is unspecified, but should 
   *             include "ellipse" and "rectangle" as a minimum.
   * @return This {@link IAnimationBuilder}
   */
  IAnimationBuilder declareShape(String name, String type);

  /**
   * Adds a transformation to the growing document.
   * 
   * @param name The name of the shape (added with {@link IAnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-coordinatePosition of the shape
   * @param y1   The initial y-coordinatePosition of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-coordinatePosition of the shape
   * @param y2   The final y-coordinatePosition of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return This {@link IAnimationBuilder}
   */
  IAnimationBuilder addMotion(String name,
                                  int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                                  int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Adds a transformation to the growing document.
   *
   * @param name The name of the shape (added with {@link IAnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-coordinatePosition of the shape
   * @param y1   The initial y-coordinatePosition of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param o1   The initial rotationDegree of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-coordinatePosition of the shape
   * @param y2   The final y-coordinatePosition of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param o2   The final rotationDegree of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return This {@link IAnimationBuilder}
   */
  IAnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int o1, int r1,
                             int g1, int b1, int t2, int x2, int y2, int w2, int h2, int o2,
                             int r2, int g2, int b2);

  /**
   * This method removes a process from the current playbackBuilder with the given start time.
   *
   * @param id           is the id of the shape that the process you are removing is associated
   *                     with.
   * @param startingTime is the start time of the process you remove.
   */
  void removeProcess(String id, int startingTime);

  /**
   * This method removes a shape from the current playbackBuilder.
   *
   * @param id is the id of the shape that the process you are removing is associated with.
   */
  void removeShape(String id);

  /**
   * This method will retrieve the processes from the playbackBuilder.
   * @return a map of the ids for the processes in the playbackBuilder.
   */
  LinkedHashMap<String, List<IAnimation>> getProcesses();

  /**
   * This method will retrieve the shapes from the playbackBuilder.
   * @return a map of the ids for the shapes in the playbackBuilder.
   */
  LinkedHashMap<String, IShape> getShapes();

  /**
   * This method will retrieve the needed space from the playbackBuilder.
   * @return the dimensions for the animation.
   */
  Dimension getNeededSpace();
}

package cs5004.animator.util;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Model;
import cs5004.animator.model.Rectangle;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This class is the builder class for the animation model class. This allows the controller to
 * input animations and shapes to the model for building an Easy Animation.
 */
public class AnimationBuilder implements IAnimationBuilder {

  private final LinkedHashMap<String, List<IAnimation>> processes;
  private final LinkedHashMap<String, IShape> shapesList;
  private int x = 0;
  private int y = 0;
  private int width = 1000;
  private int height = 600;


  /**
   * Constructor for the AnimationBuilder.
   */
  public AnimationBuilder() {
    this.processes = new LinkedHashMap<>();
    this.shapesList = new LinkedHashMap<>();
  }

  /**
   * Constructs a new model with the given Shape and Animation objects.
   */
  public IModel build() {

    for (String key : this.shapesList.keySet()) {
      if (!this.processes.containsKey(key)) {
        throw new IllegalStateException("A shape must contain at least one processes");
      }
    }

    for (String key : this.processes.keySet()) {
      if (!this.shapesList.containsKey(key)) {
        throw new IllegalStateException("A process must have at least one shape");
      }
    }

    return new Model(this.processes, this.shapesList, this.x, this.y, this.width, this.height);
  }

  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value of the animation.
   * @param y      The topmost y value of the animation.
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   */
  @Override
  public void setBounds(int x, int y, int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The view cannot have a negative dimensions");
    }

    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Adds a new shape to the document.
   *
   * @param name The unique name of the shape.
   * @param type The type of shape such as a rectangle or ellipse.
   * @return a new addition to the list.
   */
  @Override
  public IAnimationBuilder declareShape(String name, String type) {
    if (this.shapesList.containsKey(name)) {
      throw new IllegalArgumentException("Error!: This ID has already been given and"
          + "associated to a shape");
    }

    IShape shape;

    switch (type.toLowerCase()) {
      case "rectangle":
        shape = new Rectangle();
        break;
      case "ellipse":
        shape = new Ellipse();
        break;
      default:
        shape = null;
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape type is invalid. Please check your spelling");
    }
    this.shapesList.put(name, shape);
    return this;
  }

  /**
   * Adds a new Animation to the model.
   *
   * @param name is the name of the shape.
   * @param t1   is the start time of this transformation
   * @param x1   is the initial x-coordinatePosition.
   * @param y1   is the initial y-coordinatePosition.
   * @param w1   is the initial width of the shape
   * @param h1   is the initial height.
   * @param r1   is the initial red color-value.
   * @param g1   is the initial green color-value.
   * @param b1   is the initial blue color-value.
   * @param t2   is the end time of this transformation
   * @param x2   is the final x-coordinatePosition.
   * @param y2   is the final y-coordinatePosition.
   * @param w2   is the final width.
   * @param h2   is the final height.
   * @param r2   is the final red color-value.
   * @param g2   is the final green color-value.
   * @param b2   is the final blue color-value.
   * @return a new {@link AnimationBuilder} object.
   */
  @Override
  public IAnimationBuilder addAnimation(String name, int t1, int x1, int y1, int w1, int h1, int o1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2,
      int h2, int o2, int r2, int g2, int b2) {
    return updateAnimationBuilder(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2,
        b2);
  }

  /**
   * Return an updated copy of the {@link IAnimationBuilder} object that is powering the Easy
   * Animator program.
   *
   * @param name is the name of the shape.
   * @param t1   is the start time of this transformation
   * @param x1   is the initial x-coordinatePosition.
   * @param y1   is the initial y-coordinatePosition.
   * @param w1   is the initial width of the shape
   * @param h1   is the initial height.
   * @param r1   is the initial red color-value.
   * @param g1   is the initial green color-value.
   * @param b1   is the initial blue color-value.
   * @param t2   is the end time of this transformation
   * @param x2   is the final x-coordinatePosition.
   * @param y2   is the final y-coordinatePosition.
   * @param w2   is the final width.
   * @param h2   is the final height.
   * @param r2   is the final red color-value.
   * @param g2   is the final green color-value.
   * @param b2   is the final blue color-value.
   * @return a new {@link AnimationBuilder} object.
   */
  private IAnimationBuilder updateAnimationBuilder(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    String type = this.getType(x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);
    IAnimation process = new Animation(type, t1, x1, y1, w1, h1,
        r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
    if (this.processes.containsKey(name)) {
      this.addAnimationHelper(name, this.processes.get(name), process,
          indexOfProcess(this.processes.get(name), process.getStartTick()) + 1);
    } else {
      this.processes.put(name, new ArrayList<>(Collections.singletonList(process)));
    }
    return this;
  }

  /**
   * Adds a transformation to the growing document.
   *
   * @param name is the name of the shape.
   * @param t1   is the start time of this transformation
   * @param x1   is the initial x-coordinatePosition.
   * @param y1   is the initial y-coordinatePosition.
   * @param w1   is the initial width of the shape
   * @param h1   is the initial height.
   * @param r1   is the initial red color-value.
   * @param g1   is the initial green color-value.
   * @param b1   is the initial blue color-value.
   * @param t2   is the end time of this transformation
   * @param x2   is the final x-coordinatePosition.
   * @param y2   is the final y-coordinatePosition.
   * @param w2   is the final width.
   * @param h2   is the final height.
   * @param r2   is the final red color-value.
   * @param g2   is the final green color-value.
   * @param b2   is the final blue color-value.
   * @return AniBuilder List.
   */
  @Override
  public IAnimationBuilder addAnimation(String name, int t1,
      int x1, int y1,
      int w1, int h1,
      int r1, int g1, int b1,
      int t2,
      int x2, int y2,
      int w2, int h2,
      int r2, int g2, int b2) {

    return updateAnimationBuilder(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2,
        b2);
  }

  /**
   * This method will return a description of the animation type.
   *
   * @param x1 is the initial x-coordinatePosition.
   * @param y1 is the initial y-coordinatePosition.
   * @param w1 is the initial width of the shape
   * @param h1 is the initial height.
   * @param r1 is the initial red color-value.
   * @param g1 is the initial green color-value.
   * @param b1 is the initial blue color-value.
   * @param x2 is the final x-coordinatePosition.
   * @param y2 is the final y-coordinatePosition.
   * @param w2 is the final width.
   * @param h2 is the final height.
   * @param r2 is the final red color-value.
   * @param g2 is the final green color-value.
   * @param b2 is the final blue color-value.
   * @return the animation type, as a String.
   */
  private String getType(int x1, int y1, int w1, int h1, int r1, int g1, int b1, int x2, int y2,
      int w2, int h2, int r2, int g2, int b2) {
    String testDescription = "";

    if (x1 != x2 || y1 != y2) {
      testDescription = "Movement";
    }
    if (w1 != w2 || h1 != h2) {
      testDescription = "Scaling";
    }
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      testDescription = "Color change";
    } else {
      return "Movement";
    }
    return testDescription;
  }


  /**
   * Helper method that adds an animation into a list with a specific index when no overlap is
   * observed.
   */
  private void addAnimationHelper(String name, List<IAnimation> list, IAnimation animation,
      int addIndex) {
    int startTick = animation.getStartTick();
    IShape shapeCopy1 = this.shapesList.get(name).getCopy();
    IShape shapeCopy2 = this.shapesList.get(name).getCopy();

    if (addIndex == 0) {
      list.add(addIndex, animation);
      return;
    }

    IAnimation previousProcess = list.get(addIndex - 1);

    previousProcess.setState(previousProcess.getEndTick(), shapeCopy1);
    animation.setState(startTick, shapeCopy2);

    if (previousProcess.getEndTick() != startTick) {
      throw new IllegalArgumentException("The start and end times of a animation must overlap!");
    } else if (shapeCopy1.equals(shapeCopy2)) {
      list.add(addIndex, animation);
    } else {
      throw new IllegalArgumentException("Illegal object management here.");
    }
  }

  /**
   * Get the dimensions required for this Easy Animation.
   *
   * @return the dimensions for the Easy Animation. .
   */
  @Override
  public Dimension getRequiredDimensions() {
    int minX = this.x;
    int minY = this.y;
    int maxX = this.x;
    int maxY = this.y;

    for (List<IAnimation> processList : this.getAnimations().values()) {
      minX = Math.min(minX,
          processList.get(0).getStartX());
      minY = Math.min(minY,
          processList.get(0).getStartY());
      maxX = Math.max(maxX,
          processList.get(0).getStartX() + processList.get(0).getStartWidth());
      maxY = Math.max(maxY,
          processList.get(0).getStartY() + processList.get(0).getStartHeight());

      for (IAnimation process : processList) {
        minX = Math.min(minX, process.getEndX());
        minY = Math.min(minY, process.getEndY());
        maxX = Math.max(maxX, process.getEndX() + process.getEndWidth());
        maxY = Math.max(maxY, process.getEndY() + process.getEndHeight());
      }
    }
    return new Dimension(maxX - minX, maxY - minY);
  }

  /**
   * Removes and animation from the model.
   *
   * @param name the name of the shape that the animation is associated with.
   * @param tick starting tick of the animation being removed.
   * @throws IllegalArgumentException if the shape or animation do not exist.
   */
  @Override
  public void removeAnimation(String name, int tick) throws IllegalArgumentException {
    if (processes.get(name) == null) {
      throw new IllegalArgumentException("The given name does not have any animations");
    }
    for (int i = 0; i < processes.get(name).size(); i++) {
      if (tick == processes.get(name).get(i).getStartTick()) {
        processes.get(name).remove(i);
        return;
      }
    }
    if (tick == processes.get(name).get(processes.get(name).size() - 1).getEndTick()) {
      processes.get(name).remove(processes.get(name).size() - 1);
      return;
    }
    throw new IllegalArgumentException("There are no animations to remove.");
  }

  /**
   * Removes a shape with the passed name from the model.
   *
   * @param name the name of the shape being removed.
   * @throws IllegalArgumentException if the shape name passed does not exist.
   */
  @Override
  public void removeShape(String name) throws IllegalArgumentException {
    if (!shapesList.containsKey(name)) {
      throw new IllegalArgumentException("This name is not associated with any shapes here");
    }
    shapesList.remove(name);
    processes.remove(name);
  }

  /**
   * Get the shapes within the model.
   *
   * @return the shapes in the model, and their associated names as a {@link LinkedHashMap}
   */
  @Override
  public LinkedHashMap<String, IShape> getShapes() {
    LinkedHashMap<String, IShape> output = new LinkedHashMap<>();
    for (String key : shapesList.keySet()) {
      output.put(key, shapesList.get(key));
    }
    return output;
  }

  /**
   * Get the {@link IAnimation} objects contained within the model.
   *
   * @return the map of the processes from the model.
   */
  @Override
  public LinkedHashMap<String, List<IAnimation>> getAnimations() {
    return getAnimationsMap(this.processes);
  }

  /**
   * Get the animations within a model.
   *
   * @param animations the list of {@link IAnimation} to be parsed.
   * @return the map of the {@link IAnimation} objects within the model
   */
  public static LinkedHashMap<String, List<IAnimation>> getAnimationsMap(
      LinkedHashMap<String, List<IAnimation>> animations) {
    LinkedHashMap<String, List<IAnimation>> output = new LinkedHashMap<>();
    for (String key : animations.keySet()) {
      List<IAnimation> newAnimation = new ArrayList<>(animations.get(key));
      output.put(key, newAnimation);
    }
    return output;
  }

  /**
   * Use binary search method to find the starting time of a new animation.
   *
   * @param list         the list of processes through which to search for a given animation.
   * @param startingTime the time to search for within the processes.
   * @return the index int of the process, as an int.
   */
  private static int indexOfProcess(List<IAnimation> list, int startingTime) {
    if (list.isEmpty()) {
      return 0;
    }

    int indexMin = 0;
    int indexMax = list.size() - 1;

    while (indexMin < indexMax) {
      int indexMiddle = (indexMin + indexMax) / 2;
      int middleTime = list.get(indexMiddle).getStartTick();

      if (startingTime == middleTime) {
        return indexMiddle;
      } else if (startingTime > middleTime) {
        indexMin = indexMiddle + 1;
      } else {
        indexMax = indexMiddle - 1;
      }
    }

    if (startingTime < list.get(indexMin).getStartTick()) {
      return indexMin - 1;
    } else {
      return indexMin;
    }
  }


}

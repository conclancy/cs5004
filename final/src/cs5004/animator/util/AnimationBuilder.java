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
 * This class is the builder class for the animation model class. This allows the users to input
 * processes and shapes for the playBackBuilder.
 */
public class AnimationBuilder implements IAnimationBuilder {

  private LinkedHashMap<String, List<IAnimation>> processes;
  private LinkedHashMap<String, IShape> shapesList;
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
   * A method that constructs a new model given the processes and shapes in the PlaybackBuilder.
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
    return new Model(this.processes,
        this.shapesList,
        this.x,
        this.y,
        this.width,
        this.height);
  }

  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return This {@link IAnimationBuilder}
   */
  @Override
  public IAnimationBuilder setBounds(int x, int y, int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Error! The shape cannot have a negative"
          + "width or height");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    return this;
  }

  /**
   * This method adds a new shape to the document.
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
  public IAnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int o1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2,
      int h2, int o2, int r2, int g2, int b2) {
    String type = this.getType(x1, y1, w1, h1, o1, r1, g1, b1, x2, y2, w2, h2, o2, r2, g2, b2);
    IAnimation process = new Animation(type, t1, x1, y1, w1, h1, o1,
        r1, g1, b1, t2, x2, y2, w2, h2, o2, r2, g2, b2);
    if (this.processes.containsKey(name)) {
      this.addIfValidProcess(name, this.processes.get(name), process,
          this.indexOfProcess(this.processes.get(name), process.getStartTime()) + 1);
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
  public IAnimationBuilder addMotion(String name, int t1,
      int x1, int y1,
      int w1, int h1,
      int r1, int g1, int b1,
      int t2,
      int x2, int y2,
      int w2, int h2,
      int r2, int g2, int b2) {

    String type = this.getType(x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);
    IAnimation process = new Animation(type, t1, x1, y1, w1, h1,
        0, r1, g1, b1, t2, x2, y2, w2, h2, 0, r2, g2, b2);
    if (this.processes.containsKey(name)) {
      this.addIfValidProcess(name, this.processes.get(name), process,
          this.indexOfProcess(this.processes.get(name), process.getStartTime()) + 1);
    } else {
      this.processes.put(name, new ArrayList<>(Collections.singletonList(process)));
    }
    return this;
  }

  /**
   * This method will return a description of the motion.
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
   * @return AniBuilder List.
   */
  private String getType(int x1, int y1, int w1, int h1, int o1, int r1, int g1, int b1,
      int x2, int y2, int w2, int h2, int o2, int r2, int g2, int b2) {
    String testDescription = this.getType(x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);

    if (o1 != o2) {
      if (testDescription.equals("Nothing")) {
        testDescription = "Rotate";
      } else {
        testDescription += " and Rotate";
      }
    }

    return testDescription;
  }

  /**
   * This method will return a description of the motion.
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
   * @return AniBuilder List.
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
   * Helper method that adds a process into a list with a specific index when no overlap is
   * observed.
   */
  private void addIfValidProcess(String id, List<IAnimation> list,
      IAnimation process,
      int addIndex) {
    int startTick = process.getStartTime();
    IShape shapeCopy1 = this.shapesList.get(id).getCopy();
    IShape shapeCopy2 = this.shapesList.get(id).getCopy();

    if (addIndex == 0) {
      list.add(addIndex, process);
      return;
    }

    IAnimation previousProcess = list.get(addIndex - 1);

    previousProcess.setState(previousProcess.getEndTime(), shapeCopy1);
    process.setState(startTick, shapeCopy2);

    if (previousProcess.getEndTime() != startTick) {
      throw new IllegalArgumentException("The start and end times of a process must overlap!");
    } else if (this.shapesAreEqual(shapeCopy1, shapeCopy2)) {
      list.add(addIndex, process);
    } else {
      throw new IllegalArgumentException("Illegal object management here.");
    }
  }

  /**
   * Method that determines if two shapes are equal.
   */
  private boolean shapesAreEqual(IShape shape1, IShape shape2) {
    return shape1.getShapeType().equals(shape2.getShapeType())
        && shape1.getWidth() == shape2.getWidth()
        && shape1.getHeight() == shape2.getHeight()
        && shape1.getReference().getX() == shape2.getReference().getX()
        && shape1.getReference().getY() == shape2.getReference().getY()
        && shape1.getDegrees() == shape2.getDegrees()
        && shape1.getColor().getRGB() == shape2.getColor().getRGB();
  }

  @Override
  public Dimension getNeededSpace() {
    int minX = this.x;
    int minY = this.y;
    int maxX = this.x;
    int maxY = this.y;

    for (List<IAnimation> processList : this.getProcesses().values()) {
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
   * Method that removes a process.
   */
  @Override
  public void removeProcess(String id, int time) {
    if (processes.get(id) == null) {
      throw new IllegalArgumentException("The given ID does not have any processes");
    }
    for (int i = 0; i < processes.get(id).size(); i++) {
      if (time == processes.get(id).get(i).getStartTime()) {
        processes.get(id).remove(i);
        return;
      }
    }
    if (time == processes.get(id).get(processes.get(id).size() - 1).getEndTime()) {
      processes.get(id).remove(processes.get(id).size() - 1);
      return;
    }
    throw new IllegalArgumentException("There are no processes to remove.");
  }

  /**
   * Method that removes a shape.
   */
  @Override
  public void removeShape(String id) {
    if (!shapesList.containsKey(id)) {
      throw new IllegalArgumentException("This ID is not associated with any shapes here");
    }
    shapesList.remove(id);
    processes.remove(id);
  }

  /**
   * THis method returns the shapes within the map.
   *
   * @return the shapes within the map.
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
   * This method creates a linked hash map for all the processes from the model and returns it.
   *
   * @return the map of the processes from the model.
   */
  @Override
  public LinkedHashMap<String, List<IAnimation>> getProcesses() {
    LinkedHashMap<String, List<IAnimation>> output = new LinkedHashMap<>();
    for (String key : this.processes.keySet()) {
      List<IAnimation> newProcesss = new ArrayList<>();
      newProcesss.addAll(this.processes.get(key));
      output.put(key, newProcesss);
    }
    return output;
  }

  @Override
  public IAnimationBuilder addKeyframe(String name, int t, int x, int y, int w,
      int h, int r, int g, int b) {
    throw new UnsupportedOperationException("Error Please check settings");
  }

  /**
   * THis is a helper method that serches for index of a process using binary search.
   *
   * @param list         the list of processes through which to search for a given animaiton.
   * @param startingTime the time to search for within the processes.
   * @return the index int of the process.
   */
  private static int indexOfProcess(List<IAnimation> list, int startingTime) {
    if (list.isEmpty()) {
      return 0;
    }

    int indexMin = 0;
    int indexMax = list.size() - 1;

    while (indexMin < indexMax) {
      int indexMiddle = (indexMin + indexMax) / 2;
      int middleTime = list.get(indexMiddle).getStartTime();

      if (startingTime == middleTime) {
        return indexMiddle;
      } else if (startingTime > middleTime) {
        indexMin = indexMiddle + 1;
      } else {
        indexMax = indexMiddle - 1;
      }
    }

    if (startingTime < list.get(indexMin).getStartTime()) {
      return indexMin - 1;
    } else {
      return indexMin;
    }
  }


}

package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * This class represents the animation model of a process an animation using a list of the shapes
 * and a list of the processes.
 */
public class Model implements IModel {

  private final LinkedHashMap<String, List<IAnimation>> processes;
  private final LinkedHashMap<String, IShape> shapes;

  private final int x;
  private final int y;

  private final int width;
  private final int height;


  /**
   * A private constructor for Animation model to allow for only our playbackBuilder to create new
   * models. This allows us to
   *
   * @param processes which is a linked hashmap that contains IDs and shapes connected to all of the
   *                  processes that the shape will have.
   * @param shapes    which is also a linked hashmap that contains the IDs and shapes.
   */
  public Model(LinkedHashMap<String, List<IAnimation>> processes,
      LinkedHashMap<String, IShape> shapes,
      int x, int y, int width, int height) {
    this.processes = processes;
    this.shapes = shapes;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
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

  /**
   * This method retrieves the state of the shapes. THis retrieves a list of the shapes at a
   * specific time interval.
   *
   * @param timeOfInterest the time in which the state should be retrieved.
   * @return the list of shapes that are the current state of the animation.
   */
  @Override
  public List<IShape> getState(int timeOfInterest) {
    List<IShape> output = new ArrayList<>();
    //iterate through the shape list
    for (Map.Entry<String, List<IAnimation>> entry : this.processes.entrySet()) {
      String id = entry.getKey();
      IShape currShapes = this.shapes.get(id);

      // iterate via index
      int index = indexOfProcess(this.processes.get(id), timeOfInterest);
      if (index == -1) {
        continue;
      }
      if (timeOfInterest <= this.processes.get(id).get(index).getEndTime()) {
        this.processes.get(id).get(index).setState(timeOfInterest, currShapes);
      } else {
        continue;
      }
      double newX = currShapes.getReference().getX() - this.x;
      double newY = currShapes.getReference().getY() - this.y;
      currShapes.setReference(new Point2D.Double(newX, newY));
      output.add(currShapes);
    }
    return output;
  }

  /**
   * This method creates a linked hash map and then returns the shapes within the map.
   *
   * @return the shapes within the map.
   */
  @Override
  public LinkedHashMap<String, IShape> getShapes() {
    LinkedHashMap<String, IShape> output = new LinkedHashMap<>();
    for (String currKey : shapes.keySet()) {
      output.put(currKey, shapes.get(currKey));
    }
    return output;
  }

  /**
   * This method creates a linked hash map and then returns the processes within the map.
   *
   * @return processes from model in the map.
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

  /**
   * Returns the x coordinate of the model.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the model.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Returns the width of the shape.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the shape.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the last tick in the process of the model.
   *
   * @return the last tick.
   */
  @Override
  public int getLastTick() {
    int output = 0;
    for (List<IAnimation> processList : this.processes.values()) {
      output = Math.max(output, processList.get(processList.size() - 1).getEndTime());
    }
    return output;
  }

  /**
   * A description of the animation process that returns a string.
   *
   * @return String that is the description.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    for (Map.Entry<String, List<IAnimation>> entry : this.processes.entrySet()) {
      IShape shape = this.shapes.get(entry.getKey());
      output.append("Shape ").append(entry.getKey()).append(" ").append(shape.getShapeType())
          .append("\n");
      for (IAnimation process : entry.getValue()) {
        StringBuilder temp = new StringBuilder(process.getType()).append(" ")
            .append(entry.getKey()).append(" ").append(process.getStartTime()).append(" ");

        process.setState(process.getStartTime(), shape);
        temp.append(this.getShapeInfo(shape)).append("    ");

        process.setState(process.getEndTime(), shape);
        temp.append(process.getEndTime()).append(" ").append(this.getShapeInfo(shape)).append("\n");
        output.append(temp);
      }
      output.append("\n");
    }
    return output.toString().trim();
  }

  /**
   * Helper for the toString method that gives a description of a shape.
   *
   * @param shape is the shape.
   * @return String that is the description.
   */
  private String getShapeInfo(IShape shape) {
    StringBuilder temp = new StringBuilder();
    temp.append(shape.getReference().getX()).append(" ")
        .append(shape.getReference().getY()).append(" ")
        .append(shape.getWidth()).append(" ")
        .append(shape.getHeight()).append(" ")
        .append(shape.getColor().getRed()).append(" ")
        .append(shape.getColor().getGreen()).append(" ")
        .append(shape.getColor().getBlue());
    return temp.toString();
  }

}
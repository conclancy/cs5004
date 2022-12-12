package cs5004.animator.model;

import static cs5004.animator.util.AnimationBuilder.getAnimationsMap;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * This class represents the animation model of a process an animation using a list of the shapes
 * and a list of the processes.
 */
public class Model implements IModel {

  private final LinkedHashMap<String, List<IAnimation>> processes;
  private final List<IShape> shapeList;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  Appendable output;


  /**
   * A constructor for Animation model to allow for only our playbackBuilder to create new
   * models. This allows us to
   *
   * @param processes which is a linked hashmap that contains IDs and shapes connected to all of the
   *                  processes that the shape will have.
   * @param shapeList    which is also a linked hashmap that contains the IDs and shapes.
   */
  public Model(LinkedHashMap<String, List<IAnimation>> processes,
      List<IShape> shapeList,
      int x, int y, int width, int height) {
    this.processes = processes;
    this.shapeList = shapeList;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.output = new StringBuilder();
  }

  /**
   * THis is a helper method that searches for index of a process using binary search.
   *
   * @param list         the list of processes through which to search for a given animation.
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
      IShape currShapes = null;

      for(IShape shape: shapeList) {
        if(shape.getName().equals(id)) {
          currShapes = shape;
        }
      }

      if(currShapes != null) {

        // iterate via index
        int index = indexOfProcess(this.processes.get(id), timeOfInterest);

        if (index == -1) {
          continue;
        }

        if (timeOfInterest <= this.processes.get(id).get(index).getEndTick()) {
          this.processes.get(id).get(index).setState(timeOfInterest, currShapes);
        } else {
          continue;
        }

        double newX = currShapes.getReference().getX() - this.x;
        double newY = currShapes.getReference().getY() - this.y;
        currShapes.setReference(new Point2D.Double(newX, newY));
        output.add(currShapes);

      } else {
        throw new NoSuchElementException("The shapes does not exists.");
      }


    }
    return output;
  }

  /**
   * Get a list of shapes held within the model.
   *
   * @return list of shapes within the model.
   */
  public List<IShape> getShapeList() {
    List<IShape> output = new ArrayList<>();

    for (IShape shape: shapeList) {
      output.add(shape.getCopy());
    }

    return output;
  }

  /**
   * Get a shape from the model with a specific name.
   *
   * @param name the name of the shape to be retrieved.
   * @return the shape object, as an {@link IShape} object.
   * @throws NoSuchElementException if the name passed does not correspond to a shape in the model.
   */
  @Override
  public IShape getShape(String name) {
    IShape shape = null;

    for (IShape s: shapeList) {
      if(s.getName().equals(name)) {
        shape = s.getCopy();
        break;
      }
    }

    if (shape == null) {
      throw new NoSuchElementException("This shape does not exist.");
    } else {
      return shape;
    }
  }

  /**
   * This method creates a linked hash map and then returns the processes within the map.
   *
   * @return processes from model in the map.
   */
  @Override
  public LinkedHashMap<String, List<IAnimation>> getProcesses() {
    return getAnimationsMap(this.processes);
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
      output = Math.max(output, processList.get(processList.size() - 1).getEndTick());
    }
    return output;
  }

  /**
   * Formats the given information as the XML language that converts the list of processes and
   * shapes into XML to be turned into an .svg file.
   */
  @Override
  public String getSVGTags(int speed) {
    String type;
    appendHelper("<svg width=\"" + this.getWidth() + "\" height=\""
        + this.getHeight() + "\" version=\"1.1\"\n    "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (IShape shape : this.getShapeList()) {

      List<IAnimation> processes = this.getProcesses().get(shape.getName());

      switch (shape.getShapeType()) {
        case "Rectangle":
          type = "rect";
          break;
        case "Ellipse":
          type = "ellipse";
          break;
        default:
          type = "default";
      }

      this.appendHelper("<" + type + " id=\""
          + shape.getName());
      this.appendHelper(this.shapeStateHelper(processes.get(0), type));
      this.appendHelper(this.toSVGStringHelper(processes, type, speed));
      this.appendHelper("</" + type + ">\n\n");
    }
    appendHelper("</svg>");
    return this.output.toString();
  }

  /**
   * Get a text representation of the model.
   *
   * @return the text representation of the model, as a multi-line string.
   */
  @Override
  public String getModelAsText() {

    StringBuilder output = new StringBuilder();

    for (Map.Entry<String, List<IAnimation>> entry : this.getProcesses().entrySet()) {

      IShape shape = this.getShape(entry.getKey());

      output.append("Create ").append(shape.getShapeType()).append(" ")
          .append(entry.getKey()).append(" ")
          .append("with center at ").append(shape.getReference()).append(", width of ")
          .append(shape.getWidth()).append(", height of ")
          .append(shape.getHeight()).append(".")
          .append("\n");

      for (IAnimation animation : entry.getValue()) {
        String temp = "Transform "
            + entry.getKey() + " from location ("
            + animation.getStartX() + ", "
            + animation.getStartY() + ") to ("
            + animation.getEndX() + ", "
            + animation.getEndY() + ") and color "
            + animation.getStartColor() + " to "
            + animation.getEndColor() + " over t="
            + animation.getStartTick() + " to "
            + animation.getEndTick() + "."
            + "\n";
        output.append(temp);
      }
      output.append("\n");
    }

    if (output.length() != 0) {
      output.delete(output.length() - 2, output.length());
    }

    return output.toString();

  }

  /**
   * Handle the try and catch logic for appending SVG tags to the final SVG model.
   *
   * @param input the input String to be appended to the output.
   * @throws IllegalArgumentException if the output is unable to accept the input value.
   */
  private void appendHelper(String input) throws IllegalArgumentException {
    try {
      output.append(input);
    } catch (IOException e) {
      throw new IllegalStateException("Bad Appendable");
    }
  }

  /**
   * Helper method that ingests the status list, and type and passes all associated information into
   * SVG format.
   */
  private String toSVGStringHelper(List<IAnimation> list, String type, int speed) {
    StringBuilder output = new StringBuilder();
    String startingStringValue;
    String endingStringValue = "\" fill=\"freeze\" />\n";

    for (IAnimation process : list) {
      startingStringValue = "    <animate attributeType=\"xml\" begin=\""
          + process.getStartTick() * speed + "ms\" dur=\""
          + (process.getEndTick() - process.getStartTick()) * speed + "ms\" "
          + "attributeName=\"";

      if (process.getStartRotationDegree() != process.getEndRotationDegree()) {
        if (type.equals("rect")) {
          output.append("<animateTransform attributeName=\"transform\" attributeType=\"xml\""
                  + " type=\"rotate\" from=\"").append(process.getStartRotationDegree()).append(" ")
              .append(process.getStartX() + process.getStartWidth() / 2 - this.x).append(" ")
              .append(process.getStartY() + process.getStartHeight() / 2 - this.y)
              .append("\" to=\"").append(process.getEndRotationDegree()).append(" ")
              .append(process.getStartX() + process.getStartWidth() / 2 - this.x).append(" ")
              .append(process.getStartY() + process.getStartHeight() / 2 - this.y)
              .append("\" dur=\"").append((process.getEndTick() - process.getStartTick()) * speed)
              .append("ms\" ").append("repeatCount=\"0\"/>\n");
        } else if (type.equals("ellipse")) {
          output.append("<animateTransform attributeName=\"transform\" attributeType=\"xml\""
                  + " type=\"rotate\" from=\"").append(process.getStartRotationDegree()).append(" ")
              .append(process.getStartX() - this.x + process.getStartWidth() / 2).append(" ")
              .append(process.getStartY() - this.y + process.getStartHeight() / 2)
              .append("\" to=\"").append(process.getEndRotationDegree()).append(" ")
              .append(process.getStartX() - this.x + process.getStartWidth() / 2).append(" ")
              .append(process.getStartY() - this.y + process.getStartHeight() / 2)
              .append("\" dur=\"").append((process.getEndTick() - process.getStartTick()) * speed)
              .append("ms\" repeatCount=\"0\"/>\n");
        }
        continue;
      }
      if (process.getStartX() != process.getEndX()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("x\" from=\"").append(process.getStartX()
                  - this.x)
              .append("\" to=\"").append(process.getEndX()
                  - this.x).append(endingStringValue);
        } else {
          output.append(startingStringValue).append("cx\" from=\"")
              .append(process.getStartX() - this.x + process.getStartWidth() / 2)
              .append("\" to=\"")
              .append(process.getEndX() - this.x + process.getEndWidth() / 2)
              .append(endingStringValue);
        }
      }
      if (process.getStartY() != process.getEndY()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("y\" from=\"")
              .append(process.getStartY() - this.y)
              .append("\" to=\"")
              .append(process.getEndY() - this.y)
              .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("cy\" from=\"")
              .append(process.getStartY() - this.y + process.getStartHeight() / 2)
              .append("\" to=\"")
              .append(process.getEndY() - this.y + process.getEndHeight() / 2)
              .append(endingStringValue);
        }
      }
      if (process.getStartHeight() != process.getEndHeight()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("height\" from=\"")
              .append(process.getStartHeight())
              .append("\" to=\"").append(process.getEndHeight())
              .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("ry\" from=\"")
              .append(process.getStartHeight() / 2)
              .append("\" to=\"").append(process.getEndHeight() / 2)
              .append(endingStringValue);
        }
      }
      if (process.getStartWidth() != process.getEndWidth()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("width\" from=\"")
              .append(process.getStartWidth())
              .append("\" to=\"").append(process.getEndWidth())
              .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("ry\" from=\"")
              .append(process.getStartWidth() / 2)
              .append("\" to=\"").append(process.getEndWidth() / 2)
              .append(endingStringValue);
        }
      }
      if (process.getStartColor().getRed() != process.getEndColor().getRed()
          || process.getStartColor().getGreen() != process.getEndColor().getGreen()
          || process.getStartColor().getBlue() != process.getEndColor().getBlue()) {
        output.append(startingStringValue).append("fill\" from=\"rgb(")
            .append(process.getStartColor().getRed()).append(",")
            .append(process.getStartColor().getGreen()).append(",")
            .append(process.getStartColor().getBlue()).append(")\" to=\"rgb(")
            .append(process.getEndColor().getRed()).append(",")
            .append(process.getEndColor().getGreen()).append(",")
            .append(process.getEndColor().getBlue()).append(")").append(endingStringValue);
      }
    }
    return output.toString();
  }

  /**
   * Get the state of a given shape during this process step.
   */
  private String shapeStateHelper(IAnimation process, String type) {
    StringBuilder output = new StringBuilder();
    switch (type) {
      case "rect":
        output = new StringBuilder("\" x=\"").append(process.getStartX() - this.x)
            .append("\" y=\"").append(process.getStartY() - this.y).append("\" width=\"")
            .append(process.getStartWidth()).append("\" height=\"")
            .append(process.getStartHeight())
            .append("\" fill=\"rgb(").append(process.getStartColor().getRed()).append(",")
            .append(process.getStartColor().getGreen()).append(",")
            .append(process.getStartColor().getBlue())
            .append(")\" visibility=\"visible\" >\n");
        break;
      case "ellipse":
        output = new StringBuilder("\" cx=\"")
            .append(process.getStartX() - this.x + process.getStartWidth() / 2)
            .append("\" cy=\"")
            .append(process.getStartY() - this.y + process.getStartHeight() / 2)
            .append("\" rx=\"")
            .append(process.getStartWidth() / 2).append("\" ry=\"")
            .append(process.getStartHeight() / 2)
            .append("\" fill=\"rgb(").append(process.getStartColor().getRed()).append(",")
            .append(process.getStartColor().getGreen()).append(",")
            .append(process.getStartColor().getBlue())
            .append(")\" visibility=\"visible\" >\n");
        break;
      default:
        return output.toString();
    }
    return output.toString();
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
      IShape shape = this.getShape(entry.getKey());
      output.append("Shape ").append(entry.getKey()).append(" ").append(shape.getShapeType())
          .append("\n");
      for (IAnimation process : entry.getValue()) {
        StringBuilder temp = new StringBuilder(process.getType()).append(" ")
            .append(entry.getKey()).append(" ").append(process.getStartTick()).append(" ");

        process.setState(process.getStartTick(), shape);
        temp.append(this.getShapeInfo(shape)).append("    ");

        process.setState(process.getEndTick(), shape);
        temp.append(process.getEndTick()).append(" ").append(this.getShapeInfo(shape)).append("\n");
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
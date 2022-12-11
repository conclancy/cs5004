package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IProcess;

/**
 * This class allows for the creation of SVG files.
 */
public class SVGStringGenerator implements IVewText {

  private final int speed;
  private final int x;
  private final int y;
  Appendable output;
  IModel model;

  // TODO: this functionality should exist within the model, not the View.

  /**
   * Constructor for a SVGStringGenerator view that generates an SVG string from a model.
   *
   * @param model model used for the animation.
   * @param speed is the ticks per second  of the animation.
   */
  public SVGStringGenerator(IModel model, int speed) {
    if (speed < 1) {
      throw new IllegalArgumentException("Cannot pass a negative ticks per second");
    }
    this.model = model;
    output = new StringBuilder();
    this.speed = 1000 / speed;
    this.x = model.getX();
    this.y = model.getY();
  }

  /**
   * Formats the given information as the XML language that converts the list of processes and
   * shapes into XML to be turned into an .svg file.
   */
  @Override
  public void play() {
    String type;
    appendHelper("<svg width=\"" + model.getWidth() + "\" height=\""
        + model.getHeight() + "\" version=\"1.1\"\n    "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Map.Entry<String, IShape> entry : model.getShapes().entrySet()) {
      List<IProcess> processes = model.getProcesses().get(entry.getKey());
      switch (entry.getValue().getShapeType()) {
        case "Rectangle":
          type = "rect";
          break;
        case "Ellipse":
          type = "ellipse";
          break;
        default:
          type = "";
      }
      this.appendHelper("<" + type + " id=\""
          + entry.getKey());
      this.appendHelper(this.shapeStateHelper(processes.get(0), type));
      this.appendHelper(this.toSVGStringHelper(processes, type));
      this.appendHelper("</" + type + ">\n\n");
    }
    appendHelper("</svg>");
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
  private String toSVGStringHelper(List<IProcess> list, String type) {
    StringBuilder output = new StringBuilder();
    String startingStringValue;
    String endingStringValue = "\" fill=\"freeze\" />\n";

    for (IProcess process : list) {
      startingStringValue = "    <animate attributeType=\"xml\" begin=\""
          + process.getStartTime() * speed + "ms\" dur=\""
          + (process.getEndTime() - process.getStartTime()) * speed + "ms\" "
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
              .append("\" dur=\"").append((process.getEndTime() - process.getStartTime()) * speed)
              .append("ms\" ").append("repeatCount=\"0\"/>\n");
        } else if (type.equals("ellipse")) {
          output.append("<animateTransform attributeName=\"transform\" attributeType=\"xml\""
                  + " type=\"rotate\" from=\"").append(process.getStartRotationDegree()).append(" ")
              .append(process.getStartX() - this.x + process.getStartWidth() / 2).append(" ")
              .append(process.getStartY() - this.y + process.getStartHeight() / 2)
              .append("\" to=\"").append(process.getEndRotationDegree()).append(" ")
              .append(process.getStartX() - this.x + process.getStartWidth() / 2).append(" ")
              .append(process.getStartY() - this.y + process.getStartHeight() / 2)
              .append("\" dur=\"").append((process.getEndTime() - process.getStartTime()) * speed)
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
  private String shapeStateHelper(IProcess process, String type) {
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
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.output.toString();
  }

}

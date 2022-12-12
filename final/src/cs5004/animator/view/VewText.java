package cs5004.animator.view;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.IModel;

/**
 * Class that represents a text view of the given animation.  This view represents the Easy
 * Automation as a multi-line string of text.
 */
public class VewText implements IVewText {

  private Appendable outputTemp;
  private IModel model;

  /**
   * Constructor for a ViewText object.
   *
   * @param model is the model for which the VewText will create a constructor for
   */
  public VewText(IModel model) {
    this.model = model;
    outputTemp = new StringWriter();
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.outputTemp.toString();
  }

  /**
   * Plays the animation using a selected type of view.
   */
  @Override
  public void play() {

    StringBuilder output = new StringBuilder();
    for (Map.Entry<String,
        List<IAnimation>> entry : this.model.getProcesses().entrySet()) {
      // IShape shape = this.model.getShapeHash().get(entry.getKey());
      IShape shape = this.model.getShape(entry.getKey()); // TODO
      output.append("Create ").append(shape.getShapeType()).append(" ")
          .append(entry.getKey()).append(" ")
          .append("with center at ").append(shape.getReference()).append(", width of ")
          .append(shape.getWidth()).append(", height of ")
          .append(shape.getHeight()).append(".")
          .append("\n");

      for (IAnimation process : entry.getValue()) {
        StringBuilder temp = new StringBuilder().append("Transform ");
        temp.append(entry.getKey()).append(" from location (")
            .append(process.getStartX()).append(", ")
            .append(process.getStartY()).append(") to (")
            .append(process.getEndX()).append(", ")
            .append(process.getEndY()).append(") and color ")
            .append(process.getStartColor()).append(" to ")
            .append(process.getEndColor()).append(" over t=")
            .append(process.getStartTick()).append(" to ")
            .append(process.getEndTick()).append(".")

            .append("\n");
        output.append(temp);
      }
      output.append("\n");
    }

    if (output.length() != 0) {
      output.delete(output.length() - 2, output.length());
    }
    outputTemp = output;
  }
}

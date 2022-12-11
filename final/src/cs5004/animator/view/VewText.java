package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IStatusProcess;

/**
 * Class that represents a text description of the given animation.
 */
public class VewText implements IVewText {
  private Appendable outputTemp;
  private IModel model;

  /**
   * A constructor that constructs a VewText by creating a long string.
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
            List<IStatusProcess>> entry : this.model.getProcesses().entrySet()) {
      IShape shape = this.model.getShapes().get(entry.getKey());
      output.append("Create ").append(shape.getShapeType()).append(" ")
              .append(entry.getKey()).append(" ")
              .append("with center at ").append(shape.getReference()).append(", width of ")
              .append(shape.getWidth()).append(", height of ")
              .append(shape.getHeight()).append(".")
              .append("\n");

      for (IStatusProcess process : entry.getValue()) {
        StringBuilder temp = new StringBuilder().append("Transform ");
        temp.append(entry.getKey()).append(" from location (")
                .append(process.getStartX()).append(", ")
                .append(process.getStartY()).append(") to (")
                .append(process.getEndX()).append(", ")
                .append(process.getEndY()).append(") and color ")
                .append(process.getStartColor()).append(" to ")
                .append(process.getEndColor()).append(" over t=")
                .append(process.getStartTime()).append(" to ")
                .append(process.getEndTime()).append(".")

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

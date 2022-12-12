package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * This class allows for the creation of SVG files.
 */
public class SVGStringGenerator implements IViewFile {

  Appendable output;
  private FileWriter writer;

  /**
   * Constructor for a SVGStringGenerator view that generates an SVG string from a model.
   *
   * @param svg the svg tags, as a String.
   * @param speed is the ticks per second  of the animation.
   */
  public SVGStringGenerator(String svg, String fileName, int speed) {
    if (speed < 0) {
      throw new IllegalArgumentException("Cannot pass a negative ticks per second");
    }

    this.output = new StringBuilder();

    try {
      this.writer = new FileWriter(fileName);
    } catch (IOException e) {
      popUpError("------ Error: Output file cannot be created.");
    } catch (IndexOutOfBoundsException e) {
      popUpError("------ Error: Output file not defined.");
    }

    try{
      output.append(svg);
    } catch (IOException e) {
      System.out.println("SVG String Generator not working.");
    }

  }

  /**
   * Generate the SVG file using the input text.
   */
  @Override
  public void play() {
    try {
      writer.append(this.getText());
      writer.close();
    } catch (IOException e) {
      popUpError("------ Output Error: File cannot be written. Please check parameters.");
    }
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.output.toString();
  }

  /**
   * Handles exceptions as they are thrown.
   *
   * @param message the message to be shown in the dialog view box.
   */
  private static void popUpError(String message) {
    JOptionPane.showMessageDialog(null, message,
        "------ Animation Error: An error occurred", 0);
    System.exit(1);
  }

}

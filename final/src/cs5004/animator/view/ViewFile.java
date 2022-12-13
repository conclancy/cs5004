package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * This class represents a text file view.
 */
public class ViewFile implements IViewFile {

  private final String output;
  private FileWriter writer;

  /**
   * Constructor for the ViewFile class.
   *
   * @param text     the text to be included in the view.
   * @param fileName the name of the file to be created.
   */
  public ViewFile(String text, String fileName) {
    this.output = text;

    try {
      this.writer = new FileWriter(fileName);
    } catch (IOException e) {
      popUpError("------ Error: Output file cannot be created.");
    } catch (IndexOutOfBoundsException e) {
      popUpError("------ Error: Output file not defined.");
    }
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.output;
  }

  /**
   * Plays the animation using a selected type of view.
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

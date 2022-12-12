package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ViewFile implements IViewFile {

  protected String output;
  protected FileWriter writer;

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
  protected static void popUpError(String message) {
    JOptionPane.showMessageDialog(null, message,
        "------ Animation Error: An error occurred", 0);
    System.exit(1);
  }
}
package cs5004.animator;

import java.util.Arrays;

import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;

/**
 * Main class used to execute the Easy Animator program.
 */
public final class EasyAnimator {

  /**
   * Initializes the model and the view based on the given arguments and runs an animation.
   *
   * <p><p>Arguments:
   *
   * <p>- jar:    The .jar file to use for the program.
   *
   * <p>- view:   `text` for a text view, `svg` to generate an .svg file, `visual` for a simple gui
   * with no editor, `gui` / `editor` for an editor user interface.
   *
   * <p>- in:     Path to the input .txt file.
   *
   * <p>- out:    Path to the output file. Must end with a filename with a `.txt` or `.svg`
   * extension.
   *
   * <p>- speed:  [Optional - defaults to 1], speed at which to run the automation.
   *
   * @param args the input that allows the user to specify what kind of animation they desire.
   */
  public static void main(String[] args) {
    IController controller;
    String in = "";
    int ticksPS = 1;
    String viewType = "";
    String fileName = "";

    if (!(Arrays.asList(args).contains("-in")
        && (Arrays.asList(args).contains("-view")))) {
      popUpError("------ Error: View and Input Arguments Required.");
    }

    for (int i = 0; i < args.length; i++) {

      if (args[i].equals("-in")) {
        try {
          in = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: File not specified");
        }
      }

      if (args[i].equals("-view")) {
        try {
          viewType = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: View not specified");
        }
      }

      if (args[i].equals("-out")) {
        fileName = args[i + 1];
      }

      if (args[i].equals("-speed")) {
        try {
          int newSpeed = Integer.parseInt(args[i + 1]);
          if (newSpeed > 0) {
            ticksPS = newSpeed;
          } else {
            popUpError("------ Error: Speed must be a positive integer.");
          }
        } catch (NumberFormatException e) {
          popUpError("------ Error: A valid speed must be specified.");
        } catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: Speed argument not specified.");
        }
      }
    }

    controller = new Controller(in, fileName, viewType, ticksPS);
    controller.play();

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
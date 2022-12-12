package cs5004.animator;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.IAnimationBuilder;
import cs5004.animator.view.IViewFile;
import cs5004.animator.view.IViewGUI;
import cs5004.animator.view.ViewFile;
import cs5004.animator.view.ViewGUIEditor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Arrays;

import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.model.IModel;
import cs5004.animator.view.ViewGUISimple;
import cs5004.animator.view.IView;

/**
 * Main class used to execute the Easy Animator program.
 */
public final class EasyAnimator {

  /**
   * Initializes the model and the view based on the given arguments and runs an animation.
   * <p>
   * <p>
   * Arguments:
   * <p>
   * - jar:    The .jar file to use for the program.
   * <p>
   * - view:   `text` for a text view, `svg` to generate an .svg file, `visual` for a simple gui
   * with no editor, `gui` / `editor` for an editor user interface.
   * <p>
   * - in:     Path to the input .txt file.
   * <p>
   * - out:    Path to the output file. Must end with a filename with a `.txt` or `.svg` extension.
   * <p>
   * - speed:  [Optional - defaults to 1], speed at which to run the automation.
   *
   * @param args the input that allows the user to specify what kind of animation they desire.
   */
  public static void main(String[] args) {
    IAnimationBuilder playbackBuilder = new AnimationBuilder();
    IAnimationBuilder editBuilder = new AnimationBuilder();
    IModel model;
    IViewFile textView;
    IViewGUI editView;
    IController controller;
    Readable in = new StringReader("");
    int ticksPS = 1;
    String viewType = "";
    Appendable out = System.out;
    FileWriter writer = null;
    String fileName = "";

    if (!(Arrays.asList(args).contains("-in")
        && (Arrays.asList(args).contains("-view")))) {
      popUpError("------ Error: View and Input Arguments Required.");
    }

    for (int i = 0; i < args.length; i++) {

      if (args[i].equals("-in")) {
        try {
          in = new FileReader(args[i + 1]);
        } catch (FileNotFoundException e) {
          popUpError("------ Error: File not found");
          System.out.println(e.getMessage());
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

    if (viewType.equals("gui") || viewType.equals("editor")) {
      AnimationReader.parseFile(in, editBuilder);
      editView = new ViewGUIEditor(ticksPS);
      controller = new Controller(editBuilder, editView, ticksPS);
      controller.start();
      return;
    }

    model = AnimationReader.parseFile(in, playbackBuilder);

    switch (viewType) {
      //“text”, “svg”, or “visual”
      case "text":
        textView = new ViewFile(model.getModelAsText(), fileName);
        textView.play();
        break;
      case "svg":
        textView = new ViewFile(model.getSVGTags(ticksPS), fileName);
        textView.play();
        break;
      case "visual":
        IView view = new ViewGUISimple(model, ticksPS);
        view.play();
        return;
      default:
        popUpError(
            "------ Error: Valid view type was not specified ('text', 'svg', 'visual', 'gui')");
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
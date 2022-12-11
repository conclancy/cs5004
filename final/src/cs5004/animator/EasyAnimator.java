package cs5004.animator;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.IAnimationBuilder;
import cs5004.animator.view.IVewText;
import cs5004.animator.view.IViewGUI;
import cs5004.animator.view.ViewGUIEditor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.model.IModel;
import cs5004.animator.view.ViewGUISimple;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGStringGenerator;
import cs5004.animator.view.VewText;

/**
 * Represents the main class that creates and runs an animation based on the given file.
 */
public final class EasyAnimator {

  /**
   * The main function that initializes the model and the view based on the given arguments and
   * runs an animation.
   * @param args the input that allows the user to specify what kind of animation they desire.
   */
  public static void main(String [] args) {
    IAnimationBuilder playbackBuilder = new AnimationBuilder();
    IAnimationBuilder editBuilder = new AnimationBuilder();
    IModel model;
    IVewText textView;
    IViewGUI editView;
    IController controller;
    Readable in = new StringReader("");
    int ticksPS = 1;
    String viewType = "";
    Appendable out = System.out;
    FileWriter writer = null;

    if (!(Arrays.stream(args).anyMatch("-in"::equals)
            && (Arrays.stream(args).anyMatch("-view"::equals)))) {
      popUpError("------ Error: View and Input Arguments Required.");
    }

    for (int i = 0; i < args.length; i++) {

      if (args[i].equals("-in")) {
        try {
          in = new FileReader(args[i + 1]);
        }
        catch (FileNotFoundException e) {
          popUpError("------ Error: File not found");
        }
        catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: File not specified");
        }
      }

      if (args[i].equals("-view")) {
        try {
          viewType = args[i + 1];
        }
        catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: View not specified");
        }
      }

      if (args[i].equals("-out")) {
        try {
          writer = new FileWriter(args[i + 1]);
        }
        catch (IOException e) {
          popUpError("------ Error: Output file cannot be created.");
        }
        catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: Output file not defined.");
        }
      }

      if (args[i].equals("-speed")) {
        try {
          int newSpeed = Integer.parseInt(args[i + 1]);
          if (newSpeed > 0) {
            ticksPS = newSpeed;
          }
          else {
            popUpError("------ Error: Speed must be a positive integer.");
          }
        }
        catch (NumberFormatException e) {
          popUpError("------ Error: A valid speed must be specified.");
        }
        catch (IndexOutOfBoundsException e) {
          popUpError("------ Error: Speed argument not specified.");
        }
      }
    }

    if (viewType.equals("playback")) {
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
        textView = new VewText(model);
        break;
      case "svg":
        textView = new SVGStringGenerator(model, ticksPS);
        break;
      case "visual":
        IView view = new ViewGUISimple(model, ticksPS);
        view.play();
        return;
      default:
        popUpError("------ Error: Valid view type was not specified ('text', 'svg', 'visual')");
        return;
    }

    textView.play();

    try {
      if (writer != null) {
        writer.append(textView.getText());
        writer.close();
      }
      else {
        out.append(textView.getText());
      }
    }
    catch (IOException e) {
      popUpError("------ Output Error: File cannot be written. Please check parameters.");
    }
  }

  private static void popUpError(String message) {
    JOptionPane.showMessageDialog(null, message,
            "------ Animation Error: An error occurred", 0);
    System.exit(1);
  }
}
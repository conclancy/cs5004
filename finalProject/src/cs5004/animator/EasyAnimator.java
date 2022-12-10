package cs5004.animator;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.ViewGUI;
import cs5004.animator.view.ViewText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EasyAnimator {

  public static void main(String[] args) {
    String input = "[Not Set]";
    String output = "[Not Set]";
    String view = "[Not Set]";
    int speed = -1;

    String string = String.join(" ", args);

    Scanner scanner = new Scanner(string).useDelimiter("\\s");

    while (scanner.hasNext()) {
      String cmd = scanner.next();

      switch (cmd) {
        case "-in":
          input = scanner.next();
          break;
        case "-out":
          output = scanner.next();
          break;
        case "-view":
          view = scanner.next().toLowerCase();
          break;
        case "-speed":
          speed = scanner.nextInt();
          break;
        default:
          // scanner.next();
      }
    }

    scanner.close();

    System.out.println("input:  " + input);
    System.out.println("output: " + output);
    System.out.println("view:   " + view);
    System.out.println("speed:  " + speed);

    try {
      IModel model = (IModel) AnimationReader.parseFile(new BufferedReader(new FileReader(input)),
          new AnimationBuilder());

      IController controller;

      switch (view) {
        case "text":
          controller = new Controller(model, new ViewText(System.out));
          break;
        case "svg":
          controller = new Controller(model,
              new ViewText(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output),
                  StandardCharsets.UTF_8))));
          break;
        case "visual":
          controller = new Controller(model, new ViewGUI());
          break;
        default:
          throw new IllegalArgumentException(
              "Must enter a valid view type of `text`, `svg`, or `visual`");
      }

      controller.play(speed);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e + ". This program must be launch from the command line!");
    }
  }
}
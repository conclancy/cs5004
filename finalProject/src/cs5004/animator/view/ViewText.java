package cs5004.animator.view;

import cs5004.animator.controller.IController;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ViewText implements  IView {
  private Appendable output;
  private IController controller;
  private String format;

  public ViewText(Appendable text) {
    this.output = text;
    this.format = "TEXT";
  }

  public ViewText(Writer svg) {
    this.output = svg;
    this.format = "SVG";
  }


  /**
   * Display the view at the provided speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  @Override
  public void display(int speed) {
    switch(this.format) {
      case "TEXT":
        displayText(speed);
        break;
      case "SVG":
        displaySVG();
        break;
      default:
    }
  }

  /**
   * Display the text as a system output on the command line.
   *
   * @param speed at which the lines should appear on the screen.
   */
  private void displayText(int speed) {
    Scanner scan = new Scanner(this.controller.getSVGTextOutput());

    while (scan.hasNext()) {
      try {
        int milli = 1000/speed;
        Thread.sleep(milli);
        this.output.append(scan.next()).append("\n");
      } catch (InterruptedException | IOException e) {
        e.printStackTrace();
      }
    }

    scan.close();
  }

  /**
   * Generate an SVG file as the output.
   */
  private void displaySVG() {
    Writer writer = (Writer) output;

    try {
      String svg = this.controller.getSVGTextOutput();
      writer.write(svg);
      writer.close();
      System.out.println("SVG file write process complete.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Give the view access to the controller.
   *
   * @param controller for the EasyAutomation program.
   */
  @Override
  public void addController(IController controller) {
    this.controller = controller;
  }
}

package cs5004.animator.view;

import cs5004.animator.controller.IController;
import java.io.IOException;
import java.util.Scanner;

public class ViewText implements  IView {
  private Appendable output;
  private IController controller;


  /**
   * Display the view at the provided speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  @Override
  public void display(int speed) {
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
   * Give the view access to the controller.
   *
   * @param controller for the EasyAutomation program.
   */
  @Override
  public void addController(IController controller) {
    this.controller = controller;
  }
}

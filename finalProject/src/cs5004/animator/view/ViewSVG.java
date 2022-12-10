package cs5004.animator.view;

import cs5004.animator.controller.IController;
import java.io.IOException;
import java.io.Writer;

public class ViewSVG implements IView {

  private Appendable output;
  private IController controller;

  /**
   * Display the view at the provided speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  @Override
  public void display(int speed) {
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

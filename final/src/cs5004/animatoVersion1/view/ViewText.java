package cs5004.animatoVersion1.view;

import cs5004.animatoVersion1.controller.ControllerGUI;

public class ViewText implements IViewText {

  private Appendable output;

  public ViewText(Appendable out) {
    this.output = out;
  }

  /**
   * Display the EasyAnimation using the view.
   */
  @Override
  public void display(ControllerGUI controller) {

  }

  /**
   * Display the animation in text form.
   *
   * @return the animation, as a String.
   */
  @Override
  public String getText() {
    return null;
  }
}

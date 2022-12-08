package cs5004.animator;

import cs5004.animator.controller.ControllerGUI;
import cs5004.animator.view.ViewGUI;

public class EasyAnimator {

  public static void main(String[] args) {

    new ControllerGUI(new ViewGUI());
  }

}

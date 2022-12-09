package cs5004.animatoVersion1.controller;

import cs5004.animatoVersion1.model.Color;
import cs5004.animatoVersion1.model.IEasyAutomation;
import cs5004.animatoVersion1.view.IViewGUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerGUI implements IController, ActionListener {

  private IViewGUI view;
  private IEasyAutomation model;

  public ControllerGUI(IViewGUI view) {
    this.view = view;
    this.model = null;
  }

  /**
   * Begin the current automation.
   */
  @Override
  public void start(IEasyAutomation model) {
    view.display(this);
    this.model = model;
  }

  /**
   * Listening for events happening in the model.
   *
   * @param e the event occurring, as an ActionEvent.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();

    if(c.getName().equals("addShape")) {
      double x = this.view.getCoordinateX();

      try {
        model.addRectangle("test", x,1,1,1,new Color("000000"));
      } catch(IllegalArgumentException err) {
        view.showMessage(err.getMessage());
      }

      // TODO add a method here that gets a list of all the shapes from the Model.

    }
  }
}

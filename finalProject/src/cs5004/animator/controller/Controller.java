package cs5004.animator.controller;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewGUI;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the controller for the EasyAutomation program.
 */
public class Controller implements IController {

  private IView view;
  private IModel model;

  /**
   * Constructor for a Controller object.
   *
   * @param model the EasyAnimator model.
   * @param view  the EasyAnimator view desired for this instance of the program.
   */
  public Controller(IModel model, IView view) {
    this.model = model;
    this.view = view;
    this.view.addController(this);
  }

  /**
   * Plays an animation at a given speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  @Override
  public void play(int speed) {
    if (this.view instanceof ViewGUI) {
      ((ViewGUI) this.view).setGUIDimensions(
          this.model.getX(),
          this.model.getY(),
          this.model.getWidth(),
          this.model.getHeight());
    }
  }

  /**
   * Get a list of shapes from the model at a given tick.
   *
   * @param tick the tick where we need to locate state from.
   * @return a list of {@link IShape} and their state at the {@param tick}.
   */
  @Override
  public List<IShape> getShapesAtTick(int tick) {
     return this.model.getStateByTick(tick);
  }

  /**
   * Get the text view of the model.
   *
   * @return the model in an easy-to-read string format.
   */
  @Override
  public String getTextOutput() {
    return null;
  }

  /**
   * Get the SVG text of the model.
   *
   * @return the model in SVG text format.
   */
  @Override
  public String getSVGTextOutput() {
    return null;
  }

  /**
   * Get the length of the animation in ticks.
   *
   * @return the number of ticks in the animation, as an int.
   */
  @Override
  public int getEndTick() {
    return 0;
  }
}

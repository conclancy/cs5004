package cs5004.animator.controller;

import cs5004.animator.model.IShape;
import java.util.List;

/**
 * Controller for the easy automation program.
 */
public interface IController {

  /**
   * Plays an animation at a given speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  void play(int speed);

  /**
   * Get a list of shapes from the model at a given tick.
   *
   * @param tick the tick where we need to locate state from.
   * @return a list of {@link IShape} and their state at the {@param tick}.
   */
  List<IShape> getShapesAtTick(int tick);

  /**
   * Get the text view of the model.
   *
   * @return the model in an easy-to-read string format.
   */
  String getTextOutput();

  /**
   * Get the SVG text of the model.
   *
   * @return the model in SVG text format.
   */
  String getSVGTextOutput();

  /**
   * Get the length of the animation in ticks.
   *
   * @return the number of ticks in the animation, as an int.
   */
  int getEndTick();

}

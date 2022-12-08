package cs5004.animator.view;

public interface IViewGUI extends IView {

  /**
   * Get the X coordinate recorded in the view.
   *
   * @return the X coordinate from the view.
   */
  double getCoordinateX();

  /**
   * Display a message to the end user.
   *
   * @param m the message to be displayed, as a String.
   */
  void showMessage(String m);
}

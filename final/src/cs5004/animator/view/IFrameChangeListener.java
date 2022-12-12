package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * Interface for frame change {@link ActionListener} object in the {@link ViewGUIEditor}.
 */
public interface IFrameChangeListener extends ActionListener {

  /**
   * Listen for changes within animation frames.
   *
   * @param event representing changes within one of the frames.
   */
  void frameChanged(IFrameChangeEvent event);
}

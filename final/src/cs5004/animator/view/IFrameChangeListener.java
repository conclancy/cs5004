package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * This interface represents the listener that will receive events when users make a change in
 * frames.
 */
public interface IFrameChangeListener extends ActionListener {

  /**
   * Change frames when a change action is received.
   */
  void frameChanged(IFrameChangeEvent event);
}

package cs5004.animator.controller;

import cs5004.animator.model.IEasyAutomation;

/**
 * This class represents the controller for the EasyAnimation program.
 */
public interface IController {

  /**
   * Begin the current automation.
   */
  void start(IEasyAutomation model);
}

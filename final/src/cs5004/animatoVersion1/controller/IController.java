package cs5004.animatoVersion1.controller;

import cs5004.animatoVersion1.model.IEasyAutomation;

/**
 * This class represents the controller for the EasyAnimation program.
 */
public interface IController {

  /**
   * Begin the current automation.
   */
  void start(IEasyAutomation model);
}

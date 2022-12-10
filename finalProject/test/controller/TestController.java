package controller;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.model.Model;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.IAnimationBuilder;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewGUI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

public class TestController {

  private IController controller;
  private IView view;
  private IModel model;
  private IAnimationBuilder builder;


  @Before
  public void init() {
    this.builder = new AnimationBuilder();

    FileReader in;

    try {
      in = new FileReader("./smalldemo.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    AnimationReader.parseFile(in, this.builder);

    this.view = new ViewGUI();
    this.model = new Model();
    this.controller = new Controller(model, view);
  }

  @Test
  public void testAddShape() {
    this.controller.play(1);

    this.controller.ad
  }
}

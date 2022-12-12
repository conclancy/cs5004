package cs5004.animator.controller;

import cs5004.animator.util.IAnimationBuilder;
import cs5004.animator.view.IViewGUI;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.view.EFrameChangeType;
import cs5004.animator.view.FrameChangeEvent;
import cs5004.animator.view.IFrameChangeEvent;
import cs5004.animator.view.IShapeCell;
import cs5004.animator.view.IShapeChangeEvent;
import cs5004.animator.view.ShapeCell;
import cs5004.animator.view.EShapeChangeType;
import cs5004.animator.view.ShapeChangeEvent;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the controller to ensure proper functionality in the playback view.
 */
public class ControllerTest {
  private Controller controller;
  private IViewGUI view;
  private Map<String, List<IFrame>> originalFrames;

  @Before
  public void setUp() {

    controller = new Controller("./starter_code/smalldemo.txt", "./controller_test.txt", "text", 20);
    this.initOriginalFrames();
  }

  private void initOriginalFrames() {
    List<IFrame> rect = new ArrayList<>();
    rect.add(new Frame(1, 200, 200, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new Frame(10, 200, 200, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new Frame(50, 300, 300, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new Frame(51, 300, 300, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new Frame(70, 300, 300, 25, 100, 0, new Color(255, 0, 0)));
    rect.add(new Frame(100, 200, 200, 25, 100, 0, new Color(255, 0, 0)));

    List<IFrame> ellipse = new ArrayList<>();
    ellipse.add(new Frame(6, 440, 70, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new Frame(20, 440, 70, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new Frame(50, 440, 250, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new Frame(70, 440, 370, 120, 60, 0, new Color(0, 170, 85)));
    ellipse.add(new Frame(80, 440, 370, 120, 60, 0, new Color(0, 255, 0)));
    ellipse.add(new Frame(100, 440, 370, 120, 60, 0, new Color(0, 255, 0)));

    this.originalFrames = new LinkedHashMap<>();
    this.originalFrames.put("R", rect);
    this.originalFrames.put("C", ellipse);
  }

  @Test
  public void testKeyframeAddEnd() {
    this.controller.play();
    assertEquals(this.originalFrames, this.originalFrames);
    IFrameChangeEvent event = new FrameChangeEvent(this.view, EFrameChangeType.ADD,
            "R", 110, 200, 200, 25, 125, 10, new Color(255, 0, 0));
    this.controller.frameChanged(event);
    this.originalFrames.get("R").add(new Frame(110, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getFrame());
  }

  @Test
  public void testMainKeyframeAddMiddleValue() {
    this.controller.play();
    IFrameChangeEvent event = new FrameChangeEvent(this.view, EFrameChangeType.ADD,
            "R", 80, 200, 200, 25, 125, 1000, new Color(255, 0, 0));
    this.controller.frameChanged(event);
    this.originalFrames.get("R").add(5, new Frame(80, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getFrame());
  }

  @Test
  public void testMainKeyframeAddFrontValue() {
    this.controller.play();
    IFrameChangeEvent event = new FrameChangeEvent(this.view, EFrameChangeType.ADD,
            "C", 1, 440, 70, 120, 60, 1000, new Color(0, 0, 255));
    this.controller.frameChanged(event);
    this.originalFrames.get("C").add(0, new Frame(1, 440, 70, 120, 60, 0,
            new Color(0, 0, 255)));
    assertEquals(this.originalFrames, this.view.getFrame());
  }

  @Test
  public void testMainKeyframeEditText() {
    this.controller.play();
    IFrameChangeEvent event = new FrameChangeEvent(this.view, EFrameChangeType.EDIT,
            "R", 100, 200, 200, 25, 125, 1000, new Color(255, 0, 0));
    this.controller.frameChanged(event);
    this.originalFrames.get("R").remove(5);
    this.originalFrames.get("R").add(new Frame(100, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getFrame());
  }

  @Test
  public void testMainKeyframeDeleteFunction() {
    this.controller.play();
    IFrameChangeEvent event = new FrameChangeEvent(this.view, EFrameChangeType.DELETE,
            "R", 100, 0, 0, 0, 0, 10000, new Color(0));
    this.controller.frameChanged(event);
    this.originalFrames.get("R").remove(5);
    assertEquals(this.originalFrames, this.view.getFrame());
  }

  @Test
  public void testShapeAdd() {
    this.controller.play();
    IShapeChangeEvent event = new ShapeChangeEvent(this.view, EShapeChangeType.ADD,
            "Rectangle", "rectangle");
    this.controller.shapeChanged(event);
    List<IShapeCell> shapes = new ArrayList<>();
    shapes.add(new ShapeCell("R", "Rectangle"));
    shapes.add(new ShapeCell("C", "Ellipse"));
    shapes.add(new ShapeCell("rectangle", "Rectangle"));
    assertEquals(shapes, this.view.getShapes());
  }

  @Test
  public void testShapeDelete() {
    this.controller.play();
    IShapeChangeEvent event = new ShapeChangeEvent(this.view, EShapeChangeType.DELETE,
            "", "R");
    this.controller.shapeChanged(event);
    List<IShapeCell> shapes = new ArrayList<>();
    shapes.add(new ShapeCell("C", "Ellipse"));
    assertEquals(shapes, this.view.getShapes());
  }

}

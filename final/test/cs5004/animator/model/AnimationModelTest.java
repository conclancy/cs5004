package cs5004.animator.model;

import cs5004.animator.util.AnimationBuilder;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import java.awt.Color;


import static org.junit.Assert.assertEquals;

/**
 * Tests for the animation model class.
 */
public class AnimationModelTest {

  private AnimationBuilder aniBuilder;
  private IModel model;

  @Before
  public void setUp() {
    this.aniBuilder = new AnimationBuilder();
  }

  @Test
  public void testBuildAndConstructor() {
    this.aniBuilder = new AnimationBuilder();
    this.model = this.aniBuilder.build();
    assertEquals("", this.model.toString());
  }

  @Test
  public void testAddProcess() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 0, 0, 10, 10, 255, 255, 255, 50, 20,
            20, 10, 100, 255, 255, 255);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Movement R 0 0.0 0.0 10 10 255 255 255    50 20.0 20.0 10 100 255 255 255",
        this.model.toString());
  }


  @Test
  public void testAddOtherProcess() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 0, 0, 10, 10, 255, 255, 255, 50, 20,
            20, 10, 100, 255, 255, 255);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Movement R 0 0.0 0.0 10 10 255 255 255    50 20.0 20.0 10 100 255 255 255",
        this.model.toString());
  }

  @Test
  public void testAddTwoProcesss() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 0, 0, 10, 10, 255, 255, 255, 50, 20,
            20, 10, 100, 255, 255, 255)
        .addAnimation("R", 50, 20, 20, 10, 100, 255, 255, 255, 100, 40,
            40, 100, 100, 255, 255, 255);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Movement R 0 0.0 0.0 10 10 255 255 255    50 20.0 20.0 10 100 255 255 255\n"
            + "Movement R 50 20.0 20.0 10 100 255 255 255    "
            + "100 40.0 40.0 100 100 255 255 255",
        this.model.toString());
  }

  @Test
  public void testAddTwoProcesssOutOfOrder() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 5, 5, 10, 10, 0, 0, 255, 5, 10,
            10, 15, 15, 0, 255, 0)

        .addAnimation("R", 5, 10, 10, 15, 15, 0, 255, 0, 15, 20,
            20, 10, 30, 0, 255, 0)

        .declareShape("C", "Ellipse")

        .addAnimation("C", 0, 5, 5, 20, 20, 0, 0, 255, 10, 20,
            20, 20, 20, 100, 0, 0)

        .addAnimation("C", 10, 20, 20, 20, 20, 100, 0, 0, 22, 30,
            30, 20, 20, 255, 0, 0);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Color change R 0 5.0 5.0 10 10 0 0 255    5 10.0 10.0 15 15 0 255 0\n"
            + "Movement R 5 10.0 10.0 15 15 0 255 0    15 20.0 20.0 10 30 0 255 0\n"
            + "\n"
            + "Shape C Ellipse\n"
            + "Color change C 0 5.0 5.0 20 20 0 0 255    10 20.0 20.0 20 20 100 0 0\n"
            + "Color change C 10 20.0 20.0 20 20 100 0 0    22 30.0 30.0 20 20 255 0 0",
        this.model.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeFailsSameIds() {
    this.aniBuilder.declareShape("rectangle", "Rectangle").declareShape("rectangle", "Ellipse");
  }

  @Test(expected = IllegalStateException.class)
  public void testAddProcessFailsDiffIds() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20,
            10, 10, 30, 0, 255, 0)
        .addAnimation("Steve", 5, 20, 10, 10, 30, 0, 255, 0, 10, 20,
            20, 10, 30, 0, 255, 0);
    this.model = this.aniBuilder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddProcesssInvalidOverlap() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20,
            10, 10, 30, 0, 255, 0)
        .addAnimation("rectangle", 3, 16, 10, 10, 30, 0, 255, 0, 7, 26,
            15, 10, 30, 0, 255, 0);
    this.model = this.aniBuilder.build();
    assertEquals("Shape rectangle Rectangle\n"
            + "Motion rectangle 0 10.0 10.0 10 30 0 255 0    5 20.0 10.0 10 30 0 255 0",
        this.model.toString());
  }

  @Test //processes who start on the same tick that another ends are valid and work.
  public void testAddProcesssValidOverlap() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20,
            10, 10, 30, 0, 255, 0)
        .addAnimation("R", 5, 20, 10, 10, 30, 0, 255, 0, 8, 20,
            10, 10, 30, 0, 0, 255);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Movement R 0 10.0 10.0 10 30 0 255 0    5 20.0 10.0 10 30 0 255 0\n"
            + "Color change R 5 20.0 10.0 10 30 0 255 0    8 20.0 10.0 10 30 0 0 255",
        this.model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddProcesssInvalidTeleport() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20,
            10, 10, 30, 0, 255, 0)
        .addAnimation("rectangle", 5, 35, 10, 10, 30, 0, 255, 0, 10, 45,
            15, 10, 30, 0, 255, 0);
    this.model = this.aniBuilder.build();
    assertEquals("Shape rectangle Rectangle\n"
            + "Motion rectangle 0 10.0 10.0 10 30 0 255 0    5 20.0 10.0 10 30 0 255 0",
        this.model.toString());
  }

  @Test
  public void testGetStateEmptyModel() {
    this.model = this.aniBuilder.build();
    assertEquals(new ArrayList<IShape>(),
        this.model.getState(3));
  }

  @Test
  public void testGetState() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20, 10,
            10, 30, 0, 255, 0);
    this.model = this.aniBuilder.build();
    List<IShape> expected = new ArrayList<>(Arrays.asList(
        new Rectangle(10, 30, new Point2D.Double(10, 10), 0, Color.GREEN, "test")));
    assertEquals(expected, this.model.getState(0));
  }

  @Test
  public void testToString() {
    this.aniBuilder.declareShape("R", "Rectangle")
        .addAnimation("R", 0, 5, 5, 5, 22, 0, 255, 0, 5, 15, 15,
            15, 22, 0, 255, 0)
        .addAnimation("R", 5, 15, 15, 15, 22, 0, 255, 0, 33, 33, 33,
            33, 33, 0, 255, 0)
        .declareShape("C", "Ellipse")
        .addAnimation("C", 0, 1, 1, 20, 20, 255, 255, 255, 10, 20, 20,
            20, 20, 255, 0, 0);
    this.model = this.aniBuilder.build();
    assertEquals("Shape R Rectangle\n"
            + "Movement R 0 5.0 5.0 5 22 0 255 0    5 15.0 15.0 15 22 0 255 0\n"
            + "Movement R 5 15.0 15.0 15 22 0 255 0    33 33.0 33.0 33 33 0 255 0\n"
            + "\n"
            + "Shape C Ellipse\n"
            + "Color change C 0 1.0 1.0 20 20 255 255 255    10 20.0 20.0 20 20 255 0 0",
        this.model.toString());
  }


  @Test
  public void testGetWidth() {
    this.model = this.aniBuilder.build();
    assertEquals(model.getWidth(), 1000);
  }

  @Test
  public void testGetHeight() {
    this.model = this.aniBuilder.build();
    assertEquals(model.getHeight(), 600);
  }

  @Test
  public void testGetProcesss() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5, 20,
            10, 10, 30, 0, 255, 0)
        .addAnimation("rectangle", 5, 20, 10, 10, 30, 0, 255, 0, 10, 20,
            20, 10, 30, 0, 255, 0)
        .declareShape("Steve", "Ellipse")
        .addAnimation("Steve", 0, 1, 1, 20, 20, 255, 0, 0, 10, 20,
            20, 20, 20, 255, 0, 0);
    this.model = this.aniBuilder.build();
    LinkedHashMap<String, List<IAnimation>> map = new LinkedHashMap<>();
    List<IAnimation> processes1 = new ArrayList<>();
    List<IAnimation> processes2 = new ArrayList<>();
    processes1.add(new Animation("Motion", 0, 10, 10, 10, 30, 255, 0, 5,
        20, 10, 10, 30, 0, 0, 0, 0));
    processes1.add(new Animation("Motion", 6, 20, 10, 10, 30, 255, 0, 10,
        20, 20, 10, 30, 0, 0, 0, 0));
    processes2.add(new Animation("Motion", 0, 1, 1, 20, 20, 0, 0, 10,
        20, 20, 20, 20, 0, 0, 0, 0));
    map.put("rectangle", processes1);
    map.put("Steve", processes2);
    assertEquals(model.getAnimations().get("rectangle").get(1).getStartX(), 20);
    assertEquals(model.getAnimations().get("Steve").get(0).getEndWidth(), 20);
  }

  @Test
  public void testGetFinalTick() {
    this.aniBuilder.declareShape("rectangle", "Rectangle")
        .addAnimation("rectangle", 0, 10, 10, 10, 30, 0, 255, 0, 5,
            20, 10, 10, 30, 0, 255, 0)
        .addAnimation("rectangle", 5, 20, 10, 10, 30, 0, 255, 0, 10,
            20, 20, 10, 30, 0, 255, 0)
        .declareShape("Steve", "Ellipse")
        .addAnimation("Steve", 0, 1, 1, 20, 20, 255, 0, 0, 30,
            20, 20, 20, 20, 255, 0, 0);
    this.model = this.aniBuilder.build();
    assertEquals(model.getLastTick(), 30);
  }
}
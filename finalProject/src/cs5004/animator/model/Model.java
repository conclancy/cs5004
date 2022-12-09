package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Model implements IModel {

  private int x;
  private int y;
  private int width;
  private int height;
  private int lastTick;
  private HashMap<String, IShape> shapes;
  private List<IAnimation> animations;

  /**
   * Constructor for the model.
   */
  public Model() {
    this.x = 0;
    this.y = 0;
    this.width = 1000;
    this.height = 600;
    this.lastTick = 0;
    this.shapes = new HashMap<String, IShape>();
    this.animations = new ArrayList<IAnimation>();
  }

  /**
   * Add a shape to the model.
   *
   * @param name      the name of the shape, as a String.
   * @param shapeType the type of the shape, as a String, accepts 'RECTANGLE' or 'OVAL'.
   * @throws IllegalArgumentException if the {@param shapeType} does not receive a valid input.
   */
  @Override
  public void addShape(String name, String shapeType) throws IllegalArgumentException {
    switch (shapeType) {
      case "RECTANGLE":
        this.shapes.put(name, new Rectangle(name));
        break;
      case "OVAL":
        this.shapes.put(name, new Oval(name));
        break;
      default:
        throw new IllegalArgumentException("shapeType must be 'RECTANGLE' or 'OVAL'.");
    }
  }

  /**
   * Add an animation to the model.
   *
   * @param name      the name of the animation, as a String.
   * @param animation the animation, as an {@link IAnimation} object.
   * @throws NoSuchElementException if the shape name is not in the animation.
   */
  @Override
  public void addAnimation(String name, IAnimation animation) throws NoSuchElementException {

    IShape shape = this.shapes.get(name);

    if (shape == null) {
      throw new NoSuchElementException("The shape name provided does not exits.");
    }

    animation.setShape(shape);

    if (animation.getEndTick() > this.lastTick) {
      this.lastTick = animation.getEndTick();
    }

    int min = 0;
    int max = this.animations.size() - 1;

    while (min <= max) {
      int mid = (min + max) / 2;
      IAnimation midAnimation = this.animations.get(mid);

      if (midAnimation.getStartTick() == animation.getStartTick()) {
        min = mid;
        break;
      } else if (midAnimation.getStartTick() < animation.getStartTick()) {
        min = mid + 1;
      } else {
        max = mid - 1;
      }
    }

    this.animations.add(min, animation);


  }

  /**
   * Set the model's canvas properties.
   *
   * @param x      the X coordinate, as an int.
   * @param y      the Y coordinate, as an int.
   * @param width  the width of the canvass, as an int.
   * @param height the height of the canvass, as an int.
   */
  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Get the x coordinate of the canvass.
   *
   * @return the x coordinate of the canvass, as an int.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Get the y coordinate of the canvass.
   *
   * @return the y coordinate of the canvass, as an int.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Get the width coordinate of the canvass.
   *
   * @return the width coordinate of the canvass, as an int.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height coordinate of the canvass.
   *
   * @return the height coordinate of the canvass, as an int.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the state of shapes at a specific time to rendered on the canvas.
   *
   * @param tick the desired tick.
   * @return a list of shapes in their state at the given tick.
   */
  @Override
  public List<IShape> getStateByTick(int tick) {

    LinkedHashMap<String, IShape> temp = new LinkedHashMap<>();

    for (IAnimation animation : animations) {

      if (animation.getStartTick() > tick) {
        break;
      }

      String shapeName = animation.getShape().getName();

      if (temp.get(shapeName) == null) {
        temp.put(shapeName, animation.getShape().copy());
      }

      IShape shape = temp.get(shapeName);
      animation.setTickInterval(shape, tick);
    }

    return new ArrayList<IShape>(temp.values());
  }

  /**
   * The entire model formatted as an SVG string.
   *
   * @return entire model formatted as an SVG string.
   */
  @Override
  public String getSVG() {

    StringBuilder svg = new StringBuilder();

    svg.append(String.format(
        "<svg width='%d' height='%d' version='1.1' xmlns='http://www.w3.org/2000/svg'>\n",
        this.width, this.height));

    HashMap<String, IShape> temp = new HashMap<>();

    return null;
  }

  /**
   * Get the value of the final tick in the animation.
   *
   * @return the final tick value, as an int.
   */
  @Override
  public int getEndTick() {
    return 0;
  }
}

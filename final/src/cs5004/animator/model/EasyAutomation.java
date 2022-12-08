package cs5004.animator.model;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Represents the model for the Easy Automation program.  Each instance of this class represents a
 * single instance of the easy automation program.
 */
public class EasyAutomation implements IEasyAutomation {

  private List<IShape> automation;
  private double speed;

  /**
   * Constructor for a new EasyAutomation object.  The speed is by default set to 1.
   *
   * @throws IllegalArgumentException if the speed is less than or equal to 0.
   */
  public EasyAutomation() throws IllegalArgumentException {
    this.automation = new ArrayList<IShape>();
    this.speed = this.speedChecker(1);
  }

  /**
   * Constructor for a new EasyAutomation object.
   *
   * @param speed The desired speed of the animation.
   * @throws IllegalArgumentException if the speed is less than or equal to 0.
   */
  public EasyAutomation(double speed) throws IllegalArgumentException {
    this.automation = new ArrayList<IShape>();
    this.speed = this.speedChecker(speed);
  }

  /**
   * Checks to ensure the Speed value being passed is greater than 0.
   *
   * @param n the speed to check.
   * @return the checked speed, as a double.
   * @throws IllegalArgumentException if the speed is less than or equal to 0.
   */
  private double speedChecker(double n) throws IllegalArgumentException {
    if (n <= 0) {
      throw new IllegalArgumentException("Dimensions must be greater than 0.");
    } else {
      return n;
    }
  }


  /**
   * Add a new rectangle to the automation.
   *
   * @param name   the name of the rectangle.
   * @param x      coordinate of the horizontal location of the oval's center, as a double.
   * @param y      coordinate of the vertical location of the oval's center as a double.
   * @param width  the width of the rectangle, as a double.
   * @param height the height of the rectangle, as a double.
   * @param color  the color of the rectangle, a {@link Color} object.
   * @throws IllegalArgumentException if the width or height of the object is 0 or less.
   */
  @Override
  public void addRectangle(String name, double x, double y, double width, double height,
      Color color) throws IllegalArgumentException {
    this.automation.add(new Rectangle(this.nameChecker(name), x, y, width, height, color));
  }

  /**
   * Add a new Square to the automation.
   *
   * @param name       The name of the square.
   * @param x          coordinate of the horizontal location of the circle's center, as a double.
   * @param y          coordinate of the vertical location of the circle's center as a double.
   * @param sideLength the length of a single side of the square.
   * @param color      the color of the square.
   */
  @Override
  public void addSquare(String name, double x, double y, double sideLength, Color color)
      throws IllegalArgumentException {
    this.automation.add(new Square(this.nameChecker(name), x, y, sideLength, color));
  }

  /**
   * Add a new Oval to the automation.
   *
   * @param name      name of the oval.
   * @param x         coordinate of the horizontal location of the oval's center, as a double.
   * @param y         coordinate of the vertical location of the oval's center as a double.
   * @param majorAxis longest distance between the center point and the perimeter of the oval, as a
   *                  double.
   * @param minorAxis smallest distance between the center point and the perimeter of the oval, as a
   *                  double.
   * @param color     the color of the oval, as a {@link Color} object.
   * @throws IllegalArgumentException if either or both axis provided are less than or equal to 0.
   */
  @Override
  public void addOval(String name, double x, double y, double majorAxis, double minorAxis,
      Color color) throws IllegalArgumentException {
    this.automation.add(new Oval(this.nameChecker(name), x, y, majorAxis, minorAxis, color));
  }

  /**
   * Adds a new Circle to the automation.
   *
   * @param name   name of the circle.
   * @param x      coordinate of the horizontal location of the circle's center, as a double.
   * @param y      coordinate of the vertical location of the circle's center as a double.
   * @param radius distance between the center point and the perimeter of the circle, as a double.
   * @param color  the color of the circle, as a {@link Color} object.
   * @throws IllegalArgumentException if the radius provided is less than or equal to 0.
   */
  @Override
  public void addCircle(String name, double x, double y, double radius, Color color)
      throws IllegalArgumentException {
    this.automation.add(new Circle(this.nameChecker(name), x, y, radius, color));
  }

  /**
   * Set the speed of the automation.  Default speed is 1.0, speed greater than this will increase
   * speed, and speeds lower than 1.0 will decrease speed.
   *
   * @param speed the speed at which the automation will play back.
   * @throws IllegalArgumentException if the {@param speed} is less than or equal to 0.
   */
  @Override
  public void setSpeed(double speed) throws IllegalArgumentException {
    this.speed = this.speedChecker(speed);
  }

  /**
   * Get the current speed of the automation.
   *
   * @return the speed of the automation, as a double.
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return shapes available in the automation, as a List of IShape objects.
   */
  @Override
  public List<IShape> getShapes() {
    return this.automation;
  }

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return return a list of just the shape's names as a List of Strings.
   */
  @Override
  public List<String> getShapeNames() {
    return this.automation.stream()
        .map(IShape::getName)
        .collect(Collectors.toList());
  }

  /**
   * Get a shape within the automation by providing its name.  This will allow users to manipulate
   * the shape and add additional animations to the shape.
   *
   * @param name the name of the shape to be obtained.
   * @return the {@link IShape} the caller requested.
   * @throws NoSuchElementException if the {@param name} provided is not contained in the
   *                                Automation.
   */
  @Override
  public IShape getShapeByName(String name) throws NoSuchElementException {

    IShape temp = null;

    for (IShape shape : automation) {
      if (shape.getName() == name) {
        temp = shape;
        break;
      }
    }

    return temp;
  }

  /**
   * Check to ensure all shapes have a unique name.
   *
   * @param name the name to be checked.
   * @return the non-duplicate name, as a String.
   * @throws IllegalStateException if a shape in the automation already contains that name.
   */
  private String nameChecker(String name) throws IllegalStateException {
    if (this.getShapeByName(name) == null) {
      return name;
    } else {
      throw new IllegalStateException("A shape with this name already exists in the automation.  "
          + "Please pick a different unique name.");
    }
  }

  /**
   * Get the length of the automation as an int.  This is the last `end` value of the automation.
   *
   * @return the length of the automation, as an int.
   * @throws NoSuchElementException if there are no animations in the automation.
   */
  @Override
  public int getLength() throws NoSuchElementException{
    IShape temp = this.automation.stream()
        .max(Comparator.comparing(IShape::getAnimationLength))
        .orElseThrow(NoSuchElementException::new);

    return temp.getAnimationLength();
  }
}

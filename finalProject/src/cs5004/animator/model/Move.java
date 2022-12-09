package cs5004.animator.model;

/**
 * This class represents a move animation.
 */
public class Move extends AbstractAnimation {

  private Point2D start;
  private Point2D end;

  /**
   * Constructs a Move animation.
   *
   * @param startTick the starting tick for this animation.
   * @param endTick the ending tick for this animation.
   * @param startX the starting X coordinate.
   * @param startY the starting Y coordinate.
   * @param endX the starting X coordinate.
   * @param endY the starting Y coordinate.
   */
  public Move(int startTick, int endTick, int startX, int startY, int endX, int endY) {
    super(startTick, endTick);
    this.start = new Point2D(startX, startY);
    this.end = new Point2D(endX, endY);
  }

  /**
   * Set the {@link IShape} of the animation.
   *
   * @param shape the {@link IShape} for the automation.
   */
  @Override
  public void setShape(IShape shape) {
    super.shape = shape;

    if(super.startTick <= super.shape.getAppearsAt()) {
      super.shape.setAppearsAt(super.startTick);
      super.shape.setReference(this.start);
    } else {
      super.shape.setDisappearsAt(super.endTick);
    }
  }

  /**
   * Set the interval of an {@link IShape} by a tick value.
   *
   * @param shape the {@link IShape} for the animation.
   * @param tick  the length of the animation, as an int.
   * @throws IllegalArgumentException if the tick is a negative number.
   */
  @Override
  public void setTickInterval(IShape shape, int tick) throws IllegalArgumentException {

    int t = super.checkValidTick(tick);

    int x = tween(t, super.startTick, super.endTick, start.getX(), end.getX());
    int y = tween(t, super.startTick, super.endTick, start.getY(), end.getY());

    shape.setReference(new Point2D(x, y));
  }

  /**
   * Represents the animation as an SVG string.
   *
   * @return the animation as an SVG string.
   */
  @Override
  public String toString() {
    String xString  = String.format("<animate attributeType='xml' begin='%dms' "
            + "dur='%dms' attributeName='%s' from='%d' to='%d' fill='freeze' />",
        super.startTick * 1000,
        (super.endTick - super.startTick) * 1000,
        super.shape.getShapeType().equals("RECTANGLE")  ? "x" : "cx",
        this.start.getX(),
        this.end.getX()
    );

    String yString = String.format("<animate attributeType='xml' begin='%dms' "
            + "dur='%dms' attributeName='%s' from='%d' to='%d' fill='freeze' />",
        super.startTick * 1000,
        (super.endTick - super.startTick) * 1000,
        super.shape.getShapeType().equals("RECTANGLE") ? "y" : "cy",
        this.start.getY(),
        this.end.getY()
    );

    return xString + "\n" + yString;
  }
}

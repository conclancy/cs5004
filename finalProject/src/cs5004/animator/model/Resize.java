package cs5004.animator.model;

/**
 * This class represents a Resizing animation.
 */
public class Resize extends AbstractAnimation {

  private final int widthStart;
  private final int heightStart;
  private final int widthEnd;
  private final int heightEnd;

  /**
   * Constructor for the Resize animation.
   *
   * @param startTick   the starting tick for this animation.
   * @param endTick     the end tick for this animation.
   * @param widthStart  the starting width value.
   * @param heightStart the starting height value.
   * @param widthEnd    the ending width value.
   * @param heightEnd   the ending height value.
   * @throws IllegalArgumentException if any of the width or height values are less than 0.
   */
  public Resize(int startTick, int endTick, int widthStart, int heightStart, int widthEnd,
      int heightEnd) throws IllegalArgumentException {
    super(startTick, endTick);
    this.widthStart = super.checkValidTick(widthStart);
    this.heightStart = super.checkValidTick(heightStart);
    this.widthEnd = super.checkValidTick(widthEnd);
    this.heightEnd = super.checkValidTick(heightEnd);
  }

  /**
   * Set the {@link IShape} of the animation.
   *
   * @param shape the {@link IShape} for the automation.
   */
  @Override
  public void setShape(IShape shape) {
    super.shape = shape;

    if (super.startTick <= super.shape.getAppearsAt()) {
      super.shape.setAppearsAt(super.startTick);
      super.shape.setWidth(this.widthStart);
      super.shape.setHeight(this.heightStart);
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
    int width = tween(tick, super.startTick, super.endTick, widthStart, widthEnd);
    int height = tween(tick, super.startTick, super.endTick, heightStart, heightEnd);

    shape.setWidth(width);
    shape.setHeight(height);
  }

  /**
   * Return this animation formatted as an SVG string.
   *
   * @return this animation formatted as an SVG string.
   */
  @Override
  public String toString() {
    String width = String.format("<animate attributeType='xml' begin='%dms' "
            + "dur='%dms' attributeName='%s' from='%d' to='%d' fill='freeze' />",
        this.startTick * 1000,
        (this.endTick - this.startTick) * 1000,
        this.shape.getShapeType().equals("RECTANGLE") ? "width" : "rx",
        this.widthStart,
        this.widthEnd
    );

    String height = String.format("<animate attributeType='xml' begin='%dms' "
            + "dur='%dms' attributeName='%s' from='%d' to='%d' fill='freeze' />",
        this.startTick * 1000,
        (this.endTick - this.startTick) * 1000,
        this.shape.getShapeType().equals("RECTANGLE") ? "height" : "ry",
        this.heightStart,
        this.heightEnd
    );

    return width + "\n" + height;
  }
}

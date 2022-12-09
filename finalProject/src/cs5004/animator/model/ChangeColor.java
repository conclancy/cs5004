package cs5004.animator.model;

/**
 * This class represents a color change animation.
 */
public class ChangeColor extends AbstractAnimation {

  Color startColor;
  Color endColor;

  /**
   * Constructor for the ChangeColor Animation that allows the caller to build the colors as part of
   * the constructor.
   *
   * @param startTick  the starting tick for the animation.
   * @param endTick    the ending tick for the animation.
   * @param redStart   the starting red shade.
   * @param greenStart the starting blue shade.
   * @param blueStart  the starting green shade.
   * @param redEnd     the ending red shade.
   * @param greenEnd   the ending green shade.
   * @param blueEnd    the ending blue shade.
   */
  public ChangeColor(int startTick, int endTick, int redStart, int greenStart, int blueStart,
      int redEnd, int greenEnd, int blueEnd) {
    super(startTick, endTick);
    this.startColor = new Color(redStart, greenStart, blueStart);
    this.endColor = new Color(redEnd, greenEnd, blueEnd);
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
      super.shape.setColor(this.startColor);
    } else {
      super.shape.setDisappearsAt(this.endTick);
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

    int red = tween(t, super.startTick, super.endTick, startColor.getRed(), endColor.getRed());
    int green = tween(t, super.startTick, super.endTick, startColor.getGreen(),
        endColor.getGreen());
    int blue = tween(t, super.startTick, super.endTick, startColor.getBlue(),
        endColor.getBlue());

    shape.setColor(new Color(red, green, blue));
  }

  /**
   * Represents the animation as an SVG string.
   *
   * @return the animation as an SVG string.
   */
  @Override
  public String toString() {
    return String.format("<animate attributeType='xml' begin='%dms' dur='%dms' "
            + "attributeName='fill' from='%s' to='%s' fill='freeze' />",
        super.startTick * 1000,
        (super.endTick - super.startTick) * 1000,
        this.startColor.getRGB(),
        this.endColor.getRGB()
    );
  }
}

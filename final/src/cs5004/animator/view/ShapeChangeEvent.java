package cs5004.animator.view;

import java.awt.event.ActionEvent;

/**
 * This class creates an {@link ActionEvent} for {@link IShapeChangeEvent} generated in the
 * {@link ViewGUIEditor}.
 */
public class ShapeChangeEvent extends ActionEvent implements IShapeChangeEvent {

  private final EShapeChangeType shapeChangeType;
  private final String actionShapeType;
  private final String name;

  /**
   * This constructor creates a {@link ShapeChangeEvent} object.
   *
   * @param source     the object where the event stats
   * @param changeType the type of {@link EShapeChangeType} being made.
   * @param shapeType  the type of the changing shape.
   * @param name       the name of the shape.
   */
  public ShapeChangeEvent(Object source, EShapeChangeType changeType, String shapeType,
      String name) {
    super(source, ActionEvent.ACTION_PERFORMED, "SHAPE CHANGE");

    this.shapeChangeType = changeType;
    this.actionShapeType = shapeType;
    this.name = name;
  }

  /**
   * Get the {@link EShapeChangeType} of {@link IShapeChangeEvent} that is being made within this
   * event.
   *
   * @return the type of {@link EShapeChangeType}.
   */
  @Override
  public EShapeChangeType getChangeType() {
    return this.shapeChangeType;
  }

  /**
   * Get the type of Shape that is being changed as part of this {@link IShapeChangeEvent} object.
   *
   * @return the type of Shape within the event, as a String.
   */
  @Override
  public String getShapeType() {
    return this.actionShapeType;
  }

  /**
   * Get the name of the shape being changed.
   *
   * @return the name of the shape within the event, as a String.
   */
  @Override
  public String getName() {
    return this.name;
  }
}

package cs5004.animator.view;

/**
 * This class represents a change to a shape that happens within the Easy Animator GUI view.
 */
public interface IShapeChangeEvent {

  /**
   * Get the {@link EShapeChangeType} of {@link IShapeChangeEvent} that is being made within this
   * event.
   *
   * @return the type of {@link EShapeChangeType}.
   */
  EShapeChangeType getChangeType();

  /**
   * Get the type of Shape that is being changed as part of this {@link IShapeChangeEvent} object.
   *
   * @return the type of Shape within the event, as a String.
   */
  String getShapeType();

  /**
   * Get the name of the shape being changed.
   *
   * @return the name of the shape within the event, as a String.
   */
  String getName();
}

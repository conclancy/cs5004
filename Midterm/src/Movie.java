/**
 * Interface representing a Movie object.
 */
public interface Movie {

  /**
   * Gets the year this movie was originally published.
   *
   * @return the year, as an int, of publication
   */
  public int getPublicationYear();

  /**
   * Gets the title of the movie.
   *
   * @return The title of the movie, as a String
   */
  public String getTitle();


  /**
   * Resets the progress of the movie to the beginning.
   */
  public void resetProgress();


  /**
   * Saves the progress of the video, so that upon the next viewing the video starts at this
   * position.
   */
  public void saveProgress();


  /**
   * Skip to this specific time in the movie, given in minutes and seconds.
   *
   * @param minute  The minute, as an int, to move the time to.
   * @param seconds The second, as an int, to move the time to. Must be less than 60.
   * @throws IllegalArgumentException when seconds is larger than 60
   */
  public void jumpToTime(int minute, int seconds) throws IllegalArgumentException;


}

/**
 * Animated movie is a specific type of movie (it implements the Movie interface). You need to make
 * a class diagram. The class diagram should include the methods the interface requires, as well as
 * any class variables that you think the class would require given the methods and the descriptions
 * in the interface.
 * <p>
 * Once you have your class diagram, you need to give the concrete implementations for
 * getPublicationYear, jumpToTime, and resetProgress
 * <p>
 * You can assume that getTitle and saveProgress are implemented correctly. You can also assume that
 * there is a constructor that properly initializes all the class variables that you include in your
 * class diagram.
 * <p>
 * If you think you need any other methods to properly implement your 3 methods, you may add them as
 * private methods, and then update your class diagram to include them.
 * <p>
 * You do not need to make your code run or test your code. Instead, you should try to give the best
 * implementation you can be based off of the documentation. If you have any assumptions you wish to
 * make, you should specify them in comments. If you don't understand how something should work,
 * decide how you think it should work and make sure you write documentation specifying how you
 * think it should work.
 * <p>
 * Make sure to include the class diagram, that is as important as the code you write here.
 */
public class AnimatedMovie implements Movie {

  /* Put your class diagram here:
      ---------------------------------------             ---------------------------------------
      |            AnimatedMovie            |             |                 Movie               |
      ---------------------------------------             ---------------------------------------
      | - publicationYear: int              |             | + getPublicationYear(): int         |
      | - title: String                     |--(dashed)-->| + getTitle(): String                |
      | - progress: int                     |             | + resetProgress():                  |
      ---------------------------------------             | + saveProgress():                   |
                                                          | + jumpToTime(int, int): int         |
                                                          ---------------------------------------
   */

  private int publicationYear;
  private String title;
  private int progress;

  /**
   * Constructs an animated movie object.
   *
   * @param publicationYear The year the movie was published as an integer.
   * @param title           The title of the move as a string.
   */
  public AnimatedMovie(int publicationYear, String title) {
    this.publicationYear = publicationYear;
    this.title = title;
    this.progress = 0;  // Movies always initialize at the beginning
  }

  /**
   * Return the year the movie was published.
   *
   * @return integer year the movie was published
   */
  @Override
  public int getPublicationYear() {
    return this.publicationYear;
  }

  /**
   * Allows a caller to jump to a specific moment in the movie.
   *
   * @param minute  The minute, as an int, to move the time to.
   * @param seconds The second, as an int, to move the time to. Must be less than 60.
   * @throws IllegalArgumentException if seconds is greater than 59 and should be converted into a
   *                                  minutes value.
   */
  @Override
  public void jumpToTime(int minute, int seconds) throws IllegalArgumentException {
    if (seconds > 59) {
      throw new IllegalArgumentException("Seconds value must be less than 60.");
    }

    this.progress = (minute * 60) + seconds;
  }

  /**
   * Reset the progress in the movie back to the 0.
   */
  @Override
  public void resetProgress() {
    this.progress = 0;
  }

  //ALL THE METHODS BELOW HERE ARE ASSUMED TO BE PROPERLY IMPLEMENTED


  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void saveProgress() {
    // TODO Auto-generated method stub

  }

}

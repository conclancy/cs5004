package model;

/**
 * Represents an instance of a color represented in blue, green, and red.  Each instance of this
 * class represents a single shade of color.
 */
public class Color {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for a color object that takes in a traditional RGB model.  Each of the three inputs
   * represents the additive amount of colored light present within the model with valid values
   * greater than or equal to 0 and less than 256.
   *
   * @param red   amount of red light present in the color model, as an int.
   * @param green amount of green light present in the color model, as an int.
   * @param blue  amount of blue light present in the color model, as an int.
   * @throws IllegalArgumentException if any of the additive colors are greater than 255 or less
   *                                  than 0.
   */
  public Color(int red, int green, int blue) throws IllegalArgumentException {

    this.red = this.validateColorInt(red);
    this.green = this.validateColorInt(green);
    this.blue = this.validateColorInt(blue);

  }

  /**
   * Constructor for the color object that takes in a hexadecimal representation of the RGB color
   * model.  Valid hexadecimal representations are exactly 6 characters long; each 2 character block
   * represents one of the three additive amounts of light for red, green, and blue respectively.
   * Hexadecimal numbers contain only numbers between 0 and 9 and the capital letters A through F.
   *
   * @param hex hexadecimal representation of the RGB color model.
   * @throws IllegalArgumentException if characters other than numbers or the letters A through F
   *                                  are used, or if the {@param hex} string is not exactly 6
   *                                  characters long.
   */
  public Color(String hex) throws IllegalArgumentException {
    this.setHex(hex);
  }

  /**
   * Validates that a color passed to this method is a valid RGB color.
   *
   * @param color the integer value of the additive amount of light.
   * @return the validated integer representing the additive color.
   * @throws IllegalArgumentException if {@param color} is greater than 255 or less than 0.
   */
  private int validateColorInt(int color) throws IllegalArgumentException {

    if (color < 0 || color > 255) {
      throw new IllegalArgumentException(
          "Color inputs must be greater than or equal to 0 and less than 256. ");
    } else {
      return color;
    }

  }

  /**
   * Change the additive amount of red light in the color.
   *
   * @param red the additive amount of red light present within the color.
   * @throws IllegalArgumentException if {@param red} is greater than 255 or less than 0.
   */
  public void setRed(int red) throws IllegalArgumentException {
    this.red = this.validateColorInt(red);
  }

  /**
   * Change the additive amount of green light in the color.
   *
   * @param green the additive amount of green light present within the color.
   * @throws IllegalArgumentException if {@param green} is greater than 255 or less than 0.
   */
  public void setGreen(int green) {
    this.green = this.validateColorInt(green);
  }

  /**
   * Change the additive amount of blue light in the color.
   *
   * @param blue the additive amount of blue light present within the color.
   * @throws IllegalArgumentException if {@param blue} is greater than 255 or less than 0.
   */
  public void setBlue(int blue) {
    this.blue = this.validateColorInt(blue);
  }

  /**
   * Set additive colors for the RGB model.  Each of the three inputs represents the additive amount
   * of colored light present within the model with valid values greater than or equal to 0 and less
   * than 256.
   *
   * @param red   amount of red light present in the color model, as an int.
   * @param green amount of green light present in the color model, as an int.
   * @param blue  amount of blue light present in the color model, as an int.
   * @throws IllegalArgumentException if any of the additive colors are greater than 255 or less
   *                                  than 0.
   */
  public void setRGB(int red, int green, int blue) throws IllegalArgumentException {
    this.red = this.validateColorInt(red);
    this.green = this.validateColorInt(green);
    this.blue = this.validateColorInt(blue);

  }

  /**
   * Constructor for the color object that takes in a hexadecimal representation of the RGB color
   * model.  Valid hexadecimal representations are exactly 6 characters long; each 2 character block
   * represents one of the three additive amounts of light for red, green, and blue respectively.
   * Hexadecimal numbers contain only numbers between 0 and 9 and the capital letters A through F.
   *
   * @param hex hexadecimal representation of the RGB color model.
   * @throws IllegalArgumentException if characters other than numbers or the letters A through F
   *                                  are used, or if the {@param hex} string is not exactly 6
   *                                  characters long.
   */
  public void setHex(String hex) throws IllegalArgumentException {

    int greenHex = 0;
    int blueHex = 0;

    if (!hex.matches("[0-9A-F]+")) {
      throw new IllegalStateException(
          "String must be a hexadecimal.  Valid characters are 0 - 9 and the letters A - F.");
    } else if (hex.length() != 6) {
      throw new IllegalArgumentException("Hexadecimal string must be exactly 6 characters long");
    }

    int redHex = ((Character.getNumericValue(hex.charAt(0))) * 16) + Character.getNumericValue(
        hex.charAt(1));

    // TODO test this after an initial implementation.

    this.setRGB(redHex, greenHex, blueHex);
  }

  /**
   * Get the integer value of the red light additive color.
   *
   * @return the additive red light color, as an int.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Get the integer value of the blue light additive color.
   *
   * @return the additive blue light color, as an int.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Get the integer value of the green light additive color.
   *
   * @return the additive green light color, as an int.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Get the RGB color model as a string in the format (red, green, blue).
   *
   * @return the RGB color model, as a string.
   */
  public String getRGB() {
    return String.format("(%d, %d, %d)", this.red, this.green, this.blue);
  }

  /**
   * Get color model as a hexadecimal string of 6 characters.
   *
   * @return the color model in hexadecimal form, as a string.
   */
  public String getHex() {
    //TODO
    return null;
  }

}

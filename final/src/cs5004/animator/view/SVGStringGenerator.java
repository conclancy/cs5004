package cs5004.animator.view;

import java.io.IOException;

/**
 * This class allows for the creation of SVG files.
 */
public class SVGStringGenerator implements IVewText {

  Appendable output;

  /**
   * Constructor for a SVGStringGenerator view that generates an SVG string from a model.
   *
   * @param svg the svg tags, as a String.
   * @param speed is the ticks per second  of the animation.
   */
  public SVGStringGenerator(String svg, int speed) {
    if (speed < 0) {
      throw new IllegalArgumentException("Cannot pass a negative ticks per second");
    }

    this.output = new StringBuilder();

    try{
      output.append(svg);
    } catch (IOException e) {
      System.out.println("SVG String Generator not working.");
    }

  }

  /**
   * Generate the SVG file using the input text.
   */
  @Override
  public void play() {
    this.getText();
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.output.toString();
  }

}

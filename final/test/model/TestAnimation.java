package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestAnimation<T> {

  private final Color black = new Color("000000");
  private final Color white = new Color("FFFFFF");

  private Animation<Object> first;

  @Before
  public void init() {

    first = new Animation<Object>();
  }

  @Test
  public void testAddColorFront() {

    // initial add
    first.addAnimationFront(0, 1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, first.playTextDescription());

    // add an element in front of the first element
    first.addAnimationFront(0, 1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList("changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, first.playTextDescription());

    // add an element with an interval greater than 1
    first.addAnimationFront(0, 10, black);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to 000000 from t=0 to t=10",
            "changes color to FFFFFF from t=11 to t=12",
            "changes color to 000000 from t=13 to t=14"));
    assertEquals(expectedThree, first.playTextDescription());
  }

}

package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class TestAnimation<T> {

  private final Color black = new Color("000000");
  private final Color white = new Color("FFFFFF");

  private IAnimation<Object> first;

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

  @Test
  public void testAddColorBack() {
    Animation<Object> backColor = new Animation<Object>();

    // initial add
    backColor.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, backColor.playTextDescription());

    // second add
    backColor.addAnimationBack(3, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to 000000 from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4"));
    assertEquals(expectedTwo, backColor.playTextDescription());

    // third add
    backColor.addAnimationBack(6, black);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to 000000 from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=10"));
    assertEquals(expectedThree, backColor.playTextDescription());
  }

  @Test
  public void testAddColor() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementAddColor() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    colorAdd.addAnimation(3,5, black);
  }

  @Test
  public void testRemoveColorFront() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    // Test remove front.
    colorAdd.removeAnimationFront();
    List<String> expectedFour = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedFour, colorAdd.playTextDescription());

    // Test remove front again.
    colorAdd.removeAnimationFront();
    List<String> expectedFive = new ArrayList<>(
        Arrays.asList(
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedFive, colorAdd.playTextDescription());
  }

  @Test
  public void testRemoveColorBack() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    // Test remove back.
    colorAdd.removeAnimationBack();
    List<String> expectedFour = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4"));
    assertEquals(expectedFour, colorAdd.playTextDescription());

    // Test remove front again.
    colorAdd.removeAnimationBack();
    List<String> expectedFive = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1"));
    assertEquals(expectedFive, colorAdd.playTextDescription());
  }

  @Test
  public void testRemoveIndexColor() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    // Test remove element in the middle of the list.
    colorAdd.removeAnimationAtIndex(1);
    List<String> expectedFour = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedFour, colorAdd.playTextDescription());

    // Test remove element at the beginning of the list
    colorAdd.removeAnimationAtIndex(0);
    List<String> expectedFive = new ArrayList<>(
        Arrays.asList(
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedFive, colorAdd.playTextDescription());

    // Test remove element to make a list empty
    colorAdd.removeAnimationAtIndex(0);
    List<String> expectedSix = new ArrayList<>();
    assertEquals(expectedSix, colorAdd.playTextDescription());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveOutOfBoundsIndex() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    colorAdd.removeAnimationAtIndex(4);
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementRemoveStart() {
    Animation<Object> colorAdd = new Animation<Object>();

    // Create an initial animation in the list.
    colorAdd.addAnimationBack(1, black);
    List<String> expectedOne = new ArrayList<>(
        Arrays.asList("changes color to 000000 from t=0 to t=1"));
    assertEquals(expectedOne, colorAdd.playTextDescription());

    // Add an element at the beginning of the list.
    colorAdd.addAnimation(0,1, white);
    List<String> expectedTwo = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to 000000 from t=2 to t=3"));
    assertEquals(expectedTwo, colorAdd.playTextDescription());

    // Add an element in the middle of the list.
    colorAdd.addAnimation(2,4, white);
    List<String> expectedThree = new ArrayList<>(
        Arrays.asList(
            "changes color to FFFFFF from t=0 to t=1",
            "changes color to FFFFFF from t=2 to t=4",
            "changes color to 000000 from t=5 to t=6"));
    assertEquals(expectedThree, colorAdd.playTextDescription());

    colorAdd.removeAnimationAtStart(3);
  }
}

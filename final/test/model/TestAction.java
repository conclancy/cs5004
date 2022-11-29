package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TestAction {

  private Color black;

  private Action initial;
  private Action changeX;
  private Action changeY;
  private Action changeColor;

  @Before
  public void init() {

    Color black = new Color("000000");

    Action initial = new Action(0,0,0,0, black);
    Action changeX = new Action(0,1,1,0, black);
    Action changeY = new Action(1,2, 1,1,black);
    Action changeColor = new Action(1,2, 1,1,black);
  }
}

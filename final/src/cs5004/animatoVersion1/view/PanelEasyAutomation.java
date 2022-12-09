package cs5004.animatoVersion1.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelEasyAutomation extends JPanel {

  /**
   * Constructor for the PanelEasyAutomation object.
   */
  public PanelEasyAutomation() {
    super(true);

    super.setBackground(Color.WHITE);
    super.setSize(500, 500);
    super.setLocation(0,50);
  }

  /**
   * Creates the objects for the panel to display.
   *
   * @param g the graphics to be displayed.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.fillRect(100,100,10,100);
  }

}

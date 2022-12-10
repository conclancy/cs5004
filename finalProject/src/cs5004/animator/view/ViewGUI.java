package cs5004.animator.view;

import cs5004.animator.controller.IController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This class represents a graphical interface view for the EasyAutomation class.
 */
public class ViewGUI extends JFrame implements IView, ActionListener {

  private int x;
  private int y;
  private int width;
  private int height;

  private IController controller;

  public ViewGUI() {
    super("EasyAutomation CS5004 Final Project");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Set default dimensions
    x = 0;
    y = 0;
    width = 1000;
    height = 600;
  }

  /**
   * Set the location and dimensions of the JFrame GUI.
   *
   * @param x      the x location on the user's screen.
   * @param y      the y location on the user's screen.
   * @param width  the width location on the user's screen.
   * @param height the height location on the user's screen.
   */
  public void setGUIDimensions(int x, int y, int width, int height) {
    this.setSize(new Dimension(width, height));
    this.setLocation(new Point(x, y));
  }

  /**
   * Display the view at the provided speed.
   *
   * @param speed the desired speed of the animation, as an int.
   */
  @Override
  public void display(int speed) {
    JMenuBar menuBar = new JMenuBar();

    JMenu file = new JMenu("File");
    menuBar.add(file);

    JMenuItem exit = new JMenuItem("Exit");
    exit.setName("exit");
    exit.addActionListener(this);
    file.add(exit);

    this.setJMenuBar(menuBar);
  }

  /**
   * Give the view access to the controller.
   *
   * @param controller for the EasyAutomation program.
   */
  @Override
  public void addController(IController controller) {
    this.controller = controller;
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  //TODO move this to its own clas.
  private void buildPanel() {
    JPanel panel = new JPanel(true);
    panel.setBackground(Color.WHITE);
    panel.setSize(new Dimension(this.width, this.height));
    panel.setLocation(new Point(this.x, this.y));

    panel.delay
  }
}

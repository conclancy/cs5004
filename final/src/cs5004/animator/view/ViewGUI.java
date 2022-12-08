package cs5004.animator.view;

import cs5004.animator.controller.ControllerGUI;
import cs5004.animator.controller.IController;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewGUI extends JFrame implements IViewGUI, ActionListener {

  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;

  private JPanel buttonPanel;
  private JButton buttonAddShape;
  private JTextField textFieldX;
  private PanelEasyAutomation panel;

  public ViewGUI() {
    super("EasyAutomations");
    super.setSize(500, 600);
    super.setLocation(100, 50);
    super.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.menuBar = new JMenuBar();
    this.setJMenuBar(this.menuBar);
    this.file = new JMenu("File");
    this.menuBar.add(this.file);

    this.exit = new JMenuItem("Exit");
    this.exit.setName("exit");
    this.file.add(this.exit);
    this.exit.addActionListener(this);

    this.buttonPanel = new JPanel(true);
    this.buttonPanel.setBackground(Color.BLUE);
    this.buttonPanel.setSize(500, 100);
    this.buttonPanel.setLocation(0, 0);
    this.buttonPanel.setLayout(new FlowLayout());

    this.buttonAddShape = new JButton("Add Shape");
    this.buttonAddShape.setName("addShape");
    this.buttonPanel.add(this.buttonAddShape);
    this.buttonAddShape.addActionListener(this);

    this.textFieldX = new JTextField(3);
    this.buttonPanel.add(this.textFieldX);

    this.add(this.buttonPanel);

    panel = new PanelEasyAutomation();
    this.add(panel);

    super.setVisible(true);

    this.panel.repaint();
  }

  /**
   * Display the EasyAnimation using the view.
   */
  @Override
  public void display(ControllerGUI controller) {

  }

  /**
   * Get the X coordinate recorded in the view.
   *
   * @return the X coordinate from the view.
   */
  @Override
  public double getCoordinateX() {
    return Double.parseDouble(textFieldX.getText());
  }

  /**
   * Display a message to the end user.
   *
   * @param m the message to be displayed, as a String.
   */
  @Override
  public void showMessage(String m) {
    JOptionPane.showMessageDialog(this, m);
  }

  /**
   * When an action event happens, this method gets called, and we execute the logic here.
   *
   * @param e an event happening on the View.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();

    if(c.getName().equals("exit")) {
      System.exit(0);
    } else if (c.getName().equals("addShape")) {
      // TODO
    }
  }
}

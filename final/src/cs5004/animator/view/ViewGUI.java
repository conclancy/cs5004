package cs5004.animator.view;

import cs5004.animator.controller.ControllerGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewGUI extends JFrame implements IViewGUI, ActionListener, ListSelectionListener {

  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;
  private JPanel panelToolBar;
  private JPanel panelShapeEditor;

  private JPanel buttonPanel;
  private JButton buttonAddShape;
  private JTextField textFieldX;
  private JTextField textFieldSpeed;
  private PanelEasyAutomation panel;


  private JList<Graphics> shapeList;

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

    this.textFieldSpeed = new JTextField("1.00", 3);

    JScrollPane paneScroll = new JScrollPane(this.panel);
    this.addBorder(paneScroll);

    // Add Speed toolbar to the view
    this.buildSpeedToolBar();
    this.add(this.panelToolBar, BorderLayout.SOUTH);

    // Add the Shape Editor to the view
    this.buildShapeEditor();
    this.add(this.panelShapeEditor, BorderLayout.WEST);

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
   * Get the current speed of the animation.
   *
   * @return tick speed, as a double
   */
  public double getSpeed() {
    return Double.parseDouble(textFieldSpeed.getText());
  }

  /**
   * Set the speed of the animation.
   *
   * @param speed tick speed, as a double with up to two decimal places;
   */
  public void setSpeed(double speed) {
    textFieldSpeed.setText(String.format("%.2f", speed));
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

    if (c.getName().equals("exit")) {
      System.exit(0);
    } else if (c.getName().equals("addShape")) {
      // TODO
    }
  }

  /**
   * Called whenever the value of the selection changes.
   *
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // TODO
  }

  /**
   * Setter method for the shapes list.
   *
   * @param shapes a list of Swing graphics.
   */
  public void setShapes(JList<Graphics> shapes) {
    this.shapeList = shapes;
  }

  /**
   * Create a toolbar that can be used to control the speed of the animation.  The toolbar will
   * appear at the bottom of the window and default to a speed of 1.00.
   */
  private void buildSpeedToolBar() {

    // Start button
    JButton start = new JButton("Start");
    start.setName("start");
    start.addActionListener(this);

    // Pause button
    JButton pause = new JButton("Pause");
    pause.setName("pause");
    pause.addActionListener(this);

    JPanel panelCommands = new JPanel();
    panelCommands.add(start);
    panelCommands.add(pause);

    // Restart button
    JButton restart = new JButton();
    restart.setActionCommand("restart");
    restart.addActionListener(this);

    // Loop button
    JLabel labelLoop = new JLabel("Loop:");
    JCheckBox checkBoxLoop = new JCheckBox();
    checkBoxLoop.setName("loop");
    checkBoxLoop.addActionListener(this);
    JPanel panelLoop = new JPanel();
    panelLoop.add(labelLoop);
    panelLoop.add(checkBoxLoop);

    // SVG export button
    JButton buttonExport = new JButton("Export");
    buttonExport.setName("export");
    buttonExport.addActionListener(this);

    // Set speed
    JLabel labelSpeed = new JLabel("Speed:");
    this.textFieldSpeed.setPreferredSize(new Dimension(34, 26));
    this.textFieldSpeed.setName("speed");
    this.textFieldSpeed.addActionListener(this);

    JLabel labelSpeedDefault = new JLabel("(Default: 1.00)");

    JPanel panelSpeed = new JPanel();
    panelSpeed.add(labelSpeed);
    panelSpeed.add(this.textFieldSpeed);
    panelSpeed.add(labelSpeedDefault);

    // Add buttons to toolbar
    this.panelToolBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
    this.panelToolBar.add(panelSpeed);
    this.panelToolBar.add(panelCommands);
    this.panelToolBar.add(restart);
    this.panelToolBar.add(buttonExport);
    this.panelToolBar.add(panelLoop);
    this.addBorder(this.panelToolBar);

  }

  private void buildShapeEditor() {
    JLabel title = new JLabel("Add Shapes", SwingConstants.CENTER);
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
    title.setPreferredSize(new Dimension(254, 60));
    this.addBorder(title);

    this.shapeList = new JList<Graphics>(); // TODO we need a way to add shapes to this list
    this.shapeList.addListSelectionListener(this);
    JScrollPane scrollShapes = new JScrollPane(this.shapeList);
    this.addBorder(scrollShapes);

    this.panelShapeEditor = new JPanel(new BorderLayout());
    this.panelShapeEditor.add(title, BorderLayout.NORTH);
    this.panelShapeEditor.add(scrollShapes, BorderLayout.CENTER);
    this.panelShapeEditor.setPreferredSize(new Dimension(254, 82));
  }

  /**
   * Method for adding boarders to a component.
   *
   * @param c the component to add a Boarder to.
   */
  private void addBorder(JComponent c) {
    c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }


}

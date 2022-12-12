package cs5004.animator.view;

import cs5004.animator.model.IShape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs5004.animator.controller.IFrame;
import cs5004.animator.controller.Frame;

/**
 * This GUI editor view class creates an interactive window for users to control an animation. This
 * will allow users to scroll through the animation, set the animation speed, play, pause, restart,
 * repeat, and export to the visual to an SVG file.
 */
public class ViewGUIEditor extends JFrame implements IViewGUI, ListSelectionListener,
    ActionListener, ChangeListener {

  private final PaintPanel drawingPanel;
  private JPanel toolbar;
  private JPanel shapesEditor;
  private JPanel framesEditor;
  private final JSlider sliderFunction;

  private final JTextField tickSpeedField;
  private JList<IShapeCell> shapeListContainer;
  private JLabel frameListLabel;
  private JList<IFrame> frameListContainer;
  private final DefaultListModel<IShapeCell> shapesList;
  private final DefaultListModel<IFrame> framesList;
  private Map<String, List<IFrame>> keyframes;

  private final List<ActionListener> buttonListeners;
  private final List<PropertyChangeListener> tickSpeedListeners;

  private int width = 1000;
  private int height = 600;
  private int shapeSelected = -1;
  private int frameSelected = -1;
  private final IUserInputHandler handler;
  private boolean pause = true;

  /**
   * Construct the GUI Editor view which creates an interactive GUI for users to control the
   * playback of the animation using buttons and a slider bar.
   *
   * @param speed the speed of the animation in ticks per second, as an int.
   */
  public ViewGUIEditor(int speed) {
    super("EasyAnimator - CS5004 Final - cclancy");

    this.tickSpeedField = new JTextField("" + speed);

    this.handler = new UserInputHandler();
    this.buttonListeners = new ArrayList<>();
    this.tickSpeedListeners = new ArrayList<>();
    this.keyframes = new LinkedHashMap<>();

    this.shapesList = new DefaultListModel<>();
    this.framesList = new DefaultListModel<>();

    this.setLayout(new BorderLayout());

    this.drawingPanel = new PaintPanel();
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));

    JScrollPane scrollPanel = new JScrollPane(this.drawingPanel);
    this.addBorder(scrollPanel);

    sliderFunction = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    sliderFunction.addChangeListener(this);
    sliderFunction.setPreferredSize(new Dimension(200, 50));
    JPanel display = new JPanel(new BorderLayout());
    display.add(sliderFunction, BorderLayout.SOUTH);
    display.add(scrollPanel, BorderLayout.CENTER);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.add(display, BorderLayout.CENTER);

    this.createShapesEditor();
    this.add(this.shapesEditor, BorderLayout.WEST);

    this.createFramesEditor();
    this.add(this.framesEditor, BorderLayout.EAST);

    this.createToolbar();
    this.add(this.toolbar, BorderLayout.SOUTH);

    this.setSize(this.width + 500, this.height + 50);

  }

  /**
   * Adds a black borderline to a {@link JComponent}.
   *
   * @param component the {@link JComponent} to add a border to.
   */
  private void addBorder(JComponent component) {
    component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }


  /**
   * Create a toolbar at the bottom that supports the Play, Restart, and Pause buttons.
   */
  private void createToolbar() {

    // Play Button
    JButton buttonPlay = new JButton("Start");
    buttonPlay.setActionCommand("START");
    buttonPlay.addActionListener(this);

    // Pause Button
    JButton buttonPause = new JButton("Pause");
    buttonPause.setActionCommand("PAUSE");
    buttonPause.addActionListener(this);

    JPanel panelPlayPause = new JPanel();
    panelPlayPause.add(buttonPlay);
    panelPlayPause.add(buttonPause);

    // Restart Button
    JButton buttonRestart = new JButton("Restart");
    buttonRestart.setActionCommand("RESTART");
    buttonRestart.addActionListener(this);

    // Repeat Button
    JLabel loop = new JLabel("Repeat:");
    JCheckBox checkBoxRepeat = new JCheckBox();
    checkBoxRepeat.setActionCommand("LOOP");
    checkBoxRepeat.addActionListener(this);
    JPanel panelRepeat = new JPanel();
    panelRepeat.add(loop);
    panelRepeat.add(checkBoxRepeat);

    // Export to SVG
    JButton buttonExport = new JButton("Save as SVG File");
    buttonExport.setActionCommand("EXPORT");
    buttonExport.addActionListener(this);

    // Set Ticks Per Second Speed Field
    JLabel labelSpeed = new JLabel("Set Speed:");
    this.tickSpeedField.setPreferredSize(new Dimension(34, 26));
    this.tickSpeedField.setActionCommand("TICK SPEED");
    this.tickSpeedField.addActionListener(this);
    JLabel labelDefaultSpeed = new JLabel("(Default: 1)");
    JPanel panelSpeed = new JPanel();
    panelSpeed.add(labelSpeed);
    panelSpeed.add(this.tickSpeedField);
    panelSpeed.add(labelDefaultSpeed);

    // Add the buttons in order of appearance
    this.toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
    this.toolbar.add(panelSpeed);
    this.toolbar.add(panelPlayPause);
    this.toolbar.add(buttonRestart);
    this.toolbar.add(buttonExport);
    this.toolbar.add(panelRepeat);
    this.addBorder(this.toolbar);
  }

  /**
   * Create an editor pane that allows for shape editing within the GUI.
   */
  private void createShapesEditor() {
    JLabel title = new JLabel("EShapeTypes Menu", SwingConstants.CENTER);
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
    title.setPreferredSize(new Dimension(254, 60));
    this.addBorder(title);

    this.shapeListContainer = new JList<>(this.shapesList);
    this.shapeListContainer.addListSelectionListener(this);
    JScrollPane scrollShapes = new JScrollPane(this.shapeListContainer);
    this.addBorder(scrollShapes);

    this.shapesEditor = new JPanel(new BorderLayout());
    this.shapesEditor.add(title, BorderLayout.NORTH);
    this.shapesEditor.add(scrollShapes, BorderLayout.CENTER);
    this.shapesEditor.setPreferredSize(new Dimension(254, 82));
  }

  /**
   * Create an editor that allows users to edit frames.
   */
  private void createFramesEditor() {
    JLabel title = new JLabel("Event List", SwingConstants.CENTER);
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
    title.setPreferredSize(new Dimension(254, 60));
    this.addBorder(title);

    this.frameListContainer = new JList<>(this.framesList);
    this.frameListContainer.addListSelectionListener(this);
    JScrollPane scrollFrames = new JScrollPane(this.frameListContainer);
    this.addBorder(scrollFrames);
    this.frameListLabel = new JLabel("First, Select a Shape from EShapeTypes Menu",
        SwingConstants.CENTER);
    this.frameListLabel.setPreferredSize(new Dimension(254, 40));
    this.addBorder(this.frameListLabel);
    JPanel frameListTitled = new JPanel(new BorderLayout());
    frameListTitled.add(scrollFrames, BorderLayout.CENTER);
    frameListTitled.add(this.frameListLabel, BorderLayout.NORTH);

    this.framesEditor = new JPanel(new BorderLayout());
    this.framesEditor.add(title, BorderLayout.NORTH);
    this.framesEditor.add(frameListTitled, BorderLayout.CENTER);
    this.framesEditor.setPreferredSize(new Dimension(500, 271)); //Alter here for text
  }

  /**
   * Create a view, and play the animation contained within the program.
   */
  @Override
  public void play() {
    this.setVisible(true);
  }

  /**
   * Display a list of {@link IShape} objects.
   *
   * @param shapes the list of {@link IShape} to be displayed in the GUI.
   */
  @Override
  public void display(List<IShape> shapes) {
    this.drawingPanel.paint(shapes);
  }

  /**
   * Get the shapes for the animation.
   *
   * @return a list of {@link ShapeCell}
   */
  @Override
  public List<IShapeCell> getShapes() {
    List<IShapeCell> output = new ArrayList<>();

    for (int i = 0; i < this.shapesList.size(); i++) {
      output.add(this.shapesList.elementAt(i));
    }

    return output;
  }

  /**
   * Add {@link IShape} objects to the view.
   *
   * @param shapes all the shapes in the animation.
   */
  @Override
  public void setShapes(Map<String, IShape> shapes) {
    this.shapesList.clear();

    for (String key : shapes.keySet()) {
      this.shapesList.addElement(new ShapeCell(key, shapes.get(key).getShapeType()));
    }

    this.framesList.clear();
    this.frameListContainer.clearSelection();
  }

  /**
   * Get the animation frames for the visual.
   */
  @Override
  public Map<String, List<IFrame>> getFrame() {
    return this.keyframes;
  }

  /**
   * Sets frames for automation.
   *
   * @param frames the shape name, as a String, and its associated {@link IFrame} objects as a
   *               {@link LinkedHashMap}.
   */
  @Override
  public void setFrames(Map<String, List<IFrame>> frames) {
    this.keyframes = new LinkedHashMap<>();
    for (String id : frames.keySet()) {
      List<IFrame> newList = new ArrayList<>();

      for (IFrame frame : frames.get(id)) {
        newList.add(new Frame(frame.getTick(), frame.getX(), frame.getY(),
            frame.getWidth(), frame.getHeight(), frame.getShapeRotation(),
            frame.getColor()));
      }
      this.keyframes.put(id, newList);
    }
    this.updateFramesList();
  }

  /**
   * Set the width of the GUI.
   *
   * @param width the width of the GUI, as an int.
   */
  @Override
  public void setWidth(int width) {
    this.width = width;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Set the height of the GUI.
   *
   * @param height the height of the GUI, as an int.
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Add a {@link ActionListener} that receives action events from button presses.
   *
   * @param listener the listener being passed.
   */
  @Override
  public void addButtonListener(ActionListener listener) {
    this.buttonListeners.add(listener);
  }

  /**
   * Add a {@link PropertyChangeListener} that receives property change events.
   *
   * @param listener the listener being passed.
   */
  @Override
  public void addPropertyListener(PropertyChangeListener listener) {
    this.tickSpeedListeners.add(listener);
    this.handler.addExportListener(listener);
  }

  /**
   * Add a {@link IShapeChangeListener} that receives shape change events.
   *
   * @param listener the listener being passed.
   */
  @Override
  public void addShapeChangeListener(IShapeChangeListener listener) {
    this.handler.addShapeChangeListener(listener);
  }

  /**
   * Adds a {@link IFrameChangeListener} that receives frame change events.
   *
   * @param listener the listener being passed.
   */
  @Override
  public void addFrameChangeListener(IFrameChangeListener listener) {
    this.handler.addFrameChangeListener(listener);
  }


  /**
   * Displays an error in the view indicating to the user an error occurred.
   *
   * @param error the error message to be displayed, as a String.
   */
  @Override
  public void displayError(String error) {
    JOptionPane.showMessageDialog(this, error, "An error occurred",
        JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Create a new {@link ActionEvent} when a button is pressed.
   *
   * @param command the name of the command being created.
   */
  private void buttonEventHelper(String command) {
    for (ActionListener listener : this.buttonListeners) {
      listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
    }
  }


  /**
   * Change the speed of the animation.
   *
   * @param type     the type of {@link PropertyChangeEvent} that will be occurring.
   * @param newSpeed the new speed of the animation, as an int.
   */
  private void changeSpeed(String type, String newSpeed) {
    for (PropertyChangeListener listener : this.tickSpeedListeners) {
      listener.propertyChange(new PropertyChangeEvent(this, type,
          newSpeed, newSpeed));
    }
  }

  /**
   * Update the frames within the animation.
   */
  private void updateFramesList() {
    this.framesList.clear();

    if (this.shapeSelected >= 0 && this.shapeSelected < this.shapesList.size()) {
      String id = this.shapesList.elementAt(this.shapeSelected).getName();

      if (this.keyframes.containsKey(id)) {
        for (IFrame cell : this.keyframes.get(id)) {
          this.framesList.addElement(cell);
        }
      }
    }
    if (this.frameSelected >= this.framesList.size()) {
      this.frameSelected = 0;
    }
    if (this.frameSelected >= 0) {
      this.frameListContainer.setSelectedIndex(this.shapeSelected);
    }
  }


  /**
   * Event listener waiting for the user to select a list item within the view.
   */
  @Override
  public void valueChanged(ListSelectionEvent event) {
    int newShapeSelected = this.shapeListContainer.getSelectedIndex();

    int newFrameSelected = -1;

    try {
      newFrameSelected = this.frameListContainer.getSelectedIndex();
    } catch (NullPointerException ignored) {
    }

    if (newFrameSelected != -1) {
      this.frameSelected = newFrameSelected;
    }

    if (newShapeSelected != -1 && newShapeSelected != this.shapeSelected) {
      this.shapeSelected = newShapeSelected;
      updateFramesList();
      this.frameListContainer.clearSelection();
      this.frameListLabel.setText("Shape: " + this.shapesList.getElementAt(this.shapeSelected));
    }
  }

  /**
   * Event listener waiting for actions to be performed on the view.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String process = event.getActionCommand();

    if (process.equals("TICK SPEED")) {
      this.changeSpeed("TICK SPEED", this.tickSpeedField.getText());
      return;
    }

    if (process.equals("EXPORT")) {
      this.handler.exportToSVG(this);
    }

    if (process.equals("SHAPE ADD")) {
      this.handler.changeShape(this, EShapeChangeType.ADD, "");
      return;
    }

    IShapeCell shape;
    if (this.shapeSelected != -1 || !this.shapeListContainer.isSelectionEmpty()) {
      shape = this.shapesList.elementAt(this.shapeSelected);
    } else {
      this.buttonEventHelper(process);
      return;
    }

    if (process.equals("shape delete")) {
      this.handler.changeShape(this, EShapeChangeType.DELETE, shape.getName());
      return;
    }

    if (process.equals("frame add")) {
      this.handler.changeFrame(this, EFrameChangeType.ADD, shape.getName(), 0);
      return;
    }

    IFrame keyframe;
    if (this.frameSelected != -1 || !this.frameListContainer.isSelectionEmpty()) {
      keyframe = this.framesList.elementAt(this.frameSelected);
    } else {
      this.buttonEventHelper(process);
      return;
    }

    switch (process) {
      case "frame edit":
        this.handler.changeFrame(this, EFrameChangeType.EDIT, shape.getName(), keyframe.getTick(),
            keyframe.getX(), keyframe.getY(), keyframe.getWidth(), keyframe.getHeight(),
            keyframe.getShapeRotation(), keyframe.getColor());
        break;
      case "frame delete":
        this.handler.changeFrame(this, EFrameChangeType.DELETE, shape.getName(),
            keyframe.getTick());
        break;
      default:
        this.buttonEventHelper(process);
    }
  }

  /**
   * Even listener waiting for a state change event.
   *
   * @param event the {@link ChangeEvent} object. .
   */
  @Override
  public void stateChanged(ChangeEvent event) {
    if (pause) {
      this.changeSpeed("SLIDER", "" + sliderFunction.getValue());
    } else {
      pause = true;
    }
  }

  /**
   * Set the current state of the GUI playback slider.
   *
   * @param tick to set the slider at.
   */
  @Override
  public void setSlider(double tick) {
    pause = false;
    sliderFunction.setValue((int) (tick * 100));
  }
}
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
 * This ViewGUIEditor class is the GUI that will act as the third view for the animation. This will
 * allow users to scroll through the animation, select specific frames, select speed, play, pause,
 * restart, loop, and export to SVG.
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
   * This constructor constructs the playback view which creates an environment similar to the
   * visual view. The user is able to interact with the view.
   *
   * @param ticksPS the starting speed of the animation in ticks per second
   */
  public ViewGUIEditor(int ticksPS) {
    super("EasyAnimator - CS5004 Final - cclancy");

    this.tickSpeedField = new JTextField("" + ticksPS);

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
   * Method that paints borders.
   */
  private void addBorder(JComponent comp) {
    comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }


  /**
   * Method that creates a toolbar at the bottom that supports the Play, Restart, and Pause
   * buttons.
   */
  private void createToolbar() {

    // Play Button
    JButton play = new JButton("START");
    play.setActionCommand("START");
    play.addActionListener(this);

    // Pause Button
    JButton pause = new JButton("PAUSE");
    pause.setActionCommand("PAUSE");
    pause.addActionListener(this);

    JPanel playPause = new JPanel();
    playPause.add(play);
    playPause.add(pause);

    // Restart Button
    JButton restart = new JButton("RESTART");
    restart.setActionCommand("RESTART");
    restart.addActionListener(this);

    // Repeat Button
    JLabel loop = new JLabel("Repeat:");
    JCheckBox loopToggle = new JCheckBox();
    loopToggle.setActionCommand("LOOP");
    loopToggle.addActionListener(this);
    JPanel loopPanel = new JPanel();
    loopPanel.add(loop);
    loopPanel.add(loopToggle);

    // Export to SVG
    JButton export = new JButton("Save as SVG File");
    export.setActionCommand("EXPORT");
    export.addActionListener(this);

    // Set Ticks Per Second Speed Field
    JLabel speed = new JLabel("SET SPEED:");
    this.tickSpeedField.setPreferredSize(new Dimension(34, 26));
    this.tickSpeedField.setActionCommand("TICK SPEED");
    this.tickSpeedField.addActionListener(this);
    JLabel tickPerSec = new JLabel("(Default: 1)");
    JPanel tickPanel = new JPanel();
    tickPanel.add(speed);
    tickPanel.add(this.tickSpeedField);
    tickPanel.add(tickPerSec);

    // Add the buttons in order of appearance
    this.toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
    this.toolbar.add(tickPanel);
    this.toolbar.add(playPause);
    this.toolbar.add(restart);
    this.toolbar.add(export);
    this.toolbar.add(loopPanel);
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
   * Method that starts the animation.
   */
  @Override
  public void play() {
    this.setVisible(true);
  }

  /**
   * Display a list of shapes int the GUI.
   */
  @Override
  public void display(List<IShape> shapes) {
    this.drawingPanel.paint(shapes);
  }

  /**
   * Retrieves the shapes for the animation.
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
   * Add shapes to the view.
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
   * Get keyframes for automation.
   */
  @Override
  public Map<String, List<IFrame>> getKeyframes() {
    return this.keyframes;
  }

  /**
   * Sets all keyframes for automation.
   */
  @Override
  public void setKeyframes(Map<String, List<IFrame>> keyframes) {
    this.keyframes = new LinkedHashMap<>();
    for (String id : keyframes.keySet()) {
      List<IFrame> newList = new ArrayList<>();

      for (IFrame frame : keyframes.get(id)) {
        newList.add(new Frame(frame.getTime(), frame.getX(), frame.getY(),
            frame.getWidth(), frame.getHeight(), frame.getShapeRotation(),
            frame.getColor()));
      }
      this.keyframes.put(id, newList);
    }
    this.updateFramesList();
  }

  /**
   * Set the width of the GUI.
   */
  @Override
  public void setWidth(int width) {
    this.width = width;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Set the height of the GUI.
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Adds a listener that can receive action events.
   */
  @Override
  public void addButtonListener(ActionListener listener) {
    this.buttonListeners.add(listener);
  }

  /**
   * Adds a listener that can receive property change events.
   */
  @Override
  public void addPropertyListener(PropertyChangeListener listener) {
    this.tickSpeedListeners.add(listener);
    this.handler.addExportListener(listener);
  }

  /**
   * Adds a listener that receives a shape change events.
   */
  @Override
  public void addShapeChangeListener(IShapeChangeListener listener) {
    this.handler.addShapeChangeListener(listener);
  }

  /**
   * Adds a listener that receives frame change events.
   */
  @Override
  public void addFrameChangeListener(IFrameChangeListener listener) {
    this.handler.addFrameChangeListener(listener);
  }

  /**
   * Displays an error on the screen indicating to the user an error occurred.
   */
  @Override
  public void displayError(String s) {
    JOptionPane.showMessageDialog(this, s, "An error occurred",
        JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Helper method for actions performed.
   */
  private void buttonEventHelper(String type) {
    for (ActionListener listener : this.buttonListeners) {
      listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, type));
    }
  }

  /**
   * Helper method for changing the speed of the automation.
   */
  private void changeSpeed(String type, String newValue) {
    for (PropertyChangeListener listener : this.tickSpeedListeners) {
      listener.propertyChange(new PropertyChangeEvent(this, type,
          newValue, newValue));
    }
  }

  /**
   * Method to update the frames within the animation.
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
        this.handler.changeFrame(this, EFrameChangeType.EDIT, shape.getName(), keyframe.getTime(),
            keyframe.getX(), keyframe.getY(), keyframe.getWidth(), keyframe.getHeight(),
            keyframe.getShapeRotation(), keyframe.getColor());
        break;
      case "frame delete":
        this.handler.changeFrame(this, EFrameChangeType.DELETE, shape.getName(),
            keyframe.getTime());
        break;
      default:
        this.buttonEventHelper(process);
    }
  }

  /**
   * Method waiting for a shape change.
   *
   * @param event the change event.
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
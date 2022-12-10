package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

/**
 * This class handles inputs from GUI pop-ups presented to the end user.  This class allows users to
 * export their animation as an SVG file and allows users to change shapes and events that occur
 * within the automation.
 */
public class UserInputHandler implements InterfaceViewInputHandler {

  private final List<InterfaceShapeChangeListener> actionShapeChangeListeners;
  private final List<InterfaceFrameChangeListener> actionFrameChangeListeners;
  private final List<PropertyChangeListener> actionExportListeners;
  private final String[] shapeChoices = new String[]{"Rectangle", "Ellipse"};

  /**
   * A default constructor that initializes the handler class.
   */
  public UserInputHandler() {
    this.actionShapeChangeListeners = new ArrayList<>();
    this.actionFrameChangeListeners = new ArrayList<>();
    this.actionExportListeners = new ArrayList<>();
  }

  /**
   * Add a shape change listeners to the handler.
   *
   * @param listener in the form of a {@link InterfaceShapeChangeListener}.
   */
  @Override
  public void addShapeChangeListener(InterfaceShapeChangeListener listener) {
    this.actionShapeChangeListeners.add(listener);
  }

  /**
   * Add frame change listeners to the handler.
   *
   * @param listener in the form of a {@link InterfaceFrameChangeListener}
   */
  @Override
  public void addFrameChangeListener(InterfaceFrameChangeListener listener) {
    this.actionFrameChangeListeners.add(listener);
  }

  /**
   * Add property change listeners to the handler.
   *
   * @param listener in the form of a {@link PropertyChangeListener}
   */
  @Override
  public void addExportListener(PropertyChangeListener listener) {
    this.actionExportListeners.add(listener);
  }

  /**
   * Listen for an Export event.
   *
   * @param fileName the name of the file to be created.
   */
  private void eventExport(String fileName) {
    for (PropertyChangeListener listener : this.actionExportListeners) {
      listener.propertyChange(new PropertyChangeEvent(this, "EXPORT", fileName, fileName));
    }
  }

  /**
   * Listen for a {@link ShapeChange} event and update the change list.
   *
   * @param changeType the {@link ShapeChange} event type.
   * @param shapeType  the type of shape.
   * @param name       the name of the shape.
   */
  private void eventShapeChange(ShapeChange changeType, String shapeType, String name) {
    for (InterfaceShapeChangeListener listener : this.actionShapeChangeListeners) {
      listener.shapeChanged(new ShapeChangeEvent(this, changeType, shapeType, name));
    }
  }

  /**
   * List for a {@link EFrameChange} event and add it to the keyframe change list.
   *
   * @param type   the type of {@link EFrameChange} occurring.
   * @param name   the name of the shape being changed.
   * @param tick   the tick at which the frame change occurs.
   * @param x      the X coordinate of the shape.
   * @param y      the Y coordinate of the shape.
   * @param width  the width of the shape.
   * @param height the height of the shape.
   * @param degree the degree of rotation of the shape from its starting position.
   * @param color  the {@link Color} of the shape.
   */
  private void eventFrameChange(EFrameChange type, String name, int tick, int x, int y,
      int width, int height, int degree, Color color) {
    for (InterfaceFrameChangeListener listener : this.actionFrameChangeListeners) {
      listener.keyframeChanged(new FrameChangeEvent(this, type, name, tick, x, y, width,
          height, degree, color));
    }
  }

  /**
   * Add a frame deletion event to the keyframe change list.
   *
   * @param name the name of the shape.
   * @param tick the tick at which the frame occurs.
   */
  private void eventFrameDelete(String name, int tick) {
    for (InterfaceFrameChangeListener listener : this.actionFrameChangeListeners) {
      listener.keyframeChanged(new FrameChangeEvent(this, EFrameChange.DELETE, name, tick,
          0, 0, 0, 0, 0, new Color(0)));
    }
  }

  /**
   * Export the current animation to an SVG formatted text file.
   *
   * @param component the component to be formatted and exported.
   */
  @Override
  public void exportToSVG(Component component) {
    JLabel labelSaveAs = new JLabel("Save as:");
    JTextField textFieldFileName = new JTextField();
    textFieldFileName.setPreferredSize(new Dimension(150, 30));
    JLabel labelExtension = new JLabel(".svg");
    JPanel panelName = new JPanel();
    panelName.add(labelSaveAs);
    panelName.add(textFieldFileName);
    panelName.add(labelExtension);

    JLabel labelOutputLocation = new JLabel("(file will be saved to the \"out\" folder)");

    JPanel panelPopUp = new JPanel(new BorderLayout());
    panelPopUp.add(panelName, BorderLayout.CENTER);
    panelPopUp.add(labelOutputLocation, BorderLayout.SOUTH);

    int result = JOptionPane.showConfirmDialog(component, panelPopUp, "Export to SVG",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    JLabel labelError = new JLabel("Please enter a name", SwingConstants.CENTER);
    labelError.setForeground(Color.RED);
    panelPopUp.add(labelError, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      if (!textFieldFileName.getText().equals("")) {
        this.eventExport(textFieldFileName.getText());
        return;
      } else {
        result = JOptionPane.showConfirmDialog(component, panelPopUp, "Add Shape",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Change an existing shape within the automation.
   *
   * @param component the component being changed.
   * @param type      the type of shape being changed.
   * @param name      the name of the shape being changed.
   */
  @Override
  public void changeShape(Component component, ShapeChange type, String name) {
    if (type == ShapeChange.DELETE) {
      this.eventShapeChange(ShapeChange.DELETE, "", name);
      return;
    }

    JLabel labelShapeName = new JLabel("Shape Name:");
    JTextField inputFieldName = new JTextField();
    inputFieldName.setPreferredSize(new Dimension(100, 50));
    JPanel panelShapeName = new JPanel();
    panelShapeName.add(labelShapeName);
    panelShapeName.add(inputFieldName);

    JLabel labelShapeType = new JLabel("Shape Type:");
    JComboBox<String> comboShapeChoices = new JComboBox<>(this.shapeChoices);
    JPanel panelShapeType = new JPanel();
    panelShapeType.add(labelShapeType);
    panelShapeType.add(comboShapeChoices);

    JPanel panelPopUp = new JPanel(new BorderLayout());
    panelPopUp.add(panelShapeName, BorderLayout.NORTH);
    panelPopUp.add(panelShapeType, BorderLayout.SOUTH);

    int result = JOptionPane.showConfirmDialog(component, panelPopUp, "Add Shape",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    JLabel labelErrorMessage = new JLabel("Invalid Name", SwingConstants.CENTER);
    labelErrorMessage.setForeground(Color.RED);
    JPanel panelError = new JPanel(new BorderLayout());
    panelError.add(panelPopUp, BorderLayout.CENTER);
    panelError.add(labelErrorMessage, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      if (!inputFieldName.getText().equals("")) {
        this.eventShapeChange(ShapeChange.ADD,
            Objects.requireNonNull(comboShapeChoices.getSelectedItem()).toString(),
            inputFieldName.getText());
        return;
      } else {
        result = JOptionPane.showConfirmDialog(component, panelError, "Add Shape",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Allows for ADDING or DELETING an existing frame with a given name and tick location.
   *
   * @param component  the component to be set.
   * @param changeType the {@link EFrameChange} type occurring
   * @param name       the name of the frame to be changed.
   * @param tick       the tick location of the change.
   */
  @Override
  public void changeFrame(Component component, EFrameChange changeType, String name, int tick) {
    if (changeType == EFrameChange.EDIT) {
      throw new IllegalArgumentException(
          "Editing requires using the signature that takes in additional details about the change."
              + "  This signature only supports ADDING and DELETING.");
    }
    this.changeFrame(component, changeType, name, tick, 0, 0, 0, 0, 0, new Color(0));
  }

  /**
   * Allows for EDITING an existing frame within an animation.
   *
   * @param component  the component to be edited.
   * @param changeType the {@link EFrameChange} type occurring
   * @param name       the name of the frame being changed.
   * @param tick       the tick location of the change.
   * @param x          the X coordinate of the shape.
   * @param y          the Y coordinate of the shape.
   * @param width      the width of the shape.
   * @param height     the height of the shape.
   * @param degrees    the degrees of rotation from the shape's original starting orientation.
   * @param color      the {@link Color} of the shape.
   */
  @Override
  public void changeFrame(Component component, EFrameChange changeType, String name, int tick,
      int x, int y, int width, int height, int degrees, Color color) {

    String change;
    JTextField textFieldTick;
    JTextField textFieldX;
    JTextField textFieldY;
    JTextField textFieldWidth;
    JTextField textFieldHeight;
    JTextField textFieldDegrees;
    JTextField textFieldShadeRed;
    JTextField textFieldShadeGreen;
    JTextField textFieldShadeBlue;

    switch (changeType) {
      case EDIT:
        textFieldTick = new JTextField("" + tick);
        textFieldX = new JTextField("" + x);
        textFieldY = new JTextField("" + y);
        textFieldWidth = new JTextField("" + width);
        textFieldHeight = new JTextField("" + height);
        textFieldDegrees = new JTextField("" + degrees);
        textFieldShadeRed = new JTextField("" + color.getRed());
        textFieldShadeGreen = new JTextField("" + color.getGreen());
        textFieldShadeBlue = new JTextField("" + color.getBlue());
        change = "Edit Keyframe";
        break;
      case ADD:
        textFieldTick = new JTextField();
        textFieldX = new JTextField();
        textFieldY = new JTextField();
        textFieldWidth = new JTextField();
        textFieldHeight = new JTextField();
        textFieldDegrees = new JTextField();
        textFieldShadeRed = new JTextField();
        textFieldShadeGreen = new JTextField();
        textFieldShadeBlue = new JTextField();
        change = "Add Keyframe";
        break;
      case DELETE:
        this.eventFrameDelete(name, tick);
        return;
      default:
        throw new IllegalArgumentException("The change type given was invalid.  Please use EDIT, "
            + "ADD, or DELETE.");
    }

    BorderLayout layout = new BorderLayout();
    JPanel panelTick = new JPanel(layout);
    if (changeType == EFrameChange.ADD) {
      JLabel labelTick = new JLabel("tick");
      JPanel panelTickFields = new JPanel(new GridLayout(1, 2));
      JLabel labelTickInput = new JLabel("tick:");
      panelTickFields.add(labelTickInput);
      panelTickFields.add(textFieldTick);
      panelTickFields.setPreferredSize(new Dimension(144, 39));
      layout.setVgap(39);
      panelTick.add(labelTick, BorderLayout.NORTH);
      panelTick.add(panelTickFields, BorderLayout.CENTER);
    }

    JLabel labelCoordinates = new JLabel("coordinatePosition");
    JPanel panelCoordinateFields = new JPanel(new GridLayout(2, 2));
    JLabel labelX = new JLabel("x coordinate:");
    JLabel labelY = new JLabel("y coordinate:");

    panelCoordinateFields.add(labelX);
    panelCoordinateFields.add(textFieldX);
    panelCoordinateFields.add(labelY);
    panelCoordinateFields.add(textFieldY);
    panelCoordinateFields.setPreferredSize(new Dimension(144, 78));
    JPanel coordinatePositionPanel = new JPanel(new BorderLayout());
    coordinatePositionPanel.add(labelCoordinates, BorderLayout.NORTH);
    coordinatePositionPanel.add(panelCoordinateFields, BorderLayout.CENTER);

    JLabel labelSize = new JLabel("size");
    JPanel panelFieldSize = new JPanel(new GridLayout(2, 2));
    JLabel labelWidth = new JLabel("width:");
    JLabel labelHeight = new JLabel("height:");
    panelFieldSize.add(labelWidth);
    panelFieldSize.add(textFieldWidth);
    panelFieldSize.add(labelHeight);
    panelFieldSize.add(textFieldHeight);
    panelFieldSize.setPreferredSize(new Dimension(144, 78));
    JPanel panelSize = new JPanel(new BorderLayout());
    panelSize.add(labelSize, BorderLayout.NORTH);
    panelSize.add(panelFieldSize, BorderLayout.CENTER);

    BorderLayout layoutDegrees = new BorderLayout();
    JPanel panelDegrees = new JPanel(layoutDegrees);
    JLabel labelDegreesHeader = new JLabel("degrees");
    JPanel panelDegreeFields = new JPanel(new GridLayout(1, 2));
    JLabel labelRotate = new JLabel("rotate:");
    JLabel labelUnit = new JLabel("°");
    panelDegreeFields.add(labelRotate);
    panelDegreeFields.add(textFieldDegrees);
    panelDegreeFields.add(labelUnit);

    panelDegreeFields.setPreferredSize(new Dimension(160, 39));
    layoutDegrees.setVgap(39);
    panelDegrees.add(labelDegreesHeader, BorderLayout.NORTH);
    panelDegrees.add(panelDegreeFields, BorderLayout.CENTER);

    JLabel labelColor = new JLabel("color");
    JPanel panelColorFields = new JPanel(new GridLayout(3, 3));
    JLabel labelRed = new JLabel("red:");
    JLabel labelGreen = new JLabel("green:");
    JLabel labelBlue = new JLabel("blue:");
    JLabel labelRedRange = new JLabel("[0, 255]");
    JLabel labelBlueRange = new JLabel("[0, 255]");
    JLabel labelGreenRange = new JLabel("[0, 255]");
    panelColorFields.add(labelRed);
    panelColorFields.add(textFieldShadeRed);
    panelColorFields.add(labelRedRange);
    panelColorFields.add(labelGreen);
    panelColorFields.add(textFieldShadeGreen);
    panelColorFields.add(labelBlueRange);
    panelColorFields.add(labelBlue);
    panelColorFields.add(textFieldShadeBlue);
    panelColorFields.add(labelGreenRange);
    JPanel panelColor = new JPanel(new BorderLayout());
    panelColor.add(labelColor, BorderLayout.NORTH);
    panelColor.add(panelColorFields, BorderLayout.CENTER);

    JPanel panelPopUp = new JPanel();
    if (changeType == EFrameChange.ADD) {
      panelPopUp.add(panelTick);
    }
    panelPopUp.add(coordinatePositionPanel);
    panelPopUp.add(panelSize);
    panelPopUp.add(panelDegrees);
    panelPopUp.add(panelColor);

    int result = JOptionPane.showConfirmDialog(component, panelPopUp, change,
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);

    JLabel labelError = new JLabel("Invalid input", SwingConstants.CENTER);
    labelError.setForeground(Color.RED);
    JPanel panelError = new JPanel(new BorderLayout());
    panelError.add(panelPopUp, BorderLayout.CENTER);
    panelError.add(labelError, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      try {
        this.eventFrameChange(changeType, name, Integer.parseInt(textFieldTick.getText()),
            Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()),
            Integer.parseInt(textFieldWidth.getText()), Integer.parseInt(textFieldHeight.getText()),
            Integer.parseInt(textFieldDegrees.getText()),
            new Color(Integer.parseInt(textFieldShadeRed.getText()), Integer.parseInt(textFieldShadeGreen.getText()),
                Integer.parseInt(textFieldShadeBlue.getText())));
        return;
      } catch (NumberFormatException e) {
        result = JOptionPane.showConfirmDialog(component, panelError, change,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
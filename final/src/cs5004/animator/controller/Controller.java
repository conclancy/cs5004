package cs5004.animator.controller;

import cs5004.animator.model.IAnimation;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.IAnimationBuilder;
import cs5004.animator.view.IView;
import cs5004.animator.view.IViewFile;
import cs5004.animator.view.IViewGUI;
import cs5004.animator.view.ViewFile;
import cs5004.animator.view.ViewGUIEditor;
import cs5004.animator.view.ViewGUISimple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IFrameChangeEvent;
import cs5004.animator.view.IFrameChangeListener;
import cs5004.animator.view.IShapeChangeEvent;
import cs5004.animator.view.IShapeChangeListener;
import javax.swing.JOptionPane;

/**
 * This class represents the controller for the Easy Animator program.
 */
public class Controller implements IController, ActionListener, IFrameChangeListener,
    IShapeChangeListener, PropertyChangeListener {

  private final IAnimationBuilder animationBuilder;
  private IViewGUI viewGUI;
  private IModel model;
  private Timer timer;
  private int currentTickNum;
  private int ticksPS;
  private int firstTickNum;
  private int lastTickNum;
  private boolean animationPaused = true;
  private boolean animationLooped = false;
  private String out;
  private final String viewType;


  /**
   * Constructor for the controller.
   *
   * @param in    the path to the input file, as a String.
   * @param out   the path to the output file, as a String.
   * @param view  the view type, as a String.
   * @param speed the speed of the animation, as an int.
   */
  public Controller(String in, String out, String view, int speed) {
    this.animationBuilder = new AnimationBuilder();
    this.out = out;
    this.viewType = view;
    this.ticksPS = speed;

    try {
      this.model = AnimationReader.parseFile(new FileReader(in), this.animationBuilder);
    } catch (FileNotFoundException e) {
      popUpError("------ Error: File not found");
    }
  }

  /**
   * Start the view and creates the model with the given input.
   */
  public void play() {

    if (viewType.equals("gui") || viewType.equals("editor")) {

      this.viewGUI = new ViewGUIEditor(this.ticksPS);

      this.start();

      if (this.animationPaused && this.currentTickNum < model.getLastTick()) {
        this.firstTickNum = this.getFirstTick();
        this.timer = new Timer();
        this.timer.schedule(new DrawFrame(model), 0, 1000 / this.ticksPS);
        animationPaused = false;
      }

    } else if (viewType.equals("text")) {
      IView view = new ViewFile(model.getModelAsText(), this.out);
      view.play();
    } else if (viewType.equals("svg")) {
      IView view = new ViewFile(model.getSVGTags(ticksPS), this.out);
      view.play();
    } else if (viewType.equals("visual")) {
      IView view = new ViewGUISimple(model, ticksPS);
      view.play();
    } else {
      JOptionPane.showMessageDialog(null,
          "------ Error: Valid view type was not specified ('text', 'svg', 'visual', 'gui')",
          "------ Animation Error: An error occurred", 0);
    }
  }

  /**
   * Listen for changes within animation frames.
   *
   * @param event representing changes within one of the frames.
   */
  @Override
  public void frameChanged(IFrameChangeEvent event) {

    List<IAnimation> processes = animationBuilder.getAnimations()
        .get(event.getShapeName());

    if (event.getTick() < 0) {
      viewGUI.displayError("The ticks must be positive!");
      return;
    }

    switch (event.getType()) {
      case ADD:

        if (processes == null) {
          try {
            animationBuilder.addAnimation(event.getShapeName(), event.getTick(), event.getX(),
                event.getY(),
                event.getWidth(), event.getHeight(), event.getShapeRotation(),
                event.getColor().getRed(), event.getColor().getGreen(),
                event.getColor().getBlue(), event.getTick(), event.getX(), event.getY(),
                event.getWidth(), event.getHeight(), event.getShapeRotation(),
                event.getColor().getRed(), event.getColor().getGreen(),
                event.getColor().getBlue());
            this.viewGUI.setFrames(this.convertAnimationsToFrames(this.animationBuilder
                .getAnimations()));
            lastTickNum = animationBuilder.build().getLastTick();
          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
          return;
        }

        if (tickCheckHelper(event.getTick(), processes)) {
          viewGUI.displayError("This keyframe already exists.");
          return;
        }

        if (event.getTick() < processes.get(0).getStartTick()) {
          try {
            animationBuilder.addAnimation(
                event.getShapeName(),
                event.getTick(),
                event.getX(),
                event.getY(),
                event.getWidth(),
                event.getHeight(),
                event.getShapeRotation(),
                event.getColor().getRed(),
                event.getColor().getGreen(),
                event.getColor().getBlue(),
                processes.get(0).getStartTick(),
                processes.get(0).getStartX(),
                processes.get(0).getStartY(),
                processes.get(0).getStartWidth(),
                processes.get(0).getStartHeight(),
                processes.get(0).getStartRotationDegree(),
                processes.get(0).getStartColor().getRed(),
                processes.get(0).getStartColor().getGreen(),
                processes.get(0).getStartColor().getBlue());

            viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
            lastTickNum = animationBuilder.build().getLastTick();
          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
          return;
        } else if (event.getTick() > processes.get(processes.size() - 1).getEndTick()) {

          IAnimation temp = processes.get(processes.size() - 1);

          try {
            animationBuilder.addAnimation(event.getShapeName(), temp.getEndTick(),
                temp.getEndX(), temp.getEndY(), temp.getEndWidth(),
                temp.getEndHeight(), temp.getEndRotationDegree(), temp.getEndColor().getRed(),
                temp.getEndColor().getGreen(), temp.getEndColor().getBlue(),
                event.getTick(), event.getX(), event.getY(),
                event.getWidth(), event.getHeight(), event.getShapeRotation(),
                event.getColor().getRed(), event.getColor().getGreen(),
                event.getColor().getBlue());

            if (temp.getStartTick() == temp.getEndTick()) {
              this.animationBuilder.removeAnimation(event.getShapeName(), temp.getStartTick());
            }

            viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));

            lastTickNum = animationBuilder.build().getLastTick();

          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
          return;
        } else {

          for (IAnimation process : processes) {

            if (event.getTick() == process.getStartTick()
                || event.getTick() == process.getEndTick()) {
              this.viewGUI.displayError("There is already a keyframe at this tick.");
              return;
            }

            if (event.getTick() > process.getStartTick()
                && event.getTick() < process.getEndTick()) {

              try {
                animationBuilder.removeAnimation(event.getShapeName(), process.getStartTick());
                animationBuilder.addAnimation(event.getShapeName(), process.getStartTick(),
                    process.getStartX(), process.getStartY(), process.getStartWidth(),
                    process.getStartHeight(), process.getStartRotationDegree(),
                    process.getStartColor().getRed(), process.getStartColor().getGreen(),
                    process.getStartColor().getBlue(), event.getTick(), event.getX(),
                    event.getY(), event.getWidth(), event.getHeight(),
                    event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue());
                animationBuilder.addAnimation(event.getShapeName(), event.getTick(), event.getX(),
                    event.getY(),
                    event.getWidth(), event.getHeight(), event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue(), process.getEndTick(), process.getEndX(),
                    process.getEndY(), process.getEndWidth(), process.getEndHeight(),
                    process.getEndRotationDegree(), process.getEndColor().getRed(),
                    process.getEndColor().getGreen(), process.getEndColor().getBlue());
                viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
                lastTickNum = animationBuilder.build().getLastTick();
              } catch (IllegalArgumentException e) {
                viewGUI.displayError(e.getMessage());
              }
              return;
            }
          }
        }

        return;

      case EDIT:

        if (!tickCheckHelper(event.getTick(), processes)) {
          viewGUI.displayError("StatusKeyFrame does not exist at this time.");
          return;
        }

        if (event.getTick() == processes.get(0).getStartTick()) {

          try {
            animationBuilder.removeAnimation(event.getShapeName(), event.getTick());
            animationBuilder.addAnimation(event.getShapeName(), event.getTick(), event.getX(),
                event.getY(),
                event.getWidth(), event.getHeight(), event.getShapeRotation(),
                event.getColor().getRed(), event.getColor().getGreen(),
                event.getColor().getBlue(), processes.get(0).getEndTick(),
                processes.get(0).getEndX(), processes.get(0).getEndY(),
                processes.get(0).getEndWidth(), processes.get(0).getEndHeight(),
                processes.get(0).getEndRotationDegree(), processes.get(0).getEndColor()
                    .getRed(),
                processes.get(0).getEndColor().getGreen(),
                processes.get(0).getEndColor().getBlue());
            viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
        } else if (event.getTick() == processes.get(processes.size() - 1).getEndTick()) {

          try {
            animationBuilder.removeAnimation(event.getShapeName(), event.getTick());

            IAnimation lastProcess = processes.get(processes.size() - 1);
            animationBuilder.addAnimation(event.getShapeName(),
                lastProcess.getStartTick(),
                lastProcess.getStartX(),
                lastProcess.getStartY(),
                lastProcess.getStartWidth(),
                lastProcess.getStartHeight(),
                lastProcess.getStartRotationDegree(),
                lastProcess.getStartColor().getRed(),
                lastProcess.getStartColor().getGreen(),
                lastProcess.getStartColor().getBlue(), event.getTick(),
                event.getX(), event.getY(),
                event.getWidth(), event.getHeight(),
                event.getShapeRotation(), event.getColor().getRed(),
                event.getColor().getGreen(), event.getColor().getBlue());
            viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
        }

        for (int i = 1; i < processes.size(); i++) {

          if (event.getTick() == processes.get(i).getStartTick()) {

            try {
              animationBuilder.removeAnimation(event.getShapeName(),
                  processes.get(i).getStartTick());
              animationBuilder.removeAnimation(event.getShapeName(),
                  processes.get(i - 1).getStartTick());
              animationBuilder.addAnimation(event.getShapeName(),
                  processes.get(i - 1).getStartTick(),
                  processes.get(i - 1).getStartX(),
                  processes.get(i - 1).getStartY(), processes.get(i - 1).getStartWidth(),
                  processes.get(i - 1).getStartHeight(),
                  processes.get(i - 1).getStartRotationDegree(),
                  processes.get(i - 1).getStartColor().getRed(),
                  processes.get(i - 1).getStartColor().getGreen(),
                  processes.get(i - 1).getStartColor().getBlue(),
                  event.getTick(), event.getX(), event.getY(),
                  event.getWidth(), event.getHeight(), event.getShapeRotation(),
                  event.getColor().getRed(), event.getColor().getGreen(),
                  event.getColor().getBlue());
              animationBuilder.addAnimation(event.getShapeName(),
                  event.getTick(), event.getX(), event.getY(),
                  event.getWidth(), event.getHeight(), event.getShapeRotation(),
                  event.getColor().getRed(), event.getColor().getGreen(),
                  event.getColor().getBlue(), processes.get(i).getEndTick(),
                  processes.get(i).getEndX(), processes.get(i).getEndY(),
                  processes.get(i).getEndWidth(), processes.get(i).getEndHeight(),
                  processes.get(i).getEndRotationDegree(), processes.get(i).getEndColor()
                      .getRed(),
                  processes.get(i).getEndColor().getGreen(),
                  processes.get(i).getEndColor().getBlue());
              viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
            } catch (IllegalArgumentException e) {
              viewGUI.displayError(e.getMessage());
            }
          }
        }

        return;

      case DELETE:

        if (!tickCheckHelper(event.getTick(), processes)) {
          viewGUI.displayError("StatusKeyFrame does not exist at this time.");
          return;
        }

        if (event.getTick() == processes.get(0).getStartTick()
            || (event.getTick() == processes.get(processes.size() - 1).getEndTick())) {

          try {
            animationBuilder.removeAnimation(event.getShapeName(), event.getTick());
            viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
          } catch (IllegalArgumentException e) {
            viewGUI.displayError(e.getMessage());
          }
        } else {

          for (int i = 0; i < processes.size(); i++) {

            if (event.getTick() == processes.get(i).getStartTick()) {

              IAnimation process = processes.get(i - 1).combine(processes.get(i));

              try {
                animationBuilder.removeAnimation(event.getShapeName(), event.getTick());
                animationBuilder.removeAnimation(event.getShapeName(),
                    processes.get(i - 1).getStartTick());
                animationBuilder.addAnimation(event.getShapeName(), process.getStartTick(),
                    process.getStartX(),
                    process.getStartY(), process.getStartWidth(), process.getStartHeight(),
                    process.getStartRotationDegree(),
                    process.getStartColor().getRed(), process.getStartColor().getGreen(),
                    process.getStartColor().getBlue(), process.getEndTick(), process.getEndX(),
                    process.getEndY(), process.getEndWidth(), process.getEndHeight(),
                    process.getEndRotationDegree(),
                    process.getEndColor().getRed(), process.getEndColor().getGreen(),
                    process.getEndColor().getBlue());
                viewGUI.setFrames(convertAnimationsToFrames(animationBuilder.getAnimations()));
              } catch (IllegalArgumentException e) {
                viewGUI.displayError(e.getMessage());
              }
            }
          }
        }

        lastTickNum = animationBuilder.build().getLastTick();

        return;

      default:

    }
  }

  /**
   * Check if a passed tick exists as a start or an end within an animation list.
   *
   * @param tick the tick to be searched for.
   * @param list the list of {@link IAnimation} objects to be searched.
   * @return true if the time exists within the list.
   */
  private boolean tickCheckHelper(int tick, List<IAnimation> list) {

    if (list == null) {
      return false;
    } else if (tick == list.get(list.size() - 1).getEndTick()) {
      return true;
    }

    for (IAnimation process : list) {
      if (tick == process.getStartTick()) {
        return true;
      }
    }

    return false;
  }

  /**
   * Action listener method listening for shape change events created in the view. If a shape is
   * deleted, delete its associated animations and send an updated list of shapes and frames. Pass
   * error message back to the view if the event triggers an error.
   *
   * @param event shape change event created and sent by the view to alert this controller to a
   *              change in a specific shape object.
   */
  @Override
  public void shapeChanged(IShapeChangeEvent event) {

    switch (event.getChangeType()) {
      case ADD:

        if (animationBuilder.getShapeHash().get(event.getName()) != null) {
          viewGUI.displayError("Shape already exists with this id");
          return;
        }

        try {
          animationBuilder.declareShape(event.getName(), event.getShapeType());
          viewGUI.setShapes(animationBuilder.getShapeHash());
          viewGUI.setFrames(
              this.convertAnimationsToFrames(this.animationBuilder.getAnimations()));
        } catch (IllegalArgumentException e) {
          viewGUI.displayError(e.getMessage());
        }

        return;
      case DELETE:

        if (animationBuilder.getShapeHash().get(event.getName()) == null) {
          viewGUI.displayError("This shape cannot be deleted as the id does not exist");
          return;
        }

        try {
          animationBuilder.removeShape(event.getName());
          viewGUI.setShapes(animationBuilder.getShapeHash());
          viewGUI.setFrames(
              this.convertAnimationsToFrames(this.animationBuilder.getAnimations()));
          lastTickNum = animationBuilder.build().getLastTick();
        } catch (IllegalArgumentException e) {
          viewGUI.displayError(e.getMessage());
        }

        return;

      default:
    }
  }

  /**
   * Action listener method listening for buttons to be pressed on the view. Passed action include
   * play, pause, repeat, and restart the current animation.
   *
   * @param event button press event created and sent by the view to alert this controller.
   */
  @Override
  public void actionPerformed(ActionEvent event) {

    switch (event.getActionCommand()) {
      case "START":
        this.play();
        return;
      case "PAUSE":
        if (!animationPaused) {
          animationPaused = true;
        }
        return;
      case "LOOP":
        animationLooped = !animationLooped;
        return;
      case "RESTART":
        currentTickNum = firstTickNum;
        play();
        break;
      default:
        // no valid process received.
    }
  }

  /**
   * Property change listener method listening for changes to properties present on the GUI.
   *
   * @param event property change event created and sent by the view to alert this controller.
   */
  @Override
  public void propertyChange(PropertyChangeEvent event) {
    switch (event.getPropertyName()) {
      case "TICK SPEED":
        int speed;
        try {
          speed = Integer.parseInt(event.getNewValue().toString());
        } catch (NumberFormatException e) {
          viewGUI.displayError("Not a number.");
          return;
        }
        if (Integer.parseInt(event.getNewValue().toString()) < 1) {
          viewGUI.displayError("Cannot pass a negative number.");
          return;
        }

        ticksPS = speed;
        if (!animationPaused) {
          this.timer.cancel();
          this.timer = new Timer();
          this.timer.schedule(new DrawFrame(this.animationBuilder.build()), 0,
              1000 / this.ticksPS);
        }
        return;
      case "EXPORT":
        IViewFile svg = new ViewFile(animationBuilder.build().getSVGTags(ticksPS),
            event.getNewValue().toString() + ".svg");
        svg.play();
        return;
      case "SLIDER":
        currentTickNum = (int) ((Double.parseDouble(event.getNewValue().toString()) / 100.0)
            * (double) (lastTickNum - this.firstTickNum)) + firstTickNum;
        if (!animationPaused) {
          animationPaused = true;
        }
        viewGUI.display(animationBuilder.build().getState(currentTickNum));
        return;
      default:
    }
  }

  /**
   * Run the Model and the Views for the Easy Animation program.
   */
  private void start() {
    this.viewGUI.setFrames(
        this.convertAnimationsToFrames(this.animationBuilder.getAnimations()));
    this.viewGUI.setShapes(this.animationBuilder.getShapeHash());
    this.viewGUI.addButtonListener(this);
    this.viewGUI.addFrameChangeListener(this);
    this.viewGUI.addShapeChangeListener(this);
    this.viewGUI.addPropertyListener(this);
    this.viewGUI.setWidth((int) this.animationBuilder.getRequiredDimensions().getWidth());
    this.viewGUI.setHeight((int) this.animationBuilder.getRequiredDimensions().getHeight());
    this.viewGUI.play();
    this.viewGUI.display(this.animationBuilder.build().getState(this.firstTickNum));
  }

  /**
   * Converts a list of {@link IAnimation} into a list of {@link Frame} objects to be used by the
   * controller to generate graphics for the view.
   *
   * @param map the map of {@link IShape} objects and {@link IAnimation} objects.
   * @return a map of {@link IShape} objects and {@link Frame} objects.
   */
  private Map<String, List<IFrame>> convertAnimationsToFrames(Map<String,
      List<IAnimation>> map) {
    Map<String, List<IFrame>> output = new LinkedHashMap<>();

    for (Map.Entry<String, List<IAnimation>> entry : map.entrySet()) {
      ArrayList<IFrame> temp = new ArrayList<>();
      if (map.get(entry.getKey()) == null) {
        output.put(entry.getKey(), new ArrayList<>());
        continue;
      }

      for (int i = 0; i < entry.getValue().size(); i++) {
        temp.add(this.convert(entry.getValue().get(i)));
      }
      IAnimation last = entry.getValue().get(entry.getValue().size() - 1);

      if (last.getStartTick() != last.getEndTick()) {
        temp.add(new Frame(last.getEndTick(), last.getEndX(), last.getEndY(),
            last.getEndWidth(), last.getEndHeight(), last.getEndRotationDegree(),
            last.getEndColor()));
      }
      output.put(entry.getKey(), temp);
    }
    return output;
  }

  /**
   * Converts a single {@link IAnimation} object into a single {@link Frame} object.
   *
   * @param animation the animation to be converted.
   * @return the animation, as a {@link Frame} object.
   */
  private IFrame convert(IAnimation animation) {
    return new Frame(animation.getStartTick(), animation.getStartX(), animation.getStartY(),
        animation.getStartWidth(), animation.getStartHeight(), animation.getStartRotationDegree(),
        animation.getStartColor());
  }

  /**
   * Get the first tick within the animation.
   *
   * @return the first tick of the animation, as an int.
   */
  private int getFirstTick() {
    Map<String, List<IAnimation>> processes = this.animationBuilder
        .getAnimations();
    int firstTickNum = Integer.MAX_VALUE;

    for (String id : processes.keySet()) {
      if (!processes.get(id).isEmpty()) {
        firstTickNum = Math.min(firstTickNum, processes.get(id).get(0).getStartTick());
      }
    }
    if (firstTickNum == Integer.MAX_VALUE) {
      return 0;
    }
    return firstTickNum;
  }

  /**
   * A {@link TimerTask} that updates this visual view at each tick of the animation.
   */
  private class DrawFrame extends TimerTask {

    private final int lastTick;
    private final IModel model;

    // takes a final tick at which to stop the animation.
    private DrawFrame(IModel model) {
      super();
      this.lastTick = model.getLastTick();
      this.model = model;
    }

    /**
     * The action to be performed by this timer task, changing the states of each shape based on its
     * animations and calls the display method on the view. It also checks if the looped field and
     * the animationPaused field are true and either loop the animation or just end it the final
     * tick.
     */
    @Override
    public void run() {
      if (animationPaused) {
        timer.cancel();
      } else if (currentTickNum >= this.lastTick) {
        if (animationLooped) {
          currentTickNum = firstTickNum;
        } else {
          timer.cancel();
          animationPaused = true;
        }
      }
      List<IShape> shapesAtTick = this.model.getState(currentTickNum);
      viewGUI.display(shapesAtTick);
      viewGUI.setSlider((double) currentTickNum / (double) (lastTick - firstTickNum));
      currentTickNum++;
    }
  }

  private static void popUpError(String message) {
    JOptionPane.showMessageDialog(null, message,
        "------ Animation Error: An error occurred", 0);
    System.exit(1);
  }
}
# CS5004 Final Project
Using the Model, View, Controller (MVC) framework, we have created an EasyAutomation
program that will allow a caller to create an automation using shapes, colors, and 
movement actions.

# Model
## Interfaces
1. **IEasyAutomation** - Represents the model for the easy automation program. This is the entry point
into the program, allowing the controller to add Shape objects and Actions associated with those 
shapes.  EasyAutomation objects contain a list of shapes, list of actions, and the speed at which they should be replayed.
2. **IShape** - Represents shapes objects for this program.  A single instance of IShape represents a single shape object.
All shapes have a reference point, dimensions, and a color. 
3. **IAction** - Represents an action done upon a shape during an automation.  A single instance of this object represents
the state of the shape between the appear and the disappear parameters. Each action contains the time the action appears, 
the time the action disappears, the reference point of the shape during the action, the color of the shape during the
action, and the size of the shape during the action as compared to its original instantiated size. 

## Classes 
1. **EasyAutomation** - Implements the IEasyAutomation interface.
2. **AbstractShape** - Implements the reference point and the color fields of all shapes, and the logic for comparing all 
shapes based on their area. 
   * **Rectangle** - Represents a four sided shape with the reference point at the bottom left corner.
   * **Square** - Represents a special rectangle where all four sides are of equal length.
   * **Oval** - Represents a round object where the reference point is the center of the shape. The shortest distance to 
   the perimeter from the reference is measured as the minor axis and the longest distance to the perimeter is the major axis.
   * **Circle** - Represents a special oval, where the perimeter is always an equal distance from the center reference point. 
3. **Action** - Implements the Action Class.
4. **Point2D** - Represents a Cartesian coordinate for a shape with an x and y coordinate. 
5. **Color** - Represents the color of an object by quantifying Red, Green, and Blue light present within the color.  

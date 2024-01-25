package model.CommandPattern;

import model.*;
import model.Point;
import model.StrategyPattern.DrawEllipseShape;
import model.StrategyPattern.DrawRectShape;
import model.StrategyPattern.DrawShape;
import model.StrategyPattern.DrawTriangleShape;
import model.NullObjectPattern.ExecuteNullShape;
import model.interfaces.*;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

// This class draw the shapes based on user selection - Rectangle, Ellipse, and Triangle.

public class DrawCommand implements ICommand {

    Point startPoint;
    Point endPoint;
    PaintCanvasBase paintCanvas;
    IApplicationState appState;
    DrawShape drawShapeContext;
    IShape shapeStrategy;

    public DrawCommand(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas,
                       IApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        drawShapeContext =  new DrawShape();
    }

    @Override
    public void run() {

        Color primaryColor = ShapeColor.colorMap.get(appState.getActivePrimaryColor());
        Color secondaryColor = ShapeColor.colorMap.get(appState.getActiveSecondaryColor());

        // Selecting the shading type for the shape
        String shadeType = null;
        if (appState.getActiveShapeShadingType() == ShapeShadingType.FILLED_IN) {
            shadeType = "filled";
        } else if (appState.getActiveShapeShadingType() == ShapeShadingType.OUTLINE) {
            shadeType = "outline";
        } else if (appState.getActiveShapeShadingType() == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            shadeType = "filledAndOutline";
        }

        // Strategy Pattern Implementation : Defines the Strategy for the Shape
        if (appState.getActiveShapeType() == ShapeType.RECTANGLE) {
            shapeStrategy = new DrawRectShape(startPoint,endPoint,paintCanvas,shadeType,primaryColor,secondaryColor);
        }
        else if (appState.getActiveShapeType() == ShapeType.ELLIPSE) {
            shapeStrategy = new DrawEllipseShape(startPoint,endPoint,paintCanvas,shadeType,primaryColor,secondaryColor);
        }
        else if (appState.getActiveShapeType() == ShapeType.TRIANGLE) {
            shapeStrategy = new DrawTriangleShape(startPoint,endPoint,paintCanvas,shadeType,primaryColor,secondaryColor);
        }
        else {
            // Design Pattern 5 : Null Object Pattern Implementation
            shapeStrategy = new ExecuteNullShape();
        }

        // Setting the shape strategy
        drawShapeContext.setDrawShapeStrategy(shapeStrategy);

        // Passing shape drawing parameters to the drawShape interface
        drawShapeContext.drawShape();

        // Adding drawn shapes on the shape list
        DrawnShapeList.shapeList.add(shapeStrategy);

        // Adding shapes in CommandHistory
        CommandHistory.add(shapeStrategy);
    }
}

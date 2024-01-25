package model.CommandPattern;

import model.Point;
import model.ProxyPattern.SelectedOutlineProxy;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapeOutline;
import view.interfaces.PaintCanvasBase;

// Command Pattern Implementation
public class SelectModeOption {

    public static void clickedMode(Point startPoint, Point endPoint,
                                   PaintCanvasBase paintCanvas, IApplicationState appState) {
        ICommand command = null;
        SelectedOutlineProxy selectShapeProxy = null;
        SelectedOutlineProxy moveShapeProxy = null;

        if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
            command = new DrawCommand(startPoint, endPoint, paintCanvas, appState);
            command.run();
        }
        // Proxy Pattern Implementation
        else if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT) {
            command = new SelectCommand(startPoint, endPoint, paintCanvas);
            command.run();
            selectShapeProxy = new SelectedOutlineProxy(command);
            printShapeOutline(selectShapeProxy);
        }
        else if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.MOVE) {
            command = new MoveCommand(startPoint, endPoint, paintCanvas);
            command.run();
            moveShapeProxy = new SelectedOutlineProxy(command);
            printShapeOutline(moveShapeProxy);
        }
    }
//     Proxy pattern: printShapeOutline does not know the concrete type of the shapeOutline object;
//     it just knows it has received an ISelectedShapeOutline object.
    public static void printShapeOutline(ISelectedShapeOutline outlineOperation) {
        outlineOperation.shapeOutline();
    }
}

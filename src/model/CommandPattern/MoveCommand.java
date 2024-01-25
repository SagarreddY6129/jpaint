package model.CommandPattern;

import model.DrawnShapeList;
import model.Point;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.RefreshCanvas;
import view.interfaces.PaintCanvasBase;
import java.util.LinkedList;

// This class moves the shapes on the canvas

public class MoveCommand implements ICommand, IUndoable, ISelectedShapesList {

    private final PaintCanvasBase paintCanvas;
    private int xDelta;
    private int yDelta;
    private Point startPoint;
    private Point endPoint;
    private static boolean moveSelected = false;
    private static boolean undoSelected = false;
    private static boolean redoSelected = false;
    private LinkedList<IShape> tempMoveList;
    private LinkedList<IShape> tempRemoveList;

    public MoveCommand(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        tempMoveList = new LinkedList<>();
        tempRemoveList = new LinkedList<>();
    }

    @Override
    public void run() {
        undoSelected = false;
        redoSelected = false;

        // Calculating the offset - xDelta and yDelta
        xDelta = endPoint.getX() - startPoint.getX();
        yDelta = endPoint.getY() - startPoint.getY();

        // Updating the Coorodinates
        for (IShape temp1 : selectedShapes)
        {
            for (IShape temp2 : DrawnShapeList.shapeList)
            {
                if (temp1.equals(temp2))
                {
                    tempRemoveList.add(temp2);
                    temp1.updateCoordinates(xDelta, yDelta);
                    tempMoveList.add(temp1);
                }
            }
        }

        for (IShape temp1 : tempRemoveList)
        {
            selectedShapes.remove(temp1);
            DrawnShapeList.shapeList.remove(temp1);
        }
        for (IShape temp1 : tempMoveList)
        {
            selectedShapes.add(temp1);
            DrawnShapeList.shapeList.add(temp1);
        }

        // Canvas Refresh
        RefreshCanvas.refresh(paintCanvas);
        moveSelected = (selectedShapes.size() > 0) ? true : false;
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
        redoSelected = false;
        undoSelected = (tempMoveList.size() > 0) ? true : false;
        for (IShape temp1 : tempMoveList)
        {
            selectedShapes.remove(temp1);
            DrawnShapeList.shapeList.remove(temp1);
        }
        for (IShape temp1 : tempRemoveList)
        {
            temp1.updateCoordinates(0 - xDelta, 0 - yDelta);
            selectedShapes.add(temp1);
            DrawnShapeList.shapeList.add(temp1);
        }
        if (paintCanvas != null) {
            RefreshCanvas.refresh(paintCanvas);
        }
    }

    @Override
    public void redo() {
        undoSelected = false;
        redoSelected = (tempMoveList.size() > 0) ? true : false;

        for (IShape temp1 : tempRemoveList)
        {
            selectedShapes.remove(temp1);
            DrawnShapeList.shapeList.remove(temp1);
        }
        for (IShape temp1 : tempMoveList)
        {
            temp1.updateCoordinates(xDelta, yDelta);
            selectedShapes.add(temp1);
            DrawnShapeList.shapeList.add(temp1);
        }
        if (paintCanvas != null) {
            RefreshCanvas.refresh(paintCanvas);
        }
    }

    public static boolean isMoveSelected() {
        return moveSelected;
    }

    public static void setMoveSelected(boolean moveSelected) {
        MoveCommand.moveSelected = moveSelected;
    }

    public static boolean isUndoSelected() {
        return undoSelected;
    }

    public static boolean isRedoSelected() {
        return redoSelected;
    }

    public static void setUndoSelected(boolean undoSelected) {
        MoveCommand.undoSelected = undoSelected;
    }

    public static void setRedoSelected(boolean redoSelected) {
        MoveCommand.redoSelected = redoSelected;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public PaintCanvasBase getPaintCanvas() {
        return paintCanvas;
    }
}

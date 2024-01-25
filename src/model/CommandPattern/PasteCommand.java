package model.CommandPattern;

import model.DrawnShapeList;
import model.Point;
import model.SelectedShapesList;
import model.StrategyPattern.DrawEllipseShape;
import model.StrategyPattern.DrawRectShape;
import model.StrategyPattern.DrawShape;
import model.StrategyPattern.DrawTriangleShape;
import model.interfaces.ICommand;
import model.interfaces.IEventCommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.RefreshCanvas;
import view.interfaces.PaintCanvasBase;
import java.util.LinkedList;

// This class paste the copied shape from the clipboard.

public class PasteCommand implements IEventCommand, ICommand, IUndoable {

    private LinkedList<IShape> tempPasteList;
    private DrawTriangleShape tempTriangle;
    private PaintCanvasBase paintCanvas = null;
    private static boolean isPasteSelected = false;

    public PasteCommand() {
        tempPasteList = new LinkedList<>();

        for (IShape temp : clipboardShapes) {


            // Calculating the offset
            Point tempStartPoint = new Point(temp.getStartPointX() + 20, temp.getStartPointY() + 20);
            Point tempEndPoint = new Point(temp.getStartPointX() + temp.getWidth() + 20,
                    temp.getStartPointY() + temp.getHeight() + 20);

            DrawShape drawShapeContext =  new DrawShape();
            IShape shapeStrategy = null;

            if (paintCanvas == null)
                paintCanvas = temp.getPaintCanvas();

            // Drawing new object on canvas
            if(temp instanceof DrawRectShape) {
                shapeStrategy = new DrawRectShape(tempStartPoint, tempEndPoint, temp.getPaintCanvas(),temp.getShadeType(),
                        temp.getPrimaryColor(), temp.getSecondaryColor());
            }
            else if(temp instanceof DrawEllipseShape) {
                shapeStrategy = new DrawEllipseShape(tempStartPoint, tempEndPoint, temp.getPaintCanvas(),temp.getShadeType(),
                        temp.getPrimaryColor(), temp.getSecondaryColor());
            }
            else if(temp instanceof DrawTriangleShape) {
                tempTriangle = (DrawTriangleShape) temp;
                int[] xValues = tempTriangle.getXValues();
                int[] yValues = tempTriangle.getYValues();
                tempStartPoint = new Point(xValues[0] + 15, yValues[0] + 15);
                tempEndPoint = new Point(xValues[2] + 15, yValues[2] + 15);
                shapeStrategy = new DrawTriangleShape(tempStartPoint, tempEndPoint, temp.getPaintCanvas(),temp.getShadeType(),
                        temp.getPrimaryColor(), temp.getSecondaryColor());
            }

            drawShapeContext.setDrawShapeStrategy(shapeStrategy);
            drawShapeContext.drawShape();

            if (drawShapeContext != null)
                tempPasteList.add(shapeStrategy);
        }
    }

    @Override
    public void run() {
        for (IShape temp : tempPasteList) {
            if (temp != null) {
                DrawnShapeList.shapeList.add(temp);
                CommandHistory.add(this);
            }
        }
        if (paintCanvas != null) {
            isPasteSelected = (SelectedShapesList.selectedShapes.size() > 0) ? true : false;

            // Canvas Refresh
            RefreshCanvas.refresh(paintCanvas);
        }
    }

    @Override
    public void undo() {
        MoveCommand.setUndoSelected(false);
        for (IShape temp : tempPasteList)
        {
            if (temp != null)
                DrawnShapeList.shapeList.remove(temp);
        }
        if (paintCanvas != null) {
            isPasteSelected = (SelectedShapesList.selectedShapes.size() > 0) ? true : false;
            RefreshCanvas.refresh(paintCanvas);
        }
    }

    @Override
    public void redo() {
        MoveCommand.setRedoSelected(false);
        this.run();
    }

    public static void setIsPasteSelected(boolean isPasteSelected) {
        PasteCommand.isPasteSelected = isPasteSelected;
    }

    public static boolean isIsPasteSelected() {
        return isPasteSelected;
    }
}

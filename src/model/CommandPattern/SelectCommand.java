package model.CommandPattern;

import model.DrawnShapeList;
import model.Point;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;
import view.gui.RefreshCanvas;
import view.interfaces.PaintCanvasBase;

// This class selects the drawn shape
public class SelectCommand implements ICommand, ISelectedShapesList {
    private PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Point minimum;
    private int width;
    private int height;
    private static boolean isSelected = false;

    public SelectCommand(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {

        MoveCommand.setMoveSelected(false);


        // Calculating minimum(X,Y), height and width of the invisible box
        minimum = new Point(Math.min(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY()));

        width = java.lang.Math.abs(startPoint.getX() - endPoint.getX());

        height = java.lang.Math.abs(startPoint.getY() - endPoint.getY());

        selectedShapes.clear();
        isSelected = false;

        RefreshCanvas.refresh(paintCanvas);
        selectedShapes.clear();

        // Collision Detection Algorithm
        for (IShape temp : DrawnShapeList.shapeList)
        {
                if (temp.getStartPointX() < minimum.getX() + width &&
                    temp.getStartPointX() + temp.getWidth() > minimum.getX() &&
                    temp.getStartPointY() < minimum.getY() + height &&
                    temp.getStartPointY() + temp.getHeight() > minimum.getY())
            {
                     // Added the collided shapes on the list
                     selectedShapes.add(temp);

            }
        }

        isSelected = (selectedShapes.size() > 0) ? true : false;

    }

    public static boolean isSelected() {
        return isSelected;
    }

    public PaintCanvasBase getPaintCanvas() {
        return paintCanvas;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}

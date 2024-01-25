package model.StrategyPattern;

import model.DrawnShapeList;
import model.Point;
import model.SelectedShapesList;
import model.interfaces.IShape;
import view.gui.RefreshCanvas;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.LinkedList;

// This class draws the triangle shape
public class DrawTriangleShape implements IShape {

    private PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private int[] xValues = new int[3];
    private int[] yValues = new int[3];
    private String shadeType;
    private Color primaryColor;
    private Color secondaryColor;

    public DrawTriangleShape(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas,
                             String shadeType, Color primaryColor, Color secondaryColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.shadeType = shadeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        //Setting X-Coordinate
        xValues[0] = startPoint.getX();
        xValues[1] = startPoint.getX();
        xValues[2] = endPoint.getX();

        //Setting Y-Coordinate
        yValues[0] = startPoint.getY();
        yValues[1] = endPoint.getY();
        yValues[2] = endPoint.getY();
    }

    @Override
    public void drawShape() {

        // Filled in rectangles
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        if (shadeType.equals("filled")) {
            graphics2d.setColor(primaryColor);
            graphics2d.fillPolygon(xValues, yValues, 3);
        } else if (shadeType == "outline") {
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(primaryColor);
            graphics2d.drawPolygon(xValues, yValues, 3);
        } else if (shadeType == "filledAndOutline") {
            graphics2d.setColor(primaryColor);
            graphics2d.fillPolygon(xValues, yValues, 3);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(secondaryColor);
            graphics2d.drawPolygon(xValues, yValues, 3);
        }
    }

    @Override
    public LinkedList<IShape> getShapeChildren() {
        return null;
    }

    @Override
    public void updateCoordinates(int xDelta, int yDelta) {
        xValues[0] = xValues[0] + xDelta;
        xValues[1] = xValues[1] + xDelta;
        xValues[2] = xValues[2] + xDelta;
        yValues[0] = yValues[0] + yDelta;
        yValues[1] = yValues[1] + yDelta;
        yValues[2] = yValues[2] + yDelta;
    }

    public int[] getXValues() {
        int[] tempX = new int[3];
        for (int i = 0; i < 3; i++)
            tempX[i] = xValues[i];
        return tempX;
    }

    public int[] getYValues() {
        int[] tempY = new int[3];
        for (int i = 0; i < 3; i++)
            tempY[i] = yValues[i];
        return yValues;
    }

    @Override
    public int getStartPointX() {
        if (xValues[0] < xValues[2]) return xValues[0];
        else return xValues[2];
    }

    @Override
    public int getStartPointY() {
        if (yValues[0] < yValues[2]) return yValues[0];
        else return yValues[2];
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }

    @Override
    public int getWidth() {
        return java.lang.Math.abs(xValues[0] - xValues[2]);
    }

    @Override
    public int getHeight() {
        return java.lang.Math.abs(yValues[0] - yValues[2]);
    }

    @Override
    public Point getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point getEndPoint() {
        return this.endPoint;
    }

    @Override
    public IShape getDrawShapeStrategy() {
        return getDrawShapeStrategy();
    }

    @Override
    public String getShadeType() {
        return this.shadeType;
    }

    @Override
    public Color getPrimaryColor() {
        return this.primaryColor;
    }

    @Override
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    @Override
    public void undo() {
        DrawnShapeList.shapeList.removeLast();
        if (SelectedShapesList.selectedShapes.contains(this))
            SelectedShapesList.selectedShapes.removeLast();
        RefreshCanvas.refresh(paintCanvas);
    }

    @Override
    public void redo() {
        DrawnShapeList.shapeList.add(this);
        RefreshCanvas.refresh(paintCanvas);
    }
}
package model.interfaces;

import model.Point;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.LinkedList;

// IShape interface defines all the methods used by the shapes
public interface IShape extends IUndoable, ISelectedShapesList

{
    public int getStartPointX();
    public int getStartPointY();
    public int getWidth();
    public int getHeight();
    public PaintCanvasBase getPaintCanvas();
    public Point getStartPoint();
    public Point getEndPoint();
    public IShape getDrawShapeStrategy();
    public String getShadeType();
    public Color getPrimaryColor();
    public Color getSecondaryColor();
    public void drawShape();
    public void updateCoordinates(int xDelta, int yDelta);
    public LinkedList<IShape> getShapeChildren();
}

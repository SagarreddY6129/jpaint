package model.ProxyPattern;

import model.DrawnShapeList;
import model.SelectedShapesList;
import model.StrategyPattern.DrawEllipseShape;
import model.StrategyPattern.DrawRectShape;
import model.StrategyPattern.DrawTriangleShape;
import model.interfaces.ISelectedShapeOutline;
import model.interfaces.IShape;


// This class draws the selected shapes outline
public class SelectedShapeOutline implements ISelectedShapeOutline {

    GroupShapeOutline drawGroupShapeOutline = new GroupShapeOutline();
    DrawRectOutline drawRectOutline = new DrawRectOutline();
    DrawEllipseOutline drawEllipseOutline = new DrawEllipseOutline();
    DrawTriangleOutline drawTriangleOutline = new DrawTriangleOutline();

    @Override
    public void shapeOutline() {

        for (IShape temp1 : DrawnShapeList.shapeList) {
            for (IShape temp2 : SelectedShapesList.selectedShapes) {
                if (temp2.equals(temp1)) {
                    if (temp2 instanceof DrawRectShape) {
                        drawRectOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof DrawEllipseShape) {
                        drawEllipseOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof DrawTriangleShape) {
                        DrawTriangleShape tempTriangleValues = (DrawTriangleShape) temp2;
                        int[] xValues = tempTriangleValues.getXValues();
                        int[] yValues = tempTriangleValues.getYValues();
                        drawTriangleOutline.draw(xValues, yValues, temp2.getPaintCanvas());
                    }
                }
            }
        }
    }
}

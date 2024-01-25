package model.ProxyPattern;

import model.Point;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class DrawEllipseOutline {

    public void draw(int startPointX, int startPointY, int width, int height, PaintCanvasBase paintCanvas) {

        Point startPoint = new Point(startPointX - 5,startPointY - 5);
        Point endPoint = new Point (startPointX + width + 5, startPointY + height + 5);

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawOval(startPoint.getX(), startPoint.getY(), Math.abs(startPoint.getX() - endPoint.getX()),
                Math.abs(startPoint.getY() - endPoint.getY()));
    }
}

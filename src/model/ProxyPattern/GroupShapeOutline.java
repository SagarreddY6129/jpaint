package model.ProxyPattern;

import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class GroupShapeOutline {
    public void draw(int startPoint, int endPoint, int width, int height, PaintCanvasBase paintCanvas) {

        Point startPoint1 = new Point((int)startPoint - 5, (int) endPoint - 5);
        //Point endPoint1 = new Point((int)endPoint.getX() + 5, (int)endPoint.getY() + 5);

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect( (int)startPoint1.getX(), (int)startPoint1.getY(), width, height);
    }

}

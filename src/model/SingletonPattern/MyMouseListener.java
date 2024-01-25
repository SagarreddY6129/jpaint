package model.SingletonPattern;

import model.CommandPattern.SelectModeOption;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private static PaintCanvasBase paintCanvas;
    private static ApplicationState appState;
    private static MyMouseListener myMouseListenerObj;

    private MyMouseListener() { }

    public static MyMouseListener getInstance() {
        if(myMouseListenerObj == null) {
            myMouseListenerObj = new MyMouseListener();
        }
        return myMouseListenerObj;
    }

    public void setSettings(PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = new Point(e.getX(),e.getY());
        SelectModeOption.clickedMode(startPoint, endPoint, paintCanvas, appState);
    }
}

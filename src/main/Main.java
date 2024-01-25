package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.SingletonPattern.MyMouseListener;
import model.ShapeColor;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        ShapeColor.getMap();

        // Singleton Design Pattern
        // Mouse Listener event - Passing paintCanvas and application state
        MyMouseListener myMouseListenerObj = MyMouseListener.getInstance();
        paintCanvas.addMouseListener(myMouseListenerObj);
        myMouseListenerObj.setSettings(paintCanvas, appState);

        // For example purposes only; remove all lines below from your final project.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package view.gui;

import model.*;
import model.DrawnShapeList;
import model.CommandPattern.PasteCommand;
import model.ProxyPattern.SelectedShapeOutline;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

// Refreshes the canvas
public abstract class RefreshCanvas {

    private static SelectedShapeOutline selectedShapeOutline = new SelectedShapeOutline();

    public static void refresh(PaintCanvasBase paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        // Redrawing the updated shapes on the paintcanvas

        for(IShape shape: DrawnShapeList.shapeList) {

            if (PasteCommand.isIsPasteSelected()) {
                for (IShape temp: SelectedShapesList.selectedShapes) {
                    if (temp.equals(shape)) {
                        selectedShapeOutline.shapeOutline();
                        PasteCommand.setIsPasteSelected(false);
                    }
                }
            }
            shape.drawShape();
        }
    }
}


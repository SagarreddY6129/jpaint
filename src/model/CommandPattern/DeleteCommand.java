package model.CommandPattern;

import model.DrawnShapeList;
import model.ProxyPattern.SelectedShapeOutline;
import model.interfaces.*;
import view.gui.RefreshCanvas;
import view.interfaces.PaintCanvasBase;
import java.util.LinkedList;

// This class deletes the selected shapes

public class DeleteCommand implements IEventCommand, ISelectedShapesList, IUndoable {

    private LinkedList<IShape> tempList;
    private PaintCanvasBase paintCanvas = null;
    private SelectedShapeOutline selectedShapeOutline;

    public DeleteCommand() {
        tempList = new LinkedList<>();
        for (IShape temp1 : selectedShapes) {
            for (IShape temp2 : DrawnShapeList.shapeList) {
                if (paintCanvas == null)
                    paintCanvas = temp2.getPaintCanvas();
                if (temp1.equals(temp2))
                    tempList.add(temp1);
            }
        }
    }

    public void run() {
        for (IShape temp : tempList) {
            selectedShapes.remove(temp);
            DrawnShapeList.shapeList.remove(temp);
        }

        if (paintCanvas != null) {
            // Canvas Refresh
            RefreshCanvas.refresh(paintCanvas);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape temp : tempList)
        {
            selectedShapes.add(temp);
            DrawnShapeList.shapeList.add(temp);
        }
        if (paintCanvas != null) {
            RefreshCanvas.refresh(paintCanvas);
            selectedShapeOutline = new SelectedShapeOutline();
            selectedShapeOutline.shapeOutline();
        }
    }

    @Override
    public void redo() {
        this.run();
    }
}

package model.ProxyPattern;

import model.CommandPattern.MoveCommand;
import model.CommandPattern.SelectCommand;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapeOutline;
import model.persistence.ApplicationState;


// This class is the outline proxy class that checks for the condition - if the shape is selected then it will call
// the SelectedshapeOutline class and execute the outline method.
public class SelectedOutlineProxy implements ISelectedShapeOutline {
    private ICommand command;
    private SelectedShapeOutline selectedShapeOutline;
    private SelectedShapeOutline selectedShapeOutline1;
    private SelectedShapeOutline selectedShapeOutline2;
    private SelectedShapeOutline selectedShapeOutline3;
    private SelectCommand selectShape = null;
    private MoveCommand moveShape = null;
    private ApplicationState undoShape = null;
    private ApplicationState redoShape = null;

    public SelectedOutlineProxy(ICommand command) {
        this.command = command;
        if (command instanceof SelectCommand) {
            selectShape = (SelectCommand) command;
        }
        else if (command instanceof MoveCommand) {
            moveShape = (MoveCommand) command;
        }
        else if(command instanceof ApplicationState) {
            undoShape = (ApplicationState) command;
        }
        else if(command instanceof ApplicationState) {
            redoShape = (ApplicationState) command;
        }
    }

    @Override
    public void shapeOutline() {
        if (selectShape.isSelected() && !(moveShape.isMoveSelected())) {
            selectedShapeOutline = new SelectedShapeOutline();
            selectedShapeOutline.shapeOutline();
        }

        if (moveShape.isMoveSelected() && !(undoShape.isUndoSelected())) {
            selectedShapeOutline1 = new SelectedShapeOutline();
            selectedShapeOutline1.shapeOutline();
        }
        if(undoShape.isUndoSelected() && !(redoShape.isRedoSelected())) {
            selectedShapeOutline2 = new SelectedShapeOutline();
            selectedShapeOutline2.shapeOutline();
        }
        if(redoShape.isRedoSelected()) {
            selectedShapeOutline3 = new SelectedShapeOutline();
            selectedShapeOutline3.shapeOutline();
        }
    }
}

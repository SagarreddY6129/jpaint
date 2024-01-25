package model.CommandPattern;

import model.interfaces.ICommand;
import model.interfaces.IEventCommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;

// This class copies the selected shapes to clipboards
public class CopyCommand implements ICommand, IEventCommand, ISelectedShapesList {

    @Override
    public void run() {
        clipboardShapes.clear();
        for (IShape temp : selectedShapes) {
            clipboardShapes.add(temp);
        }
        System.out.println("Copied Shapes List: " + clipboardShapes.toString());
    }
}

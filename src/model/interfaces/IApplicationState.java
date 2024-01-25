package model.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {

    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    // --------------------------------- Updated Version 1.0 --------------------------------------------------------
    // Adding Functionality - Copy Shape, Paste Shape and Delete Shape

    void copySelectedToClipboard();

    void pasteFromClipboard();

    void deleteSelectedShape();

    void undoCommand();

    void redoCommand();
    
}

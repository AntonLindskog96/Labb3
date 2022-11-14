package model;

import Shapes.CircleShape;
import Shapes.RectangleShape;
import Shapes.ShapeType;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {

    ShapeModel shapeModel = new ShapeModel();

    @Test
    void testIfPointerIsInsideOrOutsideShape(){
        shapeModel.getShapeObservableList().add(new RectangleShape(Color.BLACK, 30,30,30, ShapeType.RECTANGLE));
        shapeModel.getShapeObservableList().add(new CircleShape(Color.BLACK,30,30,20,ShapeType.CIRCLE));

        double insideMouseX = 30;
        double insideMouseY = 30;

        var insideExpect = true;
        var isInside = false;

        for(var shape : shapeModel.getShapeObservableList()) {
            if(shape.isInsideShape(insideMouseX, insideMouseY));
            isInside = true;
        }
        assertEquals(insideExpect, isInside);
    }

    @Test
    void testIfPointerIsInsideWhenMouseIsOutside (){
        shapeModel.getShapeObservableList().add(new RectangleShape(Color.BLACK, 30,30,30, ShapeType.RECTANGLE));
        shapeModel.getShapeObservableList().add(new CircleShape(Color.BLACK,30,30,20,ShapeType.CIRCLE));

        double outsideMouseX = 5;
        double outsideMouseY = 5;

        var outsideExpect = true;
        var isOutside = false;

        for (var shape : shapeModel.getShapeObservableList()) {
            if(shape.isInsideShape(outsideMouseX, outsideMouseY)) {
                isOutside = true;
            }
            assertEquals(isOutside,outsideExpect);
        }
    }

}
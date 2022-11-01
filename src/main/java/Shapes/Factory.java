package Shapes;

import javafx.scene.paint.Color;

public class Factory {

        public static RectangleShape rectangleOf (Color color,double x, double y, double size){
            return new RectangleShape(color,x,y,size);
        }
            public static CircleShape circleOf (Color color,double x, double y, double size){
                return new CircleShape(color,x,y,size);
            }

    public static Shape createShape (ShapeType type,Color color, double x, double y, double size) {
       return switch (type) {
            case CIRCLESHAPE -> circleOf(color, x, y, size);
            case RECTANGLESHAPE -> rectangleOf(color, x, y, size);
        };

    }

            }
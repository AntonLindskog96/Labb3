package Shapes;

import javafx.scene.paint.Color;

public class Factory {

        public static RectangleShape rectangleOf (Color color,double x, double y, double size){
            return new RectangleShape(color,x,y,size,ShapeType.RECTANGLE);
        }
            public static CircleShape circleOf (Color color,double x, double y, double size){
                return new CircleShape(color,x,y,size,ShapeType.CIRCLE);
            }

    public static Shape createShape (ShapeType type,Color color, double x, double y, double size) {
       return switch (type) {
            case CIRCLE -> circleOf(color, x, y, size);
            case RECTANGLE -> rectangleOf(color, x, y, size);

        };

    }

            }
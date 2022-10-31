package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape extends Shape{

    public CircleShape(Color color, double x, double y, double size) {
        super(color,x,y,size );

    }

    @Override
    public void draw (GraphicsContext context){
        double size = getSize();
        double y = getY() - size / 2;
        double x = getX() - size / 2;
        context.setFill(getColor());
        context.fillOval(x,y , size, size);
    }

    @Override
    public String toString() {
        return getColor() + super.getClass().toString();
    }
}

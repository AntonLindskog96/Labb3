package Shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape extends Shape{

    public RectangleShape(Color color, double x, double y, double size,ShapeType type){
        super(color, x,y,size,type);

    }

    @Override
    public void draw (GraphicsContext context){
        double size = getSize();
        double x = getX() - size / 2* 1.75;
        double y = getY() - size / 2;
        context.setFill(getColor());
        context.fillRect(x,y, size * 1.75, size);
    }

    @Override
    public Shape copyOf (){
        return new RectangleShape(getColor(), getX(),getY(),getSize(),getShape());
    }

    @Override
    public String toString() {
        return getColor() + super.getClass().toString();
    }
}

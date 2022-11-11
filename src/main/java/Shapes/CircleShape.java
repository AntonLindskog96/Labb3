package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape extends Shape{

    public CircleShape(Color color, double x, double y, double size,ShapeType type) {
        super(color,x,y,size,type );

    }
    @Override
    public String svgFormat() {
        return "<circle cx=\"" + centerPoint().getPosX() + "\" cy=\"" + centerPoint().getPosY() + "\" r=\"" + (getSize() / 2) +
                "\" fill=\"#" + getColor().toString().substring(2,10) + "\" />";
    }

    @Override
    public void draw (GraphicsContext context){
        context.setFill(getBorderColor());
        context.fillOval(getX() - getSize() - 2.5, getY() - getSize() - 2.5, 2 * getSize() + 5, 2 * getSize() + 5);
        context.setFill(getColor());
        context.fillOval(getX() - getSize(), getY() - getSize(), 2 * getSize(), 2 * getSize());
    }

    @Override
    public Shape copyOf (){
        return new CircleShape(getColor(), getX(),getY(), getSize(),getShape());
    }



    @Override
    public String toString() {
        return getColor() + super.getClass().toString();
    }


    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        double distX = mouseX - getX();
        double distY = mouseY - getY();
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        return distance <= getSize();
    }

}

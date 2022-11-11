package Shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape extends Shape{

    public RectangleShape(Color color, double x, double y, double size,ShapeType type){
        super(color, x,y,size,type);

    }

    public String svgFormat() {
        String convertedColor = "#" + getColor().toString().substring(2,10);

        return "<rect x=\"" + (getX() - getSize()) + "\" " +
                "y=\"" + (getY() - getSize()) + "\" " +
                "width=\"" + (2 * getSize()) + "\" " +
                "height=\"" + (2 * getSize()) + "\" " +
                "fill=\"" + convertedColor + "\" />";
    }

    @Override
    public void draw (GraphicsContext context){
        context.setFill(getBorderColor());
        context.fillRect(getX() - getSize() - 2.5, getY() - getSize() - 2.5, 2 * getSize() + 5, 2 * getSize() + 5);
        context.setFill(getColor());
        context.fillRect(getX() - getSize(), getY() - getSize(), 2 * getSize(), 2 * getSize());
    }
    @Override
    public Shape copyOf (){
        return new RectangleShape(getColor(), getX(),getY(),getSize(),getShape());
    }

    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        double leftX = getX() - getSize();
        double topY = getY() - getSize();

        return mouseX >= leftX &&
                mouseX <= leftX + 2 * getSize() &&
                mouseY >= topY &&
                mouseY <= topY + 2 * getSize();
    }

    @Override
    public String toString() {
        return "" + super.getColor();
    }
}

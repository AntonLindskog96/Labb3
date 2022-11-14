package Shapes;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.example.labb3.Point;

import java.util.Objects;

public abstract class Shape {

    private final SimpleDoubleProperty y =  new SimpleDoubleProperty();
    private final SimpleDoubleProperty x = new SimpleDoubleProperty();
    private final SimpleDoubleProperty size = new SimpleDoubleProperty();
    private final SimpleObjectProperty<Color> color = new SimpleObjectProperty();
    private final SimpleObjectProperty<Color> borderColor = new SimpleObjectProperty<>();
    private final ShapeType shape;

    public ShapeType getShape(){
        return shape;
    }



    protected Shape(Color color,double x, double y, double size, ShapeType shape) {
        setY(y);
        setX(x);
        setColor(color);
        setBorderColor(Color.TRANSPARENT);
        setSize(size);
        this.shape = shape;
    }


    public Point centerPoint(){
        var centerX = getX() - getSize() / 2;
        var centerY = getY() - getSize() / 2;
        return new Point(centerX, centerY);
    }

    public double getY() {
        return y.get();
    }

    public SimpleDoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getX() {
        return x.get();
    }

    public SimpleDoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getSize() {
        return size.get();
    }

    public SimpleDoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public Color getColor() {
        return color.get();
    }

    public SimpleObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Color getBorderColor() {
        return borderColor.get();
    }

    public SimpleObjectProperty<Color> borderColorProperty() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor.set(borderColor);
    }

    public abstract String svgFormat();

    public void draw(GraphicsContext context){
    }

    public abstract Shape copyOf();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape1 = (Shape) o;
        return Objects.equals(y, shape1.y) && Objects.equals(x, shape1.x) && Objects.equals(size, shape1.size) && Objects.equals(color, shape1.color) && shape == shape1.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, size, color, shape);
    }


    public abstract boolean isInsideShape(double mouseX, double mouseY);


    // public abstract boolean pointInsideShape(Point point);
}



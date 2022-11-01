package model;

import Shapes.Shape;
import Shapes.ShapeType;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class shapeModel {
    private final BooleanProperty circleSelect;
    private final BooleanProperty rectangle;
    private final ObservableList<Shape> shapeObservableList;
    private final ObjectProperty<Color> colorPickerSelect;
    private final StringProperty sizeSelect;
    private ShapeType shapeType;

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public shapeModel() {
        this.colorPickerSelect = new SimpleObjectProperty<>(Color.BLUE);
        this.circleSelect = new SimpleBooleanProperty(false);
        this.rectangle = new SimpleBooleanProperty(false);
        this.shapeObservableList = FXCollections.observableArrayList(shape -> new Observable[]{
                shape.colorProperty(),
                shape.xProperty(),
                shape.yProperty(),
                shape.sizeProperty()
        });
        this.sizeSelect = new SimpleStringProperty("100");

    }

    public void addToShapes(Shape shape){
        if(!(shape == null))
            this.shapeObservableList.add(shape);

    }

    public void setCircleSelect(ShapeType type){
        shapeType = ShapeType.CIRCLESHAPE;
    }

    public void setRectangle(ShapeType type){
        shapeType = ShapeType.RECTANGLESHAPE;


    }
    public void setSelecitonShape(ShapeType type){
        switch (type) {
            case CIRCLESHAPE -> shapeType = ShapeType.CIRCLESHAPE;
            case RECTANGLESHAPE -> shapeType = ShapeType.RECTANGLESHAPE;

            }
        }


    public boolean isCircleSelect() {
        return circleSelect.get();
    }

    public BooleanProperty circleSelectProperty() {
        return circleSelect;
    }

    public boolean isRectangle() {
        return rectangle.get();
    }

    public BooleanProperty rectangleProperty() {
        return rectangle;
    }



    public Color getColorSelect() {
        return colorPickerSelect.get();
    }

    public ObjectProperty<Color> colorSelectProperty() {
        return colorPickerSelect;
    }


    public ObservableList<Shape> getShapeObservableList() {
        return shapeObservableList;
    }

    public void setCircleSelect(boolean circleSelect) {
        this.circleSelect.set(circleSelect);
    }

    public void setRectangle(boolean rectangle) {
        this.rectangle.set(rectangle);
    }

    public Color getColorPickerSelect() {
        return colorPickerSelect.get();
    }

    public ObjectProperty<Color> colorPickerSelectProperty() {
        return colorPickerSelect;
    }

    public void setColorPickerSelect(Color colorPickerSelect) {
        this.colorPickerSelect.set(colorPickerSelect);
    }

    public String getSizeSelect() {
        return sizeSelect.get();
    }

    public StringProperty sizeSelectProperty() {
        return sizeSelect;
    }

    public void setSizeSelect(String sizeSelect) {
        this.sizeSelect.set(sizeSelect);
    }
    public double getSizeText(){
        return Double.parseDouble(sizeSelect.getValue());
    }

    public void printRectangle(){

    }

    public void saveToFIle(Path file ) {
        StringBuffer outPut = new StringBuffer();
        for (Shape s: getShapeObservableList()){
            outPut.append(s.getX());
            outPut.append(s.getY());
            outPut.append(s.getSize());
            outPut.append(s.getColor());

        }
        try {
            Files.writeString(file, outPut.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}


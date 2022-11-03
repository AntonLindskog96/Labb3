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
import java.util.ArrayList;
import java.util.List;

public class shapeModel {
    private final BooleanProperty Circle;
    private final BooleanProperty Rectangle;
    private final ObservableList<Shape> shapeObservableList;
    private final ObjectProperty<Color> colorPickerSelect;
    private final StringProperty sizeSelect;
    private final List<List<Shape>> undoList = new ArrayList<>();
    private final BooleanProperty selectOption;
    private ShapeType shapeType;

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public shapeModel() {
        this.colorPickerSelect = new SimpleObjectProperty<>(Color.BLACK);
        this.Circle = new SimpleBooleanProperty(false);
        this.selectOption = new SimpleBooleanProperty(false);
        this.Rectangle = new SimpleBooleanProperty(false);
        this.shapeObservableList = FXCollections.observableArrayList(shape -> new Observable[]{
                shape.colorProperty(),
                shape.xProperty(),
                shape.yProperty(),
                shape.sizeProperty()
        });
        this.sizeSelect = new SimpleStringProperty("75");

    }

    public void undoItem (){
        if(undoList.size() > 0){
            undoList.remove(undoList.size() - 1);
        }
    }

    public void addToShapes(Shape shape){

        if(!(shape == null))
            this.shapeObservableList.add(shape);

    }

    public void setCircle(ShapeType type){
        shapeType = ShapeType.CIRCLESHAPE;
        setSelectOption(false);
    }

    public void setRectangle(ShapeType type){
        shapeType = ShapeType.RECTANGLESHAPE;
        setSelectOption(false);


    }



    public void setSelecitonShape(ShapeType type){
        switch (type) {
            case CIRCLESHAPE -> shapeType = ShapeType.CIRCLESHAPE;
            case RECTANGLESHAPE -> shapeType = ShapeType.RECTANGLESHAPE;

            }
        }


    public boolean isCircle() {
        return Circle.get();
    }

    public BooleanProperty circleSelectProperty() {
        return Circle;
    }

    public boolean getRectangle() {
        return Rectangle.get();
    }

    public BooleanProperty rectangleProperty() {
        return Rectangle;
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

    public void setCircle(boolean circle) {
        this.Circle.set(circle);
    }

    public void setRectangle(boolean rectangle) {
        this.Rectangle.set(rectangle);
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

    public boolean isSelectOption() {
        return selectOption.get();
    }

    public BooleanProperty selectOptionProperty() {
        return selectOption;
    }

    public void setSelectOption (){
        this.selectOption.set(true);
    }

    public void setSelectOption(boolean selectOption) {
        this.selectOption.set(selectOption);
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


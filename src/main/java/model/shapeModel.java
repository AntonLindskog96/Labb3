package model;

import Shapes.RectangleShape;
import Shapes.Shape;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class shapeModel {

    BooleanProperty circleModel = new SimpleBooleanProperty();
    BooleanProperty rectangleModel = new SimpleBooleanProperty();
    ObjectProperty<Shape> shapeList = new SimpleObjectProperty<>();
    ObjectProperty<Color> colorObjectProperty = new SimpleObjectProperty<>();
    StringProperty sizeing = new SimpleStringProperty();

    private final BooleanProperty circleSelect;
    private final BooleanProperty rectangle;
    private final ObservableList<Shape> shapeObservableList;
    private final ObjectProperty<Color> colorPickerSelect;
    private final StringProperty sizeSelect;


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

    public void setCircleSelect(){
        circleSelect.set(true);
        rectangle.set(false);
    }

    public void setRectangle(){
        circleSelect.set(false);
        rectangle.set(true);
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

    public boolean isCircleModel() {
        return circleModel.get();
    }

    public BooleanProperty circleModelProperty() {
        return circleModel;
    }

    public void setCircleModel(boolean circleModel) {
        this.circleModel.set(circleModel);
    }

    public boolean isRectangleModel() {
        return rectangleModel.get();
    }

    public BooleanProperty rectangleModelProperty() {
        return rectangleModel;
    }

    public void setRectangleModel(boolean rectangleModel) {
        this.rectangleModel.set(rectangleModel);
    }

    public Shape getShapeList() {
        return shapeList.get();
    }

    public ObjectProperty<Shape> shapeListProperty() {
        return shapeList;
    }

    public void setShapeList(Shape shapeList) {
        this.shapeList.set(shapeList);
    }

    public Color getColorObjectProperty() {
        return colorObjectProperty.get();
    }

    public ObjectProperty<Color> colorObjectPropertyProperty() {
        return colorObjectProperty;
    }

    public void setColorObjectProperty(Color colorObjectProperty) {
        this.colorObjectProperty.set(colorObjectProperty);
    }

    public String getSizeing() {
        return sizeing.get();
    }

    public StringProperty sizeingProperty() {
        return sizeing;
    }

    public void setSizeing(String sizeing) {
        this.sizeing.set(sizeing);
    }


}


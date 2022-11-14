package model;
import Shapes.Shape;
import Shapes.ShapeType;
import com.example.labb3.Point;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShapeModel {
    private final BooleanProperty Circle;
    private final BooleanProperty Rectangle;
    private Point point;

    private final ObservableList<Shape> shapeObservableList;
    private ObservableList<Shape> selectedShapes;
    public final List<List<Shape>> undoLists = new ArrayList<>();


    private final ObjectProperty<Color> colorPickerSelect;
    private final ObjectProperty<Color> borderColor;
    private final StringProperty sizeSelect;
    private final List<Shape> changeList;

    private final BooleanProperty selectOption;
    private ShapeType shapeType = ShapeType.CIRCLE;

    public ShapeType getShapeType() {
        return shapeType;

    }


    public void setPoint(double mousePointX, double mousePointY) {
        this.point = new Point(mousePointX, mousePointY);
    }

    public void changeSizeOnSelectedShape (){
        addChangesToUndoList();
        for(var shape : selectedShapes) {
            shape.setSize(getSizeText());
        }
    }



    public ShapeModel() {
        this.colorPickerSelect = new SimpleObjectProperty<>(Color.BLACK);
        this.borderColor = new SimpleObjectProperty<>();
        this.borderColor.set(Color.TRANSPARENT);
        this.Circle = new SimpleBooleanProperty(false);
        this.selectedShapes = FXCollections.observableArrayList();
        this.changeList = new ArrayList<>();
        this.selectOption = new SimpleBooleanProperty();
        this.Rectangle = new SimpleBooleanProperty(false);
        this.shapeObservableList = FXCollections.observableArrayList(shape -> new Observable[]{
                shape.colorProperty(),
                shape.xProperty(),
                shape.yProperty(),
                shape.sizeProperty()
        });
        this.sizeSelect = new SimpleStringProperty("75");

    }

  public void undoLatestChange(){
        if(undoLists.isEmpty())
            return;
        undoListChange();
    }



    public void undoListChange (){
        shapeObservableList.clear();
        for (var shape : undoLists.get(undoLists.size() - 1))
            shapeObservableList.add(shape.copyOf());
        if(undoLists.size() > 1){
            undoLists.remove(undoLists.size() - 1);
        }
    }

    public void addChangesToUndoList() {
        List<Shape> tempList = new ArrayList<>();
        undoLists.add(tempList);
        copyToTempList(tempList);

    }

    private void copyToTempList(List<Shape> tempList) {
        shapeObservableList.forEach(shape -> tempList.add(shape.copyOf()));


    }

    public void addToShapes(Shape shape){
        if(!(shape == null))
            this.shapeObservableList.add(shape);


    }

    public void setCircle(){
        shapeType = ShapeType.CIRCLE;
        setSelectOption(false);
        setRectangle(false);
    }

    public void setRectangle(){
        shapeType = ShapeType.RECTANGLE;
        setSelectOption(false);
        setCircle(false);
    }


    public void clearSelectedShapes(){
        for(var shape : shapeObservableList){
            shape.setBorderColor(Color.YELLOW);
        }
        selectedShapes.clear();
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



    public StringProperty sizeSelectProperty() {
        return sizeSelect;
    }
    public double getSizeText(){
        return Double.parseDouble(sizeSelect.getValue());
    }


    public BooleanProperty selectOptionProperty() {
        return selectOption;
    }
    public boolean isSelectOption() {
        return selectOption.get();
    }


    public void setSelectOption(boolean selectOption) {
        this.selectOption.set(selectOption);
    }

    public Color getColorPickerSelect() {
        return colorPickerSelect.get();
    }


    public void checkIfInsideShape(double x, double y) {
        for (var shape : shapeObservableList) {
            if (shape.isInsideShape(x,y))
                selectedShapesContains(shape);
        }
    }

    public void selectedShapesContains(Shape selectedShape) {
        if (selectedShapes.contains(selectedShape)) {
            selectedShape.setBorderColor(Color.TRANSPARENT);
            selectedShapes.remove(selectedShape);
        } else {
            selectedShape.setBorderColor(Color.RED);
            selectedShapes.add(selectedShape);
        }
    }

    public void changeColorOnShape(){
        addChangesToUndoList();

        for(var shape : selectedShapes){
            shape.setColor(getColorPickerSelect());
        }
    }





}


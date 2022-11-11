package com.example.labb3;

import Shapes.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.SaveFile;
import model.shapeModel;


public class PaintController {

    public Canvas canvas;
    public GraphicsContext context;
    public TextField brushSize;
    public ColorPicker colorPick;
    public MenuItem save;

    public BooleanProperty selectOption;
    public BooleanProperty circle;
    public BooleanProperty rectangle;

    public Button rectangleId;
    public Button circleId;
    public Button undoButton;
    public Button pointer;


    public shapeModel model;
    public Factory factory;

    public CheckBox selector;
    private Stage stage;


    public PaintController() {
        this.rectangle = new SimpleBooleanProperty();
        this.circle = new SimpleBooleanProperty();
        this.selectOption = new SimpleBooleanProperty();
        this.brushSize = new TextField();
        this.model = new shapeModel();
        this.factory = new Factory();

    }


    public void initialize() {

        context = canvas.getGraphicsContext2D();
        colorPick.valueProperty().bindBidirectional(model.colorSelectProperty());
        selectOption.bindBidirectional(model.selectOptionProperty());
        brushSize.textProperty().bindBidirectional(model.sizeSelectProperty());
        model.getShapeObservableList().addListener((ListChangeListener<Shape>) e -> drawShapes(context));
        model.addChangesToUndoList();
        selector.selectedProperty().bindBidirectional(model.selectOptionProperty());

    }


    public void onCanvasClicked (MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (model.isSelectOption()) {
            model.checkIfInsideShape(x, y);
        } else {
            createAndAddNewShape(x,y);
        }
    }
    public void clearCanvas (){
        context.clearRect(0,0,canvas.getWidth(), canvas.getHeight());

    }
    private void drawShapes(GraphicsContext context) {
       clearCanvas();
        for (var shape : model.getShapeObservableList()) {
            shape.draw(context);
        }
    }


    private Shape returnNewShape(ShapeType type, Color colorPick, double x, double y, double size) {
        return Factory.createShape(type, colorPick, x, y, size);
    }




    public void actionRectangle() {
     model.setRectangle();

    }

    public void onCircleClick() {
        model.setCircle();

    }
    public void onPointSelection (){
        model.setSelectOption(true);

        }


        private void createAndAddNewShape(double x, double y) {
        var newShape = returnNewShape(model.getShapeType(),colorPick.getValue(),x,y,model.getSizeText());
        model.addToShapes(newShape);
        model.addChangesToUndoList();
    }


    public void undoAction () {
        model.undoLatestChange();


    }

    public void onChangeSize() {
        model.changeSizeOnSelectedShape();
    }

    public void onSelectorAction() {
        model.clearSelectedShapes();
    }

    public void OnChangeColor() {
        model.changeColorOnShape();
    }

    public void onSave() {
        new SaveFile().save(model,stage);
    }
    public void actionExit(ActionEvent event) {
        Platform.exit();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
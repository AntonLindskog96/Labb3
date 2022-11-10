package com.example.labb3;

import Shapes.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Point;
import model.shapeModel;

import java.io.File;


public class HelloController {
    public BooleanProperty selectOption;
    public Canvas canvas;
    public BooleanProperty circle;
    public GraphicsContext context;
    public TextField brushSize;
    public ColorPicker colorPick;
    public BooleanProperty rectangle;
    public MenuItem save;
    public Button rectangleId;
    public Button circleId;
    public shapeModel model;
    public Factory factory;
    public Button pointer;
    public CheckBox selector;
    public Button undoButton;
    private Stage stage;


    public HelloController() {
        this.rectangle = new SimpleBooleanProperty();
        this.circle = new SimpleBooleanProperty();
        this.selectOption = new SimpleBooleanProperty();
        this.brushSize = new TextField();
        this.model = new shapeModel();
        this.factory = new Factory();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
            selectOrCreateShape(x, y);
        }
    }
   /*     model.setPoint(mouseEvent.getX(), mouseEvent.getY());
        if(model.isSelectOption()){
            model.checkIfInsideShape(mouseEvent.getX(), mouseEvent.getY());

        }
        selectOrCreateShape(mouseEvent.getX(), mouseEvent.getY());
    }*/

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

    public void actionExit(ActionEvent event) {
        Platform.exit();
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

   /* private void checkIfInsideShape(double x, double y) {
        for(var shape : model.getShapeObservableList())
            checkIfSelectedIsInside(x,y, shape);



    }*/

    private void selectOrCreateShape(double x, double y) {
        if(selectOption.get())
            model.checkIfInsideShape(x,y);
        else
            createAndAddNewShape(x,y);

        }

        private void createAndAddNewShape(double x, double y) {
        var newShape = returnNewShape(model.getShapeType(),colorPick.getValue(),x,y,model.getSizeText());
        model.addToShapes(newShape);
        model.addChangesToUndoList();
    }


    public void undoAction () {
        model.undoLatestChange();


    }


    public void onSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*csv"));

        File file = fileChooser.showSaveDialog(stage);


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
}
package com.example.labb3;

import Shapes.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    public shapeModel model;
    public Factory factory;
    public Button pointer;
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
        selectOption.bindBidirectional(model.circleSelectProperty());
        selectOption.bindBidirectional(model.rectangleProperty());
        brushSize.textProperty().bindBidirectional(model.sizeSelectProperty());
        model.getShapeObservableList().addListener((ListChangeListener<Shape>) e -> printAllShapes());



    }

    public void printAllShapes() {
        for (var Shape : model.getShapeObservableList())
            Shape.draw(context);
        System.out.println(model.getShapeObservableList());
    }

    public void onCanvasClicked (MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        System.out.println(x + "," + y);
        if (selectOption.getValue()) {
        }
        else {
        Shape newShape = returnNewShape(model.getShapeType(), colorPick.getValue(), mouseEvent.getX(),
                mouseEvent.getY(), model.getSizeText());
        model.addToShapes(newShape);
         }

        printAllShapes();


    }

    private Shape returnNewShape(ShapeType type, Color colorPick, double x, double y, double size) {
        return Factory.createShape(type, colorPick, x, y, size);
    }

    public void actionExit(ActionEvent event) {
        Platform.exit();
    }



    public void actionRectangle() {
        model.setSelecitonShape(ShapeType.RECTANGLESHAPE);
        model.setSelectOption(false);

    }

    public void onCircleClick() {
        model.setSelecitonShape(ShapeType.CIRCLESHAPE);
        model.setSelectOption(false);

    }
    public void onPointSelection (){
        model.setSelectOption(true);
        model.setCircle(false);
        model.setRectangle(false);


        }
    public void undoAction (){

    }



    public void onSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*csv"));

        File file = fileChooser.showSaveDialog(stage);


    }


    public RectangleShape canvasClicked(MouseEvent mouseEvent) {
        if (rectangle.get()) {
            return new RectangleShape(colorPick.getValue(), mouseEvent.getX(), mouseEvent.getY(),
                    model.getSizeText(), ShapeType.RECTANGLESHAPE);
        }

        return null;
    }
}
package com.example.labb3;

import Shapes.RectangleShape;
import Shapes.Shape;
import Shapes.Factory;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.shapeModel;

import java.io.File;

import static Shapes.Factory.circleOf;
import static Shapes.Factory.rectangleOf;


public class HelloController {
    public Canvas canvas;
    public BooleanProperty circle;
    public GraphicsContext context;
    public TextField brushSize;
    public CheckBox eraser;
    public ColorPicker cp;
    public BooleanProperty rectangle;
    public MenuItem save;
    public shapeModel model;
    public Factory factory;
    private Stage stage;


    public HelloController() {
        this.rectangle = new SimpleBooleanProperty();
        this.circle = new SimpleBooleanProperty();
        this.brushSize = new TextField();
        this.model = new shapeModel();
        this.factory = new Factory();

    }

    public void setStage (Stage stage){
        this.stage= stage;
    }





    public void initialize () {
        context = canvas.getGraphicsContext2D();
        rectangle.bindBidirectional(model.rectangleProperty());
        cp.valueProperty().bindBidirectional(model.colorSelectProperty());
        circle.bindBidirectional(model.circleSelectProperty());
        brushSize.textProperty().bindBidirectional(model.sizeSelectProperty());
        model.getShapeObservableList().addListener((ListChangeListener<Shape>) e -> drawShapeCanvas());



    }
    public void drawShape(MouseEvent mouseEvent) {


            }

    private Shape returnNewShape(ColorPicker colorPicker, double x, double y, TextField size) {
        if (circle.get())
            return circleOf(cp.getValue(), x,y, model.getSizeText());
        if (rectangle.get())
            return rectangleOf(cp.getValue(), x, y, model.getSizeText());
        return null;
    }




    public void actionExit (ActionEvent event){
            Platform.exit();
    }

    public void eraser(ActionEvent event) {
        canvas.setOnMousePressed(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size;
            double y = e.getY() - size / 2;
            if (eraser.isSelected()) {
                context.clearRect(x, y, size, size);
            } else {
                System.out.println(" ");
            }
        });
    }


        public void actionRectangle (){
        model.setRectangle();



            /*canvas.setOnMousePressed(e -> {
                double size = Double.parseDouble(brushSize.getText());
                double x = e.getX() - size;
                double y = e.getY() - size / 2;
                if(eraser.isSelected()){
                    context.clearRect(x,y,size,size);
                } else {
                    context.setFill(cp.getValue());
                    context.fillRect(x,y,2*size,size);
                }

            });*/
        }
       public void onCircleClick (){
        model.setCircleSelect();

            /*canvas.setOnMousePressed(e -> {
                double size = Double.parseDouble(brushSize.getText());
                double x = e.getX() - size / 2;
                double y = e.getY() - size / 2;
                if(eraser.isSelected()){
                    context.clearRect(x,y,size,size);
                } else {
                    context.setFill(cp.getValue());
                    context.fillOval(x,y,size,size);
                }

            });*/

      }
        public void handleColorPicker (ActionEvent event){


        }


    public void onSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV" , "*csv"));

        File file = fileChooser.showSaveDialog(stage);


    }

    public void actionPaint(ActionEvent event) {
        context = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if(eraser.isSelected()){
                context.clearRect(x,y,size,size);
            } else {
                context.setFill(cp.getValue());
                context.fillRect(x,y,size,size);
            }
        });
    }

    public void actionEraser(ActionEvent event) {
        context = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if(eraser.isSelected()){
                context.clearRect(x,y,size,size);
        }
    });
    }

    public RectangleShape canvasClicked(MouseEvent mouseEvent) {
        if(rectangle.get()){
            return new RectangleShape(cp.getValue(),mouseEvent.getX() ,mouseEvent.getY(), model.getSizeText());
        }

        return null;
    }

    private void drawShapeCanvas(){
        for(var shape : model.getShapeObservableList())
            shape.draw(context);
    }
}
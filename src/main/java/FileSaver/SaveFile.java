package FileSaver;

import Shapes.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ShapeModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {
    FileChooser fileChooser = new FileChooser();

    public void save(ShapeModel model, Stage stage){
        saveSetUp();
        Path path = fileChooser.showSaveDialog(stage.getOwner()).toPath();
        setSVGtoPath(model,path);

    }
    private void saveSetUp (){
        fileChooser.setTitle("Save SVG File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));

    }
    private void setSVGtoPath(ShapeModel model, Path path){
        try {
            Files.write(path, SVGtoString(model));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private List<String> SVGtoString(ShapeModel model) {
        List<String > svg = new ArrayList<>();
        getSvgString(model,svg);
      return svg;
    }

    private void getSvgString(ShapeModel model, List<String> svg) {
        svg.add("<svg width=\"1540.0\" height=\"740.0\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
        addShapes(svg, model);
        svg.add("</svg>");
    }
    private void addShapes(List<String> svg, ShapeModel model){
        model.getShapeObservableList().forEach(shape -> addSVGShapes(svg, shape));
    }
    private void addSVGShapes(List<String> svg, Shape shape){
        svg.add(shape.svgFormat());
    }
}

package esi.sisad.seman.fxgraph.cells;

import esi.sisad.seman.fxgraph.graph.Graph;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;

public class TriangleCell extends AbstractCell {
    private final Label label;

    public TriangleCell(String capital) {
        label = new Label(capital);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #bc0000");
    }

    @Override
    public Region getGraphic(Graph graph) {
        final double width = 50;
        final double height = 50;

        final Polygon view = new Polygon(width / 2, 0, width, height, 0, height);

        view.setStroke(Color.TRANSPARENT);
        view.setFill(Color.TRANSPARENT);

        final Pane pane = new Pane(view, label);
        pane.setPrefSize(30, 30);
        final Scale scale = new Scale(1, 1);
        view.getTransforms().add(scale);
        scale.xProperty().bind(pane.widthProperty().divide(50));
        scale.yProperty().bind(pane.heightProperty().divide(50));
        CellGestures.makeResizable(graph, pane);

        return pane;
    }

}
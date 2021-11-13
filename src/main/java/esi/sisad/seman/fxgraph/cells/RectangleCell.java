package esi.sisad.seman.fxgraph.cells;

import esi.sisad.seman.fxgraph.graph.Graph;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends AbstractCell {
    private final Label label;

    public RectangleCell(String country) {
        label = new Label(country);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-fill: black");
    }

    @Override
    public Region getGraphic(Graph graph) {
        final Rectangle view = new Rectangle(40, 40);

        view.setStroke(Color.ROYALBLUE);
        view.setFill(Color.ROYALBLUE);

        final Pane pane = new Pane(view, label);
        pane.setPrefSize(40, 40);
        view.widthProperty().bind(pane.prefWidthProperty());
        view.heightProperty().bind(pane.prefHeightProperty());
        CellGestures.makeResizable(graph, pane);

        return pane;
    }

}

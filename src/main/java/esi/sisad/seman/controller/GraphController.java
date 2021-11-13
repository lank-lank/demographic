package esi.sisad.seman.controller;

import esi.sisad.seman.fxgraph.cells.RectangleCell;
import esi.sisad.seman.fxgraph.cells.TriangleCell;
import esi.sisad.seman.fxgraph.edges.Edge;
import esi.sisad.seman.fxgraph.graph.Graph;
import esi.sisad.seman.fxgraph.graph.ICell;
import esi.sisad.seman.fxgraph.graph.Model;
import esi.sisad.seman.fxgraph.layout.RandomLayout;
import esi.sisad.seman.model.Country;
import esi.sisad.seman.service.CountryService;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GraphController {
    @FXML
    private VBox graph_root_container;

    private List<Country> countries = new ArrayList<>();

    @FXML
    private void initialize() {
        CountryService countryService = new CountryService();
        countries = countryService.findAll();

        Graph graph = new Graph();
        populateGraph(graph);
        graph.layout(new RandomLayout());

        graph.getViewportGestures().setPanButton(MouseButton.SECONDARY);
        graph.getNodeGestures().setDragButton(MouseButton.PRIMARY);

        graph_root_container.getChildren().setAll(graph.getCanvas());
    }

    private void populateGraph(Graph graph) {
        final Model model = graph.getModel();
        graph.beginUpdate();

        for (Country country : countries) {
            ICell countryCell = new RectangleCell(country.getName());
            ICell capitalCell = new TriangleCell(country.getCapital());
            model.addCell(countryCell);
            model.addCell(capitalCell);
            Edge edge = new Edge(countryCell, capitalCell, true);
            edge.textProperty().set("Capital Is");
            model.addEdge(edge);
        }
        graph.endUpdate();
    }
}

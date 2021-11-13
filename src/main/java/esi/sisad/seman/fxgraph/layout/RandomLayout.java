package esi.sisad.seman.fxgraph.layout;


import esi.sisad.seman.fxgraph.graph.Graph;
import esi.sisad.seman.fxgraph.graph.ICell;

import java.util.List;
import java.util.Random;

public class RandomLayout implements Layout {

	private final Random rnd = new Random();

	@Override
	public void execute(Graph graph) {
		final List<ICell> cells = graph.getModel().getAllCells();

		for (final ICell cell : cells) {
			final double x = rnd.nextDouble() * 600 + 50;
			final double y = rnd.nextDouble() * 600 + 10;

			graph.getGraphic(cell).relocate(x, y);
		}
	}

}
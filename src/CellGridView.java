import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane {

	private CellView[][] cellViews;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		
		cellViews = new CellView[numRows][numCols];
		for (int i = 0; i < cellViews.length; i++) {
			for (int j = 0; j < cellViews[i].length; j++) {
				CellView cell = new CellView(i, j, cellLength);
				cellViews[i][j] = cell;
				add(cell, j, i);
			}
		}
		
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		
		this.cellViews[rowIdx][colIdx].setState(state);
		
	}
	
	public void register(Presenter presenter) {
		
		for (int i = 0; i < cellViews.length; i++) {
			for (int j = 0; j < cellViews[i].length; j++) {
				cellViews[i][j].register(presenter);
			}
		}
	}
} 

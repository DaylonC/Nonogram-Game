import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class NonogramView extends BorderPane implements View{

	private RowCluesView left;
	private ColCluesView top;
	private CellGridView center;
	
	
	public NonogramView(int[][] rowClues, int[][] colClues, int cellLength) {
		
		center = new CellGridView(rowClues.length, colClues.length, cellLength);
		setCenter(center);
		
		left = new RowCluesView(rowClues, cellLength, getRowWidth(rowClues));
		setLeft(left);
		
		top = new ColCluesView(colClues, cellLength, getColWidth(colClues));
		setTop(top);
		top.setAlignment(Pos.TOP_RIGHT);
		
		getStyleClass().add("nonogram-view.css");
		
	}

	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		
		center.setCellState(rowIdx, colIdx, state);
		
	}

	
	public void setRowClueState(int rowIdx, boolean solved) {
	
		left.setRowState(rowIdx, solved);
		
		
	}

	
	public void setColClueState(int colIdx, boolean solved) {
		
		top.setColState(colIdx, solved);
		
	}

	
	public void setPuzzleState(boolean solved) {
		
		if (solved == true) {
			getStyleClass().add("nonogram-view-solved");
		}
		else {
			getStyleClass().remove("nonogram-view-solved");
		}
		
	}

	
	public void register(Presenter presenter) {
		
		center.register(presenter);
		
		
	}
	
	public int getRowWidth(int[][] rowClues) {
		
		
		int max = 0;
		for (int i = 0; i < rowClues.length; i++) {
			if (rowClues[i].length > max) {
				max = rowClues[i].length;
			}
		}
		
		return max;
	}
	
	public int getColWidth(int[][] colClues) {
		
		
		int max = 0;
		for (int i = 0; i < colClues.length; i++) {
			if (colClues[i].length > max) {
				max = colClues[i].length;
			}
		}
		
		return max;
	}
	
	
	
}

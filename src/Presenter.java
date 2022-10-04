
public class Presenter {

	private Model model;
	private View view;
	
	//Maria Doan helped me get an idea for this 
	public Presenter(Model model, View view) {
		
		for (int i = 0; i < model.getNumRows(); ++i) {
			for (int j = 0; j < model.getNumCols(); ++j) {
				
				view.setCellState(i, j, model.getCellState(i, j));
				view.setRowClueState(i, model.isRowSolved(i));
				view.setColClueState(j, model.isColSolved(j));
				view.setPuzzleState(model.isSolved());
				
			}
		}
		
		if (model.isSolved()) {
			view.setPuzzleState(true);
			for (int i = 0; i < model.getNumRows(); ++i) {
				for (int j = 0; j < model.getNumCols(); ++j) {
			if (model.getCellState(i, j) == CellState.MARKED) {
				view.setCellState(i, j, CellState.EMPTY);
				}	
			}
		}
			
		
	}	
		
		view.register(this);
		
		this.model = model;
		this.view = view;
		
		
//		if (model.isSolved()) {
//			for (int i = 0; i < model.getNumRows(); ++i) {
//				for (int j = 0; j < model.getNumCols(); ++j) {
//			if (model.getCellState(i, j) == CellState.MARKED) {
//				view.setCellState(i, j, CellState.EMPTY);
//			}
//					
//				}
//		}
//		
//	}
//		
//		this.view = view;
	}
	
	public void cellClicked(int rowIdx, int colIdx, boolean primaryButton) {
		
		
		if (model.getCellState(rowIdx, colIdx) == CellState.EMPTY) {
			
			if (primaryButton == true) {
				model.setCellState(rowIdx, colIdx, CellState.FILLED);
				
			}
			else {
				model.setCellState(rowIdx, colIdx, CellState.MARKED);
			}
			
		}
		
		else if (model.getCellState(rowIdx, colIdx) == CellState.MARKED) {
			
			if (primaryButton == true) {
				model.setCellState(rowIdx, colIdx, CellState.FILLED);
			}
			
			else {
				model.setCellState(rowIdx, colIdx, CellState.EMPTY);
			}
			
			
		}
		
		else if (model.getCellState(rowIdx, colIdx) == CellState.FILLED){
			
			if (primaryButton == true) {
				model.setCellState(rowIdx, colIdx, CellState.EMPTY);
			}
			
			else {
				model.setCellState(rowIdx, colIdx, CellState.MARKED);
			}
			
		}
		
		else {
			
		}
		
		for (int i = 0; i < model.getNumRows(); ++i) {
			for (int j = 0; j < model.getNumCols(); ++j) {
				
				view.setCellState(i, j, model.getCellState(i, j));
				view.setRowClueState(i, model.isRowSolved(i));
				view.setColClueState(j, model.isColSolved(j));
				view.setPuzzleState(model.isSolved());
				
			}
		}

	
		if (model.isSolved()) {
			view.setPuzzleState(true);
			for (int i = 0; i < model.getNumRows(); ++i) {
				for (int j = 0; j < model.getNumCols(); ++j) {
			if (model.getCellState(i, j) == CellState.MARKED) {
				view.setCellState(i, j, CellState.EMPTY);
			}
					
				}
		}
			
	}
		
		
	
	}
	
	
	
}

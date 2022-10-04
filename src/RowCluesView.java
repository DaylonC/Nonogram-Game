import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.VBox;

public class RowCluesView extends VBox{

	private List<RowClueView> rowClueViews = new ArrayList<RowClueView>();
	
	//Baraa Ahmad helped me with this 
	public RowCluesView(int[][] rowClues, int cellLength, int width) {

		for( int i=0; i < rowClues.length; ++i) {             
            rowClueViews.add(new RowClueView(rowClues[i], cellLength, width));
        }

        getChildren().addAll(rowClueViews);
		
	}
	
	public void setRowState(int rowIdx, boolean solved) {
		
		rowClueViews.get(rowIdx).setState(solved);
		
	}
	
}

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;

public class ColCluesView extends HBox{

	private List<ColClueView> colClueViews;
	
	public ColCluesView(int[][] colClues, int cellLength, int width) {
	
	
	colClueViews = new ArrayList<ColClueView>();
	
	for( int i=0; i < colClues.length; ++i) {             
        colClueViews.add(new ColClueView(colClues[i], cellLength, width));
    }

    getChildren().addAll(colClueViews);
	
	}
	
	public void setColState(int colIdx, boolean solved) {
		
		colClueViews.get(colIdx).setState(solved);
		
	}

}

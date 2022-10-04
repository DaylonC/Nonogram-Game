import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PuzzleLoader extends GridPane{

	private static final int MIN_CELL_LENGTH = 20;
	private static final int MAX_CELL_LENGTH = 50;
	private static final int DEFAULT_CELL_LENGTH = 30;
	private static final int SPACING = 10;
	private static final int PADDING = 10;
	
	public PuzzleLoader(Main main) {
		
		
		
		Label file = new Label("Nonogram file: ");
		Label cells = new Label("Cell side length (pixels): ");
		
		Spinner <Integer> cellSpin = new Spinner(MIN_CELL_LENGTH, MAX_CELL_LENGTH, DEFAULT_CELL_LENGTH);
		
		Button button = new Button("Load Puzzle");
		
		TextField textField = new TextField();
		textField.setPromptText("Enter file name");
		
		setHgap(SPACING);
		setVgap(SPACING);
		setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		
		add(file, 0, 0);
		add(cells, 0, 1);
		add(cellSpin, 1, 1);
		add(button, 1, 2);
		add(textField, 1, 0);
		
		setAlignment(Pos.CENTER);
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				
				String filename = textField.getText();
				
				
				try {
					NonogramModel model = new NonogramModel(filename);
					int cellSpinVal = cellSpin.getValue();
					main.startNonogramPlayer(model, cellSpinVal);
					
				}
				catch (IOException e1) {
					
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("File could not be read.");
					a.show();
				}
			
			}
		
		});
		
		
	}
	
	
	
	
	
}

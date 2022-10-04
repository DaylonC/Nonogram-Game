import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final String TITLE =  "Nonogram Player - Daylon Carse";
	private static final boolean RESIZABLE = false;
	private static final String STYLE_SHEET = "style.css";
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		PuzzleLoader pl = new PuzzleLoader(this);
		
		Scene scene = new Scene(pl);
		
		this.primaryStage = primaryStage;
		
		this.primaryStage.setScene(scene);
		
		this.primaryStage.setTitle(TITLE);
		
		this.primaryStage.setResizable(RESIZABLE);
		
		this.primaryStage.show();
		
	}
	
	public void startNonogramPlayer(Model model, int cellLength) {
		
		int[][] rowClues = new int[model.getNumRows()][model.getRowClue(0).length];
		
		for (int i = 0; i < model.getNumRows(); i++) {
			rowClues[i] = model.getRowClue(i);
		}
		
		int[][] colClues = new int[model.getNumCols()][model.getColClue(0).length];
		
		for (int i = 0; i < model.getNumCols(); i++) {
			colClues[i] = model.getColClue(i);
		}
		
		NonogramView view = new NonogramView(rowClues, colClues, cellLength);
		
		Presenter presenter = new Presenter(model, view);
		
		view.register(presenter);
		
		Scene scene = new Scene(view);
		
		scene.getStylesheets().add(STYLE_SHEET);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		
		
		
	}
	
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NonogramModel implements Model {

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellState;
	
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		
		this.rowClues = new int[rowClues.length][rowClues[0].length];
		
		
		for(int i = 0; i < rowClues.length; ++i) {
            this.rowClues[i] = rowClues[i];
        }
			
		this.colClues = new int[colClues.length][colClues[0].length];
		
		for(int i =0; i < colClues.length; ++i) {
            this.colClues[i] = colClues[i];
        }
	
		cellState = new CellState[rowClues.length][colClues.length];
		
		for (int i = 0; i < rowClues.length; i++) {
			for (int j = 0; j < colClues.length; j++) {
		
				cellState[i][j] = CellState.EMPTY;
				
			}
		
		}
	}
	
	//https://www.educative.io/edpresso/reading-the-nth-line-from-a-file-in-java
	//https://stackoverflow.com/questions/52315614/turn-string-values-into-2d-array-of-type-double/52315993
	public NonogramModel(String filename) throws IOException {
		
		File file = new File(filename);
		
		Scanner fileRead = new Scanner(file);
		
		StringBuilder sb = new StringBuilder();
		int rows = fileRead.nextInt();
		int cols = fileRead.nextInt();
		
		String rowLine = Files.readAllLines(Paths.get(filename)).get(1);
		rowLine = rowLine.replaceAll(" ", "");
		fileRead.nextLine();
		
	this.rowClues = new int[rows][rowLine.length()];
		
		for (int i =0; i < rows; i++) {
			String line = fileRead.nextLine();
			sb.append(line);
			sb.append(',');
			
		}
		
		String s = sb.toString();
		String[] split = s.split(",");
		
		for (int i = 0; i < split.length; i++) {
			String[] numbers = split[i].split(" ");
			@SuppressWarnings("deprecation")
			int[] ints = Arrays.stream(numbers).mapToInt(Integer::new).toArray();
			rowClues[i] = ints;
		}
		
		StringBuilder sb2 = new StringBuilder();
		String colLine = Files.readAllLines(Paths.get(filename)).get(cols);
		colLine = colLine.replaceAll(" ", "");
		
		colClues = new int[cols][colLine.length()];
		
		for (int i =0; i < cols; i++) {
			String line = fileRead.nextLine();
			sb2.append(line);
			sb2.append(',');
			
		}
		
		String s2 = sb2.toString();
		String[] split2 = s2.split(",");
		
		for (int i = 0; i < split2.length; i++) {
			String[] numbers = split2[i].split(" ");
			@SuppressWarnings("deprecation")
			int[] ints = Arrays.stream(numbers).mapToInt(Integer::new).toArray();
			colClues[i] = ints;
		}
		
		cellState = new CellState[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
		
				cellState[i][j] = CellState.EMPTY;
				
			}
		
		}
		fileRead.close();
	}
	
	public CellState getCellState(int rowIdx, int colIdx) {
		return cellState[rowIdx][colIdx];
	}
	
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		
		if (state == null || isSolved() == true) {
			return false;
		}
		
		if (getCellState(rowIdx, colIdx) == state) {
			return false;
		}
		
		cellState[rowIdx][colIdx] = state;
		
		
		return true;
	}
	
	public int getNumRows() {
		return rowClues.length;
	}
	
	public int getNumCols() {
		return colClues.length;
	}
	
	public int[] getRowClue(int rowIdx) {
		
		int[] copy = Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
		
		return copy;
	}
	
	public int[] getColClue(int colIdx) {
		
		int[] copy = Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
		
		return copy;
	}
	
	public boolean isRowSolved(int rowIdx) {
		
		ArrayList<Integer> proj = new ArrayList<Integer>();
		proj = projectRow(rowIdx);
		
		int[] clue = getRowClue(rowIdx);
		
		if (proj.size() < 1) {
			return false;
		}
		
		if (proj.size() < clue.length) {
			return false;
		}
		
		for (int i = 0; i < clue.length; i++) {
			if (proj.get(i) != clue[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isColSolved(int colIdx) {
		
		ArrayList<Integer> proj = new ArrayList<Integer>();
		proj = projectCol(colIdx);
		
		int[] clue = getColClue(colIdx);
		
		
		if (proj.size() < 1) {
			return false;
		}
		
		if (proj.size() < clue.length) {
			return false;
		}
		
		for (int i = 0; i < clue.length; i++) {
			
			if (proj.get(i) != clue[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isSolved() {
		
		
		boolean flag = true;
		for (int i = 0; i < rowClues.length; i++) {
			
			flag = isRowSolved(i);
			
			if (flag == false) {
				return false;
			}
			
		}
		
		
		for (int i = 0; i < colClues.length; i++) {
			
			flag = isColSolved(i);
			
			if (flag == false) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public ArrayList<Integer> projectRow(int rowIdx) {
		
		
		ArrayList<Integer> counts = new ArrayList<Integer>();
		
		int count = 0;
		for (int i = 0; i < colClues.length; i++) {
			
			if (getCellState(rowIdx, i).equals(CellState.FILLED)) {
				
				count++;
				
			}
			
			else {
				
				if (count != 0) {
					counts.add(count);
					count = 0;
				}
				
			}
		}
			
			if (count != 0) {
				counts.add(count);
			}
			
			if (counts.size() < 1) {
				counts.add(0);
			}
			
		
		
	return counts;
	
	}
	
	public ArrayList<Integer> projectCol(int colIdx) {
		
		ArrayList<Integer> counts = new ArrayList<Integer>();
		
		int count = 0;
		for (int i = 0; i < rowClues.length; i++) {
			
			if (getCellState(i, colIdx).equals(CellState.FILLED)) {
				
				count++;
				
			}
			
			else {
				
				if (count != 0) {
					counts.add(count);
					count = 0;
				}
				
			}
		}
			
			if (count != 0) {
				counts.add(count);
			}
			
			if (counts.size() < 1) {
				counts.add(0);
			}
			
		
		
	return counts;
	
	}
	
	
}

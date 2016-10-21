import java.util.*;
import java.io.*;

/**
 * <p>
 * The Puzzle object will store a 2D array of Cells and maintain the status of the puzzle by offering various operations
 * such as checking if a certain Block, Row, or Column has been completed correctly, and of course if the entire Puzzle
 * has been completed correctly.
 * </p>
 * @author Jeffrey Cohen
 *
 */
public class Puzzle {

	/**
	 * A 2D array of Cell objects that will be the representation of the state of the puzzle
	 */
	private Cell[][] puzzle;
	
	/**
	 * Set this to true to enable debug statements
	 */
	private final boolean DEBUG = false;
	
	/**
	 * The default constructor will set puzzle to null
	 */
	public Puzzle(){
		puzzle = null;
	}
	
	/**
	 * Generate a Puzzle from a file
	 * @param f - a File object representing the desired .txt file to read from
	 * @throws NullPointerException if f is null
	 * @throws IllegalArgumentException if the file referenced by f cannot be found
	 * @throws IllegalStateException if there was insufficient data in the file, or the file was improperly formatted
	 */
	public Puzzle(File f){
		if(f == null){throw new NullPointerException("File cannot be null");}
		puzzle = new Cell[9][9];
		try{
			generatePuzzleFromFile(f);
		}catch(FileNotFoundException e){
			throw new IllegalArgumentException(e.getMessage());
		}catch(IllegalStateException e){
			throw e;
		}
	}
	
	/**
	 * Gets the Cell at the specified location of the puzzle
	 * @param x - the row of the desired Cell, must be in the range [0,9)
	 * @param y - the column of the desired Cell, must be in the range [0,9)
	 * @return the Cell object at the location (x,y)
	 * @throws IllegalArgumentException if x or y are not in the specified ranges
	 */
	public Cell getCell(int x, int y){
		if(x >= 9 || x < 0 || y >= 9 || y < 0){throw new IllegalArgumentException("Index out of bounds");}
		return puzzle[x][y];
	}
	
	/**
	 * Gets column c of the puzzle as a Column object
	 * @param c - the number of the column being fetched, zero indexed, must be in the range [0,9)
	 * @return Column object representing column c of the puzzle
	 * @throws IllegalArgumentException if c is not in the specified range
	 */
	public Column getColumn(int c){
		if(c >= 9 || c < 0){throw new IllegalArgumentException("Index out of bounds");}
		Cell[] col = new Cell[9];
		for(int i = 0; i < 9; i++){
			col[i] = puzzle[i][c];
		}
		return new Column(col);
	}

	/**
	 * Gets row r of the puzzle as a Row object
	 * @param r - the number of the row being fetched, zero indexed, must be in the range [0,9)
	 * @return Row object containing the Cells in row r
	 * @throws IllegalArgumentException if r is not in the specified range
	 */
	public Row getRow(int r){
		if(r >= 9 || r < 0){throw new IllegalArgumentException("Index out of bounds");}
		Cell[] row = new Cell[9];
		for(int i = 0; i < 9; i++){
			row[i] = puzzle[r][i];
		}
		return new Row(row);
	}
	
	/**
	 * Gets cells at block b from the puzzle, the top-left block of the puzzle is block 0, and the bottom-left is block 8
	 * @param b - the index of the block being fetched, must be in the range [0,9)
	 * @return Block object containing the Cells in block b
	 * @throws IllegalArgumentException if b is not in the specified range
	 */
	public Block getBlock(int b){
		if(b >= 9 || b < 0){throw new IllegalArgumentException("Index out of bounds");}
		Cell[] blk = new Cell[9];
		int rn = (b/3)*3;
		int cn = (b%3)*3;
		for(int i = 0; i < 9; i++){
				blk[i] = puzzle[rn+(i/3)][cn+(i%3)];
		}
		return new Block(blk);
	}
	
	/**
	 * Returns whether or not the puzzle is both complete and correct
	 * @return true if puzzle is complete and all rows, columns, and blocks are correct, false otherwise
	 */
	public boolean checkWin(){
		List<Group> grps = new ArrayList<Group>();
		for(int i = 0; i < 9; i++){
			grps.add(this.getColumn(i));
			grps.add(this.getRow(i));
			grps.add(this.getBlock(i));
		}
		for(Group g : grps){
			if(!g.isComplete()){
				return false;
			}
			if(!g.isCorrect()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Generates a puzzle from a given .txt file
	 * @param f - a File object 
	 * @throws FileNotFoundException if the file referenced by f cannot be found
	 * @throws IllegalStateException if there was insufficient data in the file, or the file was improperly formatted
	 */
	private void generatePuzzleFromFile(File f) throws FileNotFoundException{
		debug("### STARTING PUZZLE GENERATION ###");
		Scanner scan = new Scanner(f);
		debug("Opened scanner successfuly");
		int count = 0;
		int temp;
		String line, curr;
		String regex = "\\D";
		debug("Initialized vars");
		try{
			while(scan.hasNextLine()){
				debug("While loop iteration No." + count);
				line = scan.nextLine().trim();
				debug("Line before regex: " + line);
				// we need to make sure there's no whitespace in between each number
				// this works because each number is in the range [0,9]
				line = line.replaceAll(regex, "");
				debug("Line after regex: " + line);
				for(int i = 0; i < line.length(); i++){
					curr = String.valueOf(line.charAt(i));
					debug("Current index: " + i + "\nRetrieved char: " + curr);
					temp = Integer.parseInt(curr);
					debug("Converted char to int: " + temp);
					puzzle[count][i] = new Cell(temp);
					debug("Added cell to puzzle: " + puzzle[count][i]);
				}
				count++;
			}
			debug("Exited while loop");
		}catch(Exception e){
			debug("Hit catch block");
			scan.close();
			debug(this);
			throw new IllegalStateException("Error encountered during processing because file was improperly formatted");
		}
		debug("Made it out of trycatch block");
		scan.close();
		debug("Closed scanner");
		if(count != 9){debug(count); throw new IllegalStateException("Insufficient data in file to build complete puzzle");}
		debug("All done!\n### END PUZZLE GENERATION ###");
	}
	
	/**
	 * The abstraction function for Puzzle will display a formatted, string representation of the current state of the
	 * board.
	 * @return String representation of Puzzle
	 */
	public String toString(){
		String board = "";
		String hr = " #-----------#-----------#-----------#\n";
		String bighr = " #####################################\n";
		for(int i = 0; i < 9; i++){
			if(i%3 == 0)
				board += bighr;
			else
				board += hr;
			for(int j = 0; j < 9; j++){
				if(j%3 == 0)
					board += " # ";
				else
					board += " | ";
				board += puzzle[i][j];
			}
			board += " #\n";
		}
		board += bighr;
		return board;
	}
	
	private void debug(Object dbg){
		if(DEBUG)
			System.out.println(dbg);
	}
}

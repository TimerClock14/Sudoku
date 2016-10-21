import java.util.*;
import java.io.*;


/**
 * At any given time a given Cell will contain either a permanent value
 * or some number of pencil marks.
 * 
 * @author Jeffrey Cohen
 */
public class Cell {
	
	/**
	 * <p>
	 * pencil will be an array of 9 booleans, corresponding to the possible
	 * values of 1-9 that can be penciled in to a given Cell. For a given
	 * value n, a Cell contains the pencil mark of the corresponding value n
	 * if pencil[n-1] == true.
	 * </p>
	 * <p>
	 * The contents of pencil will not be overwritten when value > 0, but will
	 * not be displayed in the GUI, as displaying value takes precedence.
	 * </p>
	 */
	private boolean[] pencil;
	
	/**
	 * value is an int in the range [0,9]. If value == 0 then the Cell
	 * currently has pencil marks and no permanent value assigned to it.
	 */
	private int value;
	
	/**
	 * This boolean specifies if the value of this Cell can be changed
	 */
	public final boolean isPerm = false;
	
	/**
	 * The default constructor initializes value to 0 and all elements in pencil to false.
	 * There are not to be any null values in value or pencil.
	 */
	public Cell(){
		this.value = 0;
		this.pencil = new boolean[9];
		for(int i = 0; i < 9; i++){
			this.pencil[i] = false;
		}
	}
	
	/**
	 * Create a Cell with a set value v
	 * @param v - an integer in the range [0,9]
	 * @throws IllegalArgumentException if v is outside the range [0,9]
	 */
	public Cell(int v){
		if(v > 9 || v < 0){throw new IllegalArgumentException("v must be in the range [0,9]");}
		this.value = v;
		this.pencil = new boolean[9];
		for(int i = 0; i < 9; i++){
			this.pencil[i] = false;
		}
	}
	
	/**
	 * Create a Cell with pencil marks according to an array of integers
	 * @param pencilMarks - an array of integers. Must be of size 9 or less, and contain numbers in the range [1,9] with no duplicates
	 * @throws IllegalArgumentException if pencilMarks contains duplicates, is of a size greater than 9, or contains numbers outside the range [1,9]
	 * @throws NullPointerException if pencilMarks is null
	 */
	public Cell(int[] pencilMarks){
		if(pencilMarks == null){throw new NullPointerException("pencilMarks cannot be null");}
		if(pencilMarks.length > 9){throw new IllegalArgumentException("pencilMarks cannot have a length > 9");}
		
		// Initialize pencil to default state
		this.pencil = new boolean[9];
		for(int i = 0; i < 9; i++){
			this.pencil[i] = false;
		}
		
		// Fill pencil according to pencilMarks
		for(int i : pencilMarks){
			if(i > 9 || i < 1){throw new IllegalArgumentException("pencilMarks cannot contain numbers outside the range [1,9]");}
			if(this.pencil[i-1]){throw new IllegalArgumentException("pencilMarks cannot contain duplicate elements");}
			this.pencil[i-1] = true;
		}
		
		// Value will be initialized to zero
		this.value = 0;
	}
	
	/**
	 * Returns the value of this
	 * @return an int in the range [0,9]
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Sets the value of this to a given int v.
	 * @param v - an int in the range [0,9]
	 * @throws IllegalArgumentException if v is outside the range [0,9]
	 */
	public void setValue(int v){
		if(v > 9 || v < 0){throw new IllegalArgumentException("v must be in the range [0,9]");}
		this.value = v;
	}
	/**
	 * Returns an int array of variable size containing the pencil markings. If the value of this is nonzero null is returned.
	 * the maximum size of the returned array is 9 and the minimum is 0.
	 * @return null if the value of this is nonzero, otherwise return an int array of variable size with max size of 9 and min of 0
	 */
	public int[] getPencilMarks(){
		if(this.value != 0){return null;}
		
		//count will be used to determine what size array we need to return
		int count = 0;
		for(boolean b : this.pencil){
			if(b){count++;}
		}
		
		//Create an array of size count and fill it
		int[] marks = new int[count];
		count = 0;
		for(int i = 0; i < 9; i++){
			if(this.pencil[i]){
				marks[count] = i+1;
				count++;
			}
		}
		return marks;
	}
	
	/**
	 * Given an array of ints pencilMarks, set the pencil marks of this.
	 * @param pencilMarks - an array of integers. Must be of size 9 or less, and contain numbers in the range [1,9] with no duplicates
	 * @throws IllegalArgumentException if pencilMarks contains duplicates, is of a size greater than 9, or contains numbers outside the range [1,9]
	 * @throws NullPointerException if pencilMarks is null
	 */
	public void setPencilMarks(int[] pencilMarks){
		if(pencilMarks == null){throw new NullPointerException("pencilMarks cannot be null");}
		if(pencilMarks.length > 9){throw new IllegalArgumentException("pencilMarks cannot have a length > 9");}
		
		// Initialize pencil to default state
		this.pencil = new boolean[9];
		for(int i = 0; i < 9; i++){
			this.pencil[i] = false;
		}
		
		// Fill pencil according to pencilMarks
		for(int i : pencilMarks){
			if(i > 9 || i < 1){throw new IllegalArgumentException("pencilMarks cannot contain numbers outside the range [1,9]");}
			if(this.pencil[i-1]){throw new IllegalArgumentException("pencilMarks cannot contain duplicate elements");}
			this.pencil[i-1] = true;
		}
	}
	
	/**
	 * Adds pencil mark m to the Cell's pencil marks
	 * @param m - an int in the range [1,9]
	 * @throws IllegalArgumentExceptin if m is not in the specified range
	 */
	public void addMark(int m){
		if(m > 9 || m < 1){throw new IllegalArgumentException("m must be in the range [1,9]");}
		this.pencil[m-1] = true;
	}
	
	/**
	 * Removes pencil mark m from the Cell's pencil marks
	 * @param m - an int in the range [1,9]
	 * @throws IllegalArgumentException if m is not in the specified range
	 */
	public void removeMark(int m){
		if(m > 9 || m < 1){throw new IllegalArgumentException("m must be in the range [1,9]");}
		this.pencil[m-1] = false;
	}
	
	/**
	 * Two Cells are equal if their values are equal and nonzero
	 * @param o - The Cell being tested for equality against this
	 * @throws NullPointerException if o is null
	 * @throws IllegalArgumentException if o is not of type Cell
	 */
	public boolean equals(Object o){
		if(o == null){throw new NullPointerException("must be given an argument of type Cell");}
		if(!(o instanceof Cell)){
			throw new IllegalArgumentException("must be given an argument of type Cell");
		}
		
		Cell c = (Cell) o;
		
		return (this.value == c.getValue()) && this.value != 0;
	}
	
	/**
	 * The abstraction function for Cell simply returns the string representation of its value if its value is nonzero, and
	 * if its value is equal to 0 it returns a string containing the whitespace character.
	 * @return String representation of this
	 */
	public String toString(){
		if(this.value == 0){
			return " ";
		}else{
			return ""+this.value;
		}
	}
	
	/**
	 * Based on the current state of the puzzle, fill in all valid pencil marks of a given Cell
	 * @param cel - Cell object whose pencil marks will be filled, cannot be a PermCell object
	 * @param blk - Block object that contains cel
	 * @param col - Column object that contains cel
	 * @param row - Row object that contains cel
	 * @throws NullPointerException if cel, blk, col, or row are null
	 * @throws IllegalArgumentException if cel is a PermCell object
	 */
	public static void fillPencilMarks(Cell cel, Block blk, Column col, Row row){
		if(cel instanceof PermCell){throw new IllegalArgumentException("Cannot fill pencil marks of a PermCell object");}
		
		// Check for NPE
		if(blk == null){throw new NullPointerException("blk cannot be null");}
		if(col == null){throw new NullPointerException("col cannot be null");}
		if(row == null){throw new NullPointerException("row cannot be null");}
		
		// We will use a subtractive process and iterate through all 9 pencil marks
		for(int i = 1; i <= 9; i++){
			// Because this process is easier if we work subtractively, we start assuming the pencil mark i is valid
			cel.addMark(i);
			
			// Next we check blk, col, and row to see if they contain a Cell with value i
			// Checking blk before col or row will cut down on the number of checks, the choice
			// of checking col before row is arbitrary.
			// The temp Cell variable is used because the Group.contains() method takes a Cell as a parameter
			Cell temp = new Cell(i);
			if(blk.contains(temp)){
				cel.removeMark(i);
			}else if(col.contains(cel)){
				cel.removeMark(i);
			}else if(row.contains(cel)){
				cel.removeMark(i);
			}
		}
	}
}

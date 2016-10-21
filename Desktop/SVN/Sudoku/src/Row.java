import java.util.*;
import java.io.*;

/**
 * The Row class is a child of the Group class, and stores Cells of the same row in one place
 * and can perform operations on them. The Row class always stores Cells in column-major order.
 * @author Jeffrey Cohen
 *
 */
public class Row extends Group{

	/**
	 * An array that stores the Cell objects of this class. This array will always be sorted into column-major (ascending) order.
	 */
	private Cell[] cells;
	
	/**
	 * The Row class stores a row of Cells in column-major order.
	 * @param c - An array of Cells, the array must be exactly size 9
	 * @throws NullPointerException if c is null
	 * @throws IllegalArgumentException if array is not of size 9
	 */
	public Row(Cell[] c){
		if(c.length != 9){throw new IllegalArgumentException("Can only take arrays of size 9");}
		cells = c.clone();
	}
	
	/**
	 * Gets the Cell at column c of this
	 * @param c - an int in the range [0,9)
	 * @return the Cell at column c of this
	 * @throws IllegalArgumentException if r is not in the specified range
	 */
	public Cell getCell(int c){
		if(c >= 9 || c < 0){throw new IllegalArgumentException("Index out of bounds");}
		return cells[c];
	}

	/**
	 * The abstraction function for Row will print each element of cells on the same line with each element surrounded by square braces
	 */
	public String toString(){
		String build = "";
		for(Cell c : cells){
			build += "[" + c + "]";
		}
		return build;
	}
}

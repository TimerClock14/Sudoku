import java.util.*;
import java.io.*;

/**
 * The Column class is a child of the Group class, and stores Cells of the same column in one place
 * and can perform operations on them. The Column class always stores Cells in row-major order.
 * @author Jeffrey Cohen
 *
 */
public class Column extends Group{

	/**
	 * An array that stores the Cell objects of this class. This array will always be sorted into row-major (ascending) order.
	 */
	private Cell[] cells;
	
	/**
	 * The Column class stores a column of Cells in row-major order.
	 * @param c - An array of Cells, the array must be exactly size 9
	 * @throws NullPointerException if c is null
	 * @throws IllegalArgumentException if array is not of size 9
	 */
	public Column(Cell[] c){
		if(c.length != 9){throw new IllegalArgumentException("Can only take arrays of size 9");}
		cells = c.clone();
	}
	
	/**
	 * Gets the Cell at row r of this
	 * @param r - an int in the range [0,9)
	 * @return the Cell at row r of this
	 * @throws IllegalArgumentException if r is not in the specified range
	 */
	public Cell getCell(int r){
		if(r >= 9 || r < 0){throw new IllegalArgumentException("Index out of bounds");}
		return cells[r];
	}
	
	/**
	 * The abstraction function for Column will print each element of cells on a new line surrounded by square braces
	 */
	public String toString(){
		String build = "";
		for(Cell c : cells){
			build += "[" + c + "]\n";
		}
		return build;
	}
}

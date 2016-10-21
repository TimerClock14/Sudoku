import java.util.*;
import java.io.*;

/**
 * The Block class is a child of the Group class, and stores Cells of the same block in one place
 * and can perform operations on them. The Block class always stores Cells in row-major, then column-major order.
 * @author Jeffrey Cohen
 *
 */
public class Block extends Group{

	/**
	 * An array that stores the Cell objects of this class. This array will always be sorted into row-major, then column-major order.
	 */
	private Cell[] cells;
	
	/**
	 * The Block class stores a block of Cells in row-major, then column-major order.
	 * @param b - An array of Cells, the array must be exactly size 9
	 * @throws NullPointerException if b is null
	 * @throws IllegalArgumentException if array is not of size 9
	 */
	public Block(Cell[] b){
		if(b.length != 9){throw new IllegalArgumentException("Can only take arrays of size 9");}
		cells = b.clone();
	}
	
	/**
	 * Gets the Cell at location (r,c) of this
	 * @param r - an int in the range [0,3)
	 * @param c - an int in the range [0,3)
	 * @return the Cell at location (r,c) of this
	 * @throws IllegalArgumentException if r or c are not in the specified range
	 */
	public Cell getCell(int r, int c){
		if(r >= 3 || r < 0 || c >= 3 || c < 0){throw new IllegalArgumentException("Index out of bounds");}
		return cells[(r*3)+c];
	}
	
	/**
	 * The abstraction function for Block will print three rows of three elements of cells with each element surrounded by square braces
	 */
	public String toString(){
		String build = "";
		int ctr = 0;
		for(Cell c : cells){
			if(ctr%3 == 2)
				build += "[" + c + "]\n";
			else
				build += "[" + c + "]";
			ctr++;
		}
		return build;
	}
}

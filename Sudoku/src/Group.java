import java.util.*;
import java.io.*;


/**
 * <p>
 * The Group class is a general class that will be extended by the Column, Row, and Block classes.
 * </p> 
 * <p>
 * The Group class provides the isComplete() and isCorrect() methods to all of its children. 
 * </p>
 * @author Jeffrey Cohen
 *
 */
public class Group {

	/**
	 * Holds all cells for this group
	 */
	private Cell[] cells;
	
	/**
	 * isComplete() returns true only if every Cell in cells has a nonzero value
	 * @return true if and only if the above condition is met, else false
	 */
	public boolean isComplete(){
		for(Cell c : cells){
			if(c.getValue() == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * <p>
	 * isCorrect() returns true only if the following conditions are met:
	 * <ul>
	 * <li>Every Cell in cells has a nonzero value</li>
	 * <li>There are no duplicate values among Cells</li>
	 * </ul>
	 * </p>
	 * @return true if and only if the above conditions are met, else false
	 */
	public boolean isCorrect(){
		if(this.isComplete()){
			TreeSet<Integer> set = new TreeSet<Integer>();
			for(Cell c : cells){
				set.add(c.getValue());
			}
			if(set.size() == 9){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Uses the equals() method to check if the given Cell is contained in the Group
	 * @param c - Cell object
	 * @return true is c is contained in the Group, false otherwise
	 */
	public boolean contains(Cell c){
		if(c == null){throw new NullPointerException("Value of c cannot be null");}
		for(Cell temp : cells){
			if(temp.equals(c)){
				return true;
			}
		}
		return false;
	}
}

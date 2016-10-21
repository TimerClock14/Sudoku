import java.util.*;
import java.util.*;

public class PermCell extends Cell {
	
	/**
	 * value is an int in the range [0,9]. This value is a constant.
	 */
	private final int value;
	
	/**
	 * This boolean specifies if the value of this Cell can be changed
	 */
	public final boolean isPerm = true;
	
	/**
	 * Creates a PermCell object with value v
	 * @param v - an integer in the range [1,9]
	 * @throws IllegalArgumentException if v is not in the specified range
	 */
	public PermCell(int v){
		if(v > 9 || v < 1){throw new IllegalArgumentException("v must be in the range [1,9]");}
		value = v;
	}
	
	/**
	 * This operation is unsupported in PermCell because a PermCell object is defined as an object with a value that does not change
	 * @throws UnsupportedOperationException unconditionally
	 */
	public void setValue(int v){
		throw new UnsupportedOperationException("Cannot change value of this object");
	}

	/**
	 * This operation is unsupported in PermCell because it does not require pencil marks
	 * and therefore does not support them or its operations.
	 * @throws UnsupportedOperationException unconditionally
	 */
	public int[] getPencilMarks(){
		throw new UnsupportedOperationException("PermCell does not contain pencil marks");
	}

	/**
	 * This operation is unsupported in PermCell because it does not require pencil marks
	 * and therefore does not support them or its operations.
	 * @throws UnsupportedOperationException unconditionally
	 */
	public void setPencilMarks(int[] pencilMarks){
		throw new UnsupportedOperationException("PermCell does not contain pencil marks");
	}

	/**
	 * This operation is unsupported in PermCell because it does not require pencil marks
	 * and therefore does not support them or its operations.
	 * @throws UnsupportedOperationException unconditionally
	 */
	public void addMark(int m){
		throw new UnsupportedOperationException("PermCell does not contain pencil marks");
	}

	/**
	 * This operation is unsupported in PermCell because it does not require pencil marks
	 * and therefore does not support them or its operations.
	 * @throws UnsupportedOperationException unconditionally
	 */
	public void removeMark(int m){
		throw new UnsupportedOperationException("PermCell does not contain pencil marks");
	}
}

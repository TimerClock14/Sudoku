import java.util.*;
import java.io.*;
import javax.swing.*;

public class Board {
	
	private PuzzleContentPane board;
	private Puzzle puzzle;
	
	public Board(Puzzle p){
		puzzle = p;
		board = new PuzzleContentPane(p);
	}
}

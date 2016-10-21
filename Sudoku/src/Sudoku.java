import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Sudoku {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				File file = new File("src/puzzle_bank.txt");
				Puzzle puzzle = new Puzzle(file);
				new Board(puzzle);
			}
		});
	}

}

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class PuzzleContentPane extends JFrame{
	
	private Puzzle puzzle;
	private JPanel[][] mainTable;
	private JLabel[][] lbls;
	
	public PuzzleContentPane(Puzzle p){
		this.puzzle = p;
		JPanel mainPanel = new JPanel(new GridLayout(3,3));
		lbls = new JLabel[9][9];
		mainTable = new JPanel[3][3];
		Block curBlock = null;
		Cell curCell = null;
		JPanel curPanel = null;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				curBlock = puzzle.getBlock(i+j);
				curPanel = new JPanel(new GridLayout(3,3));
				for(int k = 0; k < 3; k++){
					for(int q = 0; q < 3; q++){
						curCell = curBlock.getCell(k, q);
						JPanel temp = new JPanel(new FlowLayout());
						JLabel lbl = new JLabel(curCell.toString());
						lbl.setHorizontalAlignment(JLabel.CENTER);
						lbl.setVerticalAlignment(JLabel.CENTER);
						lbl.setSize(50, 50);
						lbls[i+k][j+q] = lbl;
						temp.add(lbl);
						temp.setAlignmentX(JPanel.CENTER_ALIGNMENT);
						temp.setAlignmentY(JPanel.CENTER_ALIGNMENT);
						temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						temp.setSize(50, 50);
						curPanel.add(temp);
					}
				}
				curPanel.setSize(150, 150);
				curPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				mainTable[i][j] = curPanel;
				mainPanel.add(mainTable[i][j]);
			}
		}
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sudoku");
		setSize(450, 450);
		setVisible(true);
	}
	
	public void refresh(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				lbls[i][j].setText(puzzle.getCell(i, j).toString());
			}
		}
	}
}

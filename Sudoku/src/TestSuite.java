import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSuite {

	private File f;
	private Puzzle p;
	
	@Before
	public void setUp() throws Exception {
		f = new File("src/puzzle_bank.txt");
	}

	@After
	public void tearDown() throws Exception {
		f = null;
		p = null;
	}

	@Test
	public void testPuzzleFromFileNoErrors() {
		p = new Puzzle(f);
		System.out.println("##### Puzzle #####\n" + p);
		System.out.println("##### Blocks #####\n");
		for(int i = 0; i < 9; i++){
			Block b = p.getBlock(i);
			System.out.println(b);
		}
		System.out.println("\n##### Columns #####\n");
		for(int i = 0; i < 9; i++){
			Column c = p.getColumn(i);
			System.out.println(c);
		}
		System.out.println("\n##### Rows    #####\n");
		for(int i = 0; i < 9; i++){
			Row r = p.getRow(i);
			System.out.println(r);
		}
	}
	
	@Test
	public void runMe(){
		p = new Puzzle(f);
		System.out.println("##### Puzzle #####\n" + p);
		Board b = new Board(p);
	}

}

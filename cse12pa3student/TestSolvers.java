import java.util.ArrayList;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {

	/* Helper method to compare two mazes */
	public void checkMaze(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, wl);
		if(expected == null) { assertNull(s); }
		else {
			ArrayList<Square> sp = startMaze.storePath();
			String actualStr = formatMaze(startMaze.showSolution(sp));
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}	

	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	/* Add your own Worklist tests below */

	/* ************** HINT ******************** 
	 * Use the helper methods to create simple
	 * tests that are easier to debug. 
	 */

	@Test
	public void makeQueue() {
		Square s1 = new Square(0, 1, false);
		Square s2 = new Square(1, 2, true);
		Square s3 = new Square(2, 3, false);
		SearchWorklist q = new QueueWorklist();
		
		q.add(s1);
		q.add(s2);
		assertEquals(false, q.isEmpty());
		
		q.remove();
		q.remove();
		assertEquals(true, q.isEmpty());
		
		//check queue behavior
		q.add(s1);
		q.add(s2);
		q.add(s3);
		assertEquals(s1, q.remove());
	}

	@Test
	public void makeStack() {
		Square s1 = new Square(0, 1, true);
		Square s2 = new Square(1, 2, false);
		Square s3 = new Square(2, 3, true);
		Square s4 = new Square(3, 5, false);
		SearchWorklist s = new StackWorklist();
		
		s.add(s1);
		s.add(s2);
		assertEquals(false, s.isEmpty());
		
		s.remove();
		s.remove();
		assertEquals(true, s.isEmpty());
		
		//check stack behavior
		s.add(s1);
		s.add(s2);
		s.add(s3);
		s.add(s4);
		assertEquals(s4, s.remove());
	}
	
	// # = wall
	// _ = empty space
	// S = Start
	// F = Finish
	// * = solution path
	
	@Test
	public void maze1() {
		Maze m1 = new Maze(new String[] {
					"#___",
					"__F_",
					"S##_",
					"____"
					});
		String[] qExpected = {
					"#___",
					"**F_",
					"S##_",
					"____"
					};
		checkMaze(new QueueWorklist(), m1, qExpected);
	}

	@Test
	public void maze2() {
		Maze m2 = new Maze(new String[] {
					"#_#_",
					"____",
					"_##S",
					"F___"
					});
		String[] sExpected = {
					"#_#_",
					"____",
					"_##S",
					"F***"
					};
		checkMaze(new StackWorklist(), m2, sExpected);
	}

	@Test
	public void maze3() {
		Maze m3 = new Maze(new String[] {
					"_##_",
					"#__#",
					"F##S",
					"#__#"
					});
		String[] qExpected = null;
		checkMaze(new QueueWorklist(), m3, qExpected);
	}
	
	@Test
	public void maze4() {
		Maze m4 = new Maze(new String[] {
					"###S",
					"##_#",
					"#_##",
					"F###",
					});
		String[] sExpected = null;
		checkMaze(new StackWorklist(), m4, sExpected);
	}

	@Test
	public void maze5() {
		Maze m5 = new Maze(new String[] {
					"_#F",
					"#_#",
					"S#_",
					});
		String[] qExpected = null;
		checkMaze(new QueueWorklist(), m5, qExpected);
	}
	
	@Test
	public void diffOrderv1() {
		String[] expected = {
				"#_#_",
				"****",
				"*##S",
				"F___"
				};
		String[] actualAndWrong = {
				"#_#_",
				"____",
				"_##S",
				"F***"
				};
		assertNotEquals(formatMaze(expected), formatMaze(actualAndWrong));
	}
	
	@Test
	public void diffOrderv2() {
		String[] expected = {
				"#___",
				"__F*",
				"S##*",
				"****"
				};
		String[] actualAndWrong = {
				"#___",
				"**F_",
				"S##_",
				"____"
				};
		assertNotEquals(formatMaze(expected), formatMaze(actualAndWrong));
	}
	
	@Test
	public void diffOrderv3() {
		String[] expected = {
				"#__#",
				"**##",
				"F*##",
				"_**S"
				};
		String[] actualAndWrong = {
				"#__#",
				"__##",
				"F_##",
				"***S"
				};
		assertNotEquals(formatMaze(expected), formatMaze(actualAndWrong));
	}
}




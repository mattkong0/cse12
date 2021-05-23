/* Class to implement a Maze solver */

public abstract class MazeSolver {
	
	public static Square solve(Maze maze, SearchWorklist wl) {
		Square start = maze.start;
		wl.add(start);
		start.visit();
		while (!wl.isEmpty()) {
			Square current = wl.remove();
			if (current.equals(maze.finish)) {
				return current;
			}
			else {// find path
				searchForRoute(current, maze, wl);
			}
		}
		return null; // no path
	}

	public static void searchForRoute(Square curr, Maze maze, SearchWorklist wl) {
		int row = curr.getRow();
		int col = curr.getCol();

		if (row - 1 >= 0) {// north and in bounds
			if (!maze.contents[row - 1][col].getIsWall() &&
				!maze.contents[row - 1][col].isVisited()) {// empty
				Square neighborN = maze.contents[row - 1][col];
				neighborN.visit(); // mark as visited
				neighborN.setPrevious(curr); // set previous to current
				wl.add(neighborN); // add neighbor to worklist
			}
		}
		if (row + 1 < maze.rows) {// south and in bounds
			if (!maze.contents[row + 1][col].getIsWall() &&
				!maze.contents[row + 1][col].isVisited()) {// empty
				Square neighborS = maze.contents[row + 1][col];
				neighborS.visit(); // mark as visited
				neighborS.setPrevious(curr); // set previous to current
				wl.add(neighborS); // add neighbor to worklist
			}
		}
		if (col + 1 < maze.cols) {// east and in bounds
			if (!maze.contents[row][col + 1].getIsWall() &&
				!maze.contents[row][col + 1].isVisited()) {// empty
				Square neighborE = maze.contents[row][col + 1];
				neighborE.visit(); // mark as visited
				neighborE.setPrevious(curr); // set previous to current
				wl.add(neighborE); // add neighbor to worklist
			}
		}
		if (col - 1 >= 0) {// west and in bounds
			if (!maze.contents[row][col - 1].getIsWall() &&
				!maze.contents[row][col - 1].isVisited()) {// empty and not visited
				Square neighborW = maze.contents[row][col - 1];
				neighborW.visit(); // mark as visited
				neighborW.setPrevious(curr); // set previous to current
				wl.add(neighborW); // add neighbor to worklist
			}
		}
	}
	
}

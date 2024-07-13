package maze;

import java.util.ArrayList;
import java.util.List;

public class CoinCollector {
	private static int[][] dp;
	public static void main(String[] args) {
		final int ROWS = 4;
		final int COLS = 4;
		
		MazeField[][] maze = new MazeField[ROWS][COLS];
		
		// set maze to open
		for (int row=0; row < maze.length; row++) {
			for(int col=0; col < maze[0].length; col++) {
				maze[row][col] = MazeField.OPEN;
			}
		}

		// set coin at 1, 2
		maze[1][2] = MazeField.COIN;
//		maze[2][2] = MazeField.COIN;
		// set wall at 2, 2
//		maze[2][2] = MazeField.WALL;

		for (int row=0; row < maze.length; row++) {
			for(int col=0; col < maze[0].length; col++) {
				System.out.print(maze[row][col]+" ");
			}
			System.out.println(" ");
		}

		int coins = solveMazeCoins(maze);
		List<MazeStep> steps = solveMazePath(maze);

		// Print the number of coins collected
		System.out.println("Maximum coins collected: " + coins);

		// Print the path taken to collect the maximum coins
		System.out.print("Path to collect max coins: ");
		for (MazeStep step : steps) {
			System.out.print(step + " ");
		}
		System.out.println(); // to end the line after the path output

	}

	public static int solveMazeCoins(MazeField[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;
		dp = new int[rows][cols];
		dp[rows-1][cols-1] = (maze[rows-1][cols-1] == MazeField.COIN) ? 1 : 0;
		for (int row = rows-1; row >= 0; row--) {
			for (int col = cols-1; col >= 0; col--) {
				if (maze[row][col] == MazeField.WALL) continue;
				int fromLeft = (col < cols - 1 && dp[row][col+1] != -1) ? dp[row][col+1] : -1;
				int fromBelow = (row < rows - 1 && dp[row+1][col] != -1) ? dp[row+1][col] : -1;
				if (fromLeft != -1)
					dp[row][col] = Math.max(dp[row][col], fromLeft + (maze[row][col] == MazeField.COIN ? 1 : 0));
				if (fromBelow != -1)
					dp[row][col] = Math.max(dp[row][col], fromBelow + (maze[row][col] == MazeField.COIN ? 1 : 0));
			}
		}
		return dp[0][0];
	}

	public static List<MazeStep> solveMazePath(MazeField[][] maze) {
		List<MazeStep> path = new ArrayList<>();
		int row = 0;
		int col = 0;
		while (row < maze.length - 1 || col < maze[0].length - 1) {
			if (col < maze[0].length - 1 && dp[row][col+1] >= dp[row+1][col]) {
				path.add(MazeStep.UP);
				col++;
			} else {
				path.add(MazeStep.LEFT);
				row++;
			}
		}
		return path;
	}
}

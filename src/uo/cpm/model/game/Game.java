package uo.cpm.model.game;

import uo.cpm.model.Discount;

public class Game {

	public static final int GHOSTS_PER_SQUAD = 3;
	public static final int GHOSTBUSTERS = 7;
	public static final int MAXIMUM_THROWS = 7;

	private Entity[][] rows;
	private int playsRemaining = MAXIMUM_THROWS;

	private int currentThrow;
	private final char[] squads = { 'A', 'B', 'C', 'D', 'E' };

	// INITIALIZATION SECTION

	/**
	 * Creates a new game and places entities in the board.
	 */
	public Game() {
		rows = new Entity[][] { new Entity[1], new Entity[3], new Entity[5], new Entity[7], new Entity[7] };
		placeEntities();
	}

	public int getPlaysRemaining() {
		return playsRemaining;
	}

	public int getMaxPlays() {
		return MAXIMUM_THROWS;
	}

	private void placeEntities() {
		rows[0][0] = new LeaderGhost();
		for (char squad : squads)
			for (int i = 0; i < GHOSTS_PER_SQUAD; i++) {
				int row = 1 + (int) (Math.random() * 3);
				int col = (int) (Math.random() * rows[row].length);
				if (rows[row][col] == null)
					rows[row][col] = new Ghost(squad);
				else
					i--;
			}
		for (int i = 0; i < GHOSTBUSTERS; i++)
			rows[4][i] = new Ghostbuster();
	}

	// PLAY SECTION

	public int getCurrentThrow() {
		return currentThrow;
	}

	/**
	 * Rolls a die between 1 and 2 and places the result in currentThrow
	 */
	public void roll() {
		currentThrow = (int) (1 + Math.random() * 2);
	}

	/**
	 * Performs a move in the internal board from the column passed as a parameter in the pyramidal board.
	 * @param origin column
	 * @return true if successful
	 */
	public boolean move(int virtualOriginCol) {
		// not yet thrown
		if (currentThrow == 0)
			return false;

		// out of bounds for the number of ghostbusters in the board
		if (virtualOriginCol >= rows[4].length || virtualOriginCol < 0)
			return false;

		// number of rows (vertical mobility) for each column
		int nrows = 0;
		switch (virtualOriginCol) {
		case 3:
			nrows = 5;
			break;
		case 2:
		case 4:
			nrows = 4;
			break;
		case 1:
		case 5:
			nrows = 3;
			break;
		default:
			nrows = 2;
		}

		// searching the position of the ghostbuster
		int originRow = -1;
		
		int originCol = virtualOriginCol;
		int i = rows.length - 1;
		for (int j = 0; j < nrows; j++) {
			if (j == 0) {
				if (rows[i][originCol] instanceof Ghostbuster) {
					originRow = i;
					break;
				}
			} else {
				if (rows[i][originCol] instanceof Ghostbuster) {
					originRow = i;
					break;
				}
				originCol--;
			}
			i--;
		}

		// no ghostbuster found in all the board (technically impossible
		if (originRow == -1)
			throw new IllegalStateException("Inconsistent state of the board!");

		int destRow = originRow - currentThrow;
		// no space to move
		if (destRow < 0)
			return false;

		int destCol = originCol - currentThrow;
		if (originRow==4)
			destCol++;
		if (destCol >= rows[destRow].length || destCol < 0)
			return false;
		
		// it is possible to move -> assignation
		rows[destRow][destCol] = rows[originRow][originCol];
		rows[originRow][originCol] = null;
		playsRemaining--;
		currentThrow = 0;
		return true;
	}

	// RESULT SECTION

	/**
	 * @return true if tha game has finished (the number of plays remaining is 0)
	 */
	public boolean hasFinished() {
		return playsRemaining <= 0;
	}

	/**
	 * @return discount code won from the game
	 */
	public Discount getGameResultCode() {
		if (hasFinished()) {
			if (areSquadsDeleted())
				if (isLeaderKilled())
					return Discount.EXTRA25;
				else
					return Discount.EXTRA10;
			return Discount.NO_DISCOUNT;
		} else
			return null;
	}

	private boolean areSquadsDeleted() {
		for (char c : squads) {
			int sum = 0;
			for (int row = 1; row < rows.length; row++)
				for (int col = 0; col < rows[row].length; col++) {
					if (rows[row][col] instanceof Ghost)
						if (((Ghost) rows[row][col]).getSquad() == c)
							sum++;
				}
			if (sum >= 3)
				return false;
		}
		return true;
	}

	private boolean isLeaderKilled() {
		return rows[0][0] instanceof Ghostbuster;
	}

	/**
	 * @return integer array containing the sizes of each row in the game
	 */
	public int[] getSizes() {
		int[] res = new int[rows.length];
		for (int i = 0; i < rows.length; i++)
			res[i] = rows[i].length;
		return res;
	}

	/**
	 * @return String matrix containing the text representation of the different ghosts
	 */
	public String[][] getGameTexts() {
		String[][] res = new String[rows.length][];
		for (int i = 0; i < rows.length; i++) {
			res[i] = new String[rows[i].length];
			for (int j = 0; j < rows[i].length; j++)
				res[i][j] = (rows[i][j] == null) ? " " : rows[i][j]+"";
		}
		return res;
	}

	/**
	 * @param row
	 * @param col
	 * @return argument coordinates have a ghostbuster
	 */
	public boolean hasGhostBuster(int row, int col) {
		if (row >= rows.length || row < 0)
			return false;
		int realCol = col;
		if (row <= 2) realCol -= 3-row;
		if (realCol >= rows[row].length || realCol < 0)
			return false;
		return rows[row][realCol] instanceof Ghostbuster;

	}

}

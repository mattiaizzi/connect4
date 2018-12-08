package com.mattiaizzi.forzaquattro.field;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.player.Player;

public class Field {
	private Coin[][] field;
	private int row;
	private int column;
	private int[] colDimension;

	public Field(Size size) {
		Dimension dim = Dimension.getDimension(size);
		this.row = dim.getRows();
		this.column = dim.getColumns();
		fill();
	}

	public Field() {
		this(Size.SEVEN_SIX);
	}

	private void fill() {
		this.colDimension = new int[column];
		this.field = new Coin[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				colDimension[j] = 0;
				field[i][j] = null;
			}
		}
	}

	public boolean insertCoin(int column, Player player) {
		return insertCoin(column, player.getCoin());
	}

	public boolean insertCoin(int column, Coin coin) {
		if (column < 0 || column >= this.column) {
			return false;
		}
		if (columnFull(column)) {
			return false;
		}
		field[colDimension[column]][column] = coin;
		colDimension[column]++;
		return true;
	}

	public boolean removeCoin(int column, Player player) {
		return removeCoin(column, player.getCoin());
	}

	public boolean removeCoin(int column, Coin coin) {
		if (column < 0 || column >= this.column) {
			return false;
		}
		if (colDimension[column] == 0) {
			return false;
		}
		colDimension[column]--;
		field[colDimension[column]][column] = null;
		return true;
	}

	public boolean columnFull(int column) {
		return colDimension[column] >= this.row;
	}

	public boolean checkWin(Coin coin) {
		if (checkRow(coin) || checkColumn(coin) || checkDiagonalRightLeft(coin) || checkDiagonalLeftRight(coin)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkRow(Coin coin) {
		for (int i = 0; i < row; i++) {
			int count = 0;
			for (int j = 0; j < column; j++) {
				if (field[i][j] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkColumn(Coin coin) {
		for (int i = 0; i < column; i++) {
			int count = 0;
			for (int j = 0; j < row; j++) {
				if (field[j][i] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkDiagonalLeftRight(Coin coin) {
		return checkDiagonalLeftRightOnRow(coin) || checkDiagonalLeftRightOnColumn(coin);
	}

	private boolean checkDiagonalLeftRightOnColumn(Coin coin) {
		for (int i = 0; i <= this.column - 4; i++) {
			int count = 0;
			int column, row;
			for (column = i, row = 0; row < this.row && column < this.column; row++, column++) {
				if (field[row][column] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkDiagonalLeftRightOnRow(Coin coin) {
		for (int i = 0; i <= this.row - 4; i++) {
			int count = 0;
			int column, row;
			for (column = 0, row = i; row < this.row && column < this.column; row++, column++) {
				if (field[row][column] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkDiagonalRightLeft(Coin coin) {
		return checkDiagonalRightLeftOnRow(coin) || checkDiagonalRightLeftOnColumn(coin);
	}

	private boolean checkDiagonalRightLeftOnColumn(Coin coin) {
		for (int i = this.column - 1; i >= 3; i--) {
			int count = 0;
			int column, row;
			for (column = i, row = 0; row < this.row && column >= 0; row++, column--) {
				if (field[row][column] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkDiagonalRightLeftOnRow(Coin coin) {
		for (int i = 0; i <= this.row - 4; i++) {
			int count = 0;
			int column, row;
			for (column = this.column - 1, row = i; row < this.row && column >= 0; row++, column--) {
				if (field[row][column] != coin) {
					count = 0;
				} else {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Coin[][] getField() {
		return field;
	}

	public String toString() {
		String stringa = "";
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < column; j++) {
				if (field[i][j] == null) {
					stringa += "| ";
				} else {
					stringa += "|" + field[i][j].getId();
				}
			}
			stringa += "|\n";
		}
		return stringa;
	}

	public boolean isFull() {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				if (this.field[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public void clear() {
		fill();
	}
}

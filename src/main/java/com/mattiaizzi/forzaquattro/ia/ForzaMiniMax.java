package com.mattiaizzi.forzaquattro.ia;

import java.util.ArrayList;
import java.util.List;
import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.match.GameDifficulty;
import com.mattiaizzi.forzaquattro.move.ForzaMove;
import com.mattiaizzi.forzaquattro.player.Player;


/**
 * Implementazione dell'algoritmo minimax per il gioco forza 4
 * 
 * @author Mattia Izzi
 *
 */
public class ForzaMiniMax extends MiniMax<ForzaMove> {

	private final static int PLAYER1 = 0;
	private final static int PLAYER2 = 1;
	private final Coin player;
	private final Coin opponent;
	private Coin[] coins;
	private int currentPlayer = PLAYER1;
	private Field field;
	private GameDifficulty difficulty;

	public ForzaMiniMax(Player player, Player opponent, Field field, GameDifficulty difficulty) {
		this.player = player.getCoin();
		this.opponent = opponent.getCoin();
		this.coins = new Coin[2];
		coins[0] = this.player;
		coins[1] = this.opponent;
		this.field = field;
		this.difficulty = difficulty;
	}

	public ForzaMove GetBestMove() {
		return this.getBestMoves(getDifficulty(difficulty)).get(0);
	}

	private int getDifficulty(GameDifficulty difficulty2) {
		switch (difficulty) {
		case EASY:
			return 2;
		case MEDIUM:
			return 4;
		case HARD:
			return 8;
		}
		return 0;
	}

	@Override
	public List<ForzaMove> getPossibleMoves() {
		List<ForzaMove> moves = new ArrayList<>();
		for (int i = 0; i < field.getColumn(); i++) {
			if (!field.columnFull(i)) {
				moves.add(new ForzaMove(i));
			}
		}
		return moves;
	}

	@Override
	public boolean gameOver() {
		return field.isFull() || field.checkWin(player) || field.checkWin(opponent);
	}

	@Override
	public void makeMove(ForzaMove move) {
		field.insertCoin(move.getColumn(), coins[currentPlayer]);
		changeTurn();
	}

	@Override
	public void unmakeMove(ForzaMove move) {
		field.removeCoin(move.getColumn());
		changeTurn();
	}

	private void changeTurn() {
		currentPlayer = (currentPlayer + 1) % 2;
	}

	@Override
	public int evaluate() {
		if (field.checkWin(player))
			return 512;
		else if (field.checkWin(opponent))
			return -512;
		else if (field.isFull())
			return 0;
		else
			return heuristicEvaluation();
	}

	private int heuristicEvaluation() {
		return evaluateRows() + evaluateColumns() + evaluateDiagonalLeftRight() + evaluateDiagonalRightLeft();
	}

	private int evaluateDiagonalRightLeft() {
		return evaluateDiagonalRightLeftOnRow() + evaluateDiagonaltRightLeftOnColumn();
	}

	private int evaluateDiagonaltRightLeftOnColumn() {
		int sum = 0;
		for (int i = this.field.getColumn() - 1; i >= 3; i--) {
			int countPlayer = 0, countOpponent = 0;
			int column, row;
			for (column = i, row = 0; row < this.field.getRow() && column >= 0; row++, column--) {
				if (this.field.getField()[row][column] == player) {
					countPlayer++;
				} else if (this.field.getField()[row][column] == opponent) {
					countOpponent++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int evaluateDiagonalRightLeftOnRow() {
		int sum = 0;
		for (int i = 0; i <= this.field.getRow() - 4; i++) {
			int countPlayer = 0, countOpponent = 0;
			int column, row;
			for (column = this.field.getColumn() - 1, row = i; row < this.field.getRow()
					&& column >= 0; row++, column--) {
				if (this.field.getField()[row][column] == player) {
					countPlayer++;
				} else if (this.field.getField()[row][column] == opponent) {
					countOpponent++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int evaluateDiagonalLeftRight() {
		return evaluateDiagonlLeftRightOnRow() + evaluateDiagonalLeftRightOnColumn();
	}

	private int evaluateDiagonalLeftRightOnColumn() {
		int sum = 0;
		for (int i = 0; i <= this.field.getColumn() - 4; i++) {
			int countPlayer = 0, countOpponent = 0;
			int column, row;
			for (column = i, row = 0; row < this.field.getRow() && column < this.field.getColumn(); row++, column++) {
				if (this.field.getField()[row][column] == player) {
					countPlayer++;
				} else if (this.field.getField()[row][column] == opponent) {
					countOpponent++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int evaluateDiagonlLeftRightOnRow() {
		int sum = 0;
		for (int i = 0; i <= this.field.getRow() - 4; i++) {
			int countPlayer = 0, countOpponent = 0;
			int column, row;
			for (column = 0, row = i; row < this.field.getRow() && column < this.field.getColumn(); row++, column++) {
				if (this.field.getField()[row][column] == player) {
					countPlayer++;
				} else if (this.field.getField()[row][column] == opponent) {
					countOpponent++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int evaluateColumns() {
		int sum = 0;
		for (int i = 0; i < field.getColumn(); i++) {
			int countPlayer = 0, countOpponent = 0;
			for (int j = 0; j < field.getRow(); j++) {
				if (field.getField()[j][i] == null) {
					break;
				} else if (field.getField()[j][i] == player) {
					countPlayer++;
				} else {
					countOpponent++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int evaluateRows() {
		int sum = 0;
		for (int i = 0; i < field.getRow(); i++) {
			int countPlayer = 0, countOpponent = 0;
			for (int j = 0; j < field.getColumn(); j++) {
				if (field.getField()[i][j] == opponent) {
					countOpponent++;
				} else if (field.getField()[i][j] == player) {
					countPlayer++;
				}
			}
			sum += staticEvaluation(countPlayer, countOpponent);
		}
		return sum;
	}

	private int staticEvaluation(int countPlayer, int countOpponent) {
		if (countPlayer == 0 && countPlayer == 0 || countPlayer != 0 && countPlayer != 0) {
			return 0;
		}
		return value(countPlayer) - value(countOpponent);
	}

	private int value(int count) {
		switch (count) {
		case 1:
			return 1;
		case 2:
			return 10;
		case 3:
			return 50;
		default:
			return 0;
		}
	}

}

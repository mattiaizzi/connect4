package com.mattiaizzi.forzaquattro.match;

import com.mattiaizzi.forzaquattro.controller.IOController;
import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.player.IAPlayer;
import com.mattiaizzi.forzaquattro.player.InteractivePlayer;
import com.mattiaizzi.forzaquattro.player.Player;

public class Match {
	private Options options;
	private IOController controller;
	private Player[] players;
	private int currentPlayer;
	private Field field;
	private int winner = -1;
	private boolean draw = false;
	private int lastMove = -1;

	public Match(IOController controller, Options options) {
		this.options = options;
		this.controller = controller;
		initField();
		initPlayers();
		chooseFirstPlayer();
	}

	private void chooseFirstPlayer() {
		currentPlayer = Math.random() > 0.5 ? 0 : 1;
	}

	private void initPlayers() {
		players = new Player[2];
		setPlayers();
	}

	private void setPlayers() {
		players[0] = new InteractivePlayer(options.getPlayer1(), options.getCoinPlayer1(), controller);
		if (options.getType() == GameType.PVE) {
			players[1] = new IAPlayer(options.getPlayer2(), options.getCoinPlayer2(),
					field, players[0], options.getDifficulty());
		} else {
			players[1] = new InteractivePlayer(options.getPlayer2(), options.getCoinPlayer2(), controller);
		}

	}

	private void initField() {
		this.field = new Field(options.getSize());
	}

	public void play() {
		while (!gameOver()) {
			controller.printField(field);
			makePlayerMove();
			changeTurn();
		}
		controller.printField(field);
		if(draw) {
			controller.print("Pareggio");
		}else {
			controller.print(players[winner].getName()+" ha vinto!");
		}
	}
	
	public void reset() {
		field.clear();
		winner = -1;
		draw = false;
		chooseFirstPlayer();
		controller.printField(field);
	}

	private void changeTurn() {
		currentPlayer = (currentPlayer + 1) % 2;
	}

	private void makePlayerMove() {
		do {
			lastMove = players[currentPlayer].getMove().getColumn();
		}
		while (!(field.insertCoin(lastMove, players[currentPlayer])));
	}

	private boolean gameOver() {
		if (field.checkWin(players[0].getCoin())) {
			winner = 0;
			return true;
		} else if (field.checkWin(players[1].getCoin())) {
			winner = 1;
			return true;
		} else if (field.isFull()) {
			draw = true;
			return true;
		} else {
			return false;
		}
	}
	
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	public Player getOpponentPlayer() {
		return players[(currentPlayer+1)%2];
	}
	
	public int getLastMove() {
		return this.lastMove;
	}
}

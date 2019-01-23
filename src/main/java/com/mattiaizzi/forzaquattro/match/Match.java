package com.mattiaizzi.forzaquattro.match;

import com.mattiaizzi.forzaquattro.controller.IOController;
import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.player.IAPlayer;
import com.mattiaizzi.forzaquattro.player.InteractivePlayer;
import com.mattiaizzi.forzaquattro.player.Player;


/**
 * Classe che rappresenta una partita di forza 4
 * 
 * @author Mattia Izzi
 *
 */
public class Match {
	private Options options;
	private IOController controller;
	private Player[] players;
	private int currentPlayer;
	private Field field;
	private int winner = -1;
	private boolean draw = false;
	private int lastMove = -1;

	/**
	 * Costruttore
	 * 
	 * @param controller il controller di input/output del gioco
	 * @param options opzioni con cui si inizia il gioco
	 */
	public Match(IOController controller, Options options) {
		this.options = options;
		this.controller = controller;
		initField();
		initPlayers();
		chooseFirstPlayer();
	}

	//scelta random del giocatore che inizia il game
	private void chooseFirstPlayer() {
		currentPlayer = Math.random() > 0.5 ? 0 : 1;
	}

	
	//funzione che inizializza i giocatori
	private void initPlayers() {
		players = new Player[2];
		setPlayers();
	}

	//funzione che crea i due giocatori
	private void setPlayers() {
		players[0] = new InteractivePlayer(options.getPlayer1(), options.getCoinPlayer1(), controller);
		if (options.getType() == GameType.PVE) {
			players[1] = new IAPlayer(options.getPlayer2(), options.getCoinPlayer2(),
					field, players[0], options.getDifficulty());
		} else {
			players[1] = new InteractivePlayer(options.getPlayer2(), options.getCoinPlayer2(), controller);
		}

	}

	//funzione che inizializza il campo di gioco
	private void initField() {
		this.field = new Field(options.getSize());
	}

	/**
	 * Funzione che permette di giocare una partita di forza 4
	 * Quando la partita sarà terminata verrà stampato sul controller il risultato della partita
	 */
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
	
	/**
	 * Funzione che resetta l'intera partita
	 */
	public void reset() {
		field.clear();
		winner = -1;
		draw = false;
		chooseFirstPlayer();
		controller.printField(field);
	}

	//funzione per cambiare il turno del giocatore
	private void changeTurn() {
		currentPlayer = (currentPlayer + 1) % 2;
	}

	//funzione che effettua la mossa del giocatore corrente
	private void makePlayerMove() {
		do {
			lastMove = players[currentPlayer].getMove().getColumn();
		}
		while (!(field.insertCoin(lastMove, players[currentPlayer])));
	}

	//funzione che capoisce quando il gioco è finito(uno dei due giocatori ha vinto
	//oppure il campo è pieno)
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
	
	/**
	 * 
	 * @return Il giocatore corrente
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	/**
	 * 
	 * @return L'avversario del giocatore corrente
	 */
	public Player getOpponentPlayer() {
		return players[(currentPlayer+1)%2];
	}
	
	/**
	 * 
	 * @return l'ultima mossa effettuata sul campo
	 */
	public int getLastMove() {
		return this.lastMove;
	}
}

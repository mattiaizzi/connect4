package com.mattiaizzi.forzaquattro.match;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Size;
import javafx.scene.paint.Color;

/**
 * Classe rappresentate le opzioni di un singolo game
 * 
 * @author Mattia Izzi
 *
 */

public class Options {
	
	public static Coin DEFAULT_COIN_PLAYER_1 = new Coin('X', Color.RED);
	public static Coin DEFAULT_COIN_PLAYER_2 = new Coin('O', Color.YELLOW);
	private GameType type;
	private Size size;
	private GameDifficulty difficulty;
	private String player1;
	private Coin coinP1;
	private Coin coinP2;
	private String player2;
	
	/**
	 * Costruttore 
	 * 
	 * @param type Il tipo di gioco (PVE o PVP)
	 * @param size Le dimensioni della griglia su cui si gioca
	 * @param difficulty Difficoltà del gioco in caso di PVE
	 * @param player1 Il nome del primo giocatore
	 * @param player2 Il nome del secondo giocatore
	 * @param coinP1 Il gettone del primo giocatore
	 * @param coinP2 Il gettone del secondo giocatore
	 */
	public Options(GameType type, Size size, GameDifficulty difficulty,
			String player1, String player2, Coin coinP1, Coin coinP2) {
		this.type = type;
		this.size = size;
		this.difficulty = difficulty;
		this.player1=player1;
		this.player2=player2;
		this.coinP1=coinP1;
		this.coinP2 = coinP2;
	}
	
	/**
	 * Costruttore con gettoni di default
	* @param type Il tipo di gioco (PVE o PVP)
	 * @param size Le dimensioni della griglia su cui si gioca
	 * @param difficulty Difficoltà del gioco in caso di PVE
	 * @param player1 Il nome del primo giocatore
	 * @param player2 Il nome del secondo giocatore
	 */
	public Options(GameType type,Size size, GameDifficulty difficulty, String player1, String player2) {
		this(type, size, difficulty, 
				player1, player2, DEFAULT_COIN_PLAYER_1, DEFAULT_COIN_PLAYER_2 );
	}

	public GameType getType() {
		return type;
	}

	public Size getSize() {
		return size;
	}

	public GameDifficulty getDifficulty() {
		return difficulty;
	}
	
	public String getPlayer1() {
		return player1;
	}
	
	public Coin getCoinPlayer1() {
		return this.coinP1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	
	public Coin getCoinPlayer2() {
		return this.coinP2;
	}
}

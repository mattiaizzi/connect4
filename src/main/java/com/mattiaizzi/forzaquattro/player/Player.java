package com.mattiaizzi.forzaquattro.player;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.move.ForzaMove;
/**
 * Classe che rappresenta un giocatore generico
 * 
 * 
 * @author Mattia Izzi
 *
 */
public abstract class Player {

	private String name;

	private Coin coin;

	public Player(String name, Coin coin) {
		this.name = name;
		this.coin = coin;
	}

	public String getName() {
		return name;
	}
	
	public Coin getCoin() {
		return this.coin;
	}
	
	public abstract ForzaMove getMove();
}

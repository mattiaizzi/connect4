package com.mattiaizzi.forzaquattro.player;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.ia.ForzaMiniMax;
import com.mattiaizzi.forzaquattro.match.GameDifficulty;
import com.mattiaizzi.forzaquattro.move.ForzaMove;

/**
 * Classe che rappresenta un giocatore con intelligenza artificiale
 * 
 * @author Mattia Izzi
 *
 */
public class IAPlayer extends Player {

	private ForzaMiniMax algo;

	public IAPlayer(String name, Coin coin, Field field, Player opponent, GameDifficulty difficulty) {
		super(name, coin);
		algo = new ForzaMiniMax(this, opponent, field, difficulty);
	}

	@Override
	public ForzaMove getMove() {
		return algo.GetBestMove();
	}
}

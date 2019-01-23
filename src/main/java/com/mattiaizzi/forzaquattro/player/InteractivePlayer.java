package com.mattiaizzi.forzaquattro.player;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.controller.IOController;
import com.mattiaizzi.forzaquattro.move.ForzaMove;
import com.mattiaizzi.forzaquattro.utils.Utils;


/**
 * Classe che rappresenta un giocatore interattivo.
 * 
 * Estende la classe astratta Player
 * 
 * @author Mattia Izzi
 *
 */
public class InteractivePlayer extends Player {

	private IOController controller;

	public InteractivePlayer(String name, Coin coin, IOController controller) {
		super(name, coin);
		this.controller = controller;
	}

	@Override
	public ForzaMove getMove() {
		return new ForzaMove(controller.getInt(Utils.Output.askMove(this.getName())));
	}

}

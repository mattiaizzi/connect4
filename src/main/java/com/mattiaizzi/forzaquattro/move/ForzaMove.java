package com.mattiaizzi.forzaquattro.move;

import com.mattiaizzi.forzaquattro.ia.Move;

/**
 * Classe che rappresenta un mossa di forza quattro
 * 
 * @author Mattia Izzi
 *
 */
public class ForzaMove extends Move {

	
	private int column;
	
	public ForzaMove(int column) {
		super();
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}

}

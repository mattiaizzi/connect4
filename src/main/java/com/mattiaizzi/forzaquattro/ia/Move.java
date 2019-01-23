package com.mattiaizzi.forzaquattro.ia;


/**
 * Classe che rappresenta una generica mossa in un gioco a somma zero
 * @author Mattia Izzi
 *
 */
public abstract class Move implements Comparable<Move> {

	double value;
	
	
	@Override
	public int compareTo(Move move) {
		if (Double.isNaN(this.getValue()) && Double.isNaN(move.getValue())) {
			return 0;
		} else if (Double.isNaN(this.getValue())) {
			return -1;
		} else if (Double.isNaN(move.getValue())) {
			return 1;
		}
		// per ordinare dal più grande la più piccolo
		return (int) Math.signum(move.getValue() - this.getValue());
	}

	void setValue(double value) {
		this.value = value;
	}

	double getValue() {
		return this.value;
	};
}

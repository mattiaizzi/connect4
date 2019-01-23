package com.mattiaizzi.forzaquattro.match;

/**
 * Semplice enumerazione che definisce la difficoltà di una partita in caso PVE
 * @author Mattia Izzi
 *
 */

public enum GameDifficulty {
	EASY, MEDIUM, HARD;

	public static GameDifficulty getDifficulty(int choose) {
		switch(choose){
		case 1 : return EASY;
		case 2 : return MEDIUM;
		case 3 : return HARD;
		default : return EASY;
		}
	}
}

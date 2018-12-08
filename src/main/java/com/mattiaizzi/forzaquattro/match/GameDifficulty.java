package com.mattiaizzi.forzaquattro.match;

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

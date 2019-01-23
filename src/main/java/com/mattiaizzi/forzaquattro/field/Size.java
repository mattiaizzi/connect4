package com.mattiaizzi.forzaquattro.field;

/**
 * Semplice enumerazione che definisce le dimensioni di una griglia
 * @author Mattia Izzi
 *
 */
public enum Size {
	FIVE_FOUR, SIX_FIVE, EIGHT_SEVEN, NINE_SEVEN, TEN_SEVEN, EIGHT_EIGHT, SEVEN_SIX;

	public static Size getSize(int i) {
		switch(i){
		case 1 : return FIVE_FOUR;
		case 2 : return SIX_FIVE;
		case 3 : return EIGHT_SEVEN;
		case 4 : return NINE_SEVEN;
		case 5 : return TEN_SEVEN;
		case 6 : return EIGHT_EIGHT;
		case 7:
		default:return SEVEN_SIX;
		}
	}
}

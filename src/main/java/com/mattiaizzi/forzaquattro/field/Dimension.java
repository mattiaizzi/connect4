package com.mattiaizzi.forzaquattro.field;

public class Dimension {
	private int rows;
	private int columns;
	
	public Dimension(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public static Dimension getDimension(Size size) {
		switch(size) {
		case FIVE_FOUR: return new Dimension(5,4);
		case SIX_FIVE: return new Dimension(6,5);
		case EIGHT_SEVEN: return new Dimension(8,7);
		case NINE_SEVEN: return new Dimension(9,7);
		case TEN_SEVEN: return new Dimension(10,7);
		case EIGHT_EIGHT: return new Dimension(8,8);
		case SEVEN_SIX:
		default: return new Dimension(7,6);
		}
	}
	
}

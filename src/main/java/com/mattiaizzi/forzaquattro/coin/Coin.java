package com.mattiaizzi.forzaquattro.coin;

import javafx.scene.paint.Color;

public class Coin {
	private final char id;
	private final Color color;
	
	public Coin(char id, Color color) {
		this.id=id;
		this.color=color;
	}
	
	public char getId() {
		return this.id;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(!(obj instanceof Coin)) return false;
		if(this==obj) return true;
		Coin coin = (Coin )obj;
		return (this.id==coin.id && this.color.equals(coin.color));
	}
}

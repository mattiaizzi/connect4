package com.mattiaizzi.forzaquattro;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Field;

import javafx.scene.paint.Color;

public class TestField {

	@Test
	public void TestCreate() {
		Field field = new Field();
		assertTrue(field != null);
	}

	@Test
	public void TestInsertCoin() {
		Field field = new Field();
		Coin coin = new Coin('o',Color.BLUE);
		assertTrue(field.insertCoin(0, coin));
		assertTrue(field.getField()[0][0]!=null);
	}
	
	@Test
	public void TestWinColumn() {
		Field field= new Field();
		Coin coin = new Coin('o', Color.BLUE);
		field.insertCoin(0, coin);
		field.insertCoin(0, coin);
		field.insertCoin(0, coin);
		field.insertCoin(0, coin);
		assertTrue(field.checkWin(coin));
	}
	
	@Test
	public void TestWinRow() {
		Field field= new Field();
		Coin coin = new Coin('o', Color.BLUE);
		field.insertCoin(0, coin);
		field.insertCoin(1, coin);
		field.insertCoin(2, coin);
		field.insertCoin(3, coin);
		assertTrue(field.checkWin(coin));
	}
	
	@Test
	public void testWinDiagonalRightLeft() {
		Field field = new Field();
		Coin[][] grid = field.getField();
		Coin coin = new Coin('o', Color.BLUE);
		grid[2][6] = coin;
		grid[3][5] = coin;
		grid[4][4] = coin;
		grid[5][3] = coin;
		assertTrue(field.checkWin(coin));
	}
	
	@Test
	public void testWinDiagonalLeftRight() {
		Field field = new Field();
		Coin[][] grid = field.getField();
		Coin coin = new Coin('o', Color.BLUE);
		grid[0][3] = coin;
		grid[1][4] = coin;
		grid[2][5] = coin;
		grid[3][6] = coin;
		assertTrue(field.checkWin(coin));
	}
	
	@Test
	public void TestFieldFull() {
		Field field= new Field();
		Coin coin = new Coin('o', Color.BLUE);
		for(int i=0;i<field.getRow();i++) {
			for(int j=0;j<field.getColumn();j++) {
				field.insertCoin(j, coin);
			}
		}
		assertTrue(field.isFull());
	}
}

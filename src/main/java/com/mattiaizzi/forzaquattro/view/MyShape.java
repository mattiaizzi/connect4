package com.mattiaizzi.forzaquattro.view;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class MyShape extends StackPane {

	private int row;
	private int column;
	private final Circle coin;
	final TranslateTransition translateTranstion;

	public MyShape(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		Rectangle rect = new Rectangle(50,50);
		Circle circ = new Circle(25);
		circ.centerXProperty().set(25);
		circ.centerYProperty().set(25);
		Shape cell = Path.subtract(rect, circ);
		cell.setFill(Color.BLUE);
		cell.setStroke(Color.BLUE);
		cell.setOpacity(.8);
		DropShadow effect = new DropShadow();
		effect.setSpread(.2);
		effect.setRadius(10);
		effect.setColor(Color.BLUE);
		cell.setEffect(effect);
		this.coin = new Circle(18);
		coin.setFill(Color.TRANSPARENT);
		coin.setOpacity(.5);
		translateTranstion = new TranslateTransition(Duration.millis(300), coin);
		this.getChildren().addAll(coin, cell);
	}

	public void animation(Color color) {
//			this.coin.setTranslateY(0);
//			translateTranstion.play();
			this.coin.setFill(color);
	}

	public Circle getCoin() {
		return this.coin;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}

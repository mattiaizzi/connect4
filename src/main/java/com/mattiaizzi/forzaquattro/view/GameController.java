package com.mattiaizzi.forzaquattro.view;

import java.util.LinkedList;
import java.util.List;

import com.mattiaizzi.forzaquattro.MainApp;
import com.mattiaizzi.forzaquattro.controller.IOController;
import com.mattiaizzi.forzaquattro.field.Dimension;
import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.field.Size;
import com.mattiaizzi.forzaquattro.match.Match;
import com.mattiaizzi.forzaquattro.match.Options;
import com.mattiaizzi.forzaquattro.player.IAPlayer;
import com.mattiaizzi.forzaquattro.player.InteractivePlayer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GameController implements IOController {

	@FXML
	private Label message;

	@FXML
	private Button playAgain;

	@FXML
	private AnchorPane fieldPane;

	private GridPane field;

	private Match match;
	
	private Options options;

	private int column = -1;

	private boolean moveEmpty = true;

	private boolean fieldDrawn = false;

	private MainApp mainApp;

	@FXML
	public void initialize() {
		this.playAgain.setVisible(false);
	}

	public void play(Options options) {
		this.options = options;
		this.match = new Match(this, options);
		createField(options.getSize());
		start(match);
	}

	private void start(Match match) {
		Runnable runnable = () -> {
			this.match.play();
			this.field.setDisable(true);
			this.playAgain.setVisible(true);
		};
		Thread game = new Thread(runnable);
		game.start();
	}

	private void createField(Size size) {
		this.field = new GridPane();
		this.field.getColumnConstraints().addAll(getColumnConstraints(Dimension.getDimension(size).getColumns()));
		this.field.getRowConstraints().addAll(getRowConstraints(Dimension.getDimension(size).getRows()));
		fillGrid(Dimension.getDimension(size).getRows(), Dimension.getDimension(size).getColumns());
		addField();
	}

	private void addField() {
		this.field.setAlignment(Pos.CENTER);
		this.fieldPane.getChildren().add(this.field);
		AnchorPane.setRightAnchor(this.field, 0.0);
		AnchorPane.setLeftAnchor(this.field, 0.0);
		AnchorPane.setTopAnchor(this.field, 0.0);
		AnchorPane.setBottomAnchor(this.field, 0.0);
	}

	private void fillGrid(int rows, int columns) {
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				MyShape shape = new MyShape(rows - j - 1, i);
				shape.getCoin().setOnMouseClicked(e -> {
					synchronized (this) {
						if (this.match.getCurrentPlayer() instanceof InteractivePlayer) {
							this.column = shape.getColumn();
							this.moveEmpty = false;
							notify();
						}
					}
				});
				this.field.add(shape, i, j);
			}
		}
	}

	private List<RowConstraints> getRowConstraints(int rows) {
		List<RowConstraints> rowConstraints = new LinkedList();
		for (int i = 0; i < rows; i++) {
			rowConstraints.add(new RowConstraints(50, 50, Double.MAX_VALUE));
		}
		return rowConstraints;
	}

	private List<ColumnConstraints> getColumnConstraints(int columns) {
		List<ColumnConstraints> columnConstraints = new LinkedList();
		for (int i = 0; i < columns; i++) {
			columnConstraints.add(new ColumnConstraints(50, 50, Double.MAX_VALUE));
		}
		return columnConstraints;
	}

	@Override
	public String getMessage(String message) {
		return null;
	}

	@Override
	public int getInt(String message) {
		Platform.runLater(() -> this.message.setText(message));
		synchronized (this) {
			while (this.moveEmpty) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		this.moveEmpty = true;
		return column;
	}

	@Override
	public void print(String message) {
		Platform.runLater(() -> this.message.setText(message));
	}

	@Override
	public void printField(Field field) {
		Thread printThread = printThread(field);
		printThread.start();
		synchronized (this) {
			while (!fieldDrawn) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			fieldDrawn = false;
		}
	}

	private Thread printThread(Field field) {
		return new Thread(() -> Platform.runLater(() -> {
			synchronized (this) {
				if(this.match.getCurrentPlayer() instanceof IAPlayer) {
					this.message.setText("Calcolando la mossa...");
				}
				for (Node n : this.field.getChildren()) {
					MyShape shape = (MyShape) n;
					if (field.getField()[shape.getRow()][shape.getColumn()] != null) {
						shape.animation(field.getField()[shape.getRow()][shape.getColumn()].getColor());
					}
				}
				fieldDrawn = true;
				notify();
			}
		}));
	}

	@FXML
	private void handleReGame() {
		this.mainApp.startGame(options);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

package com.mattiaizzi.forzaquattro.view;

import com.mattiaizzi.forzaquattro.MainApp;
import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Size;
import com.mattiaizzi.forzaquattro.match.GameDifficulty;
import com.mattiaizzi.forzaquattro.match.GameType;
import com.mattiaizzi.forzaquattro.match.Options;
import com.mattiaizzi.forzaquattro.utils.Utils;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SetGameController {

	@FXML
	private ChoiceBox<GameType> mode;

	@FXML
	private ChoiceBox<GameDifficulty> difficulty;

	@FXML
	private ChoiceBox<String> size;

	@FXML
	private ColorPicker colorP1;

	@FXML
	private ColorPicker colorP2;

	@FXML
	private TextField player1;

	@FXML
	private TextField player2;

	@FXML
	private Button play;

	@FXML
	private Label message;

	private MainApp mainApp;

	@FXML
	public void initialize() {
		initTextField();
		initMode();
		initDifficult();
		initSize();
		initPickColor();

	}

	private void initTextField() {
		this.player1.setPromptText("Player 1");
		this.player2.setPromptText("Player 2");
	}

	private void initPickColor() {
		colorP1.getStyleClass().add("split-button");
		colorP1.setValue(Color.RED);
		colorP2.getStyleClass().add("split-button");
		colorP2.setValue(Color.YELLOW);
	}

	private void initSize() {
		size.setItems(FXCollections.observableArrayList("7x6", "5x4", "6x5", "8x7", "9x7", "10x7", "8x8"));
		size.getSelectionModel().selectFirst();
	}

	private void initDifficult() {
		difficulty.setItems(
				FXCollections.observableArrayList(GameDifficulty.EASY, GameDifficulty.MEDIUM, GameDifficulty.HARD));
		difficulty.getSelectionModel().selectFirst();
		difficulty.setDisable(true);
	}

	private void initMode() {
		mode.setItems(FXCollections.observableArrayList(GameType.PVP, GameType.PVE));
		mode.getSelectionModel().selectFirst();
		mode.getSelectionModel().selectedIndexProperty().addListener(l -> {
			if (mode.getValue() == GameType.PVP) {
				difficulty.setDisable(false);
				player2.setText("PC Player");
				player2.setDisable(true);
			} else {
				difficulty.setDisable(true);
				player2.setText("");
				player2.setPromptText("Player 2");
				player2.setDisable(false);
			}
		});
	}

	@FXML
	private void handlePlay() {
		if(this.player1.getText().trim().isEmpty()||this.player2.getText().trim().isEmpty()) {
			this.message.setText("Inserire entrambi i nomi dei giocatori");
		}else if(colorP1.getValue().equals(colorP2.getValue())){
			this.message.setText("I colori delle pedine non possono essere uguali");
		}else {
			this.mainApp.startGame(new Options(mode.getValue(),Utils.Decode.getSize(size.getValue()),
					difficulty.getValue(),player1.getText(), player2.getText(),
					new Coin('x',colorP1.getValue()), new Coin('o',colorP2.getValue())));
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

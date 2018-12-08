package com.mattiaizzi.forzaquattro;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.mattiaizzi.forzaquattro.match.Options;
import com.mattiaizzi.forzaquattro.view.GameController;
import com.mattiaizzi.forzaquattro.view.HomeController;
import com.mattiaizzi.forzaquattro.view.RootLayoutController;
import com.mattiaizzi.forzaquattro.view.SetGameController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("FORZA QUATTRO");
		initRootLayout();
		showPane();
	}

	public void showPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/HomeView.fxml"));
			AnchorPane select = (AnchorPane) loader.load();
			HomeController controller = loader.getController();
			controller.setMainApp(this);
			rootLayout.setCenter(select);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			// carico il layout principale
			this.rootLayout = (BorderPane) loader.load();
			// mostro la scena contenuta nel rootlayout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			// Do al controllore l'accesso alla app principale
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showSetGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SetGameView.fxml"));
			AnchorPane select;
			select = (AnchorPane) loader.load();
			SetGameController controller = loader.getController();
			controller.setMainApp(this);
			rootLayout.setCenter(select);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame(Options options) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameView.fxml"));
			AnchorPane select;
			select = (AnchorPane) loader.load();
			GameController controller = loader.getController();
			controller.setMainApp(this);
			rootLayout.setCenter(select);
			controller.play(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

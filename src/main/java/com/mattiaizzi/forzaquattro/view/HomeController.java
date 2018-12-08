package com.mattiaizzi.forzaquattro.view;

import org.controlsfx.control.PopOver;

import com.mattiaizzi.forzaquattro.MainApp;
import com.mattiaizzi.forzaquattro.utils.Utils;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class HomeController {
	
	@FXML
	private Pane animationPane;
	@FXML
	private Button play;
	@FXML
	private Button info;
	@FXML
	private Button exit;
	
	private MainApp mainApp;
	
	private PopOver popOver;
	
	private Label infoMessage;
	
	@FXML
	public void initialize() {
		animation();
		infoMessage = new Label(Utils.Output.INFO);
		popOver = new PopOver(infoMessage);
		infoMessage.setStyle("-fx-background-color: #1d1d1d");
		infoMessage.setPadding(new Insets(15,15,15,15));
		popOver.setPrefSize(100, 100);
	}

	private void animation() {
		Circle circles[] = new Circle[40];
		for (int i = 0; i < circles.length; i++) {
		    circles[i] = new Circle((Math.random()*10)+20);
		    DropShadow dropShadow = new DropShadow();
		    dropShadow.setRadius(5.0);
		    circles[i].setEffect(dropShadow);
		    circles[i].setFill(Color.color(Math.random(), Math.random(), Math.random()));
		    animationPane.getChildren().add(circles[i]);
		    raining(circles[i]);
		}
	}
	
	public void raining(Circle c) {
        c.setCenterX(Math.random()*300);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setFromY(-400*(Math.random()));
        translateTransition.setToY(400+200);
        translateTransition.setFromX(c.getCenterX());
        translateTransition.setToX(c.getCenterX());
        translateTransition.setDuration(Duration.seconds(Math.random()*6+2));
        translateTransition.setOnFinished(e->{
        	raining(c);
        });
        translateTransition.setNode(c);
        translateTransition.play();
    }
	
	@FXML
	private void handlePlay() {
		this.mainApp.showSetGame();
	}
	
	@FXML
	private void handleInfo() {
		popOver.show(info);
	}
	
	@FXML
	private void outOfInfo() {
		popOver.hide();
	}
	
	@FXML
	private void handleExit() {
		Platform.exit();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
 
}

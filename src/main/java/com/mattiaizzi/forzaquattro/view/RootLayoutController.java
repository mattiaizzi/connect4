package com.mattiaizzi.forzaquattro.view;

import com.mattiaizzi.forzaquattro.MainApp;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp;

    @FXML
    public void initialize() {
    	
    }
    /**
     * Chiamata dalla mainApp per dare il riferimento a se stessa
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleHome() {
    	this.mainApp.showPane();
    }
    
    @FXML
    private void handleExit() {
    	Platform.exit();
    }
}

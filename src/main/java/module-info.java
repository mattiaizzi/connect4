module forzaquattro{
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.prefs;
	requires java.desktop;
	requires controlsfx;
	
	opens com.mattiaizzi.forzaquattro to javafx.fxml;
	exports com.mattiaizzi.forzaquattro;
	
//	opens com.mattiaizzi.forzaquattro.model to javafx.fxml;
//	exports com.mattiaizzi.forzaquattro.model;
	
	opens com.mattiaizzi.forzaquattro.view to javafx.fxml;
	exports com.mattiaizzi.forzaquattro.view;
}
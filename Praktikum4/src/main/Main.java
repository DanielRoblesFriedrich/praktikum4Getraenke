package main;

import gui.GetraenkemarktControl;
import business.GetraenkemarktModel;

import gui.guiguiWarenuebersicht.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new GetraenkemarktControl(primaryStage);
		
		Stage fenster2 = new Stage();
		new WarenuebersichtControl(fenster2, GetraenkemarktModel.getInstance());
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}




package gui.guiguiWarenuebersicht;

import business.GetraenkemarktModel;
import javafx.stage.Stage;

import Observer.*;

public class WarenuebersichtControl {

	private WarenuebersichtView warenuebersichtView;;
	
	private  GetraenkemarktModel getrModel;
	
	public WarenuebersichtControl(Stage primaryStage, GetraenkemarktModel getrModel) {
	    this.getrModel = GetraenkemarktModel.getInstance();
	    this.warenuebersichtView = new WarenuebersichtView(primaryStage, this.getrModel, this);
	}

	
	//@Override
	//public void update()
	//{
		//this.warenuebersichtView.zeigeGetraenkemarktAn();;
	//}
}

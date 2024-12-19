package gui.guiguiWarenuebersicht;

import Observer.*;
import business.Getraenk;
import business.GetraenkemarktModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class WarenuebersichtView implements Observer {

	private GetraenkemarktModel getraenkemarktModel;
	//private WarenuebersichtControl warenuebersichtControl;

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblAnzeigeGetrankemarkt= new Label("Anzeige der Warenuebersicht");
	private TextArea txtAnzeigeGetrankemarkt = new TextArea();
	private Button btnAnzeigeGetrankemarkt = new Button("Anzeige");
	// -------Ende Attribute der grafischen Oberflaeche-------

	public WarenuebersichtView(Stage primaryStage, GetraenkemarktModel getraenkeModel, WarenuebersichtControl warenuebersichtControl) {
	    Scene scene = new Scene(this.pane, 560, 340);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Anzeige der Warenuebersicht");
	    primaryStage.show();

	   
	    this.getraenkemarktModel = getraenkeModel;
	    //this.warenuebersichtControl = warenuebersichtControl;
	    this.getraenkemarktModel.addObserver(this);

	    
	    this.initKomponenten();
	    this.initListener();
	}


	private void initKomponenten() {
		// Label
		Font font = new Font("Arial", 20);
		lblAnzeigeGetrankemarkt.setLayoutX(310);
		lblAnzeigeGetrankemarkt.setLayoutY(40);
		lblAnzeigeGetrankemarkt.setFont(font);
		lblAnzeigeGetrankemarkt.setStyle("-fx-font-weight: bold;");
		pane.getChildren().add(lblAnzeigeGetrankemarkt);

		// Textbereich	
		
		txtAnzeigeGetrankemarkt.setEditable(false);
		txtAnzeigeGetrankemarkt.setLayoutX(310);
		txtAnzeigeGetrankemarkt.setLayoutY(90);
		txtAnzeigeGetrankemarkt.setPrefWidth(220);
		txtAnzeigeGetrankemarkt.setPrefHeight(185);
		pane.getChildren().add(txtAnzeigeGetrankemarkt);
		// Button
		btnAnzeigeGetrankemarkt.setLayoutX(310);
		btnAnzeigeGetrankemarkt.setLayoutY(290);
		pane.getChildren().add(btnAnzeigeGetrankemarkt);
	}

	private void initListener() {
		btnAnzeigeGetrankemarkt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeGetraenkemarktAn();
			}
		});
	}

	public void zeigeGetraenkemarktAn() {
	
	    if (getraenkemarktModel.getGetraenke().isEmpty()) {
	        zeigeInformationsfensterAn("Bisher wurde kein Getraenk aufgenommen!");
	        return;
	    }


	    StringBuilder text = new StringBuilder();
	    for (Getraenk element : getraenkemarktModel.getGetraenke()) {
	        text.append(element.gibGetraenkZurueck(' ')).append("\n");
	    }

	    txtAnzeigeGetrankemarkt.setText(text.toString());
	}


	private void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}
	
	@Override
    public void update() {
        zeigeGetraenkemarktAn();
    }

}

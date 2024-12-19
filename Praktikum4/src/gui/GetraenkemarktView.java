package gui;

import business.GetraenkemarktModel;

import business.Getraenk;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;
import Observer.*;

public class GetraenkemarktView implements Observer {

	private GetraenkemarktModel getraenkemarktModel;
	private GetraenkemarktControl getraenkemarktControl;

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblArtikelnummer = new Label("Artikelnummer:");
	private Label lblEinkaufspreis = new Label("Einkaufspreis:");
	private Label lblVerkaufspreis = new Label("Verkaufspreis:");
	private Label lblMitAlkohol = new Label("mit Alkohol:");
	private Label lblBehaeltnis = new Label("Behaeltnis:");

	private TextField txtArtikelnummer = new TextField();
	private TextField txtEinkaufspreis = new TextField();
	private TextField txtVerkaufspreis = new TextField();
	private TextField txtMitAlkohol = new TextField();
	private TextField txtBehaeltnis = new TextField();

	private TextArea txtAnzeige = new TextArea();
	private Button btnEingabe = new Button("Eingabe");
	private Button btnAnzeige = new Button("Anzeige");
	private MenuBar mnbrMenuLeiste = new MenuBar();
	private Menu mnDatei = new Menu("Datei");
	private MenuItem mnItmCsvExport = new MenuItem("csv-Export");
	private MenuItem mnItmTxtExport = new MenuItem("txt-Export");
	private MenuItem mnItmCsvImport = new MenuItem("csv-Import");
	private MenuItem mnItmTxtImport = new MenuItem("txt-Import");
	// -------Ende Attribute der grafischen Oberflaeche-------

	public GetraenkemarktView(GetraenkemarktControl getraenkemarktControl, Stage primaryStage,
			GetraenkemarktModel getraenkemarktModel) {
		Scene scene = new Scene(this.pane, 700, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Verwaltung von Getraenkemarkten");
		primaryStage.show();
		this.getraenkemarktControl = getraenkemarktControl;
		this.getraenkemarktModel = getraenkemarktModel;
		/// Hier
		this.getraenkemarktModel.addObserver(this);

		this.initKomponenten();
		this.initListener();
	}

	private void initKomponenten() {
		// Labels
		lblEingabe.setLayoutX(20);
		lblEingabe.setLayoutY(40);
		Font font = new Font("Arial", 24);
		lblEingabe.setFont(font);
		lblEingabe.setStyle("-fx-font-weight: bold;");
		lblAnzeige.setLayoutX(400);
		lblAnzeige.setLayoutY(40);
		lblAnzeige.setFont(font);
		lblAnzeige.setStyle("-fx-font-weight: bold;");

		lblArtikelnummer.setLayoutX(20);
		lblArtikelnummer.setLayoutY(90);
		lblEinkaufspreis.setLayoutX(20);
		lblEinkaufspreis.setLayoutY(130);
		lblVerkaufspreis.setLayoutX(20);
		lblVerkaufspreis.setLayoutY(170);
		lblMitAlkohol.setLayoutX(20);
		lblMitAlkohol.setLayoutY(210);
		lblBehaeltnis.setLayoutX(20);
		lblBehaeltnis.setLayoutY(250);
		pane.getChildren().addAll(lblEingabe, lblAnzeige, lblArtikelnummer, lblEinkaufspreis, lblVerkaufspreis,
				lblMitAlkohol, lblBehaeltnis);

		// Textfelder
		txtArtikelnummer.setLayoutX(170);
		txtArtikelnummer.setLayoutY(90);
		txtArtikelnummer.setPrefWidth(200);
		txtEinkaufspreis.setLayoutX(170);
		txtEinkaufspreis.setLayoutY(130);
		txtEinkaufspreis.setPrefWidth(200);
		txtVerkaufspreis.setLayoutX(170);
		txtVerkaufspreis.setLayoutY(170);
		txtVerkaufspreis.setPrefWidth(200);
		txtMitAlkohol.setLayoutX(170);
		txtMitAlkohol.setLayoutY(210);
		txtMitAlkohol.setPrefWidth(200);
		txtBehaeltnis.setLayoutX(170);
		txtBehaeltnis.setLayoutY(250);
		txtBehaeltnis.setPrefWidth(200);
		pane.getChildren().addAll(txtArtikelnummer, txtEinkaufspreis, txtVerkaufspreis, txtMitAlkohol, txtBehaeltnis);

		// Textbereich
		txtAnzeige.setEditable(false);
		txtAnzeige.setLayoutX(400);
		txtAnzeige.setLayoutY(90);
		txtAnzeige.setPrefWidth(270);
		txtAnzeige.setPrefHeight(185);
		pane.getChildren().add(txtAnzeige);

		// Buttons
		btnEingabe.setLayoutX(20);
		btnEingabe.setLayoutY(290);
		btnAnzeige.setLayoutX(400);
		btnAnzeige.setLayoutY(290);
		pane.getChildren().addAll(btnEingabe, btnAnzeige);

		// Menue
		this.mnbrMenuLeiste.getMenus().add(mnDatei);
		this.mnDatei.getItems().addAll(mnItmCsvExport, mnItmTxtExport, mnItmCsvImport, mnItmTxtImport);
		pane.getChildren().add(mnbrMenuLeiste);
	}

	private void leseGetraenkemarktAusDatei(String typ) {
		getraenkemarktControl.leseGetraenkemarktAusDatei(typ);
	}

	private void initListener() {
		btnEingabe.setOnAction(e -> nehmeGetraenkemarktAuf());
		btnAnzeige.setOnAction(e -> zeigeGetraenkemarktAn());
		mnItmCsvImport.setOnAction(e -> {
		    try {
		        getraenkemarktControl.leseGetraenkemarktAusDatei("csv");
		    } catch (Exception ex) {
		        zeigeFehlermeldungsfensterAn("Fehler beim Einlesen der CSV-Datei: " + ex.getMessage());
		    }
		});

		mnItmTxtImport.setOnAction(e -> GetraenkemarktView.this.leseGetraenkemarktAusDatei("txt"));
	}

	private void nehmeGetraenkemarktAuf() {
	    try {
	        // Text aus dem Behaeltnis-Feld in ein String-Array umwandeln
	        String[] fuellmengen = txtBehaeltnis.getText().split(";");

	        // Neues Getränk erstellen
	        Getraenk getraenke = new Getraenk(
	            txtArtikelnummer.getText(),
	            txtEinkaufspreis.getText(),
	            txtVerkaufspreis.getText(),
	            txtMitAlkohol.getText(),
	            fuellmengen // String-Array mit Füllmengen übergeben
	        );

	        // Getränk zum Modell hinzufügen
	        getraenkemarktModel.setGetraenk(getraenke);
	    } catch (IllegalArgumentException e) {
	        zeigeFehlermeldungsfensterAn("Fehler bei der Eingabe: " + e.getMessage());
	    }
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

	    this.txtAnzeige.setText(text.toString());
	}


	void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

	void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

	@Override
	public void update() {
		zeigeGetraenkemarktAn();
	}
}

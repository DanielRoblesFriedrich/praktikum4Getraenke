package gui;

import java.io.IOException;
import business.GetraenkemarktModel;
import javafx.stage.Stage;
import Observer.*;

public class GetraenkemarktControl  {
    
    private GetraenkemarktView getraenkemarktView;
    private GetraenkemarktModel getraenkemarktModel;

    public GetraenkemarktControl(Stage primaryStage) {
        this.getraenkemarktModel = GetraenkemarktModel.getInstance();
        this.getraenkemarktView = new GetraenkemarktView(this, primaryStage, getraenkemarktModel);
        
        this.getraenkemarktModel = GetraenkemarktModel.getInstance();
        //this.getraenkemarktModel.addObserver(this);
    }
    
    void leseGetraenkemarktAusDatei(String typ) {
        try {
            if ("txt".equalsIgnoreCase(typ)) {
                getraenkemarktModel.leseGetraenkAusTxtDatei();
            } else if ("csv".equalsIgnoreCase(typ)) {
                getraenkemarktModel.leseGetraenkAusCvsDatei();
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + typ);
            }
            getraenkemarktView.zeigeInformationsfensterAn("Der Getraenkemarkt wurde erfolgreich aus der " + typ + "-Datei gelesen!");
        } 
        catch (IOException exc) {
            getraenkemarktView.zeigeFehlermeldungsfensterAn("IOException beim Lesen! Details: " + exc.getMessage());
        }
        catch (NumberFormatException exc) {
            getraenkemarktView.zeigeFehlermeldungsfensterAn("Fehlerhafte Daten im Dateiformat. Details: " + exc.getMessage());
        }
        catch (Exception exc) {
            getraenkemarktView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen! Details: " + exc.getMessage());
        }
    }
    
    //@Override
    //public void update() {
      //  this.getraenkemarktView.zeigeGetraenkemarktAn();
    //}
}
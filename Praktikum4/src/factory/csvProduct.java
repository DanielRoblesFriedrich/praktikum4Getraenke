package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import business.Getraenk;
import java.util.ArrayList;
import java.util.List;

public class csvProduct extends Product {
	
	private BufferedReader reader = null;
	
	public csvProduct() {
		try {
			this.reader = new BufferedReader(new FileReader("Getraenkemarkt.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void fuegeInDateiHinzu(Object object) throws IOException {
	    ArrayList<Getraenk> getraenkeListe = (ArrayList<Getraenk>) object;

	    String zeile;
	    while ((zeile = this.reader.readLine()) != null) {
	        zeile = zeile.trim();
	        String[] daten = zeile.split(",");

	        if (daten.length < 5) {
	            System.err.println("Ungültige Datenzeile übersprungen: " + zeile);
	            continue;
	        }

	        String artikelnummer = daten[0].trim();
	        String einkaufspreis = daten[1].trim();
	        String verkaufspreis = daten[2].trim();
	        String mitAlkohol = daten[3].trim();
	        String[] fuellmengen = daten[4].trim().split(";"); 

	        try {
	            
	            System.out.println("Verarbeite Füllmengen: " + String.join(", ", fuellmengen));

	            Getraenk getraenk = new Getraenk(artikelnummer, einkaufspreis, verkaufspreis, mitAlkohol, fuellmengen);
	            getraenkeListe.add(getraenk);
	        } catch (IllegalArgumentException ex) {
	            System.err.println("Ungültige Daten in Zeile: \"" + zeile + "\". Details: " + ex.getMessage());
	        }
	    }
	}





	@Override
	public void schliesseDatei() throws IOException {
		this.reader.close();
	}

    
}


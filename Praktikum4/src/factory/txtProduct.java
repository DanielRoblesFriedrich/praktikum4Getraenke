package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.Getraenk;

public class txtProduct extends Product {
	
	private BufferedReader read = null;

	public txtProduct() {
		try {
			this.read = new BufferedReader(new FileReader("Getraenkemarkt.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void fuegeInDateiHinzu(Object object) throws IOException {
	    if (!(object instanceof ArrayList<?>)) {
	        throw new IllegalArgumentException("Ungültiges Objekt: Erwartet wird eine ArrayList.");
	    }
	    ArrayList<Getraenk> getraenkeListe = (ArrayList<Getraenk>) object;

	    String artikelnummer = null;
	    String einkaufspreis = null;
	    String verkaufspreis = null;
	    String mitAlkohol = null;
	    ArrayList<String> groessen = new ArrayList<>();

	    String zeile;
	    while ((zeile = this.read.readLine()) != null) {
	        zeile = zeile.trim();

	        if (zeile.isEmpty()) {
	            // Block abgeschlossen, Getränk erstellen
	            if (artikelnummer != null && einkaufspreis != null && verkaufspreis != null && mitAlkohol != null && !groessen.isEmpty()) {
	                String[] groessenArray = groessen.toArray(new String[0]);
	                Getraenk getraenk = new Getraenk(artikelnummer, einkaufspreis, verkaufspreis, mitAlkohol, groessenArray);
	                getraenkeListe.add(getraenk);
	            }

	            artikelnummer = null;
	            einkaufspreis = null;
	            verkaufspreis = null;
	            mitAlkohol = null;
	            groessen.clear();
	            continue;
	        }

	        if (zeile.startsWith("Artikelnummer:")) {
	            artikelnummer = zeile.split(":", 2)[1].trim();
	        } else if (zeile.startsWith("Einkaufspreis:")) {
	            einkaufspreis = zeile.split(":", 2)[1].trim();
	        } else if (zeile.startsWith("Verkaufspreis:")) {
	            verkaufspreis = zeile.split(":", 2)[1].trim();
	        } else if (zeile.startsWith("Getraenk mit Alkohol:")) {
	            mitAlkohol = zeile.split(":", 2)[1].trim();
	        } else if (zeile.startsWith("Groessen der Flaschen:")) {
	            String groessenText = zeile.split(":", 2)[1].trim();
	            groessen = new ArrayList<>(List.of(groessenText.split(","))); // Liste der Größen
	        }
	    }

	    if (artikelnummer != null && einkaufspreis != null && verkaufspreis != null && mitAlkohol != null && !groessen.isEmpty()) {
	        String[] groessenArray = groessen.toArray(new String[0]);
	        Getraenk getraenk = new Getraenk(artikelnummer, einkaufspreis, verkaufspreis, mitAlkohol, groessenArray);
	        getraenkeListe.add(getraenk);
	    }
	}



	
	
	public void schliesseDatei() throws IOException {
		this.read.close();
	}
	
	
   
}

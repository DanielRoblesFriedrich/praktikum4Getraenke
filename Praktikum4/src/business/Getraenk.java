package business;

import java.util.ArrayList;

public class Getraenk {

    private String artikelnummer;
    private String einkaufspreis;
    private String verkaufspreis;
    private String mitAlkohol;
    private ArrayList<String> fuellmengen; 

    // Konstruktor
    public Getraenk(String artikelnummer, String einkaufspreis, String verkaufspreis, String mitAlkohol, String[] fuellmengenArray) {
        this.artikelnummer = artikelnummer;
        this.einkaufspreis = einkaufspreis;
        this.verkaufspreis = verkaufspreis;
        this.mitAlkohol = mitAlkohol;
        setFuellmengenAusArray(fuellmengenArray); 
    }

    
    private void setFuellmengenAusArray(String[] fuellmengenArray) {
        this.fuellmengen = new ArrayList<>();
        for (String menge : fuellmengenArray) {
            this.fuellmengen.add(menge.trim()); 
        }
    }

 
    public String getFuellmengenAlsString(char trenner) {
        StringBuilder ergebnis = new StringBuilder();
        for (int i = 0; i < this.fuellmengen.size() - 1; i++) {
            ergebnis.append(this.fuellmengen.get(i)).append(trenner);
        }
        return ergebnis.append(this.fuellmengen.get(this.fuellmengen.size() - 1)).toString();
    }

 
    public String gibGetraenkZurueck(char trenner) {
        return this.getArtikelnummer() + trenner 
             + this.getEinkaufspreis() + trenner 
             + this.getVerkaufspreis() + trenner 
             + this.getMitAlkohol() + trenner 
             + this.getFuellmengenAlsString(trenner) + "\n";
    }

  
    public String getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }


    public String getEinkaufspreis() {
        return einkaufspreis;
    }

    public void setEinkaufspreis(String einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }

 
    public String getVerkaufspreis() {
        return verkaufspreis;
    }

    public void setVerkaufspreis(String verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }


    public String getMitAlkohol() {
        return mitAlkohol;
    }

    public void setMitAlkohol(String mitAlkohol) {
        this.mitAlkohol = mitAlkohol;
    }

  
    public ArrayList<String> getFuellmengen() {
        return fuellmengen;
    }

    public void setFuellmengen(ArrayList<String> fuellmengen) {
        this.fuellmengen = fuellmengen;
    }
}

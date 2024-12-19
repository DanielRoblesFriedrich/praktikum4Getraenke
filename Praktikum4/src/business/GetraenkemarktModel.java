package business;

import factory.*;
import java.io.IOException;
import java.util.ArrayList;
import Observer.*;

public class GetraenkemarktModel implements Observable {

    private static GetraenkemarktModel instance = null;
    
    private ArrayList<Observer> observers = new ArrayList<>();

    ArrayList<Getraenk> getraenk = new ArrayList<>();
  
    //private Getraenk getraenk;

    
    private GetraenkemarktModel() {
    }

    
    
    public static GetraenkemarktModel getInstance() {
        if (instance == null) { 
            instance = new GetraenkemarktModel();
        }
        return instance;
    }

    

    public void leseGetraenkAusTxtDatei() throws IOException {
        Creator readerCreator = new txtCreator();
        Product produkt = readerCreator.factoryMethod();
        
        produkt.fuegeInDateiHinzu(this.getraenk);
		produkt.schliesseDatei();
		
		 this.notifyObservers();
    }

    public void leseGetraenkAusCvsDatei() throws IOException {
        Creator readerCreator = new csvCreator();
        Product produkt = readerCreator.factoryMethod();

        
        produkt.fuegeInDateiHinzu(this.getraenk);
        produkt.schliesseDatei();

        this.notifyObservers();
    }

    
    public Getraenk getGetraenk(int i) {
        if (i < 0 || i >= this.getraenk.size()) {
            throw new IndexOutOfBoundsException("Ungueltiger Index: " + i);
        }
        return this.getraenk.get(i);
    }

    
    public ArrayList<Getraenk> getGetraenke(){
    	return this.getraenk;
    }
    
    public void setGetraenk(Getraenk ge){
    	this.getraenk.add(ge);
    	this.notifyObservers();
    }
    
    public void addGetraenk(Getraenk ge) {
    	this.getraenk.add(ge);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}

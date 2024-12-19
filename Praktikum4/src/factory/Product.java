package factory;

import java.io.IOException;
import business.Getraenk;

public abstract class Product {
    //public abstract Getraenk leseAusDatei() throws IOException;
	public abstract void fuegeInDateiHinzu (Object object) throws IOException;
	
	
	public abstract void schliesseDatei() throws IOException;

}

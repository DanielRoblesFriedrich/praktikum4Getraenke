package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Getraenk;

class GetrankTest {

    private Getraenk getraenk;

    @BeforeEach
    void setUp() throws Exception {
        String[] fuellmengen = {"0.5L", "1.0L"};
        getraenk = new Getraenk("1234", "10.0", "15.0", "Ja", fuellmengen);
        assertNotNull(getraenk);
    }

    @AfterEach
    void tearDown() throws Exception {
        getraenk = null; 
    }

    @Test
    void testGetArtikelnummer() {
      
        assertEquals("1234", getraenk.getArtikelnummer(), "Die Artikelnummer sollte '1234' sein.");
    }
}


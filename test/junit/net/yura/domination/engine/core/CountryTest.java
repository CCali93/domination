/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.yura.domination.engine.RiskUIUtil;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author damas
 */
public class CountryTest {
    
        static private RiskGame instance;
    private Country c1, c2, c3, c4;
    private Continent ct;

    @BeforeClass
    public static void setUp() throws Exception {

        try {
            RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
            instance = new RiskGame();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        
    }
    
    @Before
    public void before(){
        ct = new Continent("NA", "North America", 50, 0);
        
        c1 = new Country();
        c2 = new Country(255,"USA", "United States of America", ct, 3, 4);
        c3 = new Country();
        c4 = new Country();
        
        c4.addNeighbour(c1);
        c1.addNeighbour(c3);
        c1.addNeighbour(c2);
        c3.addNeighbour(c2);
        
        c4.addArmies(20);
        c3.addArmies(3);
        c2.addArmies(2);
        c1.addArmies(1);
    }
    
    @Test
    public void countryPropertiesTest(){
        assertTrue(c1.getIdString() == null);
        
        c1.setIdString("Canada");
        assertTrue(c1.getIdString().equals("Canada"));
        assertTrue(c2.getIdString().equals("USA"));
        assertTrue(c2.getName().equals("United States of America"));
        assertTrue(c2.toString().equals("USA (255)"));
        assertTrue(c2.getContinent() == ct);
        assertTrue(c2.getColor() == 255);
    }
    
    @Test
    public void countryNeighborhoodTest(){
        assertTrue(c3.isNeighbours(c2));
        
        Vector v = c1.getNeighbours();
        Vector u = c4.getNeighbours();
        
       assertTrue(v.get(0) == c3);
       assertTrue(v.get(1) == c2);
       assertTrue(u.get(0) == c1);
       assertTrue(c1.getCrossContinentNeighbours().get(0).toString().equals("USA (255)"));
    }
    
    @Test
    public void countryArmiesTest(){
        assertTrue(c4.getArmies() == 20);
        c4.addArmies(5);
        assertTrue(c4.getArmies() == 25);
        c4.removeArmies(9);
        assertTrue(c4.getArmies() == 16);
    }
    
    @Test
    public void countryCordinatesTest(){
        assertTrue(c2.getX() == 3);
        assertTrue(c2.getY() == 4);
        
        int dist = c2.getDistanceTo(0, 0);
        assertTrue(dist == 5);
        
    }
    
}

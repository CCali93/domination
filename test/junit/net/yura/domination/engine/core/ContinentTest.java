/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import java.io.File;
import java.util.Vector;
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
public class ContinentTest {
    
    static private RiskGame instance;
    private Country c1, c2, c3, c4;
    private Continent ct;
    Player player;

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
        ct = new Continent("NA", "North America", 50, 1);
        player = new Player(1, "Player", 0, "address");
        
        
        c1 = new Country();
        c2 = new Country(255,"USA", "United States of America", ct, 3, 4);
        c3 = new Country(254,"MX", "Mexico", ct, 1,2);
        c4 = new Country(253,"CND", "Canada", ct, 5,5);
        
        c4.addNeighbour(c1);
        c1.addNeighbour(c3);
        c1.addNeighbour(c2);
        c3.addNeighbour(c2);
        
        c4.addArmies(20);
        c3.addArmies(3);
        c2.addArmies(2);
        c1.addArmies(1);
        
        ct.addTerritoriesContained(c1);
        ct.addTerritoriesContained(c2);
        ct.addTerritoriesContained(c4);
        ct.addTerritoriesContained(c3);
        
        c1.setOwner(player);
        c2.setOwner(player);
        c3.setOwner(player);
        c4.setOwner(player);
    }
    
    @Test
    public void continentPropertiesTest(){
        assertTrue(ct.getIdString().equals("NA"));
        assertTrue(ct.toString().equals("NA [50]"));
        assertTrue(ct.getName().equals("North America"));
        assertTrue(ct.getColor() == 1);
    }
    
    @Test
    public void modifyContinentTest(){
        ct.setColor(99);
        ct.setArmyValue(52);
        ct.setName("N.America");
        ct.setIdString("N.A");
        
        assertTrue(ct.getIdString().equals("N.A"));
        assertTrue(ct.toString().equals("N.A [52]"));
        assertTrue(ct.getName().equals("N.America"));
        assertTrue(ct.getColor() == 99);
    }
    
    @Test
    public void continentTerritoriesTest(){        
        Vector v = ct.getTerritoriesContained();
        assertTrue(v.capacity() == 10);
        
        Vector u = ct.getBorderCountries();
        assertTrue(u.capacity() == 2);
        
        assertTrue(ct.isOwned(player));
        
        assertTrue(ct.getNumberOwned(player) == 4);
        
        assertTrue(ct.getOwner() == player);
    }
    
    @Test
    public void continentArmiesTest(){
        assertTrue(ct.getArmyValue() == 50);
        ct.setArmyValue(0);
        assertTrue(ct.toString().equals("NA"));
        assertTrue(ct.getArmyValue() == 0);
    }
    
}

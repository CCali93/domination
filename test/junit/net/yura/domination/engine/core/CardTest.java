/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import java.io.File;
import static junit.framework.Assert.fail;
import net.yura.domination.engine.RiskUIUtil;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author damas
 */
public class CardTest {
        
    static private RiskGame instance;
    private Country c1, c2, c3, c4;
    private Continent ct;
    Player player;
    Card card;

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
        
        card = new Card("Cannon", c1);
    }
    
    @Test
    public void cardPropertiesTest(){
        assertTrue(card.getName().equals("Cannon"));
        assertTrue(card.getCountry() == c1);
        System.out.println(card.toString());
        assertTrue(card.toString().equals("Cannon - null (0)"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void modifyCardTest(){
        card.setName("Cavalry");
        assertTrue(card.getName().equals("Cavalry"));
        
        card.setName("New Card");
        try{
            assertTrue(card.getName().equals("New Card"));
            fail("Excpected illegal argument exception to be thrown");
        } catch (IllegalArgumentException e){
            assertThat(e.getMessage(), is("trying to make a card with an unknown type: New Card"));
        }
        
    }
}

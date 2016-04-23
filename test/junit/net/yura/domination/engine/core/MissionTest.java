/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import java.io.File;
import static junit.framework.Assert.assertTrue;
import net.yura.domination.engine.RiskUIUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author damas
 */
public class MissionTest {
    
     static private RiskGame instance;
    private Country c1, c2, c3, c4;
    private Continent ct1, ct2, ct3;
    Player player;
    Mission mission;

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
        ct1 = new Continent("NA", "North America", 50, 1);
        ct2 = new Continent("SA", "South America", 40, 1);
        ct3 = new Continent("EU", "Europe", 30, 2);
        player = new Player(1, "Player", 0, "address");
        
        
        c1 = new Country();
        c2 = new Country(255,"USA", "United States of America", ct1, 3, 4);
        c3 = new Country(254,"MX", "Mexico", ct1, 1,2);
        c4 = new Country(253,"CND", "Canada", ct1, 5,5);
        
        c4.addNeighbour(c1);
        c1.addNeighbour(c3);
        c1.addNeighbour(c2);
        c3.addNeighbour(c2);
        
        c4.addArmies(20);
        c3.addArmies(3);
        c2.addArmies(2);
        c1.addArmies(2);
        
        ct1.addTerritoriesContained(c1);
        ct1.addTerritoriesContained(c2);
        ct1.addTerritoriesContained(c4);
        ct1.addTerritoriesContained(c3);
        
        c1.setOwner(player);
        c2.setOwner(player);
        c3.setOwner(player);
        c4.setOwner(player);
        
        mission = new Mission(player, 3, 2, ct1, ct2, ct3, "test");
    }
    
    
    @Test
    public void missionPropertiesTest(){
        assertTrue(mission.getPlayer() == player);
        assertTrue(mission.getContinent1() == ct1);
        assertTrue(mission.getContinent2() == ct2);
        assertTrue(mission.getContinent3() == ct3);
        assertTrue(mission.getDiscription().equals("test"));
        assertTrue(mission.getNoofcountries() == 3);
        
        
    }
    
    @Test
    public void modifyMissionPropertiesTests(){
        mission.setDiscription("");
        assertTrue(mission.getDiscription().equals(""));
        assertTrue(mission.toString().equals("(Discription Missing)"));
        
        mission.setContinent3(ct1);
        mission.setContinent2(ct3);
        mission.setContinent1(ct2);
        
        assertTrue(mission.getContinent1() == ct2);
        assertTrue(mission.getContinent2() == ct3);
        assertTrue(mission.getContinent3() == ct1);
        
        mission.setNoofarmies(5);
        mission.setNoofcountries(4);
        Player temp = new Player(1, "Temp", 0, "cache");
        mission.setPlayer(temp);
        
        assertTrue(mission.getNoofcountries() == 4);
        assertTrue(mission.getNoofarmies()== 5);
        assertTrue(mission.getPlayer() == temp);
        
    }
    
    
}

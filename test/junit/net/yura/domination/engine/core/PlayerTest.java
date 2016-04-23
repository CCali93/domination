/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import net.yura.domination.engine.RiskUIUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author damas
 */
public class PlayerTest {

    static private RiskGame instance;
    private Country c1, c2, c3, c4;

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
        c1 = new Country();
        c2 = new Country();
        c3 = new Country();
        c4 = new Country();
        
        instance.addPlayer(Player.PLAYER_AI_CRAP, "Player1", 169, "ai");
        instance.addPlayer(Player.PLAYER_HUMAN, "Player2", 99, "human");
        
        c4.addNeighbour(c1);
        c1.addNeighbour(c3);
        c1.addNeighbour(c2);
        c3.addNeighbour(c2);
        
        c4.addArmies(20);
        c3.addArmies(3);
        c2.addArmies(2);
        c1.addArmies(1);

        Field gameState = null;
        
//        instance.setIntProperty("gameState", RiskGame.STATE_ATTACKING, 0);
       
        try {
            gameState = instance.getClass().getDeclaredField("gameState");
            gameState.setAccessible(true);
            gameState.set(instance, RiskGame.STATE_ATTACKING);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Test
    public void renamePlayerTest(){
        Player player = instance.setCurrentPlayer(0);
        assertTrue((player.getName()).equals("Player1"));
        player.rename("Player0");
        assertTrue((player.getName()).equals("Player0"));
    }
    
    @Test
    public void playerPropertiesTest(){
        Player player = instance.setCurrentPlayer(1);
        assertTrue(player.toString().equals("Player2"));
        assertTrue(player.getColor() == 99);
        assertTrue(player.getAddress().equals("human"));
    }
    
    @Test
    public void playerArmiesTest(){
        Player player = instance.setCurrentPlayer(1);
        assertTrue(player.getNoArmies() == 0);
        assertTrue(player.getNoTerritoriesOwned() == 0);
    }
    
    @Test
    public void modifyPlayerTest(){
        Player player = new Player(1, "Player", 0, "address");
        player.setColor(255);
        player.setAddress("address1");
        Country pc = new Country();
        player.setCapital(pc);
        player.setAutoDefend(true);
        player.setAutoEndGo(false);
        
        //Assert
        assertTrue(player.getAutoDefend());
        assertTrue(!player.getAutoEndGo());
        assertTrue(player.getCapital() == pc);
        assertTrue(player.getColor() == 255);
    }

    @Test
    public void EliminatePlayerTest() {
        Player ai = instance.setCurrentPlayer(0);
        c1.setOwner(ai);
        c2.setOwner(ai);
        c3.setOwner(ai);
        
        Player human = instance.setCurrentPlayer(1);
        c4.setOwner(human);
        
        try {
            instance.startGame(RiskGame.MODE_DOMINATION, RiskGame.CARD_FIXED_SET, true, true);
        } catch (Exception ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertTrue(instance.attack(c4, c1));
        assertTrue(instance.rollA(3));
//        assertTrue(instance.rollD(1));
        
        int[] humanResults = {6,6,6};
        int[] aiResults = {3,4};
        
        int[] battleResults = instance.battle(humanResults, aiResults);
        
        for(int i: battleResults){
            System.out.println(i);
        }
        
 //       System.out.println(human.getNoArmies() + " " + human.getNoTerritoriesOwned());
        


    }

}

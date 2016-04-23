package net.yura.domination.engine.core;

import java.io.File;
import junit.framework.TestCase;
import net.yura.domination.engine.RiskUIUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.yura.domination.engine.core.RiskGame.MODE_SECRET_MISSION;
import static net.yura.domination.engine.core.RiskGame.STATE_ROLLING;
import org.junit.Assert;

/**
 * @author Yur Mamyrin
 */
public class RiskGameTest extends TestCase {
    private RiskGame instance;
    private Country c1, c2;
    private Player p1, p2;
    
    public RiskGameTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
            instance = new RiskGame();
        }
        catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Test of trade method, of class RiskGame.
     */
    public void testTrade() {
        System.out.println("trade");

        //Country country =  new Country(1, "name", "Full Name", new Continent("name", "Full Name", 5, 0xFFFF0000), 10, 10);

        // 3 different cards = there are 24 combinations

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );


        // 2 cards are the same - CANNON
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );


        int all_INFANTRY = 4;
        int all_CAVALRY = 6;
        int all_CANNON = 8;
        int all_DIFF = 10;
        int all_WILDCARD = 12;


        // 3 different cards = there are 24 combinations

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_WILDCARD, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );


        // 2 cards are the same - CANNON
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );

        
        
        all_INFANTRY = 6;//4;
        all_CAVALRY = 8;//6;
        all_CANNON = 4;//8;
        all_DIFF = 10;
        int one_wildcard_2_the_same = 12;
        int one_wildcard_2_different = 0;
        int two_wildcards = 0;
        all_WILDCARD = 0;//12;


        // 3 different cards = there are 24 combinations

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_WILDCARD, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );


        // 2 cards are the same - CANNON
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        
        
        
        
    }
    
    public void testGameProperties(){
        initGame(15,10);
        assertTrue(instance.getMapFile().equals("luca.map"));
        assertTrue(instance.getState() == 3);
        assertTrue(instance.getCurrentPlayer() == p1);
        
        Vector v = instance.getPlayers();
        assertTrue(v.get(0) == p1);
        assertTrue(instance.getMapName().equals("World War Map"));
        assertTrue(instance.getImageMap().equals("luca_map.gif"));
        assertTrue(instance.getImagePic().equals("luca_pic.jpg"));
        
        assertTrue(instance.getNoContinents() == instance.getContinents().length);
        assertTrue(instance.getNoContinentsOwned(p1) == 0);
        assertTrue(instance.getNoCards() == instance.getCards().size());
        assertTrue(instance.getCountries().length == instance.getNoCountries());
        assertTrue(instance.getNoMissions() == instance.getMissions().size());
        assertTrue(instance.getVersion() == 1);
        assertTrue(instance.getPlayer("p1") == p1);
        
    }
    
    public void testGameModify(){
        Country[] co = {c1, c2};
        instance.setCountries(co);
        assertTrue(instance.getCountries().length == 2);
        
        Continent ct0 = new Continent("NA", "North America", 50, 1);
        Continent ct1 = new Continent("SA", "South America", 45, 2);
        
        Continent[] cn = {ct0, ct1};
        instance.setContinents(cn);
        assertTrue(instance.getContinents().length == 2);
        
        instance.setVersion(2);
        assertTrue(instance.getVersion() == 2);
    }

    public void testSimpleAttackVictory() {
        initGame(2, 1);

        assertTrue(instance.getPlayers().size() == 2);
        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.rollA(1));
        assertTrue(instance.rollD(1));
        
        assertTrue(instance.getAttackerDice() == 1);
        assertTrue(instance.getDefenderDice() == 1);
        
        int[] p1Results = {6},
              p2Results = {3};
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 0, 1, 2, 1, 1}, battleResults);
        
        assertEquals(RiskGame.STATE_BATTLE_WON, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(0));
    }
    
    public void testThreeDiceAttackVictory() {
        initGame(4, 1);

        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.rollA(3));
        assertTrue(instance.rollD(1));
        
        int[] p1Results = {4, 4, 4},
              p2Results = {3};
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 0, 1, 2, 3, 3}, battleResults);
        
        assertEquals(RiskGame.STATE_BATTLE_WON, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(0));
    }
    
    public void testAttackVictoryWithAttackerArmyLoss() {
        initGame(4, 2);
        
        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.getAttacker() == c1);
        assertTrue(instance.getDefender() == c2);
        
        assertTrue(instance.rollA(3));
        assertTrue(instance.rollD(2));
        
        int[] p1Results = {4, 4, 4},
              p2Results = {6, 2};
        
        
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 0, 0, 0}, battleResults);
        
        assertEquals(RiskGame.STATE_ROLLING, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(1));
    }
    
    public void testAttackWithAttackerRetreat() {
        initGame(3, 4);
        
        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.rollA(2));
        assertTrue(instance.rollD(2));
        
        int[] p1Results = {3, 4},
              p2Results = {6, 5};
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 2, 0, 0, 0, 0}, battleResults);
        
        assertEquals(RiskGame.STATE_ATTACKING, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(1));
        
        assertTrue(!instance.canContinue());
    }
    
    public void testAttackWithContinuation() {
        initGame(4, 4);
        
        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.rollA(2));
        assertTrue(instance.rollD(2));
        
        int[] p1Results = {3, 4},
              p2Results = {6, 5};
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 2, 0, 0, 0, 0}, battleResults);
        
        assertEquals(RiskGame.STATE_ROLLING, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(1));
    }
    
    public void testDefenseWithThreeDice() {
        initGame(15, 10);
        
        assertTrue(instance.attack(c1, c2));
        assertTrue(instance.rollA(3));
        assertTrue(instance.rollD(3));
        
        int[] p1Results = {3, 4, 1},
              p2Results = {6, 2, 3};
        
        int[] battleResults = instance.battle(p1Results, p2Results);
        Assert.assertArrayEquals(new int[]{1, 2, 1, 0, 0, 0}, battleResults);
        
        assertEquals(RiskGame.STATE_ROLLING, instance.getState());
        assertEquals(c2.getOwner(), instance.setCurrentPlayer(1));
    }
    
    private void initGame(int p1Armies, int p2Armies) {
        c1 = new Country();
        c2 = new Country();
        
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p1", 255, "");
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p2", 0, "");
        
        c1.addNeighbour(c2);
        c1.addArmies(p1Armies);
        
        c2.addArmies(p2Armies);
        
        p2 = instance.setCurrentPlayer(1);
        c2.setOwner(p2);
        
        p1 = instance.setCurrentPlayer(0);
        c1.setOwner(p1);
        
        try {
            instance.startGame(
                RiskGame.MODE_DOMINATION,
                RiskGame.CARD_FIXED_SET,
                true,
                true
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Field gameState = null;
        try {
            gameState = instance.getClass().getDeclaredField("gameState");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
        
        gameState.setAccessible(true);
        
        try {
            gameState.set(instance, RiskGame.STATE_ATTACKING);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void testDominationModeWin() {
        
        System.out.println("win");
        
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p1", 255, "");
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p2", 0, "");
        
        p2 = instance.setCurrentPlayer(1);
        p1 = instance.setCurrentPlayer(0);
        
        int cuntreeCount = 11;
        ArrayList<Country> countries = new ArrayList<Country>();
        for( int i = countries.size(); i<cuntreeCount; i++){
            Country c = new Country();
            c.setOwner(p1);
            countries.add(c);
        }
        
        c1 = new Country();
        countries.add(c1);
        c1.setOwner(p2);
        
        try {
            instance.startGame(
                RiskGame.MODE_DOMINATION,
                RiskGame.CARD_FIXED_SET,
                true,
                true
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Field gameState = null;
        try {
            gameState = instance.getClass().getDeclaredField("gameState");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
        
        gameState.setAccessible(true);
        
        assertEquals(instance.checkPlayerWon(), false);
    }
    
    public void testCapitalModeWin() {
        
        System.out.println("win");
        
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p1", 255, "");
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p2", 0, "");
        
        p2 = instance.setCurrentPlayer(1);
        p1 = instance.setCurrentPlayer(0);
        
        int cuntreeCount = 11;
        ArrayList<Country> countries = new ArrayList<Country>();
        for( int i = countries.size(); i<cuntreeCount; i++){
            Country c = new Country();
            c.setOwner(p1);
            countries.add(c);
        }
        
        c1 = new Country();
        c2 = new Country();
        countries.add(c1);
        countries.add(c2);
        p1.setCapital(c1);
        p2.setCapital(c2);
        c2.setOwner(p2);
        c1.setOwner(p1);
        
        try {
            instance.startGame(
                RiskGame.MODE_CAPITAL,
                RiskGame.CARD_FIXED_SET,
                true,
                true
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Field gameState = null;
        try {
            gameState = instance.getClass().getDeclaredField("gameState");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
        
        gameState.setAccessible(true);
        
        assertEquals(instance.checkPlayerWon(), false);
    }
    
    public void testMissionModeWin() {
        
        System.out.println("win");
        
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p1", 255, "");
        instance.addPlayer(Player.PLAYER_AI_CRAP, "p2", 0, "");
        
        p2 = instance.setCurrentPlayer(1);
        p1 = instance.setCurrentPlayer(0);
        
        int cuntreeCount = 11;
        ArrayList<Country> countries = new ArrayList<Country>();
        for( int i = countries.size(); i<cuntreeCount; i++){
            Country c = new Country();
            c.setOwner(p1);
            countries.add(c);
        }
        
        c1 = new Country();
        c2 = new Country();
        countries.add(c1);
        countries.add(c2);
        p1.setCapital(c1);
        p2.setCapital(c2);
        c2.setOwner(p2);
        c1.setOwner(p1);
        
        try {
            instance.startGame(
                RiskGame.MODE_SECRET_MISSION,
                RiskGame.CARD_FIXED_SET,
                true,
                true
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Field gameState = null;
        try {
            gameState = instance.getClass().getDeclaredField("gameState");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
        
        gameState.setAccessible(true);
        
        assertEquals(instance.checkPlayerWon(), false);
    }
}

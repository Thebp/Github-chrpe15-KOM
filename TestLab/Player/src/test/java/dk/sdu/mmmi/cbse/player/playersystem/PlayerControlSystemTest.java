package dk.sdu.mmmi.cbse.player.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christian
 */
public class PlayerControlSystemTest {

    public PlayerControlSystemTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProcess() {
        Entity entity = new Entity();
        
        entity.setType(EntityType.PLAYER);
        entity.setIsHit(true);
        entity.setX(10);
        entity.setY(10);
        
        
        assertTrue(entity.getType().equals(EntityType.PLAYER));
        assertTrue(entity.getIsHit());
        assertFalse(entity.getX() == 12);
        assertTrue(entity.getY() != 124);
        

    }

}

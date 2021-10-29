package alibi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class DinoTest {

    private Dino dino;

    @Before
    public void setup() {
        this.dino = new Dino(30, 200, null, new ArrayList<Cactus>());
    }

    @Test
    public void constructor() {
        assertNotNull(new Dino(30, 200, null, null));
    }

    // Test that jump works when on ground
    @Test
    public void jumpTest1() {
        assertTrue(dino.jump());
        dino.tick();
        assertEquals(188, dino.getY());
    }

    // Test that you can't jump when in air
    @Test
    public void jumpTest2() {
        assertTrue(dino.jump());
        dino.tick();
        assertFalse(dino.jump());
        dino.tick();
        assertEquals(177, dino.getY());
    }

    // Test that gravity works when on ground
    @Test
    public void gravityTest1() {
        assertEquals(200, dino.getY());
        dino.tick();
        assertEquals(200, dino.getY());
        dino.tick();
        assertEquals(200, dino.getY());
    }

    // Test that gravity works when in air (and that you don't fall through ground)
    @Test
    public void gravityTest2() {
        assertTrue(dino.jump());

        int yVel = 12;
        int y = 200;

        while (y < 200) {
            assertEquals(y, dino.getY());
            y -= yVel;
            yVel--;
            dino.tick();
        }
    }
}
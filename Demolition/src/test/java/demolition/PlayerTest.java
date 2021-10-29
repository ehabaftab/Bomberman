package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

public class PlayerTest{
    private Player testPlayer;
    private RedEnemy testRedEnemy;
    private YellowEnemy testYellowEnemy;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<Player> player = new ArrayList<Player>();
    private ArrayList<Broken> broken = new ArrayList<Broken>();
    private ArrayList<Goal> goal = new ArrayList<Goal>();
    private App app;
    
    
//hi
    @Test
    public void constructor() {
        assertNotNull(new Player(app,30, 200, null,this.walls,null,null));
        assertNotNull(new RedEnemy(app,30, 200, null,null,null,null));
        assertNotNull(new Wall(30, 200, null));
        assertNotNull(new Broken(30, 200, null));
        assertNotNull(new Empty(30, 200, null));
        assertNotNull(new Goal(30, 200, null));
    }

    /*
    P B - - -
    W R - - - 
    - Y - - -
    - B - - -
    */

     @BeforeEach
    public void setup(){
        this.walls.add(new Wall(32,96,null));
        this.broken.add(new Broken(64,64,null));
        this.broken.add(new Broken(64,160,null));
        this.testPlayer = new Player(app,32,48,null,this.walls, this.broken,this.goal);
        this.testRedEnemy = new RedEnemy(app,64,80,null,this.walls, this.broken,this.player);
        this.testYellowEnemy = new YellowEnemy(app,64,112,null,this.walls, this.broken,this.player);
        
    }

    @Test
    public void moveRightTest(){
        assertFalse(this.testPlayer.moveRight());
        assertTrue(this.testRedEnemy.moveRight());
    }

    @Test
    public void moveLeftTest(){
        assertTrue(this.testPlayer.moveLeft());
        assertFalse(this.testRedEnemy.moveLeft());
    }
    @Test
    public void moveUpTest(){
        assertTrue(this.testPlayer.moveUp());
        assertFalse(this.testRedEnemy.moveUp());
    }
        
    @Test
    public void moveDownTest(){
        assertFalse(this.testPlayer.moveDown());
        assertTrue(this.testRedEnemy.moveDown());
    }

    @Test
    public void enemyMovement(){
        for(int i=0; i<4; i++){
            this.testRedEnemy.tick();
            this.testYellowEnemy.tick();
        }
        assertEquals(this.testRedEnemy.getY(),112);
        assertEquals(this.testYellowEnemy.getX(),32);

    }
}
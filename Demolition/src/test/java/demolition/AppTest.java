package demolition;

import java.util.ArrayList;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AppTest {
    private Player testPlayer;
    private RedEnemy redEnemy;
    private YellowEnemy yellowEnemy;
    private App app = new App();
    private ArrayList<RedEnemy> testRedEnemy = new ArrayList<>();
    private ArrayList<YellowEnemy> testYellowEnemy = new ArrayList<>();
    private Bomb testBombOne;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<Player> player = new ArrayList<Player>();
    private ArrayList<Broken> broken = new ArrayList<Broken>();
    private ArrayList<Goal> goal = new ArrayList<Goal>();



     @BeforeEach
    public void setup(){
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        this.testPlayer = new Player(app,96,144,null,this.walls, this.broken,this.goal);
        this.walls.add(new Wall(32,96,null));      
        this.broken.add(new Broken(64,160,null));
        this.redEnemy = new RedEnemy(app,64,80,null,this.walls, this.broken,this.player);
        this.yellowEnemy = new YellowEnemy(app,64,112,null,this.walls, this.broken,this.player);
    }
    @Test 
    public void basicTest() {
        this.testRedEnemy.add(new RedEnemy(app,64,80,null,this.walls, this.broken,this.player));
        this.broken.add(new Broken(64,160,null));
        this.player.add(new Player(app,32,48,null,this.walls, this.broken,this.goal));
        this.testYellowEnemy.add(new YellowEnemy(app,64,80,null,this.walls, this.broken,this.player));
        app.settings();
        testRedEnemy.get(0).tick();

        //setSprite Check
        testPlayer.tick();
        testPlayer.moveLeft();
        testPlayer.tick();
        testPlayer.moveRight();
        testPlayer.tick();
        testPlayer.moveUp();
        testPlayer.tick();
        testYellowEnemy.get(0).tick();
        app.addBomb();
        app.reset();
        app.keyPressed();
        for(int i=0; i<240;i++)    
            app.draw();   
        app.gameOverScreen(); 
        
    }
    /*
    - - W- -
    - W B W
    */
    // rendering when surrounded by walls
    @Test
    public void bombTest(){
        this.testBombOne = new Bomb(app,96,96,null,broken,walls,player,testRedEnemy,testYellowEnemy);   
        testBombOne.renderExplosion();
        this.walls.add(new Wall(64,96,null));      
        this.walls.add(new Wall(128,96,null));      
        this.walls.add(new Wall(96,64,null));
        this.walls.add(new Wall(96,128,null));
        this.broken.add(new Broken(64,96,null));   
        this.broken.add(new Broken(128,96,null));   
        this.broken.add(new Broken(96,64,null));   
        this.broken.add(new Broken(96,128,null));   
        testBombOne.renderExplosion();

    }

    /*
    P B - - -
    W B - - - 
    B Y - - -
    - B - - -
    */
    //movement of yellow enemy
    @Test
    public void yellowEnemyTest(){
        this.broken.add(new Broken(64,64,null));
        this.broken.add(new Broken(32,128,null));
        for(int i=0; i<4; i++)
            this.yellowEnemy.tick();
        assertEquals(this.yellowEnemy.getY(),80);
        this.broken.add(new Broken(64,80,null));
        for(int i=0; i<4; i++)
            this.yellowEnemy.tick();
        assertEquals(this.yellowEnemy.getX(),96);
        
    }

    /*
    B - - - -
    W R B - - 
    B B - - -
    - B - - -
    */
    //movement of red enemy
    @Test
    public void redEnemyTest(){
        this.broken.add(new Broken(96,96,null));
        this.broken.add(new Broken(64,32,null));
        assertEquals(this.redEnemy.getY(),80);
        this.broken.add(new Broken(64,128,null));
        for(int i=0; i<4; i++)
            this.redEnemy.tick();
        assertEquals(this.redEnemy.getY(),48);
        this.broken.add(new Broken(32,64,null));
        for(int i=0; i<4; i++)
            this.redEnemy.tick();
        assertEquals(this.redEnemy.getY(),48);
        assertEquals(this.redEnemy.getX(),96);
    }
}

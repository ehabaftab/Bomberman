package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class KillMoving{
    private Player testPlayer;
    private ArrayList<RedEnemy> testRedEnemy = new ArrayList<>();
    private ArrayList<YellowEnemy> testYellowEnemy = new ArrayList<>();
    private Bomb testBombOne;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<Player> player = new ArrayList<Player>();
    private ArrayList<Broken> broken = new ArrayList<Broken>();
    private ArrayList<Goal> goal = new ArrayList<Goal>();
    private App app;


/*
    P B - - -
    W R B1 - - 
    - Y - - -
    - B - - -
    */

     @BeforeEach
    public void setup(){
        this.testBombOne = new Bomb(app,96,96,null,broken,walls,player,testRedEnemy,testYellowEnemy);
        this.testRedEnemy.add(new RedEnemy(app,64,80,null,this.walls, this.broken,this.player));
        this.walls.add(new Wall(32,96,null));
        this.broken.add(new Broken(64,160,null));
        this.player.add(new Player(app,32,48,null,this.walls, this.broken,this.goal));
        this.testYellowEnemy.add(new YellowEnemy(app,64,80,null,this.walls, this.broken,this.player));
        
    }

    @Test
    public void bombTestRed(){
        this.testRedEnemy.add(new RedEnemy(app,128,80,null,this.walls, this.broken,this.player));
        this.testRedEnemy.add(new RedEnemy(app,96,48,null,this.walls, this.broken,this.player));
        this.testRedEnemy.add(new RedEnemy(app,96,112,null,this.walls, this.broken,this.player));
        assertEquals(this.testRedEnemy.size(),4);
        for(int i=0; i<8; i++)
            testBombOne.tick();
        this.testBombOne.kill();
        assertEquals(this.testRedEnemy.size(),0);
    }
    

    @Test
    public void bombTestYellow(){
        this.testYellowEnemy.add(new YellowEnemy(app,128,80,null,this.walls, this.broken,this.player));
        this.testYellowEnemy.add(new YellowEnemy(app,96,48,null,this.walls, this.broken,this.player));
        this.testYellowEnemy.add(new YellowEnemy(app,96,112,null,this.walls, this.broken,this.player));
        assertEquals(this.testYellowEnemy.size(),4);
        for(int i=0; i<8; i++)
            testBombOne.tick();
        this.testBombOne.kill();
        assertEquals(this.testYellowEnemy.size(),0);
    }
    @Test
    public void bombTestBroken(){
        this.testBombOne = new Bomb(app,96,160,null,broken,walls,player,testRedEnemy,testYellowEnemy);
        this.broken.add(new Broken(128,160,null));
        this.broken.add(new Broken(96,128,null));
        assertEquals(this.broken.size(),3);
        for(int i=0; i<8; i++)
            testBombOne.tick();
        this.testBombOne.kill();
        assertEquals(this.broken.size(),0);
    }

}
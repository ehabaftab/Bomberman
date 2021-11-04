package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 
import java.util.ArrayList;

/**
class for objects that can move
*/
public abstract class MovingObject extends GameObject {
    /**
    stores the walls
     */
    protected ArrayList<Wall> walls;
    /**
    stores the broken walls
     */
    protected ArrayList<Broken> broken;
    /**
    the direction of the object moving
     */
    protected int direction;
    protected PApplet app;

    /**
    Constructor
    @param walls stores the list of walls
    @param broken stores the list of broken walls
     */
    public MovingObject(PApplet app,int x, int y, PImage sprite, ArrayList<Wall> walls, ArrayList<Broken> broken){
        super(x, y, sprite);
        this.walls = walls;
        this.broken = broken;
        this.app = app;

    }

    public void tick(){}

    /**
    moves the object right
    @return success 
     */
    public boolean moveRight(){
        boolean collision = false;
        int wallX;
        int wallY;
        if(walls != null){
            for (Wall wall : this.walls) {
                wallX = wall.getX();
                wallY = wall.getY();
                if(this.x + 32 == wallX && this.y+16 == wallY)
                    collision = true;
                
            }
        }
        if(broken != null){
            for (Broken broken : this.broken) {
                wallX = broken.getX();
                wallY = broken.getY();
                if(this.x + 32 == wallX && this.y+16 == wallY)
                    collision = true;
            }
        }
        if(!collision){
            this.x += 32;
            direction = 1;
            return true;
        }
        return false;
    }

    /**
    moves the object left
    @return success 
     */
    public boolean moveLeft(){
        boolean collision = false;
        int wallX;
        int wallY;
        if(walls != null){
            for (Wall wall : this.walls) {
                wallX = wall.getX();
                wallY = wall.getY();
                if(this.x - 32 == wallX && this.y+16 == wallY)
                    collision = true;
                
            }
        }
        if(broken != null){
            for (Broken broken : this.broken) {
                wallX = broken.getX();
                wallY = broken.getY();
                if(this.x - 32 == wallX && this.y+16 == wallY)
                    collision = true;
            }
        }
        if(!collision){
            this.x -= 32;
            direction = 3;
            return true;
        }
        return false;
    }

    /**
    moves the object Up
    @return success 
     */
    public boolean moveUp(){
        boolean collision = false;
        int wallX;
        int wallY;
        if(walls != null){
            for (Wall wall : this.walls) {
                wallX = wall.getX();
                wallY = wall.getY();
                if(this.x  == wallX && (this.y+16) - 32 == wallY)
                    collision = true;
                
            }
        }
        if(broken != null){    
            for (Broken broken : this.broken) {
                wallX = broken.getX();
                wallY = broken.getY();
                if(this.x  == wallX && (this.y+16) - 32 == wallY)
                    collision = true;
            }
        }
        if(!collision){
            this.y -= 32;
            direction = 0;
            return true;
        }
        return false;
    }

    /**
    moves the object down
    @return success 
     */
    public boolean moveDown(){
        boolean collision = false;
        int wallX;
        int wallY;
        if(walls != null){
            for (Wall wall : this.walls) {
                wallX = wall.getX();
                wallY = wall.getY();
                if(this.x  == wallX && (this.y+16) + 32 == wallY)
                    collision = true;
            }
        }
        if(broken != null){
            for (Broken broken : this.broken) {
                wallX = broken.getX();
                wallY = broken.getY();
                if(this.x  == wallX && (this.y+16) + 32 == wallY)
                    collision = true;
            }
        }
        if(!collision){
            this.y += 32;
            direction = 2;
            return true;
        }
        return false;
    }
    
}
package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

/**
class for all the objects on the screen
*/
public abstract class GameObject {
    /**
    the x coordinate
     */
    protected int x;
    /**
    the y coordinate
     */
    protected int y;
    /**
    the sprite of the object
     */
    protected PImage sprite;

    /**
    Constructor
    @param x the x-coordinate
    @param y the y-coordinate
    @param sprite the sprote for the object
     */
    public GameObject(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void tick();

    /**
    Draws on the screen
    @param app the app object to draw on
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
    to get the x coordinate
    @return the int value of the coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
    to get the y coordinate
    @return the int value of the coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
    to change the sprite of the object
    @param sprite the PImage object to use to draw on the screen
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    
}
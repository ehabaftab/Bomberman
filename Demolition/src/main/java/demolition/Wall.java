package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

/**
walls that cannot be broken
*/
public class Wall extends GameObject {

    /**
    constructor
     */
    public Wall(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public void tick() {}
    
}
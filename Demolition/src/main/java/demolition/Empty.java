package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

/**
empty tiles
*/
public class Empty extends GameObject {

    /**
    Constructor
     */
    public Empty(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public void tick() {}
    
}
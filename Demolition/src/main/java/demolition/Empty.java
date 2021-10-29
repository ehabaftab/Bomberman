package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

public class Empty extends GameObject {

    public Empty(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public void tick() {}
    
}
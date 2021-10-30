package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 
import java.util.ArrayList;

/**
Bomberman whose objective is to reach the goal tile to win the game
*/
public class Player extends MovingObject{
    /**
    stores the goal tile
     */
    private ArrayList<Goal> goal;
    private int counter;

    /**
    Constructor
     */
    public Player(PApplet app,int x, int y, PImage sprite, ArrayList<Wall> walls, ArrayList<Broken> broken,ArrayList<Goal> goal) {
        super(app,x, y, sprite,walls,broken);
        this.goal = goal;
        this.direction = 2;
        this.counter = 1;

    }

    /**
    creates animation
     */
    public void tick() {
        
        if (direction == 2){
            this.setSprite(app.loadImage("src/main/resources/player/player" + counter + ".png"));
        }else if(direction == 3){
            this.setSprite(app.loadImage("src/main/resources/player/player_left" + counter + ".png"));
        }else if(direction == 0){
            this.setSprite(app.loadImage("src/main/resources/player/player_up" + counter + ".png"));
        }else if(direction == 1){
            this.setSprite(app.loadImage("src/main/resources/player/player_right" + counter + ".png"));
        }
        counter += 1;
        if(counter>4)
            counter = 1;

    }
    
    /**
    Checks if the player has reached the goal tile
    @return Passed
     */
    public boolean checkWin(){
        int objectX;
        int objectY;
        for(int j=0; j < this.goal.size(); j++){
            objectY = this.goal.get(j).getY();
            objectX = this.goal.get(j).getX();
            if(this.y + 16 == objectY && this.x == objectX){
                return true;
            }        
        }
        return false;
    }


}
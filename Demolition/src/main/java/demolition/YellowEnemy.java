package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 
import java.util.Random;
import java.util.ArrayList;

public class YellowEnemy extends MovingObject {
    
    private Random rand = new Random();
    private boolean collision;
    private int counter;
    private int move;
    private ArrayList<Player> player;
    

    public YellowEnemy(PApplet app,int x, int y, PImage sprite, ArrayList<Wall> walls, ArrayList<Broken> broken,ArrayList<Player> player) {
        super(app,x, y, sprite,walls,broken);
        this.direction = 2;
        this.player = player;
        this.collision = false;
        this.counter = 1;
        this.move = 0;
    }

    public void tick() {
        move += 1;
        if(move==4){
        boolean success = false;
            while(!success) {
                if (this.direction==1){
                    success=this.moveRight();
                }else if(this.direction==3){
                    success=this.moveLeft();
                }else if(this.direction==2){
                    success=this.moveDown();
                }else if(this.direction==0){
                    success=this.moveUp();
                }
                if(!success)
                    this.direction += 1;
                    if (this.direction>3)
                        this.direction = 0;
            }
            move = 0;
        }
        try{
            if (direction == 2){
                this.setSprite(app.loadImage("src/main/resources/yellow_enemy/yellow_down" + counter + ".png"));
            }else if(direction == 3){
                this.setSprite(app.loadImage("src/main/resources/yellow_enemy/yellow_left" + counter + ".png"));
            }else if(direction == 0){
                this.setSprite(app.loadImage("src/main/resources/yellow_enemy/yellow_up" + counter + ".png"));
            }else if(direction == 1){
                this.setSprite(app.loadImage("src/main/resources/yellow_enemy/yellow_right" + counter + ".png"));
            }
        }catch(Exception e){}
        counter += 1;
        if(counter>4)
            counter = 1;   
    }

    public boolean meetPlayer(){
        int playerX = 0;
        int playerY = 0; 
        for(Player player : this.player){
            playerX = player.getX();
            playerY = player.getY();
        }
        if(this.x == playerX && this.y == playerY)
            return true;
        return false;
    }


    


    
}
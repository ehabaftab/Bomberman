package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 
import java.util.ArrayList;

/**
able to kill moving objects and broken walls
*/
public class Bomb extends GameObject {
    private PApplet app;
    private ArrayList<Broken> broken;
    private ArrayList<Wall> walls;
    private ArrayList<Player> player;
    private ArrayList<RedEnemy> redEnemy;
    private ArrayList<YellowEnemy> yellowEnemy;
    private int counter;
    private boolean detonate;
    
    /**
    Constructor
    @param broken stores the broken walls
    @param player stores the player
    @param walls stores the solid walls
    @param redEnemy stores the red enemy
    @param yellowEnemy stores the yellow enemy
     */
    public Bomb(PApplet app, int x, int y, PImage sprite, ArrayList<Broken> broken,ArrayList<Wall> walls, ArrayList<Player> player,
    ArrayList<RedEnemy> redEnemy, ArrayList<YellowEnemy> yellowEnemy) {
        super(x, y, sprite);
        this.app = app;
        this.broken = broken;
        this.player = player;
        this.walls = walls;
        this.redEnemy = redEnemy;
        this.yellowEnemy = yellowEnemy;
        this.counter = 1;
        this.detonate = false;
    
    }

    /**
    This method works as a timer for the bomb
     */
    public void tick() {
        try{
            this.setSprite(app.loadImage("src/main/resources/bomb/bomb" + counter + ".png"));
        }catch(Exception e){}
        counter += 1;
        if(counter == 9){
            detonate = true;
            counter = 1;
        }
    }

    /**
    Checks if the bomb timer is up
    @return the state of the bomb
     */

    public boolean detonateCheck(){
        return detonate;
    }

    /**
    Kills any moving objects and breaks broken walls nearby
    @return If the player has been killed
     */
    public boolean kill(){
        detonate = false;
        int startX = this.x;
        int startY = this.y;
        int objectX;
        int objectY;
        boolean alreadyBrokenUp = false;
        boolean alreadyBrokenRight = false;
        boolean alreadyBrokenDown = false;
        boolean alreadyBrokenLeft = false;
        boolean reset = false;
        //for vertical
        for(int i=0; i < 3; i++){
        
            for(int j=0; j < this.walls.size(); j++){
                objectY = this.walls.get(j).getY();
                objectX = this.walls.get(j).getX();
                if(startY + (32*i) == objectY && startX == objectX){
                    alreadyBrokenDown = true;
                }
                if(startY - (32*i) == objectY && startX == objectX){
                    alreadyBrokenUp = true;
                }
                if(startX + (32*i) == objectX && startY == objectY){
                    alreadyBrokenRight = true;
                }
                if(startX - (32*i) == objectX && startY == objectY){
                        alreadyBrokenLeft = true;
                }
            }
        
        
            for(int j=0; j < this.broken.size(); j++){
                objectY = this.broken.get(j).getY();
                objectX = this.broken.get(j).getX();
                if(startY + (32*i) == objectY && startX == objectX && !alreadyBrokenDown){
                    this.broken.remove(broken.get(j));
                    alreadyBrokenDown = true;
                    j=-1;
                }
                else if(startY - (32*i) == objectY && startX == objectX && !alreadyBrokenUp){
                    this.broken.remove(broken.get(j));
                    alreadyBrokenUp = true;
                    j=-1;
                }
                else if(startX + (32*i) == objectX && startY == objectY && !alreadyBrokenRight){
                    this.broken.remove(broken.get(j));
                    alreadyBrokenRight = true;
                    j=-1;
                }
                else if(startX - (32*i) == objectX && startY == objectY && !alreadyBrokenLeft){
                    this.broken.remove(broken.get(j));
                    alreadyBrokenLeft = true;
                    j=-1;
                }            
            }

            for(int j=0; j < this.player.size(); j++){
                objectY = this.player.get(j).getY();
                objectX = this.player.get(j).getX();
                if(startY + (32*i)-16 == objectY && startX == objectX && !alreadyBrokenDown){
                    this.player.remove(this.player.get(j));
                    reset = true;
                }
                else if(startY - (32*i)-16 == objectY && startX == objectX && !alreadyBrokenUp){
                    this.player.remove(this.player.get(j));
                    reset = true;
                }
                else if(startX + (32*i) == objectX && startY-16 == objectY && !alreadyBrokenRight){
                    this.player.remove(this.player.get(j));
                    reset = true;
                }
                else if(startX - (32*i) == objectX && startY-16 == objectY && !alreadyBrokenLeft){
                    this.player.remove(this.player.get(j));
                    reset = true;
                }
                    
            }

            for(int j=0; j < this.redEnemy.size(); j++){
                objectY = this.redEnemy.get(j).getY();
                objectX = this.redEnemy.get(j).getX();
                if(startY + (32*i)-16 == objectY && startX == objectX && !alreadyBrokenDown){
                    this.redEnemy.remove(this.redEnemy.get(j));
                    j=-1;
                }else if(startY - (32*i)-16 == objectY && startX == objectX && !alreadyBrokenUp){
                    this.redEnemy.remove(this.redEnemy.get(j));
                    j=-1;
                }else if(startX + (32*i) == objectX && startY-16 == objectY && !alreadyBrokenRight){
                    this.redEnemy.remove(this.redEnemy.get(j));
                    j=-1;
                }else if(startX - (32*i) == objectX && startY-16 == objectY && !alreadyBrokenLeft){
                    this.redEnemy.remove(this.redEnemy.get(j));
                    j=-1;
                }
                    
            }
            for(int j=0; j < this.yellowEnemy.size(); j++){
                objectY = this.yellowEnemy.get(j).getY();
                objectX = this.yellowEnemy.get(j).getX();
                if(startY + (32*i)-16 == objectY && startX == objectX && !alreadyBrokenDown){
                    this.yellowEnemy.remove(yellowEnemy.get(j));
                    j=-1;
                
                }else if(startY - (32*i)-16 == objectY && startX == objectX && !alreadyBrokenUp){
                    this.yellowEnemy.remove(yellowEnemy.get(j));
                    j=-1;
                
                }else if(startX + (32*i) == objectX && startY-16 == objectY && !alreadyBrokenRight){
                    this.yellowEnemy.remove(yellowEnemy.get(j));
                    j=-1;
                
                }else if(startX - (32*i) == objectX && startY-16 == objectY && !alreadyBrokenLeft){
                    this.yellowEnemy.remove(yellowEnemy.get(j));
                    j=-1;
                }
                    
            }
            
        }
        alreadyBrokenUp = false;
        alreadyBrokenRight = false;
        alreadyBrokenDown = false;
        alreadyBrokenLeft = false;
        return reset;
    }

    /**
    renders explosion on the screen
     */
    public void renderExplosion(){
        boolean foundPositive = false;
        boolean foundNegative = false;
        int startX = this.x;
        int startY = this.y;
        int objectX;
        int objectY;
        //for vertical
        for(int i=1; i < 3; i++){
            for (Wall wall : this.walls) {
                objectY = wall.getY();
                objectX = wall.getX();
                if(startY + (32*i) == objectY && startX == objectX)
                    foundPositive = true;
                if(startY - (32*i) == objectY && startX == objectX)
                    foundNegative = true;
            }
            for (Broken broken: this.broken) {
                objectY = broken.getY();
                objectX = broken.getX();
                if(startY + (32*i) == objectY && startX == objectX)
                    foundPositive = true;
                if(startY - (32*i) == objectY && startX == objectX)
                    foundNegative = true;
            }
             if(!foundPositive)
                 app.image(app.loadImage("src/main/resources/explosion/vertical.png"), startX,startY +(32*i));
             if(!foundNegative)
                app.image(app.loadImage("src/main/resources/explosion/vertical.png"), startX,startY - (32*i));
        }

        foundPositive = false;
        foundNegative = false;
        for(int i=1; i < 3; i++){
            for (Wall wall : this.walls) {
                objectX = wall.getX();
                objectY = wall.getY();
                if(startX + (32*i) == objectX && startY == objectY)
                    foundPositive = true;
                if(startX - (32*i) == objectX && startY == objectY)
                    foundNegative = true;
            }
            for (Broken broken: this.broken) {
                objectX = broken.getX();
                objectY = broken.getY();
                if(startX + (32*i) == objectX && startY == objectY)
                    foundPositive = true;
                if(startX - (32*i) == objectX && startY == objectY)
                    foundNegative = true;
            }
            if(!foundPositive)
                app.image(app.loadImage("src/main/resources/explosion/horizontal.png"),startX +(32*i),startY);
            if(!foundNegative)
                app.image(app.loadImage("src/main/resources/explosion/horizontal.png"), startX - (32*i),startY);
        }
       
    }
}
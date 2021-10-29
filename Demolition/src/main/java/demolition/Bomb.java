package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 
import java.util.ArrayList;

public class Bomb extends GameObject {
    private PApplet app;
    private ArrayList<Broken> broken;
    private ArrayList<Wall> walls;
    private ArrayList<Player> player;
    private ArrayList<RedEnemy> redEnemy;
    private ArrayList<YellowEnemy> yellowEnemy;
    private boolean reset;
    private boolean alreadyBrokenUp;
    private boolean alreadyBrokenRight;
    private boolean alreadyBrokenDown;
    private boolean alreadyBrokenLeft;
    private int counter;
    private boolean detonate;
    

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
        this.reset = false;
        alreadyBrokenUp = false;
        alreadyBrokenRight = false;
        alreadyBrokenDown = false;
        alreadyBrokenLeft = false;
    
    }

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

    public boolean detonateCheck(){
        return detonate;
    }

    public boolean kill(){
        int startX = this.x;
        int startY = this.y;
        int objectX;
        int objectY;
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
}
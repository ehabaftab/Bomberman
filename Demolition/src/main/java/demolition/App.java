package demolition;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.lang.Object;
import processing.data.JSONObject;


public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    public static final int FPS = 60;
    private PImage emptyImage;
    private PImage wallImage;
    private PImage brokenImage;
    private PImage playerImage;
    private PImage redEnemyImage;
    private PImage goalImage;
    private PImage yellowEnemyImage;
    private PImage bombImage;
    private PImage livesIcon;
    private PImage timerIcon;
    private PFont timerFont;
    private PFont gameOverFont;
    private ArrayList<Empty> empty;
    private ArrayList<Player> player;
    private ArrayList<RedEnemy> redEnemy;
    private ArrayList<YellowEnemy> yellowEnemy;
    private ArrayList<Bomb> bomb;
    private ArrayList<Wall> walls;
    private ArrayList<Broken> broken;
    private ArrayList<Goal> goal;
    String levelOneFile;
    String levelTwoFile;
    int currentLevel = 1;
    int levelOneTime;
    int levelTwoTime;
    int lives;
    int playerTimer = 15;
    int redEnemyTimer = 15;
    int yellowEnemyTimer = 15;
    int bombTimer = 15;
    int explosionTimer = 30;
    int levelTime;
    int counter = 0;
    int bombX;
    int bombY;
    Bomb bombToRemove;
    boolean pressed = false;
    boolean bombRemove = false;
    boolean explosion = false;
    boolean reset = false;
    boolean gameOver = false;
    boolean gameWon = false;
    boolean levelFinished = false;

    String temp;

    public App() {
        this.walls = new ArrayList<Wall>();
        this.empty = new ArrayList<Empty>();
        this.broken = new ArrayList<Broken>();
        this.player = new ArrayList<Player>();
        this.redEnemy = new ArrayList<RedEnemy>();
        this.yellowEnemy = new ArrayList<YellowEnemy>();
        this.bomb = new ArrayList<Bomb>();
        this.goal = new ArrayList<Goal>();
        
        
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }


    /**
    Sets up the screen and level using the config file
     */
    public void setup() {
        frameRate(FPS);
        
        this.emptyImage = this.loadImage("src/main/resources/empty/empty.png");
        this.playerImage = this.loadImage("src/main/resources/player/player1.png");
        this.redEnemyImage = this.loadImage("src/main/resources/red_enemy/red_down1.png");
        this.yellowEnemyImage = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.bombImage = this.loadImage("src/main/resources/bomb/bomb.png");
        this.wallImage = this.loadImage("src/main/resources/wall/solid.png");
        this.brokenImage = this.loadImage("src/main/resources/broken/broken.png");
        this.goalImage = this.loadImage("src/main/resources/goal/goal.png");
        this.timerFont = createFont("src/main/resources/PressStart2P-Regular.ttf",20);
        this.gameOverFont = createFont("src/main/resources/PressStart2P-Regular.ttf",25);
        this.timerIcon = this.loadImage("src/main/resources/icons/clock.png");
        this.livesIcon = this.loadImage("src/main/resources/icons/player.png");

        StringBuilder s = new StringBuilder();
        try{

            File jsonFile = new File("config.json");
            Scanner jsonReader = new Scanner(jsonFile);
            int counter = 0;
            while(jsonReader.hasNextLine()){
                s.append(jsonReader.nextLine());
            }
            JSONObject config = JSONObject.parse(s.toString());
            lives = config.getInt("lives");
            levelOneFile = config.getJSONArray("levels").getJSONObject(0).getString("path");
            levelOneTime = config.getJSONArray("levels").getJSONObject(0).getInt("time");
            levelTwoFile = config.getJSONArray("levels").getJSONObject(1).getString("path");
            levelTwoTime = config.getJSONArray("levels").getJSONObject(1).getInt("time");
            jsonReader.close();
        } catch (FileNotFoundException e) {
            println("An error occurred.");
        }
        loadLevel(currentLevel);
    }

    /**
    draws on screen
     */
    public void draw() {
        if(levelFinished){
            
            if(currentLevel == 2){
                gameOver = true;
                gameWon = true;
                gameOverScreen();
            }else{
                currentLevel += 1;
                reset();
                setup();
            }
            levelFinished = false;
        }

        if(!gameWon){
            if (lives==0 || levelTime==0) {
                gameOver = true;
                currentLevel = 1;
                gameOverScreen();
            }
        }
        //if the game is not over the game keeps running
        if(!gameOver){
            counter++;
            loadHeader();
            if(bombTimer == 0){
                for(Bomb bomb : this.bomb){
                    bomb.tick();
                }   
                bombTimer = 15;
            }
            if(redEnemyTimer == 0){
                for (RedEnemy redEnemy : this.redEnemy){
                    redEnemy.tick();
                }
                redEnemyTimer=15;
            }
            if(yellowEnemyTimer == 0){
                for (YellowEnemy yellowEnemy : this.yellowEnemy){
                    yellowEnemy.tick();
                }
                yellowEnemyTimer=15;
            }
            if (playerTimer == 0){
                for (Player player : this.player) {
                    player.tick();
                }
                playerTimer = 15;
            }
            for (Empty empty : this.empty){
                empty.draw(this);
            }
            for (Wall wall : this.walls) {
                wall.draw(this);
            }
            for (Broken broken : this.broken) {
                broken.draw(this);
            }
            for (Goal goal : this.goal) {
                goal.draw(this);
            }
            
            for (Player player : this.player) {
                player.draw(this);
            }
            for (RedEnemy redEnemy : this.redEnemy){
                redEnemy.draw(this);
                if(redEnemy.meetPlayer() == true){
                    this.bomb.clear();
                    reset = true;
                }
            }
            for (YellowEnemy yellowEnemy : this.yellowEnemy){
                yellowEnemy.draw(this);
                if(yellowEnemy.meetPlayer() == true){
                    this.bomb.clear();
                    reset = true;
                }
            }
            for(Bomb bomb : this.bomb){
                bomb.draw(this);
                
                if(bomb.detonateCheck()== true){
                    bombX = bomb.getX();
                    bombY = bomb.getY();
                    bombToRemove = bomb;
                    explosion = true;
                    explosionTimer = 30;
                    if(bomb.kill())
                        reset = true;
                }    
            }

            if(explosion){
                this.image(this.loadImage("src/main/resources/explosion/centre.png"), bombX,bombY);
                bombToRemove.renderExplosion();
                if(explosionTimer==0){
                    this.bombRemove = true;
                    explosion = false;
                    
                }
            }

            if(this.bombRemove){
                removeBomb(bombToRemove);
            }

            if(reset){
                reset();
            }

            
            
            if(counter == 60){
                levelTime -= 1;
                counter = 0;
            }
            bombTimer -= 1;
            playerTimer -= 1;
            redEnemyTimer -= 1;
            yellowEnemyTimer -= 1;
            explosionTimer -= 1;
        }
    }

    /**
    to control the player using the arrow keys
     */
    public void keyPressed(){
        if(!pressed){
            playerTimer = 0;

            if(keyCode == RIGHT){
                for(Player player : this.player){
                    player.moveRight();
                    levelFinished = player.checkWin();
                }
            }else if(keyCode == LEFT){
                for(Player player : this.player){
                    player.moveLeft();
                    levelFinished = player.checkWin();
                }
            }else if(keyCode == UP){
                for(Player player : this.player){
                    player.moveUp();
                    levelFinished = player.checkWin();
                }
            }else if(keyCode == DOWN){
                for(Player player : this.player){
                    player.moveDown();
                    levelFinished = player.checkWin();
                }
            }else if(key == 32){
                if(gameOver){
                    gameWon = false;
                    currentLevel = 1;
                    reset();
                    setup();
                    gameOver = false;
                
                }else{
                    addBomb();
                }
            }
            
            pressed = true;
        }
    }

    public void keyReleased(){
        pressed = false;
    }

    /**
    removes the bomb after explosion
    @param bomb bomb to remove
     */
    public void removeBomb(Bomb bomb){
        this.bomb.remove(bomb);
    }

    /**
    adds bomb on clicking spacebar
     */
    public void addBomb(){
        int xValue = 0;
        int yValue = 0;
        for (Player player : this.player){
            xValue = player.getX();
            yValue = player.getY();
        }
        // if(this.bomb.size() == 0){
            this.bomb.add(new Bomb(this,xValue,yValue+16,bombImage,broken,walls,player,redEnemy,yellowEnemy));
            bombTimer = 15;
        // 
    }


    /**
    resets the level if the user dies
     */
    public void reset(){
        if(!gameOver){
            lives -= 1;
            if(lives<0)
                lives = 0;
        }
        reset = false;
        this.bomb.clear();
        this.walls.clear();
        this.empty.clear();
        this.broken.clear();
        this.player.clear();
        this.redEnemy.clear();
        this.yellowEnemy.clear();
        this.goal.clear();
        if(!gameOver && !levelFinished)
            loadLevel(currentLevel);
    }

    /**
    renders the game over screen when the timer ends or player runs out of lives
     */
    public void gameOverScreen(){
        reset();
        background(239, 129, 0);
        textFont(gameOverFont);
        fill(0);
        if (lives==0 || levelTime==0){
            text("GAME OVER",130,240);
        }else{
            text("YOU WIN",150,240);
        }
        
    }

    /**
    loads header containing lives of the player and the timer
     */
    public void loadHeader(){
        background(239, 129, 0);
        textFont(timerFont);
        fill(0);
        text(lives,175,45);
        text(levelTime,295,45);
        this.image(livesIcon, 135,18);
        this.image(timerIcon, 255,18);
    }

    /**
    loads level
     */
    public void loadLevel(int num){
        int x = 0;
        int y = 64;
        File myObj;
        try {
            if(num == 1){
                myObj = new File(levelOneFile);
                levelTime = levelOneTime;
            }else{
                myObj = new File(levelTwoFile);
                levelTime = levelTwoTime;
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int i=0; i<data.length(); i++) {
                    if(data.charAt(i) == 'W'){
                        this.walls.add(new Wall(x+(32*i), y,wallImage));
                    }else if(data.charAt(i) == 'B'){
                        this.broken.add(new Broken(x+(32*i), y,brokenImage));
                        this.empty.add(new Empty(x+(32*i), y,emptyImage));
                    }else if(data.charAt(i) == 'G'){
                        this.goal.add(new Goal(x+(32*i), y,goalImage));
                    }else if(data.charAt(i) == 'P'){
                        this.player.add(new Player(this,x+(32*i), y-16,playerImage,walls,broken,goal));
                        this.empty.add(new Empty(x+(32*i), y,emptyImage));
                    }else if(data.charAt(i) == 'Y'){
                        this.yellowEnemy.add(new YellowEnemy(this,x+(32*i), y-16,yellowEnemyImage,walls,broken,player));
                        this.empty.add(new Empty(x+(32*i), y,emptyImage));
                    }else if(data.charAt(i) == 'R'){
                        this.redEnemy.add(new RedEnemy(this,x+(32*i), y-16,redEnemyImage,walls,broken,player));
                        this.empty.add(new Empty(x+(32*i), y,emptyImage));
                    }else{
                        this.empty.add(new Empty(x+(32*i), y,emptyImage));
                    }
                    
                }
                y += 32;
        }
         myReader.close();
        } catch (FileNotFoundException e) {
            println("An error occurred.");
        }
    }
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}


//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
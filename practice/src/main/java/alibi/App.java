package alibi;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;

public class App extends PApplet {

    public final int WIDTH = 500;
    public final int HEIGHT = 330;

    private Dino dino;
    private ArrayList<Cactus> cacti;
    private int timer;
    private Random rand;

    private PImage cactusImage;
    
    public App() {
        // Construct objects here
        this.cacti = new ArrayList<Cactus>();
        this.timer = 100;
        this.rand = new Random();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(60);

        // Load images here
        this.dino = new Dino(30, 200, this.loadImage("src/main/resources/dino1.png"), cacti);
        this.cactusImage = this.loadImage("src/main/resources/cactus.png");

        this.cacti.add(new Cactus(400, 210, 5, cactusImage));
    }

    public void draw() {
        // Main loop here
        this.dino.tick();

        for (Cactus cactus : this.cacti) {
            cactus.tick();
        }

        this.rect(-1, -1, WIDTH + 2, HEIGHT + 2);
        this.dino.draw(this);

        for (Cactus cactus : this.cacti) {
            cactus.draw(this);
        }

        this.timer--;

        if (this.timer < 0) {
            cacti.add(new Cactus(540, 210, 5, this.cactusImage));
            this.timer = this.rand.nextInt(60) + 40;
        }
    }

    public void mousePressed() {
        this.dino.jump();
    }

    public static void main(String[] args) {
        PApplet.main("alibi.App");
    }
}

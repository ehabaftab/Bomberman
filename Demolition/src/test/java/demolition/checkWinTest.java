package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

public class checkWinTest{
    private Player testPlayer;
    private ArrayList<Goal> goal = new ArrayList<Goal>();
    private App app;

    @BeforeEach
    public void setup(){
        this.testPlayer = new Player(app,32,48,null,null,null,this.goal);
        this.goal.add(new Goal(64,64,null));
    }

    @Test
    public void checkWin(){
        assertFalse(this.testPlayer.checkWin());
        this.testPlayer.moveRight();
        assertTrue(this.testPlayer.checkWin());
    }
}
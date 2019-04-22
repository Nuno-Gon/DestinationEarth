package logicaJogo;
import java.util.*;

public class GameData { //Dados do jogo
    // JOURNEY TRACKER
    private final String[] journeyTracker;
    
    // PLAYER BOARD
    //Health Tracker
    private final int healthTracker;
    //Hull Tracker
    private final int hullTracker;
    //Inspiration Points
    private final int inspirationPoints;
    
    public GameData() {
        this.journeyTracker = new String[]{"S","2A","3A","4A","5A*","R","4A","5A","6A*","R","6A","7A*","R","8A","E"};
        this.healthTracker = 8;
        this.hullTracker = 8;
        this.inspirationPoints = 0;
    }
}

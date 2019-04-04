package logicaJogo;
import java.util.*;

public class Game{
    private GameData gameData; //Dados do jogo
    
    public Game(){
        gameData = new GameData(); //Novo jogo
    }
    public GameData getGameData() {
        return gameData;
    }		
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
}
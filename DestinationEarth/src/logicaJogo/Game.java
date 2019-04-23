package logicaJogo;

import java.util.*;
import logicaEstados.*;

public class Game {

    private GameData gameData; //Dados do jogo
    private IStates state;

    public Game() {
        gameData = new GameData(); //Novo jogo
        state = new AwaitBeginning(gameData);
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;
    }

    //--------------Estados--------------
    //AwaitBeginning
    public void resolveStart() {
        setState(getState().start());
    }
}

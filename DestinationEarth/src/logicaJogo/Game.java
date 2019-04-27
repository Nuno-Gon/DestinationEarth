package logicaJogo;

import java.util.*;
import logicaEstados.*;

public class Game {

    private GameData gameData; //Dados do jogo
    private IStates state;

    public Game() {
        gameData = new GameData(); //Novo jogo
        state = new AwaitBeginning(gameData); //Estado inicial
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

    //--------------Estados + Eventos--------------
    //iu
    //AwaitBeginning
    public void resolveStart() {
        setState(getState().start());
    }

    //AwaitCrewMembersSelection
    public void selectCrewMember(int x) {
        setState(getState().selectCM(x));
    }

    //AwaitThirdTokenPlacementCM1
    public void thirdTokenPlacementCM1(int x) {
        setState(getState().thirdTokenCM1(x));
    }

    //AwaitThirdTokenPlacementCM2
    public void thirdTokenPlacementCM2(int x) {
        setState(getState().thirdTokenCM2(x));
    }

    //AwaitAlienSpawn
    public void alienPlacment() {
        setState(getState().alienPlacment());
    }

    //AwaitCrewPhase
    public void move() {
        setState(getState().move());
    }

    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sealRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void moveCM(Room r, int cm) {
        setState(getState().moveCM(r, cm));
    }
}

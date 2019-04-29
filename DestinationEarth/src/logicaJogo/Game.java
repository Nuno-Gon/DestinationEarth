package logicaJogo;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaEstados.*;
import logicaJogo.crewMembers.*;

public class Game implements Serializable {

    private static final String SAVE_PATH = "C:\\Users\\Psyk0\\Documents\\GitHub\\DestinationEarth\\DestinationEarth\\svs\\savefile.dat";
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
        setState(getState().attack());
    }

    public void noAP() {
        setState(getState().noAP());
    }

    public void gameOver() {
        setState(getState().gameOver());
    }

    public void fixHull() {
        setState(getState().fixHull());
    }

    public void heal() {
        setState(getState().heal());
    }

    public void trap(int trap, int room) {
        setState(getState().trap(trap, room));
    }

    public void seal(int x) {
        setState(getState().seal(x));
    }

    public void detonatePD(int x) {
        setState(getState().detonatePD(x));
    }

    //AwaitMove
    public void moveCM(Room r, int cm) {
        setState(getState().moveCM(r, cm));
    }

    //AwaitDieRoll
    public void attackAlien(CrewMember cm, int x) {
        setState(getState().attackAlien(cm, x));
    }

    public void noAlien() {
        setState(getState().noAlien());
    }

    //GameOver
    public void replay() {
        setState(getState().replay());
    }

    //AwaitAlienPhase
    public void alienPhase() {
        setState(getState().alienPhase());
    }

    //AwaitRestPhase
    public void rest() {
        setState(getState().rest());
    }

    //AwaitRestPhase
    public void noIP() {
        setState(getState().noIP());
    }

    public void addMovement(int cm) {
        setState(getState().addMovement(cm));
    }

    public void addAttackDie(int cmd) {
        setState(getState().addAttackDie(cmd));
    }

    public void addOneToAttackDie() {
        setState(getState().addOneToAttackDie());
    }

    public void saveGameToFile(Game obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);

            oos.writeObject(obj);
            oos.close();
            fileOut.close();
        } catch (IOException ex) {
        }
        System.out.println("Game Saved!");
    }

    public Object loadGameFromFile() {
        Game gg = null;
        try {

            FileInputStream fileIn = new FileInputStream(SAVE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            return ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

package iu.grafico;

import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import logicaEstados.*;
import logicaJogo.crewMembers.CM_ScienceOfficer;
import logicaJogo.crewMembers.CrewMember;

public class ObservableGame extends Observable {

    private Game game;
    private int move_cm;

    public ObservableGame() {
        game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;

        setChanged();
        notifyObservers();
    }

    public IStates getState() {
        return game.getState();
    }

    public void setState(IStates state) {
        this.game.setState(state);

        setChanged();
        notifyObservers();
    }

    public void updateObservers() {
        setChanged();
        notifyObservers();
    }

    public void resolveStart() {
        game.resolveStart();
        //game.setState(game.getState().start());
        setChanged();
        notifyObservers();
    }

    public void selectCM(int i) {
        game.selectCrewMember(i);
        setChanged();
        notifyObservers();
    }

    public void thirdTokenCM1(int i) {
        game.thirdTokenPlacementCM1(i);
        setChanged();
        notifyObservers();
    }

    public void thirdTokenCM2(int i) {
        game.thirdTokenPlacementCM2(i);
        setChanged();
        notifyObservers();
    }

    public int getLocationCM1() {
        return game.getGameData().getCrewMemberFirst().getCurrentRoom().getNum();
    }

    public int getLocationCM2() {
        return game.getGameData().getCrewMemberSecond().getCurrentRoom().getNum();
    }

    int getJourneyTackerTurn() {
        return game.getGameData().getTurn();
    }

    String[] getJourneyTacker() {
        return game.getGameData().getJourneyTracker();
    }

    void alienSpawn() {
        game.alienPlacment();
        setChanged();
        notifyObservers();
    }

    int getActionPoints() {
        return game.getGameData().getActionPoints();
    }

    int getCMNum(int n) {
        if (n == 1) {
            return game.getGameData().getCrewMemberFirst().getNum();
        } else {
            return game.getGameData().getCrewMemberSecond().getNum();
        }
    }

    CrewMember getCrewMemberFirst() {
        return game.getGameData().getCrewMemberFirst();
    }

    CrewMember getCrewMemberSecond() {
        return game.getGameData().getCrewMemberSecond();
    }

    int getTHealth() {
        return game.getGameData().getHealthTracker();
    }

    int getTHull() {
        return game.getGameData().getHullTracker();
    }

    int getTInspirationP() {
        return game.getGameData().getInspirationPoints();
    }

    public int getMove_cm() {
        return move_cm;
    }

    public void setMove_cm(int move_cm) {
        this.move_cm = move_cm;
        setChanged();
        notifyObservers();
    }

    public String getNameCM1() {
        return game.getGameData().getCrewMemberFirst().getName();
    }

    public String getNameCM2() {
        return game.getGameData().getCrewMemberSecond().getName();
    }

    public void moveCM(int i) {
        setMove_cm(i);

        game.move();
        setChanged();
        notifyObservers();
    }

    public String getNameMoving() {
        if (getMove_cm() == 1) {
            return getNameCM1();
        } else {
            return getNameCM2();
        }
    }

    public List<Room> getRooms() {
        return game.getGameData().getShipRoomList();
    }

    public Room getCM1_CurrentRoom() {
        return game.getGameData().getCrewMemberFirst().getCurrentRoom();
    }

    public Room getCM2_CurrentRoom() {
        return game.getGameData().getCrewMemberSecond().getCurrentRoom();
    }

    void moveCMRoom(Room r, int i) {
        game.moveCM(r, i);
        setChanged();
        notifyObservers();
    }

    void noAP() {
        if (game.getGameData().getActionPoints() == 0) {
            game.noAP();
        }
        setChanged();
        notifyObservers();
    }

    boolean getGameOver() {
        return (game.getGameData().getHealthTracker() == 0 || game.getGameData().getHullTracker() == 0);
    }

    void gameOver() {
        game.gameOver();
        setChanged();
        notifyObservers();
    }

    void alienPhase() {
        game.alienPhase();
        setChanged();
        notifyObservers();
    }

    void move(int x) {
        Room cr1, cr2;
        cr1 = getCM1_CurrentRoom();
        cr2 = getCM2_CurrentRoom();

        List<Room> roomList = game.getGameData().getShipRoomList();
        Room r = roomList.get(x);
        r.getExits().keySet().forEach((Integer i) -> {
            if (getMove_cm() == 1 && cr1 == r.getExits().get(i)) {
                moveCMRoom(r, 1);
            } else if (getMove_cm() == 2 && cr2 == r.getExits().get(i)) {
                moveCMRoom(r, 2);
            }
        });
    }

    void attack(int i) {
        CrewMember cr1 = getCrewMemberFirst();
        CrewMember cr2 = getCrewMemberSecond();

        //Special ScienceOfficer
        List<Room> roomList = game.getGameData().getShipRoomList();
        Room r = roomList.get(i);
        
        if(cr1 instanceof CM_ScienceOfficer || cr2 instanceof CM_ScienceOfficer ){
            r.getExits().keySet().forEach((Integer j) -> {
                if (cr1.getCurrentRoom() == r.getExits().get(i)) {
                    game.attackAlien(cr1, i);
                } else if (cr2.getCurrentRoom() == r.getExits().get(i)) {
                    game.attackAlien(cr2, i);
                }
            });
        }
        
        //CM1
        if (cr1.getCurrentRoom().getAliens() != 0) {
            if (cr1.getCurrentRoom().getNum() == i) {
                game.attackAlien(cr1, i);
            }
        }
        //CM2
        if (cr2.getCurrentRoom().getAliens() != 0) {
            if (cr2.getCurrentRoom().getNum() == i) {
                game.attackAlien(cr2, i);
            }
        }
    }
}

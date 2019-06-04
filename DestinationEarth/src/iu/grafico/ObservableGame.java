package iu.grafico;

import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import logicaEstados.*;

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

    Object getCrewMemberFirst() {
        return game.getGameData().getCrewMemberFirst();
    }

    Object getCrewMemberSecond() {
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
}

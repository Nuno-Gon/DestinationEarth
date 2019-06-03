package iu.grafico;

import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import logicaEstados.*;

public class ObservableGame extends Observable {

    private Game game;

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
}

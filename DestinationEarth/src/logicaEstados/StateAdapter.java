package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.*;

public class StateAdapter implements IStates {

    GameData gameData;

    public StateAdapter(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public IStates start() {
        return this;
    }

    @Override
    public IStates sealRoom() {
        return this;
    }

    @Override
    public IStates selectCM(int x) {
        return this;
    }

    @Override
    public IStates thirdTokenCM1(int x) {
        return this;
    }

    @Override
    public IStates thirdTokenCM2(int x) {
        return this;
    }

    @Override
    public IStates moveJourneyTacker() {
        return this;
    }

    @Override
    public IStates alienPlacment() {
        return this;
    }

    @Override
    public IStates attack() {
        return this;
    }

    @Override
    public IStates move() {
        return this;
    }

    @Override
    public IStates moveCM(Room r, int cm) {
        return this;
    }

    @Override
    public IStates noAP() {
        return this;
    }

    @Override
    public IStates seal(int x) {
        return this;
    }

    @Override
    public IStates trap(int trap, int room) {
        return this;
    }

    @Override
    public IStates detonatePD(int x) {
        return this;
    }

    @Override
    public IStates attackAlien(CrewMember cm, int x) {
        return this;
    }

    @Override
    public IStates noAlien() {
        return this;
    }

    @Override
    public IStates gameOver() {
        return this;
    }

    @Override
    public IStates replay() {
        return this;
    }

    @Override
    public IStates fixHull() {
        return this;
    }

    @Override
    public IStates heal() {
        return this;
    }

    @Override
    public IStates healSpecial() {
        return this;
    }

    @Override
    public IStates alienPhase() {
        return this;
    }

    @Override
    public IStates rest() {
        return this;
    }

    @Override
    public IStates noIP() {
        return this;
    }

    @Override
    public IStates addMovement(int cm) {
        return this;
    }

    @Override
    public IStates addAttackDie(int cmd) {
        return this;
    }

    @Override
    public IStates addOneToAttackDie() {
        return this;
    }
}

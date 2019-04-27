package logicaEstados;

import logicaJogo.GameData;

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
}

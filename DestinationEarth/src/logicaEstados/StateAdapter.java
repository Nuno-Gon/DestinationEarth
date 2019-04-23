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
}

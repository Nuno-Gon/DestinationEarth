package logicaEstados;

import logicaJogo.*;

public class AwaitRestPhase extends StateAdapter {

    public AwaitRestPhase(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates start() {
        return new AwaitCrewMembersSelection(gameData);
    }
}

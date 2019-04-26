package logicaEstados;

import logicaJogo.*;

public class AwaitBeginning extends StateAdapter {

    public AwaitBeginning(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates start() {
        return new AwaitCrewMembersSelection(gameData);
    }
}

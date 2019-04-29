package logicaEstados;

import logicaJogo.*;

public class Victory extends StateAdapter {

    public Victory(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates replay() {
        return new AwaitBeginning(new GameData());
    }
}

package logicaEstados;

import logicaJogo.*;

public class GameOver extends StateAdapter {

    public GameOver(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates replay() {
        return new AwaitBeginning(gameData);
    }
}

package logicaEstados;

import logicaJogo.*;

public class AwaitAlienPhase extends StateAdapter {

    public AwaitAlienPhase(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates start() {
        return new GameOver(gameData);
    }
}

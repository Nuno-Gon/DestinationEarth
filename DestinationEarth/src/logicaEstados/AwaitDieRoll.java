package logicaEstados;

import logicaJogo.*;

public class AwaitDieRoll extends StateAdapter {

    public AwaitDieRoll(GameData gameData) {
        super(gameData);
        gameData.setActionPoints(5);
    }

    @Override
    public IStates attack() {

        //gameData.setActionPoints(0);
        return new AwaitCrewPhase(gameData);
    }
}

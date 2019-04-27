package logicaEstados;

import logicaJogo.*;

public class AwaitDieRoll extends StateAdapter {

    public AwaitDieRoll(GameData gameData) {
        super(gameData);
        gameData.setActionPoints(5);
    }

    @Override
    public IStates start() {

        //gameData.setActionPoints(0);
        return new AwaitCrewMembersSelection(gameData);
    }
}

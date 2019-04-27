package logicaEstados;

import logicaJogo.*;

public class AwaitCrewPhase extends StateAdapter {

    public AwaitCrewPhase(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates attack() {

        //gameData.setActionPoints(0);
        return new AwaitDieRoll(gameData);
    }

    @Override
    public IStates move() {
        //gameData.setActionPoints(0);
        return new AwaitMove(gameData);
    }

}

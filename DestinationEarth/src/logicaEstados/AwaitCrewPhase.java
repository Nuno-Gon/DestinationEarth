package logicaEstados;

import logicaJogo.*;

public class AwaitCrewPhase extends StateAdapter {

    public AwaitCrewPhase(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates attack() {
        return new AwaitDieRoll(gameData);
    }

    @Override
    public IStates move() {
        return new AwaitMove(gameData);
    }

    @Override
    public IStates trap() {
        return new AwaitMove(gameData);
    }

    @Override
    public IStates seal() {
        return new AwaitMove(gameData);
    }

    @Override
    public IStates noAP() {
        gameData.setActionPoints(5);
        return new AwaitAlienPhase(gameData);
    }

}

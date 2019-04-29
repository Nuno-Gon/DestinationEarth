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
    public IStates noAP() {
        if (gameData.getHealthTracker() == 0 || gameData.getHullTracker() == 0) {
            return new GameOver(gameData);
        } else {
            gameData.setActionPoints(5);
            return new AwaitAlienPhase(gameData);
        }
    }

    @Override
    public IStates gameOver() {
        return new GameOver(gameData);
    }

    @Override
    public IStates fixHull() {
        gameData.setHullTracker(gameData.getHullTracker() + 1);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates heal() {
        gameData.setHealthTracker(gameData.getHealthTracker() + 1);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates trap(int trap, int room) { //trap 1-OrganicDetonator, 2-ParticleDisperser
        switch (trap) {
            case 1:
                gameData.getIndexShipRoomList(room).setTrapOrganicDetonator(true);
                break;
            case 2:
                gameData.getIndexShipRoomList(room).setTrapParticleDisperser(true);
                break;
            default:
                return new AwaitCrewPhase(gameData);
        }
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates seal(int x) {
        gameData.getIndexShipRoomList(x).setSealed(true);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates detonatePD(int x) {//x- room to detonate Particle Disperser
        //game over if player in the room
        if (gameData.getCrewMemberFirst().getCurrentRoom().getNum() == x || gameData.getCrewMemberSecond().getCurrentRoom().getNum() == x) {
            return new GameOver(gameData);
        } else {
            gameData.getIndexShipRoomList(x).setTrapParticleDisperser(false);
            gameData.getIndexShipRoomList(x).setAliens(0);
            gameData.setActionPoints(gameData.getActionPoints() - 1);
            return new AwaitCrewPhase(gameData);
        }
    }
}

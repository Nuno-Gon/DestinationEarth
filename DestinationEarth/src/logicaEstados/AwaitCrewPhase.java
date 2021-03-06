package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.CM_Commander;

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
        if (gameData.getActionPoints() == 0) {
            gameData.setActionPoints(5);
            noAP();
        }
        return new AwaitMove(gameData);
    }

    @Override
    public IStates noAP() {

        if (gameData.getHealthTracker() == 0 || gameData.getHullTracker() == 0) {
            return new GameOver(gameData);
        } else {
            if (gameData.getCrewMemberFirst() instanceof CM_Commander
                    || gameData.getCrewMemberFirst() instanceof CM_Commander) {
                gameData.setActionPoints(6);
                return new AwaitAlienPhase(gameData);
            }
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
        if (gameData.getActionPoints() == 0) {
            return new AwaitAlienPhase(gameData);
        }
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates heal() {
        gameData.setHealthTracker(gameData.getHealthTracker() + 1);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        if (gameData.getActionPoints() == 0) {
            return new AwaitAlienPhase(gameData);
        }
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
        if (gameData.getActionPoints() == 0) {
            noAP();
        }
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates seal(int x) {
        gameData.getIndexShipRoomList(x).setSealed(true);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        if (gameData.getActionPoints() == 0) {
            noAP();
        }
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates sealRoom() {
        return new AwaitSeal(gameData);
    }

    @Override
    public IStates detonatePD(int x) {//x- room to detonate Particle Disperser
        //game over if player in the room
        if (gameData.getCrewMemberFirst().getCurrentRoom().getNum() == x || gameData.getCrewMemberSecond().getCurrentRoom().getNum() == x) {
            return new GameOver(gameData);
        } else {
            gameData.getIndexShipRoomList(x).setTrapParticleDisperser(false);
            gameData.setInspirationPoints(gameData.getIndexShipRoomList(x).getAliens());
            gameData.getIndexShipRoomList(x).setAliens(0);
            gameData.setActionPoints(gameData.getActionPoints() - 1);
            if (gameData.getActionPoints() == 0) {
                noAP();
            }
            return new AwaitCrewPhase(gameData);
        }
    }
}

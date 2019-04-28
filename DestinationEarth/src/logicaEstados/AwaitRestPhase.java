package logicaEstados;

import logicaJogo.*;

public class AwaitRestPhase extends StateAdapter {

    public AwaitRestPhase(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates rest() {
        return this;
    }

    @Override
    public IStates trap(int trap, int room) { //trap 1-OrganicDetonator, 2-ParticleDisperser
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 2);
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
    public IStates heal() {
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 1);
        gameData.setHealthTracker(gameData.getHealthTracker() + 1);
        return this;
    }

    @Override
    public IStates fixHull() {
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 1);
        gameData.setHullTracker(gameData.getHullTracker() + 1);
        return this;
    }

    @Override
    public IStates addMovement(int cm) {
        if (cm == 1) {
            gameData.getCrewMemberFirst().setMovement(gameData.getCrewMemberFirst().getMovement() + 1);
        } else if (cm == 2) {
            gameData.getCrewMemberSecond().setMovement(gameData.getCrewMemberSecond().getMovement() + 1);
        }
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 4);
        return this;
    }

    @Override
    public IStates noIP() {
        return new AwaitAlienSpawn(gameData);
    }

    @Override
    public IStates move() {
        return new AwaitAlienSpawn(gameData);
    }

    @Override
    public IStates seal(int x) {
        gameData.getIndexShipRoomList(x).setSealed(true);
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 5);
        return this;
    }

    @Override
    public IStates addAttackDie(int cmd) {
        if (cmd == 1) {
            gameData.getCrewMemberFirst().setAttack(gameData.getCrewMemberFirst().getAttack() + 1);
        } else if (cmd == 2) {
            gameData.getCrewMemberSecond().setAttack(gameData.getCrewMemberSecond().getAttack() + 1);
        }
        gameData.setInspirationPoints(gameData.getInspirationPoints() - 6);
        return this;
    }

    @Override
    public IStates addOneToAttackDie() {
        gameData.setAddToAttackDie(gameData.getAddToAttackDie() + 1);
        return this;
    }
}

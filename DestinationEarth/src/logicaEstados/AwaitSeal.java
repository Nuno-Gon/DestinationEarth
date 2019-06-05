package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.CM_Commander;

public class AwaitSeal extends StateAdapter {

    public AwaitSeal(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates seal(int x) {
        gameData.getIndexShipRoomList(x - 1).setSealed(true);
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        if (gameData.getActionPoints() == 0) {
            return new AwaitAlienPhase(gameData);
        }
        return new AwaitCrewPhase(gameData);
    }
}

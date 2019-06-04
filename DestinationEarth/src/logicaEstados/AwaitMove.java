package logicaEstados;

import logicaJogo.*;

public class AwaitMove extends StateAdapter {

    public AwaitMove(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates moveCM(Room r, int cm) {//r- room moving to, cm- Crew Member
        if (cm == 1) {
            gameData.getCrewMemberFirst().setCurrentRoom(r);
        }
        if (cm == 2) {
            gameData.getCrewMemberSecond().setCurrentRoom(r);
        }
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        if (gameData.getActionPoints() == 0) {
            return new AwaitAlienPhase(gameData);
        }
        return new AwaitCrewPhase(gameData);
    }
}

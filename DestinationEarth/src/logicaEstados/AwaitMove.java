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
            System.out.println("hey");
        }
        if (cm == 2) {
            gameData.getCrewMemberSecond().setCurrentRoom(r);
            System.out.println("ho");
        }
        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }
}

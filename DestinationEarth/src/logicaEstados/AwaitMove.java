package logicaEstados;

import logicaJogo.*;

public class AwaitMove extends StateAdapter {

    int movements_cm1, movements_cm2;

    public AwaitMove(GameData gameData) {
        super(gameData);
        movements_cm1 = gameData.getCrewMemberFirst().getMovement();
        movements_cm2 = gameData.getCrewMemberSecond().getMovement();
    }

    @Override
    public IStates moveCM(Room r, int cm) {//r- room moving to, cm- Crew Member
        if (movements_cm1 != 0 && cm == 1) {
            movements_cm1--;
            gameData.getCrewMemberFirst().setCurrentRoom(r);
            if (movements_cm1 > 0) {
                return this;
            }
        }

        if (movements_cm2 != 0 && cm == 2) {
            movements_cm2--;
            gameData.getCrewMemberSecond().setCurrentRoom(r);
            if (movements_cm2 > 0) {
                return this;
            }
        }
        gameData.setActionPoints(gameData.getActionPoints() - 1);
//        for (int j = 0; j < gameData.getCrewMemberFirst().getMovement(); j++) {
//            System.out.println(j);
//            game.moveCM(1);

        if (gameData.getActionPoints() == 0) {
            return new AwaitAlienPhase(gameData);
        }
        return new AwaitCrewPhase(gameData);
    }
}

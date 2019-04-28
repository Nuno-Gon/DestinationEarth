package logicaEstados;

import logicaJogo.*;

public class AwaitThirdTokenPlacementCM2 extends StateAdapter {

    public AwaitThirdTokenPlacementCM2(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates thirdTokenCM2(int x) {
        if (x == 420) { //If random, roll 2 dice and set value on x
            gameData.setCurrentDice(gameData.rollDice() + gameData.rollDice());
            x = gameData.getCurrentDice();
        }
        gameData.getCrewMemberSecond().setCurrentRoom(selectRoom(x));
        gameData.setTurn(gameData.getTurn() + 1);
        if (gameData.getJourneyTrackerIndex(gameData.getTurn()).equals("R")) {
            return new AwaitRestPhase(gameData);
        } else {
            return new AwaitAlienSpawn(gameData);
        }
    }

    public Room selectRoom(int x) {
        return gameData.getIndexShipRoomList(x - 1);
    }
}

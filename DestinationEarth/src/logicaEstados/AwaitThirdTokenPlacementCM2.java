package logicaEstados;

import logicaJogo.*;

public class AwaitThirdTokenPlacementCM2 extends StateAdapter {

    public AwaitThirdTokenPlacementCM2(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates thirdTokenCM2(int x) {
        gameData.getCrewMemberFirst().setCurrentRoom(selectRoom(x));
        return new AwaitBeginning(gameData);
    }

    public Room selectRoom(int x) {
        return gameData.getIndexShipRoomList(x - 1);
    }
}

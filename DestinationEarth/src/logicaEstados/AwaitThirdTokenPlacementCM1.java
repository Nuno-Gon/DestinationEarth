package logicaEstados;

import logicaJogo.*;

public class AwaitThirdTokenPlacementCM1 extends StateAdapter {

    public AwaitThirdTokenPlacementCM1(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates thirdTokenCM1(int x) {
        gameData.getCrewMemberFirst().setCurrentRoom(selectRoom(x));
        return new AwaitThirdTokenPlacementCM2(gameData);
    }

    public Room selectRoom(int x) {
        return gameData.getIndexShipRoomList(x - 1);
    }
}

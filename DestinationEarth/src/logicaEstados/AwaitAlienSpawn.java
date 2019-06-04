package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.*;

public class AwaitAlienSpawn extends StateAdapter {

    public AwaitAlienSpawn(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates alienPlacment() {
        alienSpawn();
        String ss = gameData.getJourneyTrackerIndex(gameData.getTurn());
        /*if (!ss.equals("R")) {
            return new AwaitRestPhase(gameData);
        }*/
        
        if(gameData.getCrewMemberFirst()instanceof CM_Commander ||
                     gameData.getCrewMemberFirst()instanceof CM_Commander){
                gameData.setActionPoints(6);
                return new AwaitCrewPhase(gameData);
            }
        gameData.setActionPoints(5);
        return new AwaitCrewPhase(gameData);
    }

    public void alienSpawn() {
        //Get string(turn) from the Journey Tracker index
        //Get the first 0,1 Char and transform in Int (num aliens to spawn)
        String ss = gameData.getJourneyTrackerIndex(gameData.getTurn());
        if (!ss.equals("R")) {
            String k = ss.substring(0, 1);
            int a = Integer.parseInt(k);

            //roll 2D6 for each alien and place them in the room
            for (int i = 0; i < a; i++) {
                int die = gameData.rollDice() + gameData.rollDice();
                gameData.getIndexShipRoomList(die - 1).setAliens(gameData.getIndexShipRoomList(die - 1).getAliens() + 1);
            }
        }

    }
}

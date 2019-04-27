package logicaEstados;

import logicaJogo.*;

public class AwaitAlienSpawn extends StateAdapter {

    public AwaitAlienSpawn(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates alienPlacment() {
        alienSpawn();
        return new AwaitCrewPhase(gameData);
    }

    public void alienSpawn() {
        //gameData.setCurrentDice(gameData.rollDice() + gameData.rollDice());
        String ss = gameData.getJourneyTrackerIndex(gameData.getTurn());
        String k = ss.substring(0, 1);
        int i = Integer.parseInt(k);
        System.out.println("kek: " + i);

        //for para rodar o "k" vezes e spawnar os aliens
    }
}

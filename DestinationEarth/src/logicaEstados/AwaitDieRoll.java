package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.*;

public class AwaitDieRoll extends StateAdapter {

    public AwaitDieRoll(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates attackAlien(CrewMember cm, int x) {
        int dice = 0;
        //cm->Crew Member que vai atacar, x->se for 420 é random senao é o valor que foi introduzido
        if (x == 420) { //If random, roll attack dice and set value on dice
            for (int i = 0; i < cm.getAttack(); i++) {
                dice += gameData.rollDice();
            }
            gameData.setCurrentDice(dice);
        } else {
            gameData.setCurrentDice(x); //Só para a msg aparecer bem no debug do UI
        }
        x = gameData.getCurrentDice() + gameData.getAddToAttackDie(); //Upgrade add to dice
        //Kill alien if 5 or higher
        System.out.println("x:" + x + "cur:" + gameData.getCurrentDice());
        if (x >= 5) {
            System.out.println("entrou x");
            if (cm.getCurrentRoom().getAliens() != 0) {
                System.out.println("entrou y");
                cm.getCurrentRoom().setAliens(cm.getCurrentRoom().getAliens() - 1);
                gameData.setInspirationPoints(gameData.getInspirationPoints() + 1);
            }
        }

        gameData.setActionPoints(gameData.getActionPoints() - 1);
        return new AwaitCrewPhase(gameData);
    }

    @Override
    public IStates noAlien() {
        return new AwaitCrewPhase(gameData);
    }
}

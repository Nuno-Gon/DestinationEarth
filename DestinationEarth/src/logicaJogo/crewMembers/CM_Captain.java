package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Captain extends CrewMember {
    GameData g;
    
    public CM_Captain(int n,GameData gameData) {
        super(n);
        g = gameData;
        infoSpecial = "\nCan attack an alien on a 3+.\n";
        name = "Captain";
        //g.setAddToAttackDie(gameData.getAddToAttackDie()+ 3);
        special(g);
    }

    @Override
    public void special(GameData gameData) {
        gameData.setAddToAttackDie(gameData.getAddToAttackDie() + 3); //special
    }
}

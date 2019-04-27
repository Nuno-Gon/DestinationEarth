package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_Engineer extends CrewMember {

    public CM_Engineer() {
        super();
        infoSpecial = "\nCan fix 2 Hull for 1 IP when resting.\nCan fix 1 Hull for 1 AP.\nCan fix 1 Hull per round for free if in Engineering.\n";
        name = "Engineer";
    }

    @Override
    public void special(GameData gameData) {

    }
}

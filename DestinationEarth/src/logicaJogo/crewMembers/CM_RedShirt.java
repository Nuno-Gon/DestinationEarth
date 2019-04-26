package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_RedShirt extends CrewMember {

    public CM_RedShirt() {
        super();
        infoSpecial = "\nCan be sacrificed to gain 5 health at any time. If you do play on with only the other single crew member.\n";
    }

    @Override
    public void special(GameData gameData) {

    }
}

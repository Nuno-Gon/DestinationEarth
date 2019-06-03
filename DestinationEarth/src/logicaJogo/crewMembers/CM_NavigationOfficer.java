package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_NavigationOfficer extends CrewMember {

    public CM_NavigationOfficer(int n) {
        super(n);
        infoSpecial = "\nCan move 2 rooms for 1 Action Point.\n";
        name = "Navigation Officer";
    }

    @Override
    public void special(GameData gameData) {

    }
}

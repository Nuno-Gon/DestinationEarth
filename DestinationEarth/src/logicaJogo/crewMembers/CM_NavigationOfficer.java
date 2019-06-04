package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_NavigationOfficer extends CrewMember {

    public CM_NavigationOfficer(int n) {
        super(n);
        infoSpecial = "\nCan move 2 rooms for 1 Action Point.\n";
        name = "Navigation Officer";
        this.movement = 2;
    }

    @Override
    public void special(GameData gameData) {

    }
}

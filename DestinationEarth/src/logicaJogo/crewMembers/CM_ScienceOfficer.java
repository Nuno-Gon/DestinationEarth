package logicaJogo.crewMembers;

import logicaJogo.GameData;

public class CM_ScienceOfficer extends CrewMember {

    public CM_ScienceOfficer() {
        super();
        infoSpecial = "\nCan attack aliens in any adjacent room as long as there is an open door from this crew member to the targeted alien.\n";
    }

    @Override
    public void special(GameData gameData) {

    }
}

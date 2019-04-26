package logicaEstados;

import logicaJogo.*;
import logicaJogo.crewMembers.*;

public class AwaitCrewMembersSelection extends StateAdapter {

    public AwaitCrewMembersSelection(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates selectCM(int x) {
        if (gameData.getCrewMemberFirst() == null) {
            //chose first cm and transition to pick second
            gameData.setCrewMemberFirst(pickCrewMember(x));
            return new AwaitCrewMembersSelection(gameData);
        } else {
            //chose second cm and transition to next state
            gameData.setCrewMemberSecond(pickCrewMember(x));
            return new AwaitThirdTokenPlacementCM1(gameData);
        }
    }

    public CrewMember pickCrewMember(int x) {
        if (x == 0) {
            x = (int) (Math.random() * 9) + 1;
        }
        switch (x) {
            case 1:
                return new CM_Doctor();
            case 2:
                return new CM_Captain();
            case 3:
                return new CM_Engineer();
            case 4:
                return new CM_Commander();
            case 5:
                return new CM_RedShirt();
            case 6:
                return new CM_MoralOfficer();
            case 7:
                return new CM_CommsOfficer();
            case 8:
                return new CM_ScienceOfficer();
            case 9:
                return new CM_TransporterChief();
        }
        return null;
    }
}

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
            case 1://special done
                return new CM_Doctor(1);
            case 2:
                return new CM_Captain(2,gameData);
            case 3://special done
                return new CM_Engineer(3);
            case 4://special done
                return new CM_Commander(4);
            case 5:
                return new CM_RedShirt(5);
            case 6://special done
                return new CM_MoralOfficer(6,gameData);
            case 7:
                return new CM_CommsOfficer(7);
            case 8:
                return new CM_ScienceOfficer(8);
            case 9:
                return new CM_TransporterChief(9);
            case 10://special done
                return new CM_SecurityOfficer(10);
            case 11://special done
                return new CM_NavigationOfficer(11);
            case 12://special done
                return new CM_ShuttlePilot(12,gameData);
        }
        return null;
    }
}

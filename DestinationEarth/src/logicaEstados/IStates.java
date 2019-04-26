package logicaEstados;

public interface IStates {

    // AwaitBeginning
    public IStates start();

    //AwaitCrewMembersSelection
    public IStates selectCM(int x);

    //AwaitThirdTokenPlacementCM1
    public IStates thirdTokenCM1(int x);

    //AwaitThirdTokenPlacementCM2
    public IStates thirdTokenCM2(int x);
}

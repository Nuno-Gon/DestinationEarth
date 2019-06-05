package destinationearth;

import logicaJogo.*;
import iu.texto.*;
import iu.grafico.*;

public class DestinationEarth {

    public static void main(String[] args) {
//        TextUserInterface ui = new TextUserInterface(new Game());
//        ui.run();

        DestinationEarthView gui = new DestinationEarthView(new ObservableGame());
    }
}

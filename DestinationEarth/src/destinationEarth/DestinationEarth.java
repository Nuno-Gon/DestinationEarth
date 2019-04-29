package destinationearth;

import logicaJogo.*;
import iu.texto.*;

public class DestinationEarth {

    public static void main(String[] args) {
        TextUserInterface ui = new TextUserInterface(new Game());
        ui.run();
    }
}

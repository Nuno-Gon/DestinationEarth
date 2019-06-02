package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class PlayerBoardPanel extends JPanel implements Observer {

    ObservableGame game;
    //Image
    private final String imageFiles[] = {"imagens/cDoctor.png", "imagens/cCaptain.png", "imagens/cEngineer.png", "imagens/cCommander.png", "imagens/cRedShirt.png", "imagens/cMoralOfficer.png", "imagens/cCommsOfficer.png", "imagens/cScienceOfficer.png", "imagens/cTransporterChief.png", "imagens/cSecurityOfficer.png", "imagens/cNavigationOfficer.png", "imagens/cShuttlePilot.png"};
    private final Image[] imagem = new Image[imageFiles.length];
    boolean loaded = false;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    PlayerBoardPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    @Override
    public void update(Observable o, Object arg) {
        //setVisible(game.getState() instanceof AwaitBeginning);
        setVisible(true);
        revalidate();
        repaint();
    }

    Image getImagem(int i) {

        if (!loaded) {
            int j = 0;
            loaded = true;
            for (String fileName : imageFiles) {
                try {
                    imagem[j] = ImageIO.read(Resources.getResourceFile(fileName));
                    j++;
                } catch (IOException ex) {
                }
            }
        }

        return imagem[i];
    }
}

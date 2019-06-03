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
    //Crew Member panels right side
    JLabel cmIMG_1, cmIMG_2;
    int cmNum_1, cmNum_2;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    PlayerBoardPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);
        setupComponents();
        setupLayout();

    }

    private void setupComponents() {
        cmIMG_1 = new JLabel();
        cmIMG_2 = new JLabel();
//        cmNum_1 = game.getGame().getGameData().getCrewMemberFirst().getNum();
//        cmNum_2 = game.getGame().getGameData().getCrewMemberSecond().getNum();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(cmIMG_1);
        add(cmIMG_2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        cmIMG_1.setIcon(new ImageIcon(getImagem(1)));
        cmIMG_2.setIcon(new ImageIcon(getImagem(2)));
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

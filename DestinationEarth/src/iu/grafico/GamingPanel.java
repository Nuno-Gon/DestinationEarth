package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class GamingPanel extends JPanel implements Observer {

    ObservableGame game;
    String[] jt;
    int jtTurn;
    JPanel jtP, infoP;
    JLabel x, infoL;

    @SuppressWarnings({"LeakingThisInConstructor", "OverridableMethodCallInConstructor"})
    GamingPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        validate();
    }

    private void setupComponents() {
        jtP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jtP.setPreferredSize(new Dimension(250, 100));
        jt = game.getJourneyTacker();
        infoP = new JPanel();
        infoP.setLayout(new BoxLayout(infoP, BoxLayout.PAGE_AXIS));
        infoP.setPreferredSize(new Dimension(200, 30));
        infoP.setOpaque(false);
        infoL = new JLabel();
        infoL.setForeground(Color.YELLOW);
        infoL.setFont(new Font("Arial Black", Font.PLAIN, 20));
        infoL.setOpaque(false);

    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        //Journy tracker, FlowLayout com labels
        x = new JLabel("Journey Tracker: ");
        x.setOpaque(false);
        x.setFont(new Font("Arial Black", Font.PLAIN, 16));
        x.setForeground(Color.red);
        jtP.add(x);

        for (int i = 0; jt.length > i; i++) {
            x = new JLabel(jt[i]);
            x.setOpaque(false);
            x.setFont(new Font("Arial Black", Font.PLAIN, 14));
            x.setForeground(Color.white);
            jtP.add(x);
        }
        jtP.setOpaque(false);
        setOpaque(false);
        add(jtP, BorderLayout.NORTH);

        //Dialogue box
        infoP.add(infoL);
        add(infoP, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            jtTurn = game.getJourneyTackerTurn();
            Component a = jtP.getComponent(jtTurn + 1);
            a.setForeground(Color.red);
            a.repaint();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Painting Journey Tracker out of bounds 15+");
        }
        if (game.getState() instanceof AwaitThirdTokenPlacementCM1) {
            infoL.setText("Click the Room to place the #1 Crew Member!");
        } else if (game.getState() instanceof AwaitThirdTokenPlacementCM2) {
            infoL.setText("Click the Room to place the #2 Crew Member!");
        } else if (game.getState() instanceof AwaitCrewPhase) {
            infoL.setText("It's your turn, choose your actions wisely! " + game.getActionPoints() + " AP remaning!");
        } else {
            infoL.setText("Continue...");
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(true);
        revalidate();
        repaint();
    }

}

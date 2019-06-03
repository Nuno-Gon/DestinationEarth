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
    int healthTracker, hullTracker, ipTracker;
    JProgressBar healthBar, hullBar;
    JPanel jtP, infoP, gameOptionsP, statsP;
    JLabel x, infoL, healthLabel, hullLabel = new JLabel("Hull: "), ipLabel, ipLTracker;
    GridBagConstraints c;

    @SuppressWarnings({"LeakingThisInConstructor", "OverridableMethodCallInConstructor"})
    GamingPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        validate();
    }

    private void setupComponents() {
        setOpaque(false);
        //Journey Tracker Panel NORTH
        jtP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jtP.setPreferredSize(new Dimension(100, 30));
        jtP.setOpaque(false);
        jt = game.getJourneyTacker();
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

        //Dialogue SOUTH
        infoP = new JPanel();
        infoP.setLayout(new BoxLayout(infoP, BoxLayout.PAGE_AXIS));
        infoP.setPreferredSize(new Dimension(200, 30));
        infoP.setOpaque(false);
        infoL = new JLabel();
        infoL.setForeground(Color.YELLOW);
        infoL.setFont(new Font("Arial Black", Font.PLAIN, 20));
        infoL.setOpaque(false);

        //CENTER PANEL
        gameOptionsP = new JPanel(new BorderLayout());
        gameOptionsP.setOpaque(false);

        //Stats CENTER.NORTH
        statsP = new JPanel();
        statsP.setLayout(new GridBagLayout());
        statsP.setOpaque(false);

        healthBar = new JProgressBar(0, 12);
        healthBar.setPreferredSize(new Dimension(350, 20));
        healthBar.setBackground(Color.red);
        healthBar.setForeground(Color.green);
        hullBar = new JProgressBar(0, 12);
        hullBar.setPreferredSize(new Dimension(350, 20));
        hullBar.setBackground(Color.red);
        hullBar.setForeground(Color.green);

        healthLabel = new JLabel("Health: ");
        healthLabel.setOpaque(false);
        healthLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        healthLabel.setForeground(Color.LIGHT_GRAY);

        hullLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        hullLabel.setForeground(Color.LIGHT_GRAY);
        hullLabel.setOpaque(false);

        ipLabel = new JLabel("Inspiration Points: ");
        ipLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        ipLabel.setForeground(Color.ORANGE);
        ipLabel.setOpaque(false);

        ipLTracker = new JLabel("Inspiration Points: ");
        ipLTracker.setFont(new Font("Arial Black", Font.PLAIN, 16));
        ipLTracker.setForeground(Color.LIGHT_GRAY);
        ipLTracker.setOpaque(false);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(healthBar, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullLabel, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 25;
        c.anchor = GridBagConstraints.WEST;
        statsP.add(hullBar, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLabel.setText(" " + ipTracker);
        statsP.add(ipLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        ipLTracker.setText("Inspiration Points:");
        statsP.add(ipLTracker, c);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        //Journy tracker, FlowLayout com labels
        add(jtP, BorderLayout.NORTH);

        //Dialogue box
        infoP.add(infoL);
        add(infoP, BorderLayout.SOUTH);

        //Stats Panel ( HP, Hull, Inspiration Ponints)
        gameOptionsP.add(statsP, BorderLayout.NORTH);

        //Game Options Panel
        add(gameOptionsP, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        healthTracker = game.getTHealth();
        hullTracker = game.getTHull();
        ipTracker = game.getTInspirationP();
        healthBar.setValue(healthTracker);
        hullBar.setValue(hullTracker);
        try {
            Component a = jtP.getComponent(jtTurn + 1);
            a.setForeground(Color.red);
            a.repaint();
            for (int i = 2; jt.length > i; i++) {
                a = jtP.getComponent(jtTurn);
                a.setForeground(Color.white);
                a.repaint();
            }
            jtTurn = game.getJourneyTackerTurn();
            a = jtP.getComponent(0);
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
            ipLabel.setText(" " + ipTracker);
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

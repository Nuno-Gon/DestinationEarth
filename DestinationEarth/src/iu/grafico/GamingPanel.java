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
    JPanel jtP;
    JLabel x;

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
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        x = new JLabel("Journey Tracker: ");
        x.setOpaque(false);
        x.setFont(new Font("Arial Black", Font.PLAIN, 13));
        x.setForeground(Color.red);
        jtP.add(x);

        for (int i = 0; jt.length > i; i++) {
            x = new JLabel(jt[i]);
            x.setOpaque(false);
            x.setFont(new Font("Arial Black", Font.PLAIN, 12));
            x.setForeground(Color.white);
            jtP.add(x);
        }
        jtP.setOpaque(false);
        setOpaque(false);
        add(jtP, BorderLayout.NORTH);
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
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(true);
        revalidate();
        repaint();
    }

}

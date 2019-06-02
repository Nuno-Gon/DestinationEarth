package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class CMSelectionPanel extends JPanel implements Observer {

    ObservableGame game;
    //Images
    private final String imageFiles[] = {"imagens/cDoctor.png", "imagens/cCaptain.png", "imagens/cEngineer.png", "imagens/cCommander.png", "imagens/cRedShirt.png", "imagens/cMoralOfficer.png", "imagens/cCommsOfficer.png", "imagens/cScienceOfficer.png", "imagens/cTransporterChief.png", "imagens/cSecurityOfficer.png", "imagens/cNavigationOfficer.png", "imagens/cShuttlePilot.png"};
    private final Image[] imagem = new Image[imageFiles.length];
    boolean loaded = false;
    //Buttons
    JButton cm1, cm2, cm3, cm4, cm5, cm6, cm7, cm8, cm9, cm10, cm11, cm12;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    public CMSelectionPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);
        setupLayout();
        setupComponents();

        validate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        cm1.setIcon(new ImageIcon(getImagem(0)));
        cm2.setIcon(new ImageIcon(getImagem(1)));
        cm3.setIcon(new ImageIcon(getImagem(2)));
        cm4.setIcon(new ImageIcon(getImagem(3)));
        cm5.setIcon(new ImageIcon(getImagem(4)));
        cm6.setIcon(new ImageIcon(getImagem(5)));
        cm7.setIcon(new ImageIcon(getImagem(6)));
        cm8.setIcon(new ImageIcon(getImagem(7)));
        cm9.setIcon(new ImageIcon(getImagem(8)));
        cm10.setIcon(new ImageIcon(getImagem(9)));
        cm11.setIcon(new ImageIcon(getImagem(10)));
        cm12.setIcon(new ImageIcon(getImagem(11)));
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(game.getState() instanceof AwaitCrewMembersSelection);
        revalidate();
        repaint();
    }

    @SuppressWarnings("Convert2Lambda")
    private void setupComponents() {
        cm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm1);
                game.selectCM(1);
            }
        });
        cm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm2);
                game.selectCM(2);
            }
        });
        cm3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm3);
                game.selectCM(3);
            }
        });
        cm4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm4);
                game.selectCM(4);
            }
        });
        cm5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm5);
                game.selectCM(5);
            }
        });
        cm6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm6);
                game.selectCM(6);
            }
        });
        cm7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm7);
                game.selectCM(7);
            }
        });
        cm8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm8);
                game.selectCM(8);
            }
        });
        cm9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm9);
                game.selectCM(9);
            }
        });
        cm10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm10);
                game.selectCM(10);
            }
        });
        cm11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm11);
                game.selectCM(11);
            }
        });
        cm12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                remove(cm12);
                game.selectCM(12);
            }
        });
    }

    private void setupLayout() {
        setLayout(new GridLayout(2, 6, 5, 10));

        cm1 = new JButton();
        cm2 = new JButton();
        cm3 = new JButton();
        cm4 = new JButton();
        cm5 = new JButton();
        cm6 = new JButton();
        cm7 = new JButton();
        cm8 = new JButton();
        cm9 = new JButton();
        cm10 = new JButton();
        cm11 = new JButton();
        cm12 = new JButton();
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        add(cm1);
        add(cm2);
        add(cm3);
        add(cm4);
        add(cm5);
        add(cm6);
        add(cm7);
        add(cm8);
        add(cm9);
        add(cm10);
        add(cm11);
        add(cm12);
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

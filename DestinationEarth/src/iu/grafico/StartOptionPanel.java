package iu.grafico;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logicaJogo.*;
import javax.imageio.*;
import logicaEstados.*;

public class StartOptionPanel extends JPanel implements Observer {

    ObservableGame game;
    //Buttons
    JButton play = new JButton("Play");
    JButton load = new JButton("Load");
    JButton exit = new JButton("Exit");
    //Image
    private final String imgInicial = "imagens/pagIni2.png";
    Image bg;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    public StartOptionPanel(ObservableGame g) {
        game = g;
        game.addObserver(this);
        setupComponents();
        setupLayout();

        validate();
    }

    @SuppressWarnings("Convert2Lambda")
    private void setupComponents() {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                game.resolveStart();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.out.println("load"); //por implementar
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        load.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());

        add(play);
        add(Box.createVerticalStrut(20));

        add(load);
        add(Box.createVerticalStrut(20));

        add(exit);
        add(Box.createVerticalStrut(90));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            int w = size.width;
            int h = size.height;
            bg = ImageIO.read(Resources.getResourceFile(imgInicial));
            bg = bg.getScaledInstance(w, h - 10, 0);
        } catch (IOException ex) {

        }
        g.drawImage(bg, 0, 0, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(game.getState() instanceof AwaitBeginning);
        revalidate();
        repaint();
    }
}

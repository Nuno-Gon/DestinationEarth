package iu.grafico;

import com.sun.glass.events.KeyEvent;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class DestinationEarthView extends JFrame implements Observer {

    ObservableGame game;
    DestinationEarthPanel panel;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"}) //JFrame set's in consctructor
    public DestinationEarthView(ObservableGame observableGame) {
        super("Destination Earth");

        game = observableGame;
        game.addObserver(this);

        panel = new DestinationEarthPanel(game);

        addComponents();
        menu();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        //this.setLocation(250, 150);
        //this.setMinimumSize(new Dimension(862, 540));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        validate();
    }

    private void addComponents() {
        Container cp = getContentPane();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        cp.setSize(size);
        cp.setLayout(new BorderLayout());
        cp.add(panel, BorderLayout.CENTER);
    }

    @SuppressWarnings("Convert2Lambda")
    private void menu() {

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Jogo");

        JMenuItem newObjJMI = new JMenuItem("Parar");
        newObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem readObjJMI = new JMenuItem("Carregar");
        newObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        JMenuItem saveObjJMI = new JMenuItem("Gravar");
        newObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem exitObjJMI = new JMenuItem("Exit");
        newObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        exitObjJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DestinationEarthView.this, "Player left the game!", "Exit", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        });
        gameMenu.add(newObjJMI);
        gameMenu.add(readObjJMI);
        gameMenu.add(saveObjJMI);
        gameMenu.addSeparator();

        gameMenu.add(exitObjJMI);
        menuBar.add(gameMenu);

        JMenu helpMenu = new JMenu("Opções");

        JMenuItem helpContentJMI = new JMenuItem("Help");
        helpContentJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        JMenuItem aboutJMI = new JMenuItem("About");
        aboutJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        helpMenu.add(helpContentJMI);
        helpMenu.add(aboutJMI);
        menuBar.add(helpMenu);

        helpContentJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DestinationEarthView.this, "Carregue nas Opções disponiveis! Tente a nave até chegar a terra!", "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });
        aboutJMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DestinationEarthView.this, "Destination Earth Game", "About", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        revalidate();
        repaint();
    }
}

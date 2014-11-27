package Pong;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julian on 16.10.2014.
 */
public class Scoreboard extends JPanel {

    private int boardHight = 50;
    private int boardWidth = 200;

    private JLabel outLeft = new JLabel("0");
    private JLabel outRight = new JLabel("0");
    private JLabel space = new JLabel(":");
    private int scoreLeft = 0;
    private int scoreRight = 0;


    public Scoreboard() {
        setOpaque(false);
        setPreferredSize(new Dimension(boardWidth,boardHight));
        setLayout(new FlowLayout());
        outLeft.setFont(new Font("Verdana",1,30));
        outRight.setFont(new Font("Verdana",1,30));
        space.setFont(new Font("Verdana",1,30));

        add(outLeft);
        add(space);
        add(outRight);
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(205,149,12));
        g2d.fillRect(0,0,boardWidth,boardHight);
    }

    public void addPoints(int l, int r) {

        scoreLeft = scoreLeft+l;
        scoreRight = scoreRight+r;
        outLeft.setText(Integer.toString(scoreLeft));
        outRight.setText(Integer.toString(scoreRight));
    }

    public void resetScore() {
        scoreLeft = 0;
        scoreRight = 0;
        outLeft.setText(Integer.toString(scoreLeft));
        outRight.setText(Integer.toString(scoreRight));
    }
}

package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Julian on 19.11.2014.
 */
public class Startframe extends JFrame implements ActionListener{

    private JTextField tf = new JTextField();
    private JButton accept = new JButton("START");
    private JLabel text = new JLabel("Speed [1-7]   ");
    private Timer timer = new Timer(500 , this);

    public Startframe() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Geschwindigkeit wählen");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        tf.setPreferredSize(new Dimension(100,27));
        setSize(new Dimension(300, 80));

        text.setFont(new Font("Verdana",1,15));


        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(text);
        gbc.gridy = 1;
        panel.add(tf);
        gbc.gridx = 0;
        panel.add(accept);

        add(panel);

        setLocation(new Point( (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (this.getWidth()/2)), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (this.getHeight()/2))));
        this.setVisible(true);

        accept.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int i = Integer.parseInt(tf.getText());
                    if (i > 0 && i <8) {
                        new Field(i);
                        close();
                    } else {
                        tf.setBackground(Color.RED);
                        timer.start();
                    }
                } catch (NumberFormatException e) {
                    tf.setBackground(Color.RED);
                    timer.start();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent actionEvent) {
        timer.stop();
        tf.setBackground(Color.WHITE);
        tf.setText("");
    }

    private void close() {
        this.dispose();
    }
}


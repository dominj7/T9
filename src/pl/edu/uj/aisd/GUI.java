package pl.edu.uj.aisd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUI extends JFrame implements ActionListener {

    private final Dictionary dictionary;
    private final JButton[] buttons = new JButton[12];
    private final StringBuilder code = new StringBuilder();
    private String text = "";
    private String word = "";
    private int index = 0;
    private ArrayList<String> list;
    private final JTextArea screen = new JTextArea();

    public GUI(Dictionary dictionary) {
        this.dictionary = dictionary;
        // Screen
        screen.setBackground(new Color(240, 240, 240));
        screen.setBounds(50, 50, 400, 400);
        screen.setFont(new Font("Arial", Font.PLAIN, 20));
        screen.setEditable(false);
        screen.setLineWrap(true);

        // Creating buttons
        int x = 50;
        int y = 500;
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(x, y, 100, 75);                    // Button position (x,y) - top left
            buttons[i].setFont(new Font("Arial", Font.BOLD, 15));
            buttons[i].setBackground(new Color(96, 96, 96));            // Button color
            buttons[i].setForeground(Color.BLACK);                               // Text color
            buttons[i].setBorderPainted(true);
            buttons[i].addActionListener(this);
            if (i > 0 && i < 9) {
                buttons[i].setText(i + 1 + " " + Dictionary.getLetters(i - 1));
            }
            x += 150;
            if (i == 2 || i == 5 || i == 8) {
                x = 50;
                y += 125;
            }
        }
        buttons[0].setText("1");
        buttons[9].setText("*");
        buttons[10].setText("0  ͟");
        buttons[11].setText("#");

        // Rest
        this.setTitle("T9");
        this.setSize(500, 1085);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(32, 32, 32));

        this.add(screen);
        this.setLayout(null);
        for (JButton button: buttons) {
            this.add(button);
        }
    }


    // Actions
    public void actionPerformed(ActionEvent event) {

        for (int i = 1; i < 9; i++) {
            if (event.getSource() == buttons[i]) {
                buttons[9].setBackground(new Color(96, 96, 96));
                code.append(i + 1);
                list = dictionary.getWords(code.toString());
                if (!list.isEmpty()) {
                    word = list.get(0);
                } else {
                    word = "";
                }

                if (list.size() > 1) {
                    buttons[9].setBackground(new Color(146, 146, 146));
                }

                screen.setText(text + word);
                return;
            }
        }

        if (event.getSource() == buttons[9]) {  // *
            if (!list.isEmpty()) {
                index++;
                word = list.get(index % list.size());
                screen.setText(text + word);
            }
        } else if (event.getSource() == buttons[10]) {     // 0 _
            buttons[9].setBackground(new Color(96, 96, 96));
            text += word;
            text += " ";
            word = "";
            index = 0;
            code.delete(0, code.length());
        } else if (event.getSource() == buttons[11]) {     // #
            buttons[9].setBackground(new Color(96, 96, 96));
            code.delete(0, code.length());
            text = "";
            word = "";
            index = 0;
            screen.setText("");
        }

    }

}

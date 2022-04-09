package pl.edu.uj.aisd;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        String fileName = "dictionary.txt";
        if (args.length != 0) {
            fileName = args[0];
        }

        try {
            Dictionary dictionary = new Dictionary(fileName);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new GUI(dictionary);
                }
            });
        } catch (Exception ex) {
            System.out.println("error");
        }

    }
}

package pl.edu.uj.aisd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Dictionary {

    private final Map<Integer, String> keyboard = new HashMap<>();
    private final Map<String, ArrayList<String>> dictionary = new HashMap<>();
    private final static String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    private String translate(String word) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 2; j < 10; j++) {
                if (keyboard.get(j).indexOf(word.charAt(i)) != -1) {
                    result.append(j);
                    break;
                }
            }
        }
        return result.toString();
    }


    public Dictionary(String fileName) throws Exception {
        for (int i = 2; i < 10; i++) {
            keyboard.put(i, letters[i - 2]);
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String word;
        while ((word = reader.readLine()) != null) {
            String key = translate(word);
            if (!dictionary.containsKey(key)) {
                dictionary.put(key, new ArrayList<>());
            }
            dictionary.get(key).add(word);
        }
        reader.close();
    }


    public static String getLetters(int key) {
        return letters[key];
    }


    public ArrayList<String> getWords(String key) {
        if (dictionary.get(key) != null) {
            return dictionary.get(key);
        }
        return new ArrayList<>();
    }

}

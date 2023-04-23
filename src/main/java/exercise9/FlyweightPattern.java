package exercise9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Sentence {
    private String[] words;
    private Map<Integer, WordFormat> tokens = new HashMap<>();

    public Sentence(String plainText) {
        words = plainText.split(" ");
    }

    public WordFormat getWord(int index) {
        if (!tokens.containsKey(index)) {
            WordFormat wt = new WordFormat();
            tokens.put(index, wt);
        }
        return tokens.get(index);
    }

    @Override
    public String toString() {
        ArrayList<String> ws = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (tokens.containsKey(i) && tokens.get(i).capitalize)
                w = w.toUpperCase();
            if(tokens.containsKey(i) && tokens.get(i).withHash)
                w = "#"+w+"#";
            ws.add(w);
        }
        return String.join(" ", ws);
    }

    class WordFormat {
        public boolean capitalize;
        public boolean withHash;
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
//        var sentence = new Sentence("hello world");
//        sentence.getWord(1).capitalize = true;
//        System.out.println(sentence); // writes "hello WORLD"
//
//        sentence.getWord(1).withHash = true;
//        System.out.println(sentence);

        Sentence s = new Sentence("alpha beta gamma");
        s.getWord(1).capitalize = true;
        System.out.println("alpha BETA gamma = " + s);
    }
}

package languageObjects;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {
    private final List<Letter> letters;

    public Alphabet () {
        this.letters = new ArrayList<>();
    }

    public void addLetter (Letter letter) {
        if(!letters.contains(letter))
            letters.add(letter);
    }

    public Letter searchLetterByName (String sign) {
        for(Letter letter : letters) {
            if(letter.getSign().equals(sign))
                return letter;
        }
        return null;
    }

    public List<Letter> getLetters() {
        return letters;
    }
}

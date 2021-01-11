package languageObjects;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    private Alphabet alphabet;
    private final List<Letter> word;
    private final List<LetterPair> D;
    private final List<LetterPair> I;

    public Translator () {
        this.D = new ArrayList<>();
        this.I = new ArrayList<>();
        this.word = new ArrayList<>();
    }

    public void translate (List<String> input) {
        List<Character> specialSigns = new ArrayList<>();
        for(Character c : "():=+-_*/ 0123456789{},.".toCharArray())
            specialSigns.add(c);

        alphabet = new Alphabet();
        int breaks = 0;
        for(String line : input) {
            if(line.equals(""))
                breaks++;
            else {
                if(breaks == 0) {
                    Letter letter = new Letter();
                    int letterState = 0;
                    for(Character c : line.toCharArray()) {
                        if(!specialSigns.contains(c)) {
                            if(letterState == 2) {
                                letter.addModifier(c.toString());
                            } else if (letterState == 1) {
                                letter.setTarget(c.toString());
                                letterState++;
                            } else if (letterState == 0) {
                                letter.setSign(c.toString());
                                letterState++;
                            }
                        }
                    }
                    alphabet.addLetter(letter);
                } else if (breaks == 1) {
                    for(Character c : line.toCharArray()) {
                        if(!specialSigns.contains(c) && c != 'A') {
                            if(alphabet.searchLetterByName(c.toString()) == null) {
                                System.out.println("Input file incorrect, "+c+" letter not found");
                                System.exit(2137);
                            }
                        }
                    }
                    System.out.println("Alphabet translated correctly");
                } else if (breaks == 2) {
                    for(Character c : line.toCharArray()) {
                        if(!specialSigns.contains(c) && c != 'w')
                            word.add(alphabet.searchLetterByName(c.toString()));
                    }
                    System.out.print("Word = ");
                    for(Letter l : word)
                        System.out.print(l.getSign());

                    System.out.print("\n");
                }
            }
        }
    }

    public void calculateDependencies () {
        int i = 1;
        List<Letter> list = alphabet.getLetters();
        for(Letter l1 : list) {
            D.add(new LetterPair(l1,l1));
            for(int j=i; j<list.size(); j++) {
                Letter l2 = list.get(j);
                if(l1.isModifier(l2.getTarget()) || l2.isModifier(l1.getTarget())) {
                    D.add(new LetterPair(l1,l2));
                    D.add(new LetterPair(l2,l1));
                } else {
                    I.add(new LetterPair(l1,l2));
                    I.add(new LetterPair(l2,l1));
                }
            }
            i++;
        }
    }

    public boolean areDependent (String first, String second) {
        for(LetterPair pair : D) {
            if(pair.isPair(first,second))
                return true;
        }
        for(LetterPair pair : I) {
            if(pair.isPair(first,second))
                return false;
        }
        System.out.println("Pair not found");
        System.exit(2137);
        return true;
    }

    public List<Letter> getWord() {
        return word;
    }

    public String AtoString() {
        StringBuilder result = new StringBuilder("A = {");
        boolean first = true;
        for(Letter letter : alphabet.getLetters()) {
            if(first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(letter.getSign());
        }
        return result.append("}").toString();
    }
    public String DtoString() {
        return getString(new StringBuilder("D = {"), D);
    }
    public String ItoString() {
        return getString(new StringBuilder("I = {"), I);
    }
    private String getString(StringBuilder result, List<LetterPair> i) {
        boolean first = true;
        for(LetterPair pair : i) {
            if(first) {
                first = false;
            } else {
                result.append(", ");
            }
            result.append(pair.getString());
        }
        result.append("}");
        return result.toString();
    }
}

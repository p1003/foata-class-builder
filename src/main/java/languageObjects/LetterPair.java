package languageObjects;

public class LetterPair {
    private final Letter first;
    private final Letter second;

    public LetterPair (Letter first, Letter second) {
        this.first = first;
        this.second = second;
    }

    public boolean isPair (String first, String second) {
        return this.first.getSign().equals(first) && this.second.getSign().equals(second);
    }

    public String getString() {
        return "("+first.getSign()+","+second.getSign()+")";
    }
}

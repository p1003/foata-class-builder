package languageObjects;

import java.util.ArrayList;
import java.util.List;

public class Letter {
    private String sign;
    private String target;
    private final List<String> modifiers;

    public Letter () {
        modifiers = new ArrayList<>();
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public String getSign() {
        return sign;
    }
    public String getTarget() {
        return target;
    }

    public void addModifier (String modifier) {
        if(!modifiers.contains(modifier)) {
            modifiers.add(modifier);
        }
    }
    public boolean isModifier (String modifier) {
        return modifiers.contains(modifier);
    }
}

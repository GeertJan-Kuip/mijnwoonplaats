package com.geertjankuip.mijnwoonplaats.textcreation;

import java.util.List;

public class TextObject {

    private List<String> text;

    public TextObject(List<String> text) {
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}

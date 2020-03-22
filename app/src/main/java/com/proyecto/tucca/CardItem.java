package com.proyecto.tucca;

public class CardItem {
    private String textNumber, textStartFinish;

    public CardItem(String textNumber, String textStartFinish){
        this.textNumber = textNumber;
        this.textStartFinish = textStartFinish;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public String getTextStartFinish() {
        return textStartFinish;
    }
}

package com.proyecto.tucca.model;

public class CreditCard {
    private String cardUser;
    private String textNumber;

    public CreditCard(String cardUser, String textNumber){
        this.cardUser = cardUser;
        this.textNumber = textNumber;
    }

    public String getCardUser() {
        return cardUser;
    }

    public void setCardUser(String cardUser) {
        this.cardUser = cardUser;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }
}

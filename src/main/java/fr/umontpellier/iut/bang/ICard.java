package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.logic.cards.Card;
import javafx.scene.control.Button;

public class ICard {

    private Card card;
    private int x, y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ICard(Card card, int x, int y) {
        this.card = card;
        this.x = x;
        this.y = y;
    }

    public ICard(Card card) {
        this.card = card;
    }

    public String getName() {
        return card.getName();
    }

    public String getImageName() {
        return card.getImageName();
    }

    public Card getCard() {
        return card;
    }

    public Button createBtn(){
        Button btn = new Button();
        btn.setStyle("-fx-background-image: "+getImageName());
        return btn;
    }
}

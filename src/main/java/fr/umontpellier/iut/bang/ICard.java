package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.logic.cards.Card;
import javafx.scene.control.Button;

public class ICard {

    private Card card;

    public ICard(Card card, int x, int y) {
        this.card = card;
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

}

package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.scene.control.Label;

public class CardViewEssai extends CardView {

    public CardViewEssai(ICard card, PlayerArea playerArea){
        super(card, playerArea);
        Label nomCarte = new Label(card.getName());
        getChildren().add(nomCarte);

    }


    @Override
    public void setVisible() {


    }

    @Override
    public void setUnVisible() {

    }

    @Override
    protected void setCardSelectionListener() {

    }
}

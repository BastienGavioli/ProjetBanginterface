package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DrawnCardView extends CardViewEssai{
    public DrawnCardView(ICard card, PlayerArea playerArea) {
        super(card, playerArea);
        setCardSelectionListener();
    }

    @Override
    public void setVisible() {
        super.setVisible();
    }

    @Override
    public void setUnVisible() {
        super.setUnVisible();
    }

    @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    EventHandler<MouseEvent> whenCardSelected = mouseEvent -> {
        CardViewEssai selectedCardView = (CardViewEssai) mouseEvent.getSource();
        ICard selectedICard = selectedCardView.getICard();
        IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
        currentGame.onDrawnCardSelection(selectedICard);
        setVisible(false);

    };
}

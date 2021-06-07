package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class CardViewEssai extends CardView {
    private Boolean isVisible = false;
    private ImageView frontImage;
    private IPlayer player;
    private Label cardName;
    private HBox affichage;

    public CardViewEssai(ICard card, PlayerArea playerArea){
        super(card, playerArea);
        frontImage = new ImageView(new Image(card.getImageName()));
        player = playerArea.getIPlayer();
        cardName = new Label(getICard().getName());
        affichage = new HBox();
        setCardSelectionListener();
        affichage.getChildren().add(cardName);
        affichage.getChildren().add(frontImage);
        getChildren().add(affichage);

    }


    @Override
    public void setVisible() {
        if(!isVisible){
            isVisible = true;
        }
    }

    @Override
    public void setUnVisible() {
        if(isVisible){
            isVisible = false;
            }
        }


     @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    EventHandler<MouseEvent> whenCardSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            CardViewEssai selectedCardView = (CardViewEssai) mouseEvent.getSource();
            ICard selectedICard = selectedCardView.getICard();
            IPlayer owner = selectedCardView.getPlayerArea().getIPlayer();
            IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
            currentGame.onCardSelection(selectedICard,owner);
        }
    };


}


package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class CardViewEssai extends CardView {
    private Boolean isVisible;
    private ImageView frontImage, backImage, displayImage;

    private IPlayer player;
    private Label cardName;
    private VBox affichage;


    public CardViewEssai(ICard card, PlayerArea playerArea){
        super(card, playerArea);
        setCardSelectionListener();
        isVisible=true;


        frontImage = new ImageView(new Image(card.getImageName()));
        frontImage.setFitHeight(120);
        frontImage.setFitWidth(85);

        backImage = new ImageView(getBack());
        backImage.setFitHeight(120);
        backImage.setFitWidth(85);

        displayImage = frontImage;

        player = playerArea.getIPlayer();
        cardName = new Label(getICard().getName());
        cardName.setStyle("-fx-translate-y: -110px");

        affichage = new VBox();
        affichage.setAlignment(Pos.CENTER);
        affichage.getChildren().add(displayImage);
        affichage.getChildren().add(cardName);

        getChildren().add(affichage);

    }


    @Override
    public void setVisible() {
        affichage.getChildren().remove(displayImage);
        affichage.getChildren().remove(cardName);
        isVisible = !isVisible;
        displayImage = frontImage;
        affichage.getChildren().add(displayImage);
        affichage.getChildren().add(cardName);
    }

    @Override
    public void setUnVisible() {
        affichage.getChildren().remove(displayImage);
        affichage.getChildren().remove(cardName);
        isVisible = !isVisible;
        displayImage = backImage;
        affichage.getChildren().add(displayImage);
    }


     @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    EventHandler<MouseEvent> whenCardSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            //Mise à jour des variables
            CardViewEssai selectedCardView = (CardViewEssai) mouseEvent.getSource();
            ICard selectedICard = selectedCardView.getICard();
            IPlayer owner = selectedCardView.getPlayerArea().getIPlayer();
            IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();

            //Action !
            currentGame.onCardSelection(selectedICard,owner);
            //sons
            if(isVisible){
                URL url;
                if(selectedICard.getCard() instanceof WeaponCard)
                    url = getClass().getClassLoader().getResource("sounds/Arme.mp3");
                else
                    url = getClass().getClassLoader().getResource("sounds/"+selectedICard.getName()+".mp3");
                if(url!=null) {
                    Media media = new Media(url.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(getPlayerArea().getGameView().getIGame().getVolume());
                    mediaPlayer.play();
                }
                else{
                    System.out.println("sounds/"+selectedICard.getName()+".mp3");
                }
            }
        }
    };


}


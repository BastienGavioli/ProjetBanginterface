package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.Colt;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class OurPlayerArea extends PlayerArea {
    private VBox rootPlayer;
    private Label name;
    private ImageView img;
    private HBox handView;
    private HBox inPlay; //Stocke les cartes inPlay
    private VBox hp;




    public OurPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);

        setOnMouseClicked(whenPlayerSelected);

        rootPlayer = new VBox();
        rootPlayer.setId("rootPlayer");
        name = new Label(player.getName());
        name.setStyle("-fx-font-family: Algerian;" + "-fx-text-fill: #808000");
        rootPlayer.setAlignment(Pos.CENTER);
        img = new ImageView(getImageName());
        img.setFitHeight(200);
        img.setFitWidth(150);

        hp = new VBox();
        for(int i =0; i < super.getIPlayer().getHealthPoints();i++){
            ImageView hpFull = new ImageView("images/bullet.png");
            hpFull.setFitWidth(30);
            hpFull.setFitHeight(12);
            hp.getChildren().add(i, hpFull);
        }
        hp.setTranslateY(-350);


        handView = new HBox();




        inPlay = new HBox();
        inPlay.setMaxWidth(140);
        inPlay.setTranslateX(150);
        inPlay.setTranslateY(-155);
        inPlay.getChildren().add(0,new CardViewEssai(new ICard(new Colt()), OurPlayerArea.this));

        setInPlayListener(whenInPlayIsUpdated);
        setWeaponListener(whenWeaponChanges);
        setHealthPointsListener(whenHealthPointsIsUpdated);

        rootPlayer.getChildren().add(img);
        rootPlayer.getChildren().add(name);
        rootPlayer.getChildren().add(inPlay);
        rootPlayer.getChildren().add(hp);
        getChildren().add(rootPlayer);




    }

    private String getImageName(){
        if(!getPlayer().isDead())
            return("images/characters/" + super.getIPlayer().getBangCharacter().getName().toLowerCase().replaceAll("\\s+","")+".png" );
        else
            return("images/deadCharacters/" + super.getIPlayer().getBangCharacter().getName().toLowerCase().replaceAll("\\s+","")+".png" );
    }

    public void updateImg(){
        if(getPlayer().isDead())
            img.setImage(new Image(getImageName()));
    }

    @Override
    protected void setHandListener(ListChangeListener<Card> whenHandIsUpdated) {
        super.setHandListener(whenHandIsUpdated);
    }

    @Override
    protected void setInPlayListener(ListChangeListener<BlueCard> whenInPlayIsUpdated) {
        super.setInPlayListener(whenInPlayIsUpdated);
    }

    @Override
    protected void setHealthPointsListener(ChangeListener<? super Number> whenInPlayIsUpdated) {
        super.setHealthPointsListener(whenInPlayIsUpdated);
    }

    @Override
    protected void setWeaponListener(ChangeListener<? super WeaponCard> whenWeaponChanges) {
        super.setWeaponListener(whenWeaponChanges);
    }

    @Override
    public void highlightCurrentArea() {
        name.setStyle("-fx-border-color: red;" + "-fx-font-family: Algerian;" + "-fx-text-fill: #808000");


    }

    @Override
    public void deHightlightCurrentArea() {
        name.setStyle("-fx-border: none;" + "-fx-font-family: Algerian;" + "-fx-text-fill: #808000");
    }

    private ListChangeListener<BlueCard> whenInPlayIsUpdated = new ListChangeListener<BlueCard>() {
        @Override
        public void onChanged(Change<? extends BlueCard> change) {
            while (change.next()){
                if(change.wasAdded()){
                    for(Card c: change.getAddedSubList()) {
                        if(!(c instanceof WeaponCard)){
                            inPlay.getChildren().add(new CardViewEssai
                                    (new ICard(c), OurPlayerArea.this));
                        }
                    }
                }
                else if(change.wasRemoved()){
                    for(Card c: change.getRemoved()) {
                        if(!(c instanceof WeaponCard)){
                            inPlay.getChildren().remove(findInPlayIndex(c));
                        }
                    }
                }
            }
        }
    };

    private ChangeListener<? super WeaponCard> whenWeaponChanges = new ChangeListener<WeaponCard>() {
        @Override
        public void changed(ObservableValue<? extends WeaponCard> observableValue, WeaponCard oldWeapon, WeaponCard newWeapon) {
            inPlay.getChildren().remove(0);
            if(newWeapon == null){
                inPlay.getChildren().add(0,new CardViewEssai(new ICard(new Colt()), OurPlayerArea.this));
            }
            else{
                inPlay.getChildren().add(0,new CardViewEssai(new ICard(newWeapon), OurPlayerArea.this));
            }
        }
    };

    EventHandler<MouseEvent> whenPlayerSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            OurPlayerArea selectedYourPlayerArea = (OurPlayerArea) mouseEvent.getSource();
            IPlayer target = selectedYourPlayerArea.getIPlayer();
            GameView currentGame = selectedYourPlayerArea.getGameView();
            currentGame.getIGame().onTargetSelection(target);
        }
    };

    private int findInPlayIndex(Card card){
        for (int i =0; i < inPlay.getChildren().size(); i++){
            CardViewEssai cv = (CardViewEssai) inPlay.getChildren().get(i);
            if(cv.getCard().equals(card)){
                return i;
            }
        }
        return -1;
    }


    ChangeListener<? super Number> whenHealthPointsIsUpdated = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number formerHp, Number newHp) {
            int fhp = (int) formerHp;
            int nhp = (int) newHp;
            fhp--;
            nhp--;
            int diff = fhp - nhp;
            if (!(fhp + 1 > OurPlayerArea.super.getIPlayer().getHealthPointsMax()) && !(nhp + 1 > OurPlayerArea.super.getIPlayer().getHealthPointsMax())) {
                switch (diff) {
                    case -1:
                        ImageView hpFull = new ImageView("images/bullet.png");
                        hpFull.setFitWidth(30);
                        hpFull.setFitHeight(12);
                        hp.getChildren().remove(nhp);
                        hp.getChildren().add(nhp, hpFull);
                        break;
                    case 1:
                        ImageView hpEmpty1 = new ImageView("images/bullet_grey.png");
                        hpEmpty1.setFitWidth(30);
                        hpEmpty1.setFitHeight(12);
                        hp.getChildren().remove(fhp);
                        hp.getChildren().add(fhp, hpEmpty1);
                        break;
                    case 3:
                        for (int i = 0; i < 3; i++) {
                            ImageView hpEmpty2 = new ImageView("images/bullet_grey.png");
                            hpEmpty2.setFitWidth(30);
                            hpEmpty2.setFitHeight(12);
                            hp.getChildren().remove(nhp + 1 + i);
                            hp.getChildren().add(nhp + 1 + i, hpEmpty2);
                        }
                        break;
                }
            }
            updateImg();
        }
    };

}

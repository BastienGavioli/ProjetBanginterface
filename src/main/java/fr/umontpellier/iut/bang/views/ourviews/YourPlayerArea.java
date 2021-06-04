package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;

public class YourPlayerArea extends PlayerArea {




    public YourPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        VBox rootPlayer = new VBox();
        rootPlayer.setId("rootPlayer");
        Label name = new Label(player.getName());
        rootPlayer.setAlignment(Pos.CENTER);
        ImageView img = new ImageView("/src/main/resources/images/characters/bartcassidy.png");
        img.setFitHeight(200);
        img.setFitWidth(150);
        rootPlayer.getChildren().add(img);
        rootPlayer.getChildren().add(name);
        getChildren().add(rootPlayer);
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
        setStyle("-fx-border-color: red");
    }

    @Override
    public void deHightlightCurrentArea() {
        setStyle("-fx-border: none");
    }


}

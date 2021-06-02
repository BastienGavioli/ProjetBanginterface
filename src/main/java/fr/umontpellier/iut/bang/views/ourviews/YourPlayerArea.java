package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;

public class YourPlayerArea extends PlayerArea {

    private GameView gameView;
    private IPlayer player;

    public YourPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        this.gameView = gameView;
        this.player=player;
    }

    @Override
    public void highlightCurrentArea() {

    }

    @Override
    public void deHightlightCurrentArea() {

    }
}

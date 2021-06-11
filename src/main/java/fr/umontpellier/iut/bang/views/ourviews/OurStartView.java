package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.StartView;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;

import java.net.URL;
import java.util.ResourceBundle;

public class OurStartView extends StartView {
    public OurStartView() {
        super();
    }

    @Override
    public void setPlayersListSetListener(ListChangeListener<String> whenPlayersNamesListIsSet) {
    }

    @Override
    protected void setNbPlayersChangeListener(ChangeListener<Integer> whenNbPlayersChanged) {

    }

    @Override
    protected int getNumberOfPlayers() {
        return 0;
    }

    @Override
    protected String getPlayerNameByNumber(int playerNumber) {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

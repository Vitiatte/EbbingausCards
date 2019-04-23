package sample.controller.training;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.db.entity.Card;
import sample.db.entity.EnglishCard;

public class BackSideOfCardController implements TrainingWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text ua;

    @FXML
    private Text expl;

    @FXML
    private Text contex;

    @FXML
    public void initialize(Card card) {
        EnglishCard englishCard = (EnglishCard) card;
        ua.setText(englishCard.getUa());
        expl.setText(englishCard.getEng_trans());
        contex.setText(englishCard.getEng_context());
    }

}

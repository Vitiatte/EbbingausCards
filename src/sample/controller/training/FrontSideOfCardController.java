package sample.controller.training;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.db.entity.Card;
import sample.db.entity.EnglishCard;

public class FrontSideOfCardController implements TrainingWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text textField;

    @FXML
    public void initialize(Card card) {
        EnglishCard englishCard = (EnglishCard) card;
        textField.setText(englishCard.getEng());
    }
}

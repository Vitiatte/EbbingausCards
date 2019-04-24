package sample.controller.training;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import sample.db.entity.Card;
import sample.db.entity.NoteCard;

import java.net.URL;
import java.util.ResourceBundle;

public class BackSideOfNoteController implements TrainingWindow{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea noteTextArea;

    @FXML
    public void initialize(Card card) {
        NoteCard noteCard = (NoteCard) card;
        noteTextArea.setText(noteCard.getNote());
    }
}

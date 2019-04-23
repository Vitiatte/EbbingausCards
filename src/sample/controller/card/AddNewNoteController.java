package sample.controller.card;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constant.AddNewNoteMessageConstant;
import sample.constant.ResourceConstants;
import sample.constant.ViewConstants;
import sample.controller.Controller;
import sample.controller.RegisteredUser;
import sample.db.entity.User;
import sample.service.card_service.AddNewCardService;
import sample.service.card_service.AddNewNoteCardService;

public class AddNewNoteController extends Controller implements RegisteredUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField bookTextField;

    @FXML
    private Text messageText;

    @FXML
    private TextArea noteTextArea;

    @FXML
    public void initialize(User user) {
        registeredUser = user;
        backButton.setOnAction(event -> backButtonPressed());
        addButton.setOnAction(event -> addButtonPressed());
    }

    private void backButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.MAIN_MENU_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
    }

    private void addButtonPressed() {
        AddNewCardService service = new AddNewNoteCardService(
                titleTextField.getText(),
                bookTextField.getText(),
                noteTextArea.getText(),
                registeredUser.getUserId()
        );

        String message = service.addNewCard();
        if (message.equals(AddNewNoteMessageConstant.NOTE_ADDED_SUCCESSFUL)) {
            messageText.setFill(Color.GREEN);
            clearInputFields();
        } else {
            messageText.setFill(Color.RED);
        }
        messageText.setText(message);
    }

    private void clearInputFields() {
        titleTextField.setText("");
        bookTextField.setText("");
        noteTextArea.setText("");
    }
}

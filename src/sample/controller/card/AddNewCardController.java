package sample.controller.card;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constant.AddNewCardMessageConstant;
import sample.constant.ResourceConstants;
import sample.controller.Controller;
import sample.controller.RegisteredUser;
import sample.db.entity.User;
import sample.service.card_service.AddNewEnglishCardService;
import sample.constant.ViewConstants;

public class AddNewCardController extends Controller implements RegisteredUser {

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
    private TextField uaMeaningTextField;

    @FXML
    private TextField engMeaningTextField;

    @FXML
    private TextField engExplanationTextField;

    @FXML
    private TextField engContextTextField;

    @FXML
    private Text messageText;

    @FXML
    public void initialize(User user) {
        registeredUser = user;
        addButton.setOnAction(event -> addButtonPressed());
        backButton.setOnAction(event -> sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.MAIN_MENU_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu));
    }

    private void addButtonPressed() {
        AddNewEnglishCardService cardService = new AddNewEnglishCardService(
                uaMeaningTextField.getText(),
                engMeaningTextField.getText(),
                engExplanationTextField.getText(),
                engContextTextField.getText(),
                registeredUser.getUserId()
        );
        String messageString = cardService.addNewCard();
        if (messageString.equals(AddNewCardMessageConstant.CARD_SUCCESSFUL_ADDED)) {
            messageText.setFill(Color.GREEN);
            clearInputTextFields();
        } else {
            messageText.setFill(Color.RED);
        }
        messageText.setText(messageString);
    }

    private void clearInputTextFields() {
        uaMeaningTextField.setText("");
        engMeaningTextField.setText("");
        engExplanationTextField.setText("");
        engContextTextField.setText("");
    }

}

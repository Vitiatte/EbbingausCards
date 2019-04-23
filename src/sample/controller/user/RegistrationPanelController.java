package sample.controller.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.constant.ViewConstants;
import sample.controller.Controller;
import sample.service.user_service.RegistrationPanelService;

public class RegistrationPanelController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private Button logupButton;

    @FXML
    private PasswordField passwordRepeatField;

    @FXML
    private TextField mailField;

    @FXML
    private Text loginMessageField;

    @FXML
    private Text passwordMessageField;

    @FXML
    private Text passwordRepeatMessageField;

    @FXML
    private Text mailMessageField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text logupButtonMessage;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> backButtonPressed());
        logupButton.setOnAction(event -> logupButtonPressed());
    }

    private void logupButtonPressed() {
        RegistrationPanelService service = new RegistrationPanelService();
        boolean dataInOrder = checkInputDataAndShowMessage(service);

        if (dataInOrder) {
            service.registerNewUser(loginField.getText(), passwordField.getText(), mailField.getText());
            sceneLoader.loadNewScene(anchorPane, "/sample/view/user/RegistrationSuccessfulView.fxml", anchorPane.getWidth(), anchorPane.getHeight());
        }

    }

    private boolean checkInputDataAndShowMessage(RegistrationPanelService service) {
        int countOfError = 0;
        //Login
        if (loginField.getText().length()<3 || loginField.getText().length()>10) {
            loginMessageField.setText("Login must contain from 3 to 10 letters.");
            countOfError++;
        }
        else
            loginMessageField.setText("");
        //Password
        if (passwordField.getText().length()<8) {
            passwordMessageField.setText("Password must contain more then 8 symbols");
            countOfError++;
        }
        else
            passwordMessageField.setText("");
        //Repeat Password
        if (!(passwordField.getText().equals(passwordRepeatField.getText()))) {
            passwordRepeatMessageField.setText("Passwords are not equal");
            countOfError++;
        }
        else
            passwordRepeatMessageField.setText("");
        //Mail
        if (!(mailField.getText().contains("@"))) {
            mailMessageField.setText("Email wrong");
            countOfError++;
        }
        else
            mailMessageField.setText("");

        //Check in database
        String result = service.verifyDataInDatabase(loginField.getText(), mailField.getText());

        if (!result.isEmpty()) {
            logupButtonMessage.setText(result);
            countOfError++;
        }
        else
            logupButtonMessage.setText("");

        return countOfError == 0;
    }

    private void backButtonPressed() {
        sceneLoader.loadNewScene(anchorPane, "/sample/view/user/LoginPanelView.fxml", ViewConstants.weightLoginPanel, ViewConstants.heightLoginPanel);
    }
}
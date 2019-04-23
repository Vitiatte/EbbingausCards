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
import sample.db.entity.User;
import sample.service.user_service.LoginPanelService;

public class LoginPanelController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button logupButton;

    @FXML
    private Text messageField;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> loginButtonPressed());
        logupButton.setOnAction(event -> logupButtonPressed());
    }

    void loginButtonPressed() {
        LoginPanelService service = new LoginPanelService();
        boolean isUserExist = service.isUserExist(loginField.getText(), passwordField.getText());

        if (isUserExist) {
            User user = service.getUserByLogin(loginField.getText());
            sceneLoader.loadNewScene(user, anchorPane, "/sample/view/MainMenuView.fxml", ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
            messageField.setText("");
        }
        else {
            messageField.setText("Login or password is wrong!");
        }
    }
    void logupButtonPressed() {
        sceneLoader.loadNewScene(anchorPane, "/sample/view/user/RegistrationPanelView.fxml", ViewConstants.weightLoginPanel, ViewConstants.heightLoginPanel);
    }


}

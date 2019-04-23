package sample.controller.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.constant.ResourceConstants;
import sample.constant.ViewConstants;
import sample.controller.Controller;

public class RegistrationSuccessfulController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button loginPanelButton;

    @FXML
    void initialize() {
        loginPanelButton.setOnAction(event -> {
            sceneLoader.loadNewScene(anchorPane, ResourceConstants.LOGIN_PANEL_VIEW, ViewConstants.weightLoginPanel, ViewConstants.heightLoginPanel);
        });
    }
}

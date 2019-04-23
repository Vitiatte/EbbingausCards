package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.constant.ResourceConstants;
import sample.constant.ViewConstants;
import sample.db.entity.User;
import sample.service.util.Controllers;

public class MainMenuController extends Controller implements RegisteredUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Button addNewButton;

    @FXML
    private Button showAllButton;

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startNoteTraining;

    @FXML
    private Button addNewNote;

    @FXML
    private Button showAllNotes;

    @FXML
    public void initialize(User user) {
        registeredUser = user;
        logoutButton.setOnAction(event -> logoutButtonPressed());
        startButton.setOnAction(event -> startButtonPressed());
        addNewButton.setOnAction(event -> addNewButtonPressed());
        showAllButton.setOnAction(event -> showAllButtonPressed());
        startNoteTraining.setOnAction(event -> startNoteTrainingButtonPressed());
        addNewNote.setOnAction(event -> addNewNodePressedPressed());
        showAllNotes.setOnAction(event -> showAllNotesButtonPressed());
    }

    private void logoutButtonPressed() {
        sceneLoader.loadNewScene(anchorPane, ResourceConstants.LOGIN_PANEL_VIEW, ViewConstants.weightLoginPanel, ViewConstants.heightLoginPanel);
    }

    private void startButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.TRAINING_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu, Controllers.ForTrainingView.EnglishCardController);
    }

    private void addNewButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, "/sample/view/card/AddNewCardView.fxml", ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
    }

    private void showAllButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.ALL_CARDS_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
    }

    private void startNoteTrainingButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.TRAINING_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu, Controllers.ForTrainingView.NoteCardController);

    }

    private void addNewNodePressedPressed() {
        sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.ADD_NEW_NOTE_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
    }

    private void showAllNotesButtonPressed() {
        showAllNotes.setOnAction(event -> sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.ALL_NOTES_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu));

    }

}

package sample.controller.training;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.constant.ResourceConstants;
import sample.constant.ViewConstants;
import sample.controller.Controller;
import sample.controller.RegisteredUser;
import sample.db.entity.Card;
import sample.db.entity.User;
import sample.service.training_service.TrainingService;

public abstract class TrainingController extends Controller implements RegisteredUser {

    protected Card cardToTrain;
    protected TrainingService trainingService;
    protected LoadedSideOfCurrentCard loadedSideOfCurrentCard = LoadedSideOfCurrentCard.Front;

    @FXML
    private Button iKnowButton;

    @FXML
    private Button iDontKnowButton;

    @FXML
    private Button backButton;

    @FXML
    private Button nextCardButton;

    @FXML
    private Button otherSideButton;

    @FXML
    private Button startButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button newTrainButton;

    @FXML
    private AnchorPane trainPane;

    @FXML
    public void initialize(User user) {
        blockAllButtons();
        newTrainButton.setVisible(false);
        registeredUser = user;

        startButton.setOnAction(event -> startButtonPressed());
        otherSideButton.setOnAction(event -> otherSideButtonPressed());
        nextCardButton.setOnAction(event -> nextCardButtonPressed());
        iKnowButton.setOnAction(event -> knowButtonPressed());
        iDontKnowButton.setOnAction(event -> dontKnowButtonPressed());
        backButton.setOnAction(event -> sceneLoader.loadNewScene(user, borderPane, ResourceConstants.MAIN_MENU_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu));
        newTrainButton.setOnAction(event -> newTrainButtonPressed());
    }

    //Button pressed methods
    private void startButtonPressed() {
        if (trainingService.getNumberOfCardsInTrainList() == 0) {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceConstants.NOTHING_TO_TRAIN));
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            borderPane.setCenter(root);
        } else {
            unblockAllButtons();
            nextCardButtonPressed();
        }
    }
    private void otherSideButtonPressed() {
        if (loadedSideOfCurrentCard == LoadedSideOfCurrentCard.Front)
            loadBackSideOfCard();
        else
            loadFrontSideOfCard();
    }
    private void nextCardButtonPressed() {
        cardToTrain = trainingService.getNextCardToTrain();
        loadFrontSideOfCard();
        unblockKnowAndDontKnowButton();

        if (!trainingService.nextCardForTrainingExist()) {
            nextCardButton.setDisable(true);
        }

    }
    private void knowButtonPressed() {
        trainingService.iKnowCurrentCard();
        blockKnowAndDontKnowButton();
        if (!trainingService.nextCardForTrainingExist()) {
            loadPaneAfterTraining(ResourceConstants.AFTER_TRAINING_VIEW);
        }
    }
    private void dontKnowButtonPressed() {
        trainingService.iDontKnowCurrentCard();
        blockKnowAndDontKnowButton();
        if (!trainingService.nextCardForTrainingExist()) {
            loadPaneAfterTraining(ResourceConstants.AFTER_TRAINING_VIEW);
        }
    }
    private void newTrainButtonPressed() {
        sceneLoader.loadNewScene(registeredUser, borderPane, ResourceConstants.TRAINING_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu);
    }

    //Utility methods
    private void blockAllButtons() {
        blockKnowAndDontKnowButton();
        nextCardButton.setDisable(true);
        otherSideButton.setDisable(true);
    }
    private void unblockAllButtons() {
        unblockKnowAndDontKnowButton();
        nextCardButton.setDisable(false);
        otherSideButton.setDisable(false);
    }
    private void blockKnowAndDontKnowButton() {
        iKnowButton.setDisable(true);
        iDontKnowButton.setDisable(true);
    }
    private void unblockKnowAndDontKnowButton() {
        iKnowButton.setDisable(false);
        iDontKnowButton.setDisable(false);
    }
    protected void loadTrainingWindow(String urlView, Card cardToShow) {
        Parent root = null;
        System.out.println(urlView);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(urlView));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TrainingWindow controller = loader.<TrainingWindow>getController();
        controller.initialize(cardToShow);
        borderPane.setCenter(root);
    }
    private void loadPaneAfterTraining(String urlView) {
        blockAllButtons();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(urlView));

        try {

            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AfterTrainingController controller = loader.<AfterTrainingController>getController();
        controller.initialize(trainingService);
        borderPane.setCenter(root);
        newTrainButton.setVisible(true);
    }

    protected abstract void loadBackSideOfCard();

    protected abstract void loadFrontSideOfCard();

    enum LoadedSideOfCurrentCard {
        Back, Front
    }
}

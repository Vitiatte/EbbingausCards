package sample.controller.training;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.service.training_service.TrainingService;

public class AfterTrainingController {

    @FXML
    private Text numberOfCardsField;

    @FXML
    private Text numberOfCardsYouKnowField;

    @FXML
    void initialize(TrainingService trainingService) {
        numberOfCardsField.setText(String.valueOf(trainingService.getNumberOfCardsInTrainList()));
        numberOfCardsYouKnowField.setText(String.valueOf(trainingService.getNumberOfCardsBeKnown()));
    }
}

package sample.controller.training;

import sample.constant.ResourceConstants;
import sample.db.entity.User;
import sample.service.enums.CardType;
import sample.service.training_service.TrainingService;

public class NoteTrainingController extends TrainingController {
    public void initialize(User user) {
        super.initialize(user);
        trainingService = new TrainingService(user.getUserId(), CardType.NoteCard);
    }

    protected void loadBackSideOfCard() {
        loadTrainingWindow(ResourceConstants.BACK_SIDE_OF_NOTE_VIEW, cardToTrain);
        loadedSideOfCurrentCard = LoadedSideOfCurrentCard.Back;
    }
    protected void loadFrontSideOfCard() {
        loadTrainingWindow(ResourceConstants.FRONT_SIDE_OF_NOTE_VIEW, cardToTrain);
        loadedSideOfCurrentCard = LoadedSideOfCurrentCard.Front;
    }
}

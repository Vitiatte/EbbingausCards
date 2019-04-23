package sample.controller.training;

import sample.constant.ResourceConstants;
import sample.db.entity.User;
import sample.service.training_service.TrainingService;
import sample.service.enums.CardType;

public class EnglishCardTrainingController extends TrainingController {
    public void initialize(User user) {
        super.initialize(user);
        trainingService = new TrainingService(registeredUser.getUserId(), CardType.EnglishCard);
    }

    protected void loadBackSideOfCard() {
        loadTrainingWindow(ResourceConstants.BACK_SIDE_OF_ENGLISH_CARD_VIEW, cardToTrain);
        loadedSideOfCurrentCard = LoadedSideOfCurrentCard.Back;
    }
    protected void loadFrontSideOfCard() {
        loadTrainingWindow(ResourceConstants.FRONT_SIDE_OF_ENGLISH_CARD_VIEW, cardToTrain);
        loadedSideOfCurrentCard = LoadedSideOfCurrentCard.Front;
    }
}

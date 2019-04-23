package sample.service.training_service;

import sample.db.DatabaseCardHandler;
import sample.db.DatabaseEnglishCardHandler;
import sample.db.DatabaseNoteHandler;
import sample.db.entity.Card;
import sample.db.entity.EnglishCard;
import sample.service.enums.CardRememberedSuccessful;
import sample.service.enums.CardType;
import sample.service.util.CardIntervalTimeChanger;
import sample.service.util.ResultSetParser;

import java.util.ArrayList;

public class TrainingService {

    private CardIntervalTimeChanger intervalTimeChanger;
    private DatabaseCardHandler cardHandler;
    ResultSetParser parser;

    private int indexOfTrainingCard = -1;
    private int numberOfCardsInTrainList = 0;
    private int numberOfCardsBeKnown = 0;
    private ArrayList<? extends Card> listToTrain;

    public TrainingService(int userId, CardType type) {
        intervalTimeChanger = new CardIntervalTimeChanger();
        cardHandler = getDatabaseCardHandlerByCardType(type);
        parser = new ResultSetParser();
        listToTrain = parser.parseToArrayList(cardHandler.getResultSetOfCardsForTrainingById(userId), type);
        numberOfCardsInTrainList = listToTrain.size();
    }

    public Card getNextCardToTrain() {
        indexOfTrainingCard++;
        if (indexOfTrainingCard > -1 && indexOfTrainingCard < listToTrain.size()) {
            Card card = listToTrain.get(indexOfTrainingCard);
            return card;
        } else
        {
            return null;
        }
    }

    public int getIndexOfTrainingCard() {
        return indexOfTrainingCard;
    }

    public boolean nextCardForTrainingExist() {
        int nextIndex = indexOfTrainingCard + 1;
        if (nextIndex < listToTrain.size())
            return true;
        return false;
    }

    public void iKnowCurrentCard() {
        Card card = listToTrain.get(indexOfTrainingCard);
        intervalTimeChanger.changeTimeInterval(card, CardRememberedSuccessful.YES);
        cardHandler.updateCard(card);
        numberOfCardsBeKnown++;
    }

    public void iDontKnowCurrentCard() {
        Card card = listToTrain.get(indexOfTrainingCard);
        intervalTimeChanger.changeTimeInterval(card, CardRememberedSuccessful.NO);
        cardHandler.updateCard(card);
    }

    public int getNumberOfCardsInTrainList() {
        return numberOfCardsInTrainList;
    }

    public int getNumberOfCardsBeKnown() {
        return numberOfCardsBeKnown;
    }

    private DatabaseCardHandler getDatabaseCardHandlerByCardType(CardType type) {
        switch (type) {
            case EnglishCard:
                return new DatabaseEnglishCardHandler();
            case NoteCard:
                return new DatabaseNoteHandler();
             default:
                 return null;
        }

    }
}

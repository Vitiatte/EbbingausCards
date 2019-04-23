package sample.service.util;

import sample.constant.EnglishCardDatabaseConstant;
import sample.db.DatabaseEnglishCardHandler;
import sample.db.entity.Card;
import sample.db.entity.EnglishCard;
import sample.service.enums.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CardGeneratorForUser {

    private ArrayList<Card> listOfAllCardsForUSer;
    private ArrayList<Card> listCardsToTrain;

    private int userId;
    private int numberOfAllCards;

    public CardGeneratorForUser(int userId) {
        this.userId = userId;
        generateListOfCard();
        numberOfAllCards = listOfAllCardsForUSer.size();
        generateListCardsToTrain();
    }

    public void update() {
        //Clean all fields
        listOfAllCardsForUSer = null;
        listCardsToTrain = null;
        numberOfAllCards = 0;
        //Update
        generateListOfCard();
        numberOfAllCards = listOfAllCardsForUSer.size();
        generateListCardsToTrain();
    }
    public ArrayList<Card> getListOfAllCardsForUser() {
        return listOfAllCardsForUSer;
    }
    public ArrayList<Card> getListCardsToTrain() {
        return listCardsToTrain;
    }
    public int getNumberOfAllCards() {
        return numberOfAllCards;
    }

    //Util Methods
    private void generateListOfCard() {
        listOfAllCardsForUSer = new ArrayList();
        DatabaseEnglishCardHandler handler = new DatabaseEnglishCardHandler();
        ResultSet resultSet = handler.getResultSetOfCardsById(userId);

        try {
            while (resultSet.next()) {
                Card card = new EnglishCard(
                        resultSet.getInt(EnglishCardDatabaseConstant.CARD_ID),
                        resultSet.getString(EnglishCardDatabaseConstant.UA_MEANING),
                        resultSet.getString(EnglishCardDatabaseConstant.ENG_MEANING),
                        resultSet.getString(EnglishCardDatabaseConstant.ENG_EXPLANATION),
                        resultSet.getString(EnglishCardDatabaseConstant.ENG_CONTEXT),
                        resultSet.getDate(EnglishCardDatabaseConstant.LAST_TRAINING),
                        Level.valueOf(resultSet.getString(EnglishCardDatabaseConstant.LEVEL)),
                        resultSet.getDate(EnglishCardDatabaseConstant.NEXT_TRAINING),
                        resultSet.getInt(EnglishCardDatabaseConstant.CARD_ID_USER)
                );
                listOfAllCardsForUSer.add(card);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void generateListCardsToTrain() {
        listCardsToTrain = new ArrayList();
        Date duringTheSession = new Date();
        for (int i = 0, j = 0; listOfAllCardsForUSer.size()>i && j<10; i++) {
            Card card = listOfAllCardsForUSer.get(i);
            if (card.getNextDate().before(duringTheSession)) {
                listCardsToTrain.add(card);
                j++;
            }
        }
    }

}

package sample.service.card_service;

import sample.constant.AddNewCardMessageConstant;
import sample.db.DatabaseCardHandler;
import sample.db.DatabaseEnglishCardHandler;
import sample.db.entity.EnglishCard;
import sample.service.enums.Level;

import java.util.Date;

public class AddNewEnglishCardService implements AddNewCardService {
    DatabaseCardHandler<EnglishCard> cardHandler;

    private String ua;
    private String eng;
    private String engExplanation;
    private String engContext;
    private int userId;

   public AddNewEnglishCardService(String ua, String eng, String engExplanation, String engContext, int userId) {
        this.ua = ua;
        this.eng = eng;
        this.engExplanation = engExplanation;
        this.engContext = engContext;
        this.userId = userId;
    }

    public String addNewCard() {

        String errorString = checkInputData();

        if (errorString.isEmpty()) {
            cardHandler = new DatabaseEnglishCardHandler();
            EnglishCard cardToAdd = createCard();
            cardHandler.addCard(cardToAdd);
            return  AddNewCardMessageConstant.CARD_SUCCESSFUL_ADDED;
        }
        return errorString;
    }

    private String checkInputData() {
        String errorString = "";

        if (ua.isEmpty())
            errorString = AddNewCardMessageConstant.IF_UKRAINIAN_FIELD_EMPTY;
        if (eng.isEmpty())
            errorString = errorString + ",\n" + AddNewCardMessageConstant.IF_ENGLISH_FIELD_EMPTY;
        if (engExplanation.isEmpty())
            errorString = errorString + ",\n" + AddNewCardMessageConstant.IF_ENGLISH_EXPLANATION_FIELD_EMPTY;
        if (engContext.isEmpty())
            errorString = errorString + ",\n" + AddNewCardMessageConstant.IF_ENGLISH_CONTEXT_FIELD_EMPTY;

        return errorString;

    }

    private EnglishCard createCard() {
        EnglishCard cardToReturn = new EnglishCard(ua, eng, engExplanation, engContext);
        cardToReturn.setLevel(Level.Today);
        cardToReturn.setLastDate(new Date());
        cardToReturn.setNextDate(new Date());
        cardToReturn.setUserId(userId);

        return cardToReturn;
    }
}

package sample.service.card_service;

import javafx.collections.ObservableList;
import sample.db.DatabaseEnglishCardHandler;
import sample.db.entity.EnglishCard;
import sample.db.entity.User;
import sample.service.enums.CardType;

public class AllEnglishCardsService extends AllCardService {

    public AllEnglishCardsService(User user) {
        super(user);
        cardHandler = new DatabaseEnglishCardHandler();
    }

    public ObservableList<EnglishCard> getListOfCards() {
        return (ObservableList<EnglishCard>) resultSetParser.parseToObservableList(cardHandler.getResultSetOfCardsById(user.getUserId()), CardType.EnglishCard);
    }
}

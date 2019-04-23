package sample.service.card_service;

import javafx.collections.ObservableList;
import sample.db.DatabaseCardHandler;
import sample.db.DatabaseEnglishCardHandler;
import sample.db.entity.Card;
import sample.db.entity.User;
import sample.service.util.ResultSetParser;

public abstract class AllCardService {
    protected User user;
    protected DatabaseCardHandler cardHandler;
    protected ResultSetParser resultSetParser = new ResultSetParser();

    public AllCardService(User user) {
        this.user = user;
    }

    public abstract ObservableList<? extends Card> getListOfCards();
}

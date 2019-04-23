package sample.service.card_service;

import javafx.collections.ObservableList;
import sample.db.DatabaseNoteHandler;
import sample.db.entity.NoteCard;
import sample.db.entity.User;
import sample.service.enums.CardType;

public class AllNoteCardsService extends AllCardService {

    public AllNoteCardsService(User user) {
        super(user);
        cardHandler = new DatabaseNoteHandler();
    }
    @Override
    public ObservableList<NoteCard> getListOfCards() {
        return (ObservableList<NoteCard>) resultSetParser.parseToObservableList(cardHandler.getResultSetOfCardsById(user.getUserId()), CardType.NoteCard);

    }
}

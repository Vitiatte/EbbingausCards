package sample.service.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.constant.EnglishCardDatabaseConstant;
import sample.constant.NoteDatabaseConstant;
import sample.db.entity.Card;
import sample.db.entity.EnglishCard;
import sample.db.entity.NoteCard;
import sample.service.enums.CardType;
import sample.service.enums.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetParser {

    public ArrayList<? extends Card> parseToArrayList(ResultSet resultSet, CardType type) {
        switch (type) {

            case NoteCard:
                return parseResultSetOfNoteCardsToArrayList(resultSet);

            case EnglishCard:
                return parseResultSetOfEnglishCardToArrayList(resultSet);
        }
        return null;
    }

    public ObservableList<? extends Card> parseToObservableList(ResultSet resultSet, CardType type) {
        return FXCollections.observableList(parseToArrayList(resultSet, type));
    }

    private ArrayList<EnglishCard> parseResultSetOfEnglishCardToArrayList(ResultSet resultSet) {
        ArrayList<EnglishCard> englishCards = new ArrayList<>();
        try {
            while (resultSet.next()) {
                EnglishCard englishCard = new EnglishCard(
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
                englishCards.add(englishCard);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return englishCards;
    }

    private ArrayList<NoteCard> parseResultSetOfNoteCardsToArrayList(ResultSet resultSet) {
        ArrayList<NoteCard> noteCards = new ArrayList<>();
        try {
            while (resultSet.next()) {
                NoteCard noteCard = new NoteCard(
                        resultSet.getInt(NoteDatabaseConstant.NOTE_ID),
                        resultSet.getString(NoteDatabaseConstant.TITLE),
                        resultSet.getString(NoteDatabaseConstant.NOTE),
                        resultSet.getString(NoteDatabaseConstant.BOOK),
                        resultSet.getDate(NoteDatabaseConstant.LAST_TRAINING),
                        Level.valueOf(resultSet.getString(NoteDatabaseConstant.LEVEL)),
                        resultSet.getDate(NoteDatabaseConstant.NEXT_TRAINING),
                        resultSet.getInt(NoteDatabaseConstant.CARD_ID_USER)
                );
                noteCards.add(noteCard);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return noteCards;
    }
}

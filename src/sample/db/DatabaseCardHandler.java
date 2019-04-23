package sample.db;

import sample.db.entity.Card;

import java.sql.ResultSet;

public abstract class DatabaseCardHandler<T extends Card> extends DatabaseHandler{
    public abstract ResultSet getResultSetOfCardsById(int id);
    public abstract ResultSet getResultSetOfCardsForTrainingById(int id);
    public abstract void updateCard(T card);
    public abstract void removeCardById(int id);
    public abstract void addCard(T card);
}

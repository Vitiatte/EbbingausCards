package sample.db;

import sample.constant.EnglishCardDatabaseConstant;
import sample.db.entity.EnglishCard;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DatabaseEnglishCardHandler extends DatabaseCardHandler<EnglishCard>{

    public ResultSet getResultSetOfCardsById(int id) {
        String select = "SELECT * FROM " + EnglishCardDatabaseConstant.CARD_TABLE + " WHERE " + EnglishCardDatabaseConstant.CARD_ID_USER + " = " + id;
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getResultSetOfCardsForTrainingById(int id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = simpleDateFormat.format(new java.util.Date());
        int limitOfRows = 10;

        String select = "SELECT * FROM " + EnglishCardDatabaseConstant.CARD_TABLE +
                " WHERE " + EnglishCardDatabaseConstant.CARD_ID_USER + " = " + id + " AND " +
                EnglishCardDatabaseConstant.NEXT_TRAINING + " < '" + dateNow +
                "' LIMIT " + limitOfRows;

        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return resultSet;
    }


    public void addCard(EnglishCard card) {
        String insert = "INSERT INTO " +
                EnglishCardDatabaseConstant.CARD_TABLE +
                " ( " +
                EnglishCardDatabaseConstant.UA_MEANING + ", " +
                EnglishCardDatabaseConstant.ENG_MEANING + ", " +
                EnglishCardDatabaseConstant.ENG_EXPLANATION + ", " +
                EnglishCardDatabaseConstant.ENG_CONTEXT + ", " +
                EnglishCardDatabaseConstant.LAST_TRAINING + ", " +
                EnglishCardDatabaseConstant.LEVEL + ", " +
                EnglishCardDatabaseConstant.NEXT_TRAINING + ", " +
                EnglishCardDatabaseConstant.CARD_ID_USER +
                " ) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, card.getUa());
            preparedStatement.setString(2, card.getEng());
            preparedStatement.setString(3, card.getEng_trans());
            preparedStatement.setString(4, card.getEng_context());
            preparedStatement.setDate(5, new java.sql.Date(card.getLastDate().getTime()));
            preparedStatement.setString(6, card.getLevel().toString());
            preparedStatement.setDate(7, new java.sql.Date(card.getNextDate().getTime()));
            preparedStatement.setInt(8, card.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateCard(EnglishCard cardToUpdate) {
        String update = "UPDATE " + EnglishCardDatabaseConstant.CARD_TABLE +
                " SET " +
                EnglishCardDatabaseConstant.LAST_TRAINING + " = ?, " +
                EnglishCardDatabaseConstant.LEVEL + " = ?, " +
                EnglishCardDatabaseConstant.NEXT_TRAINING + " = ?"  +
                " WHERE " +
                EnglishCardDatabaseConstant.CARD_ID + " = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

            preparedStatement.setDate(1, new java.sql.Date(cardToUpdate.getLastDate().getTime()));
            preparedStatement.setString(2, cardToUpdate.getLevel().toString());
            preparedStatement.setDate(3, new java.sql.Date(cardToUpdate.getNextDate().getTime()));
            preparedStatement.setInt(4, cardToUpdate.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCardById(int id) {
        String delete = "DELETE FROM " + EnglishCardDatabaseConstant.CARD_TABLE +
                " WHERE " + EnglishCardDatabaseConstant.CARD_ID_USER + " = " + id;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

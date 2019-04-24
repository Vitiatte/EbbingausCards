package sample.db;

import sample.constant.NoteDatabaseConstant;
import sample.db.entity.NoteCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DatabaseNoteHandler extends DatabaseCardHandler<NoteCard>{

    public ResultSet getResultSetOfCardsById(int id) {
        String select = "SELECT * FROM " + NoteDatabaseConstant.NOTE_TABLE + " WHERE " + NoteDatabaseConstant.CARD_ID_USER + " = " + id;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateNow = simpleDateFormat.format(new java.util.Date());
        int limitOfRows = 10;

        String select = "SELECT * FROM " + NoteDatabaseConstant.NOTE_TABLE +
                " WHERE " + NoteDatabaseConstant.CARD_ID_USER + " = " + id + " AND " +
                NoteDatabaseConstant.NEXT_TRAINING + " <= '" + dateNow +
                "' LIMIT " + limitOfRows;

        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            System.out.println(resultSet.getFetchSize());
        } catch (SQLException e) {
            System.out.println("sdf");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public void addCard(NoteCard note) {
        String insert = "INSERT INTO " +
                NoteDatabaseConstant.NOTE_TABLE +
                " ( " +
                NoteDatabaseConstant.TITLE + ", " +
                NoteDatabaseConstant.NOTE + ", " +
                NoteDatabaseConstant.BOOK + ", " +
                NoteDatabaseConstant.LAST_TRAINING + ", " +
                NoteDatabaseConstant.LEVEL + ", " +
                NoteDatabaseConstant.NEXT_TRAINING + ", " +
                NoteDatabaseConstant.CARD_ID_USER +
                " ) " +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getNote());
            preparedStatement.setString(3, note.getBook());
            preparedStatement.setDate(4, new java.sql.Date(note.getLastDate().getTime()));
            preparedStatement.setString(5, note.getLevel().toString());
            preparedStatement.setDate(6, new java.sql.Date(note.getNextDate().getTime()));
            preparedStatement.setInt(7, note.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateCard(NoteCard cardToUpdate) {
        String update = "UPDATE " + NoteDatabaseConstant.NOTE_TABLE +
                " SET " +
                NoteDatabaseConstant.LAST_TRAINING + " = ?, " +
                NoteDatabaseConstant.LEVEL + " = ?, " +
                NoteDatabaseConstant.NEXT_TRAINING + " = ?"  +
                " WHERE " +
                NoteDatabaseConstant.NOTE_ID + " = ?";

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
        String delete = "DELETE FROM " + NoteDatabaseConstant.NOTE_TABLE +
                " WHERE " + NoteDatabaseConstant.CARD_ID_USER + " = " + id;

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

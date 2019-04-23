package sample.db;

import sample.constant.UserDatabaseConstant;
import sample.db.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUserHandler extends DatabaseHandler {

    public void registerUser(User user) {
        String insert = "INSERT INTO " + UserDatabaseConstant.USER_TABLE + " ( " + UserDatabaseConstant.USER_LOGIN + ", " + UserDatabaseConstant.USER_PASSWORD +
                "," + UserDatabaseConstant.USER_EMAIL + " ) " + "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,user.getUserLogin());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getUserEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getAllUsers() {
        String select = "SELECT * FROM " + UserDatabaseConstant.USER_TABLE;
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

    public ArrayList<User> getListUsers() {
        ResultSet setOfUsers = getAllUsers();
        ArrayList<User> users = new ArrayList<>();
        try {
            while (setOfUsers.next()) {
                User user = new User();
                user.setUserId(setOfUsers.getInt(UserDatabaseConstant.USER_ID));
                user.setUserLogin(setOfUsers.getString(UserDatabaseConstant.USER_LOGIN));
                user.setUserPassword(setOfUsers.getString(UserDatabaseConstant.USER_PASSWORD));
                user.setUserEmail(setOfUsers.getString(UserDatabaseConstant.USER_EMAIL));

                users.add(user);
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByLogin(String login) {
        String select = "SELECT * FROM " + UserDatabaseConstant.USER_TABLE + " WHERE " + UserDatabaseConstant.USER_LOGIN + " = \"" + login + "\"";
        ResultSet resultSet = null;

        PreparedStatement preparedStatement;
        try {
            preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        User user = new User();
        try {
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt(UserDatabaseConstant.USER_ID));
                user.setUserLogin(resultSet.getString(UserDatabaseConstant.USER_LOGIN));
                user.setUserPassword(resultSet.getString(UserDatabaseConstant.USER_PASSWORD));
                user.setUserEmail(resultSet.getString(UserDatabaseConstant.USER_EMAIL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

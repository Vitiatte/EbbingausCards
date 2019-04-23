package sample.service.user_service;

import sample.db.DatabaseUserHandler;
import sample.db.entity.User;
import java.util.ArrayList;

public class LoginPanelService {

    DatabaseUserHandler userHandler;

    public boolean isUserExist(String login, String pass) {
        boolean isUserExist = false;
        userHandler = new DatabaseUserHandler();
        ArrayList<User> listOfUsers = userHandler.getListUsers();

        for (User user : listOfUsers) {
            if (user.getUserLogin().equals(login) && user.getUserPassword().equals(pass)) {
                isUserExist = true;
                break;
            }
        }
        return isUserExist;
    }

    public User getUserByLogin(String login) {
        userHandler = new DatabaseUserHandler();
        return userHandler.getUserByLogin(login);
    }
}

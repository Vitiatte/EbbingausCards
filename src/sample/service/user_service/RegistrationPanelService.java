package sample.service.user_service;

import sample.db.DatabaseUserHandler;
import sample.db.entity.User;

import java.util.ArrayList;

public class RegistrationPanelService {
    DatabaseUserHandler databaseUserHandler;

    /**
     * Verify input data in database and return message depends situation;
     * @param login of user to verify in database
     * @param mail of user to verify in database
     * @return empty string if passed data is not exist in database or message with specific problem.
     */
    public String verifyDataInDatabase(String login, String mail) {
        String messageToReturn = "";
        databaseUserHandler = new DatabaseUserHandler();
        ArrayList<User> users = databaseUserHandler.getListUsers();
        for (User user : users) {
            if (user.getUserLogin().equals(login)) {
                messageToReturn = "User with that login exist!";
                break;
            }
            if (user.getUserEmail().equals(mail)) {
                messageToReturn = "User with that mail exist!";
            }
        }
        return messageToReturn;
    }

    public void registerNewUser(String login, String pass, String mail) {
        databaseUserHandler = new DatabaseUserHandler();
        User userToAdd = new User(login, pass, mail);

        databaseUserHandler.registerUser(userToAdd);
    }
}

package sample.service.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controller.RegisteredUser;
import sample.db.entity.User;

import java.io.IOException;

public class SceneLoader {

    private Parent root;

    public void loadNewScene(Pane pane, String resource, double width, double height) {
        FXMLLoader loader = getFXMLLoader(resource);
        root = tryLoadParent(loader);

        if (root != null) {
            showNewStage(pane, new Scene(root, width, height));
        }
    }

    public void loadNewScene(User user, Pane pane, String resource, double width, double height) {
        FXMLLoader loader = getFXMLLoader(resource);
        root = tryLoadParent(loader);

        if (root != null) {
            RegisteredUser controller = loader.<RegisteredUser>getController();
            controller.initialize(user);

            showNewStage(pane, new Scene(root, width, height));
        }
    }

    public void loadNewScene(User user, Pane pane, String resource, double width, double height, Controllers.TypeController typeController) {
        FXMLLoader loader = getFXMLLoader(resource);
        loader.setController(typeController.getController());
        root = tryLoadParent(loader);

        if (root != null) {
            RegisteredUser controller = loader.<RegisteredUser>getController();
            controller.initialize(user);

            showNewStage(pane, new Scene(root, width, height));
        }
    }

    private Parent tryLoadParent(FXMLLoader loader) {
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showNewStage(Pane pane, Scene scene) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private FXMLLoader getFXMLLoader(String resource) {
        return new FXMLLoader(getClass().getResource(resource));
    }

}

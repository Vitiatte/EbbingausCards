package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.constant.ViewConstants;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/user/LoginPanelView.fxml"));
        primaryStage.setTitle("EbbiCards");
        primaryStage.setScene(new Scene(root, ViewConstants.weightLoginPanel, ViewConstants.heightLoginPanel));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

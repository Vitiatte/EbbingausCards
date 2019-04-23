package sample.controller.card;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.constant.ResourceConstants;
import sample.constant.ViewConstants;
import sample.controller.MainMenuController;
import sample.db.entity.EnglishCard;
import sample.db.entity.User;
import sample.service.card_service.AllEnglishCardsService;

public class AllCardsController extends MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected TableColumn<EnglishCard, Integer> idColumn;

    @FXML
    protected TableColumn<EnglishCard, String> uaColumn;

    @FXML
    protected TableColumn<EnglishCard, String> engColumn;

    @FXML
    protected TableColumn<EnglishCard, Date> lastTraningColum;

    @FXML
    protected TableColumn<EnglishCard, Date> nextTraningColumn;

    @FXML
    protected TableColumn<?, ?> deleteColumn;

    @FXML
    protected Button backButton;

    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected TableView<EnglishCard> table;

    @FXML
    protected Button showAllButton;

    @FXML
    public void initialize(User user) {
        registeredUser = user;
        backButton.setOnAction(event -> sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.MAIN_MENU_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu));
        showAllButton.setOnAction(event -> showTable());
        showTable();
    }

    private void showTable() {
        AllEnglishCardsService service = new AllEnglishCardsService(registeredUser);
        ObservableList<EnglishCard> observableList = service.getListOfCards();

        idColumn.setCellValueFactory(new PropertyValueFactory<EnglishCard, Integer>("id"));
        uaColumn.setCellValueFactory(new PropertyValueFactory<EnglishCard, String>("ua"));
        engColumn.setCellValueFactory(new PropertyValueFactory<EnglishCard, String>("eng"));
        lastTraningColum.setCellValueFactory(new PropertyValueFactory<EnglishCard, Date>("lastDate"));
        nextTraningColumn.setCellValueFactory(new PropertyValueFactory<EnglishCard, Date>("nextDate"));

        table.getColumns().clear();
        table.setItems(observableList);
        table.getColumns().addAll(idColumn, uaColumn, engColumn, lastTraningColum, nextTraningColumn);

    }
}

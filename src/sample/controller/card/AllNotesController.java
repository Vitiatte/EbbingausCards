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
import sample.controller.Controller;
import sample.controller.RegisteredUser;
import sample.db.entity.NoteCard;
import sample.db.entity.User;
import sample.service.card_service.AllNoteCardsService;

public class AllNotesController extends Controller implements RegisteredUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<NoteCard> table;

    @FXML
    private TableColumn<NoteCard, Integer> idColumn;

    @FXML
    private TableColumn<NoteCard, String> titleColumn;

    @FXML
    private TableColumn<NoteCard, String> bookColumn;

    @FXML
    private TableColumn<NoteCard, Date> lastTraningColum;

    @FXML
    private TableColumn<NoteCard, Date> nextTraningColumn;

    @FXML
    private TableColumn<?, ?> deleteColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button showAllButton;

    @FXML
    public void initialize(User user) {
        registeredUser = user;
        backButton.setOnAction(event -> sceneLoader.loadNewScene(registeredUser, anchorPane, ResourceConstants.MAIN_MENU_VIEW, ViewConstants.weightMainMenu, ViewConstants.heightMainMenu));
        showAllButton.setOnAction(event -> showTable());
        showTable();
    }

    private void showTable() {
        AllNoteCardsService cardService = new AllNoteCardsService(registeredUser);
        ObservableList<NoteCard> observableList = cardService.getListOfCards();

        idColumn.setCellValueFactory(new PropertyValueFactory<NoteCard, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<NoteCard, String>("title"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<NoteCard, String>("book"));
        lastTraningColum.setCellValueFactory(new PropertyValueFactory<NoteCard, Date>("lastDate"));
        nextTraningColumn.setCellValueFactory(new PropertyValueFactory<NoteCard, Date>("nextDate"));

        table.getColumns().clear();
        table.setItems(observableList);
        table.getColumns().addAll(idColumn, titleColumn, bookColumn, lastTraningColum, nextTraningColumn);

    }
}

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Represents a chess GUI.
 *
 * @author schen475
 * @version 1.0
 */
public class ChessGui extends Application {


    /**
     * Sets the stage; analogous to class with
     * single main method in command programs.
     *
     * @param stage the stage
     *
     */
    public void start(Stage stage) {
        stage.getIcons().add(new Image(this.getClass().
                getResource("RookFavicon.png").toString()));
        ChessDb chessDb = new ChessDb();
        ObservableList<ChessGame> games =
            FXCollections.observableArrayList(chessDb.getGames());
        TableView<ChessGame> table = createTable(games);

        Button viewButton = new Button("Play-by-Play");
        viewButton.setOnAction(e -> {
                ChessGame game = table.getSelectionModel().getSelectedItem();
                viewDialog(game);
            });
        viewButton.disableProperty().bind(Bindings
                .isNull(table.getSelectionModel().selectedItemProperty()));

        Button viewAllButton = new Button("All Moves");
        viewAllButton.setOnAction(e -> {
                ChessGame game = table.getSelectionModel().getSelectedItem();
                viewAllMoves(game);
            });
        viewAllButton.disableProperty().bind(Bindings
                .isNull(table.getSelectionModel().selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(viewButton,
                viewAllButton, dismissButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, buttonBox);
        final Scene scene = new Scene(vbox, 1225, 250);
        scene.setFill(null);
        stage.setScene(scene);
        stage.setTitle("Sarah Chen | Chess GUI");
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
    }

    /**
     * Creates a table with the games in
     * the database.
     *
     * @param games the games in the database
     *
     */
    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {

        TableView<ChessGame> table = new TableView<ChessGame>();
        table.setItems(games);

        TableColumn<ChessGame, String> eventCol =
            new TableColumn<ChessGame, String>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));

        TableColumn<ChessGame, String> siteCol =
            new TableColumn<ChessGame, String>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));

        TableColumn<ChessGame, String> dateCol =
            new TableColumn<ChessGame, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<ChessGame, String> whiteCol =
            new TableColumn<ChessGame, String>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));

        TableColumn<ChessGame, String> blackCol =
            new TableColumn<ChessGame, String>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));

        TableColumn player = new TableColumn("Player");
        player.getColumns().addAll(whiteCol, blackCol);

        TableColumn<ChessGame, String> resultCol =
            new TableColumn<ChessGame, String>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));

        TableColumn<ChessGame, String> openingCol =
            new TableColumn<ChessGame, String>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("opening"));

        table.getColumns().setAll(eventCol, siteCol, dateCol, player,
                resultCol, openingCol);

        return table;
    }

    /**
     * Formats the pop-up when
     * 'View' is pressed.
     *
     * @param game the game whose moves
     * are being presented to the user.
     *
     */
    private void viewDialog(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().
                getResource("RookFavicon.png").toString()));

        alert.setTitle("Sarah Chen | Chess GUI");
        alert.setHeaderText(String.format("Event: %s%nSite: %s%n"
                + "Date: %s%nWhite: %s%nBlack: %s%nResult: "
                + "%s%nOpening: %s%n", game.getEvent(),
                game.getSite(), game.getDate(), game.getWhite(),
                game.getBlack(), game.getResult(),
                game.getOpening(game.getMoves())));
        int count = game.getMoves().size();
        for (String move : game.getMoves()) {
            alert.setContentText(String.format("Move # %d:%n[White] %s     "
                    + "[Black]  %s%n",
                    game.getMoves().indexOf(move) + 1,
                    game.whiteMove(move), game.blackMove(move)));
            ButtonType buttonTypeOk = new ButtonType(--count + " Moves Left",
                    ButtonData.NEXT_FORWARD);
            ButtonType buttonTypeCancel = new ButtonType("Cancel",
                    ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOk) {
                continue;
            } else {
                break;
            }
        }
    }
    private void viewAllMoves(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().
                getResource("RookFavicon.png").toString()));

        alert.setTitle("Sarah Chen | Chess GUI");
        alert.setHeaderText(String.format("Event: %s%nSite: %s%n"
                + "Date: %s%nWhite: %s%nBlack: %s%nResult: "
                + "%s%nOpening: %s%n", game.getEvent(),
                game.getSite(), game.getDate(), game.getWhite(),
                game.getBlack(), game.getResult(),
                game.getOpening(game.getMoves())));
        String answer = "";
        for (int i = 1; i <= game.getMoves().size(); i++) {
            answer += String.format("%d. %s  ",
            i, game.getMove(i));
        }
        alert.setContentText(answer);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",
                ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeCancel) {
            alert.close();
        }
    }
}

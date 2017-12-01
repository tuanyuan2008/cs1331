import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Represents a chess GUI.
 *
 * @author schen475
 * @version 1.3
 */
public class ChessGui extends Application {
    private ChessDb chessDb = new ChessDb();
    private ObservableList<ChessGame> games =
        FXCollections.observableArrayList(chessDb.getGames());
    private TableView<ChessGame> mesa = createTable(games);

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

        Button viewButton = new Button("Play-by-Play");
        viewButton.setOnAction(e -> {
                ChessGame game = mesa.getSelectionModel().getSelectedItem();
                viewDialog(game);
            });
        viewButton.disableProperty().bind(Bindings
                .isNull(mesa.getSelectionModel().selectedItemProperty()));

        Button viewAllButton = new Button("All Moves");
        viewAllButton.setOnAction(e -> {
                ChessGame game = mesa.getSelectionModel().getSelectedItem();
                viewAllMoves(game);
            });
        viewAllButton.disableProperty().bind(Bindings
                .isNull(mesa.getSelectionModel().selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction(e -> Platform.exit());

        Button c = new Button("Load Folder");
        c.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();

                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("PGN File (*.pgn)",
                        "*.pgn");
                fileChooser.getExtensionFilters().add(extFilter);

                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    String fileName = fileContent(file.getPath());
                    ChessGame match = new ChessGame(tagValue("Event",
                            fileName), tagValue("Site", fileName),
                            tagValue("Date", fileName), tagValue("White",
                            fileName), tagValue("Black", fileName),
                            tagValue("Result", fileName));
                    int startingPos = fileName.indexOf("1. ",
                            fileName.lastIndexOf("]"));
                    String[] moves = fileName.substring(startingPos).
                            split("\\s+");
                    match.setMoves(moves);
                    getGames().add(match);
                }
            }
        });

        HBox buttonBox = new HBox();

        buttonBox.getChildren().addAll(c, viewButton,
                viewAllButton, dismissButton);

        Image imageSearch = new Image(getClass().
                getResourceAsStream("SearchFav.png"));
        Button btn = new Button();
        btn.setGraphic(new ImageView(imageSearch));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                event.consume();
            }
        });

        TextField search = new TextField();
        search.setPrefColumnCount(21);
        search.setPromptText("Search");
        search.textProperty().addListener(
            new ChangeListener() {
                public void changed(ObservableValue observable,
                    Object oldVar, Object newVar) {
                    searchKey((String) oldVar, (String) newVar);
                }
            });

        HBox searchBox = new HBox(btn, search);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchBox, mesa, buttonBox);
        final Scene scene = new Scene(vbox, 1275, 465);
        scene.setFill(null);
        stage.setScene(scene);
        stage.setTitle("Sarah Chen | Chess GUI");
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
    }

    /**
     * Lists the games present in the mesa.
     * @return the games in the database
     *
     */
    public ObservableList<ChessGame> getGames() {
        return games;
    }
    /**
     * Creates a table with the games in
     * the database.
     *
     * @param games the games in the database
     *
     */
    private TableView<ChessGame> createTable(ObservableList<ChessGame> events) {

        TableView<ChessGame> table = new TableView<ChessGame>();
        table.setItems(events);

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
                game.getOpening()));
        int count = game.getMoves().size();
        boolean cont = true;
        for (int i = 1; i <= game.getMoves().size() && cont; i++) {
            alert.setContentText(String.format("Move # %d:%n[White] %s     "
                    + "[Black]  %s%n",
                    i, game.whiteMove(game.getMove(i)),
                    game.blackMove(game.getMove(i))));
            ButtonType buttonTypeOk = new ButtonType(--count + " Moves Left",
                    ButtonData.NEXT_FORWARD);
            ButtonType buttonTypeCancel = new ButtonType("Cancel",
                    ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOk) {
                continue;
            } else {
                cont = false;
            }
        }
    }
    private void viewAllMoves(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        stage.getIcons().add(new Image(this.getClass().
                getResource("RookFavicon.png").toString()));

        alert.setTitle("Sarah Chen | Chess GUI");
        alert.setHeaderText(String.format("Event: %s%nSite: %s%n"
                + "Date: %s%nWhite: %s%nBlack: %s%nResult: "
                + "%s%nOpening: %s%n", game.getEvent(),
                game.getSite(), game.getDate(), game.getWhite(),
                game.getBlack(), game.getResult(),
                game.getOpening()));
        String answer = "";
        for (String move : game.getMoves()) {
            answer += String.format("%d. %s  ",
            game.getMoves().indexOf(move) + 1, move);
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

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        int valueBeg = game.indexOf(tagName) + tagName.length() + 1;
        if (valueBeg > tagName.length()) {
            String value = "";
            for (int i = valueBeg + 1; game.charAt(i) != '"'; i++) {
                value += game.charAt(i);
            }
            return value;
        } else {
            return "NOT GIVEN";
        }
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the4 file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param oldVar the old value
     * @param newVar the new value
     *
     */
    public void searchKey(String oldVar, String newVar) {
        if (oldVar != null && (newVar.length() < oldVar.length())) {
            mesa.setItems(games);
        }

        newVar = newVar.toUpperCase();

        ObservableList<ChessGame> subEntries =
                FXCollections.observableArrayList();
        for (ChessGame game: mesa.getItems()) {
            String text0 = (String) game.getEvent();
            if (text0.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text1 = (String) game.getOpening();
            if (text1.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text2 = (String) game.getSite();
            if (text2.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text3 = (String) game.getDate();
            if (text3.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text4 = (String) game.getWhite();
            if (text4.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text5 = (String) game.getBlack();
            if (text5.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
            String text6 = (String) game.getResult();
            if (text6.toUpperCase().contains(newVar)
                    && !subEntries.contains(game)) {
                subEntries.add(game);
            }
        }
        mesa.setItems(subEntries);
    }
}

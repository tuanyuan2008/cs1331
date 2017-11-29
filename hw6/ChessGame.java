import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a chess game.
 *
 * @author schen475
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private StringProperty opening = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
     * Contains all the aspects of a chess game.
     * @param event the event of the match
     * @param site the site of the match
     * @param date the date of the match
     * @param white the white player
     * @param black the black player
     * @param result the final result
     *
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }

    /**
     * Adds a move to the match.
     * @param move the move to be added
     *
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * Gives the nth move of the match.
     *
     * @param n the nth move
     * @return the nth move of the match.
     *
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }


    /**
     * Gives the moves of the match.
     *
     * @return the moves of the match.
     *
     */
    public List<String> getMoves() {
        return moves;
    }

    /**
     * Gives the event of the chess match.
     *
     * @return the event of the chess match.
     *
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * Gives the site of the chess match.
     *
     * @return the site of the chess match.
     *
     */
    public String getSite() {
        return site.get();
    }

    /**
     * Gives the date of the chess match.
     *
     * @return the date of the chess match.
     *
     */
    public String getDate() {
        return date.get();
    }

     /**
     * Gives white (player).
     *
     * @return white (player).
     *
     */
    public String getWhite() {
        return white.get();
    }

    /**
     * Gives white move.
     *
     * @param move the move being analyzed
     *
     * @return white move.
     *
     */
    public String whiteMove(String move) {
        String[] colorMoves = move.split("\\s+");
        return colorMoves[0];
    }

    /**
     * Gives black (player).
     *
     * @return black (player).
     *
     */
    public String getBlack() {
        return black.get();
    }

    /**
     * Gives white move.
     *
     * @param move the move being analyzed
     *
     * @return white move.
     *
     */
    public String blackMove(String move) {
        String[] colorMoves = move.split("\\s+");
        if (colorMoves.length == 2) {
            return colorMoves[1];
        }
        return result.get();
    }

    /**
     * Lists the results of the chess game.
     *
     * @return the result of the match.
     *
     */
    public String getResult() {
        return result.get();
    }

    /**
     * Lists the opening of the chess game.
     *
     * @param moves the lit of moves being analyzed
     *
     * @return the opening of the match.
     *
     */
    public String getOpening(List<String> mMoves) {
        if (mMoves.size() >= 3 && mMoves.get(0).equals("e4 e5")
                && mMoves.get(1).equals("Nf3 Nc6")
                && mMoves.get(2).equals("Bc4 Bc5")) {
            return "Giuoco Piano";
        } else if (mMoves.size() >= 3 && mMoves.get(0).equals("e4 e5")
                && mMoves.get(1).equals("Nf3 Nc6")
                && mMoves.get(2).split("\\s+")[0].equals("Bb5")) {
            return "Ruy Lopez";
        } else if (mMoves.size() >= 1 && mMoves.get(0).equals("e4 c5")) {
            return "Sicilian Defense";
        } else if (mMoves.size() >= 2 && mMoves.get(0).equals("d4 d5")
                && mMoves.get(1).split("\\s+")[0].equals("c4")) {
            return "Queen's Gambit";
        } else if (mMoves.size() >= 1 && mMoves.get(0).equals("d4 Nf6")) {
            return "Indian Defence";
        } else if (mMoves.size() >= 2 && mMoves.get(0).equals("e4 e5")
                && mMoves.get(1).equals("Nf3 d6")) {
            return "Philidor Defence";
        }
        return "--";
    }
}

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a chess game.
 *
 * @author schen475
 * @version 1.2
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
        this.opening.set("NOT GIVEN");
    }

    /**
     * Adds a move to the match.
     * @param move the move to be added
     *
     */
    public void addMove(String move) {
        moves.add(move);
        if (moves.size() >= 3 && getMove(1).equals("e4 e5")
                && getMove(2).equals("Nf3 Nc6")
                && getMove(3).equals("Bc4 Bc5")) {
            this.opening.set("Giuoco Piano");
        } else if (moves.size() >= 3 && getMove(1).equals("e4 e5")
                && getMove(2).equals("Nf3 Nc6")
                && getMove(3).split("\\s+")[0].equals("Bb5")) {
            this.opening.set("Ruy Lopez");
        } else if (moves.size() >= 1 && getMove(1).equals("e4 c5")) {
            this.opening.set("Sicilian Defense");
        } else if (moves.size() >= 2 && getMove(1).equals("d4 d5")
                && getMove(2).split("\\s+")[0].equals("c4")) {
            this.opening.set("Queen's Gambit");
        } else if (moves.size() >= 1 && getMove(1).equals("d4 Nf6")) {
            this.opening.set("Indian Defence");
        } else if (moves.size() >= 2 && getMove(1).equals("e4 e5")
                && getMove(2).equals("Nf3 d6")) {
            this.opening.set("Philidor Defence");
        } else {
            this.opening.set("--");
        }
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
     * @return the opening of the match.
     *
     */
    public String getOpening() {
        return opening.get();
    }

    /**
     * Sets the moves of the chess match.
     *
     * @param moves the moves of the match
     *
     */
    public void setMoves(String[] moves) {
        String wholeMove = "";
        int count = 0;
        for (String move : moves) {
            if (!Character.isDigit(move.charAt(0))) {
                wholeMove += String.format("%s ", move);
                if (++count % 2 == 0 || Arrays.asList(moves).
                        indexOf(move) == moves.length - 1) {
                    this.addMove(wholeMove);
                    wholeMove = "";
                }
            }
        }
    }
}

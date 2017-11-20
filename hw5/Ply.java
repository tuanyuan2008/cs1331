import java.util.Optional;

/**
 * Represents one player's move (either black or white).
 *
 * @author schen475
 * @version 1.0
 */
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;

/**
 * Simulates one move made by either black or white.
 *
 * @param piece the piece being played
 * @param from the starting square
 * @param to the ending square
 * @param comment optional comment???
 *
 */
    public Ply(Piece piece, Square from, Square to, Optional<String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = comment;
    }

    /**
     * @return this piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * @return this piece's starting square
     */
    public Square getFromSquare() {
        return this.from;
    }

    /**
     * @return this piece's ending square
     */
    public Square getToSquare() {
        return this.to;
    }

    /**
     * @return this piece's optional comment
     */
    public String getComment() {
        return this.comment;
    }
}
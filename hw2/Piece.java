/**
 * Represents a chess piece defined by color, algebraic
 * name, Fen name, and possible squares the piece
 * move to from its current square on a chess board
 * containing only the piece.
 *
 * @author schen475
 * @version 1
 */
public abstract class Piece {

    private Color pieceColor;

/**
 * Creates a piece with specified color.
 *
 * @param pieceColor the color of the piece
 *
 */
    public Piece(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

/**
 * @return this piece's color
 */
    public Color getColor() {
        return this.pieceColor;
    }

/**
 * @return the algebraic name of the piece.
 */
    public abstract String algebraicName();

/**
 * @return the Fen name of the piece.
 */
    public abstract String fenName();

/**
 * @return an array of possible locations the
 * piece can move to from its current
 * point.
 * @param square location of piece
 */
    public abstract Square[] movesFrom(Square square);

}
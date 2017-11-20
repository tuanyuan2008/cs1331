/**
 * Represents a pawn.
 *
 * @author schen475
 * @version 2.0
 */
public class Pawn extends Piece {

    /**
     * Creates a pawn with specified color.
     *
     * @param pieceColor the color of the pawn
     *
     */
    public Pawn(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this pawn's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name ""
     */
    public String algebraicName() {
        return "";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }

    /**
     *@return an array of possible locations
    * the pawn can move to from its current
    * point.
    * @param square location of pawn
    */
    public Square[] movesFrom(Square square) {
        char rank = square.getRank();
        char file = square.getFile();
        if (getColor() == Color.WHITE) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'),
                    new Square(file, '3')};
            } else {
                return new Square[]{new Square(file, (char) (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'),
                    new Square(file, '6')};
            } else {
                return new Square[]{new Square(file, (char) (rank - 1))};
            }
        }
    }

    /**
    *@return whether or not a position is valid
    *
    * @param file file of pawn
    * @param rank rank of pawn
    */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
}

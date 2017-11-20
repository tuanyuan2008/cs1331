/**
 * Represents a king.
 *
 * @author schen475
 * @version 2.0
 */
public class King extends Piece {

    /**
     * Creates a king with specified color.
     *
     * @param pieceColor the color of the king
     *
     */
    public King(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this king's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name "K"
     */
    public String algebraicName() {
        return "K";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "K";
        } else {
            return "k";
        }
    }

    /**
     * @return an array of possible locations
     * the king can move to from its current
     * point.
     * @param square location of king
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (isInBoard((char) (file + c), (char) (rank + r))) {
                    sq[counter++] = new Square((char) (file + c),
                        (char) (rank + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }

    /**
    *@return whether or not a position is valid
    *
    * @param file file of king
    * @param rank rank of king
    */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
}

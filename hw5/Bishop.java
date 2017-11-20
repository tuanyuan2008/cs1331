/**
 * Represents a bishop.
 *
 * @author schen475
 * @version 1.1
 */
public class Bishop extends Piece {

    /**
     * Creates a bishop with specified color.
     *
     * @param pieceColor the color of the bishop
     *
     */
    public Bishop(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this bishop's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name "B"
     */
    public String algebraicName() {
        return "B";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "B";
        } else {
            return "b";
        }
    }

    /**
     * @return an array of possible locations
     * the bishop can move to from its current
     * point.
     * @param square location of bishop
     */
    public Square[] movesFrom(Square square) {
        int count = 0;
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row >= '1';
                column--, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                count++;
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row <= '8';
                column--, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                count++;
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row >= '1';
                column++, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                count++;
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row <= '8';
                column++, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                count++;
            }
        }

        Square[] initSquares = new Square[count];

        int i = -1;
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row >= '1';
                column--, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                i++;
                initSquares[i] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row <= '8';
                column--, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                i++;
                initSquares[i] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row >= '1';
                column++, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                i++;
                initSquares[i] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row <= '8';
                column++, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                i++;
                initSquares[i] = new Square(column, row);
            }
        }
        return initSquares;
    }
}
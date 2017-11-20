/**
 * Represents a queen.
 *
 * @author schen475
 * @version 1.1
 */
public class Queen extends Piece {

    /**
     * Creates a queen with specified color.
     *
     * @param pieceColor the color of the queen
     *
     */
    public Queen(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this queen's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name "Q"
     */
    public String algebraicName() {
        return "Q";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "Q";
        } else {
            return "q";
        }
    }

    /**
     *@return an array of possible locations
    * the queen can move to from its current
    * point.
    * @param square location of queen
    */
    public Square[] movesFrom(Square square) {
        int count = 14;
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

        int index = -1;
        for (char i = '1'; i < square.getRank(); i++) {
            index++;
            initSquares[index] = new Square(square.getFile(), i);
        }
        for (char j = (char) (square.getRank() + 1); j <= '8'; j++) {
            index++;
            initSquares[index] = new Square(square.getFile(), j);
        }
        for (char m = 'a'; m < square.getFile(); m++) {
            index++;
            initSquares[index] = new Square(m, square.getRank());
        }
        for (char n = (char) (square.getFile() + 1); n <= 'h'; n++) {
            index++;
            initSquares[index] = new Square(n, square.getRank());
        }
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row >= '1';
                column--, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                index++;
                initSquares[index] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column >= 'a' && row <= '8';
                column--, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                index++;
                initSquares[index] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row >= '1';
                column++, row--) {
            if (column != square.getFile() && row != square.getRank()) {
                index++;
                initSquares[index] = new Square(column, row);
            }
        }
        for (char column = square.getFile(), row = square.getRank();
                column <= 'h' && row <= '8';
                column++, row++) {
            if (column != square.getFile() && row != square.getRank()) {
                index++;
                initSquares[index] = new Square(column, row);
            }
        }
        return initSquares;
    }
}
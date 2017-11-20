/**
 * Represents a rook.
 *
 * @author schen475
 * @version 1.1
 */
public class Rook extends Piece {

    /**
     * Creates a rook with specified color.
     *
     * @param pieceColor the color of the rook
     *
     */
    public Rook(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this rook's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name "R"
     */
    public String algebraicName() {
        return "R";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }

    /**
     *@return an array of possible locations
    * the rook can move to from its current
    * point.
    * @param square location of rook
    */
    public Square[] movesFrom(Square square) {
        int count = -1;
        Square[] initSquares = new Square[14];
        for (char i = '1'; i < square.getRank(); i++) {
            count++;
            initSquares[count] = new Square(square.getFile(), i);
        }
        for (char j = (char) (square.getRank() + 1); j <= '8'; j++) {
            count++;
            initSquares[count] = new Square(square.getFile(), j);
        }
        for (char m = 'a'; m < square.getFile(); m++) {
            count++;
            initSquares[count] = new Square(m, square.getRank());
        }
        for (char n = (char) (square.getFile() + 1); n <= 'h'; n++) {
            count++;
            initSquares[count] = new Square(n, square.getRank());
        }
        return initSquares;
    }
}
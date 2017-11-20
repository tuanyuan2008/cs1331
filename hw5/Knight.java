/**
 * Represents a knight.
 *
 * @author schen475
 * @version 1.1
 */
public class Knight extends Piece {

    /**
     * Creates a knight with specified color.
     *
     * @param pieceColor the color of the knight
     *
     */
    public Knight(Color pieceColor) {
        super(pieceColor);
    }

    /**
     * @return this knight's color
     */
    @Override
    public Color getColor() {
        return super.getColor();
    }

    /**
     * @return the algebraic name "N"
     */
    public String algebraicName() {
        return "N";
    }

    /**
     * @return the Fen name depending on
     * pieceColor
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }

    /**
     *@return an array of possible locations
    * the knight can move to from its current
    * point.
    * @param square location of knight
    */
    public Square[] movesFrom(Square square) {
        int count = 0;
        if ((char) (square.getFile() + 1) <= 'h'
                && (char) (square.getFile() + 1) >= 'a'
                && (char) (square.getRank() + 2) <= '8'
                && (char) (square.getRank() + 2) >= '1') {
            count++;
        }
        if ((char) (square.getFile() - 1) <= 'h'
                && (char) (square.getFile() - 1) >= 'a'
                && (char) (square.getRank() + 2) <= '8'
                && (char) (square.getRank() + 2) >= '1') {
            count++;
        }
        if ((char) (square.getFile() + 1) <= 'h'
                && (char) (square.getFile() + 1) >= 'a'
                && (char) (square.getRank() - 2) <= '8'
                && (char) (square.getRank() - 2) >= '1') {
            count++;
        }
        if ((char) (square.getFile() - 1) <= 'h'
                && (char) (square.getFile() - 1) >= 'a'
                && (char) (square.getRank() - 2) <= '8'
                && (char) (square.getRank() - 2) >= '1') {
            count++;
        }
        if ((char) (square.getFile() + 2) <= 'h'
                && (char) (square.getFile() + 2) >= 'a'
                && (char) (square.getRank() + 1) <= '8'
                && (char) (square.getRank() + 1) >= '1') {
            count++;
        }
        if ((char) (square.getFile() - 2) <= 'h'
                && (char) (square.getFile() - 2) >= 'a'
                && (char) (square.getRank() + 1) <= '8'
                && (char) (square.getRank() + 1) >= '1') {
            count++;
        }
        if ((char) (square.getFile() + 2) <= 'h'
                && (char) (square.getFile() + 2) >= 'a'
                && (char) (square.getRank() - 1) <= '8'
                && (char) (square.getRank() - 1) >= '1') {
            count++;
        }
        if ((char) (square.getFile() - 2) <= 'h'
                && (char) (square.getFile() - 2) >= 'a'
                && (char) (square.getRank() - 1) <= '8'
                && (char) (square.getRank() - 1) >= '1') {
            count++;
        }

        Square[] initSquares = new Square[count];

        int i = -1;
        if ((char) (square.getFile() + 1) <= 'h'
                && (char) (square.getFile() + 1) >= 'a'
                && (char) (square.getRank() + 2) <= '8'
                && (char) (square.getRank() + 2) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() + 1),
                    (char) (square.getRank() + 2));
        }
        if ((char) (square.getFile() - 1) <= 'h'
                && (char) (square.getFile() - 1) >= 'a'
                && (char) (square.getRank() + 2) <= '8'
                && (char) (square.getRank() + 2) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() - 1),
                    (char) (square.getRank() + 2));
        }
        if ((char) (square.getFile() + 1) <= 'h'
                && (char) (square.getFile() + 1) >= 'a'
                && (char) (square.getRank() - 2) <= '8'
                && (char) (square.getRank() - 2) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() + 1),
                    (char) (square.getRank() - 2));
        }
        if ((char) (square.getFile() - 1) <= 'h'
                && (char) (square.getFile() - 1) >= 'a'
                && (char) (square.getRank() - 2) <= '8'
                && (char) (square.getRank() - 2) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() - 1),
                    (char) (square.getRank() - 2));
        }
        if ((char) (square.getFile() + 2) <= 'h'
                && (char) (square.getFile() + 2) >= 'a'
                && (char) (square.getRank() + 1) <= '8'
                && (char) (square.getRank() + 1) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() + 2),
                    (char) (square.getRank() + 1));
        }
        if ((char) (square.getFile() - 2) <= 'h'
                && (char) (square.getFile() - 2) >= 'a'
                && (char) (square.getRank() + 1) <= '8'
                && (char) (square.getRank() + 1) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() - 2),
                    (char) (square.getRank() + 1));
        }
        if ((char) (square.getFile() + 2) <= 'h'
                && (char) (square.getFile() + 2) >= 'a'
                && (char) (square.getRank() - 1) <= '8'
                && (char) (square.getRank() - 1) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() + 2),
                    (char) (square.getRank() - 1));
        }
        if ((char) (square.getFile() - 2) <= 'h'
                && (char) (square.getFile() - 2) >= 'a'
                && (char) (square.getRank() - 1) <= '8'
                && (char) (square.getRank() - 1) >= '1') {
            i++;
            initSquares[i] = new Square((char) (square.getFile() - 2),
                    (char) (square.getRank() - 1));
        }
        return initSquares;
    }
}
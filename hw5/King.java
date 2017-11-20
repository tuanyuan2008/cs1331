/**
 * Represents a king.
 *
 * @author schen475
 * @version 1.1
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
        int count = 0;

        Square[] initSquaresAll = {
            new Square((char) (square.getFile() - 1),
                    square.getRank()),
            new Square((char) (square.getFile() + 1),
                    square.getRank()),
            new Square(square.getFile(),
                    (char) (square.getRank() - 1)),
            new Square(square.getFile(),
                    (char) (square.getRank() + 1)),
            new Square((char) (square.getFile() - 1),
                    (char) (square.getRank() - 1)),
            new Square((char) (square.getFile() + 1),
                    (char) (square.getRank() - 1)),
            new Square((char) (square.getFile() - 1),
                    (char) (square.getRank() + 1)),
            new Square((char) (square.getFile() + 1),
                    (char) (square.getRank() + 1))
        };

        for (Square squareAll : initSquaresAll) {
            if (squareAll.getFile() >= 'a'
                    && squareAll.getFile() <= 'h'
                    && squareAll.getRank() >= '1'
                    && squareAll.getRank() <= '8') {
                count++;
            }
        }

        Square[] initSquares = new Square[count];
        int i = -1;
        for (Square squareAll : initSquaresAll) {
            if (squareAll.getFile() >= 'a'
                    && squareAll.getFile() <= 'h'
                    && squareAll.getRank() >= '1'
                    && squareAll.getRank() <= '8') {
                i++;
                initSquares[i] = squareAll;
            }
        }
        return initSquares;
    }
}
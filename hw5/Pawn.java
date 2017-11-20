/**
 * Represents a pawn.
 *
 * @author schen475
 * @version 1.1
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
        Square[] initSquaresAll = new Square[2];
        int count;
        if (this.getColor() == Color.WHITE) {
            initSquaresAll[0] = new Square(square.getFile(),
                    (char) (square.getRank() + 1));
            initSquaresAll[1] = new Square(square.getFile(),
                    (char) (square.getRank() + 2));
            if (square.getRank() == '2') {
                count = 2;
            } else if (square.getRank() == '1'
                    || square.getRank() == '8') {
                count = 0;
            } else {
                count = 1;
            }
        } else {
            initSquaresAll[0] = new Square(square.getFile(),
                    (char) (square.getRank() - 1));
            initSquaresAll[1] = new Square(square.getFile(),
                    (char) (square.getRank() - 2));
            if (square.getRank() == '7') {
                count = 2;
            } else if (square.getRank() == '1'
                    || square.getRank() == '8') {
                count = 0;
            } else {
                count = 1;
            }
        }

        Square[] initSquares = new Square[count];

        for (int i = 0; i < count; i++) {
            initSquares[i] = initSquaresAll[i];
        }

        return initSquares;
    }
}
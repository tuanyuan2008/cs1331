/**
 * Represents the exception that occurs
 * when a square outside a valid chess board
 * is called. This is an unchecked exception
 * because you can't read the PGN file until
 * runtime.
 *
 * @author schen475
 * @version 1.0
 */

class InvalidSquareException extends RuntimeException {

    /**
     * Checks whether the given square
     * is legal inside of a standard chessboard.
     *
     * @param square the square passed in.
     */
    InvalidSquareException(String square) {
        super(square);
    }
}

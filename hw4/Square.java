/**
 * Represents a chessboard tile characterized by file and rank.
 *
 * @author schen475
 * @version 1.1
 */
public class Square {

    private char file;
    private char rank;
    private String name;

/**
 * Creates a square with file and rank as parameters.
 *
 * @param file the file of the square
 * @param rank the rank of the square
 *
 */
    public Square(char file, char rank) {
        if ('a' <= file && file <= 'h') {
            this.file = file;
        } else {
            throw new InvalidSquareException("" + file + rank);
        }
        if ('1' <= rank && rank <= '8') {
            this.rank = rank;
        } else {
            throw new InvalidSquareException("" + file + rank);
        }
        this.name = "" + file + rank;
    }

/**
 * Creates a square with name in algebraic notation.
 *
 * @param name combined file and rank
 *
 */
    public Square(String name) {
        if (name.length() == 2) {
            if ('a' <= name.charAt(0) && name.charAt(0) <= 'h') {
                this.file = name.charAt(0);
            } else {
                throw new InvalidSquareException(name);
            }
            if ('1' <= name.charAt(1) && name.charAt(1) <= '8') {
                this.rank = name.charAt(1);
            } else {
                throw new InvalidSquareException(name);
            }
            this.name = name;
        } else {
            throw new InvalidSquareException(name);
        }
    }

/**
 * @return this piece's file
 */
    public char getFile() {
        return this.file;
    }

/**
 * @return this piece's rank
 */
    public char getRank() {
        return this.rank;
    }

/**
 * @return this square's name in algebraic notation
 */
    @Override
    public String toString() {
        return this.name;
    }

/**
 * @return true if this object is the same as the obj
 * argument; false otherwise
 *
 * @param obj the reference object with which to be
 * compared with
 *
 */
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Square) {
            Square that = (Square) obj;
            if (that.file == this.file && that.rank == this.rank) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}

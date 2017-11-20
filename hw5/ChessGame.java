import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a complete chess game; a sequence of moves.
 *
 * @author schen475
 * @version 1.0
 */
public class ChessGame {
    private List<Move> moves;

    /**
     * Simulates one move made by either black or white.
     *
     * @param moves the moves made in the chess match
     *
     */
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }


    /**
     * @param n the nth move
     *
     * @return the nth move
     */
    public Move getMove(int n) {
        return this.moves.get(n);
    }

    /**
     * @param filter the filter applied to the predicate
     *
     * @return the valid moves
     */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> valid = new ArrayList<>();
        for (Move move : valid) {
            if (filter.test(move)) {
                valid.add(move);
            }
        }
        return valid;
    }

    /**
     * @return the moves with optional comment
     */
    public List<Move> getMovesWithComment() {
        List<Move> valid = this.filter(a -> !a.getBlackPly()
                .getComment().isPresent()
                || !a.getWhitePly().getComment().isPresent());
        return valid;
    }


     /**
     * @return the moves without optional comment
     */
    public List<Move> getMovesWithoutComment() {
        List<Move> valid = this.filter(new Predicate<Move>() {
            public boolean test(Move move) {
                return move.getBlackPly().getComment().isPresent()
                        || move.getWhitePly().getComment().isPresent();
            }
        });
        return valid;
    }

    /**
     * @param p the specified piece
     *
     * @return the moves involving the specified piece
     */
    public List<Move> getMovesWithPiece(Piece p) {
        List<Move> containsP = this.filter(new WithPiece(p));
        return containsP;
    }

    private class WithPiece implements Predicate<Move> {
        private Piece p;

        public WithPiece(Piece p) {
            this.p = p;
        }

        public boolean test(Move move) {
            return move.getBlackPly().getPiece().algebraicName()
                    .equals(this.p.algebraicName())
                    || move.getWhitePly().getPiece().algebraicName()
                    .equals(this.p.algebraicName());
        }
    }
}
/**
 * Represents a single move or two plies.
 *
 * @author schen475
 * @version 1.0
 */
public class Move {
    private Ply whitePly;
    private Ply blackPly;

    /**
     * Simulates one move made by either black or white.
     *
     * @param whitePly the move that white makes
     * @param blackPly the move that black makes
     *
     */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    /**
     * @return white's move
     */
    public Ply getWhitePly() {
        return this.whitePly;
    }

    /**
     * @return black's move
     */
    public Ply getBlackPly() {
        return this.blackPly;
    }
}
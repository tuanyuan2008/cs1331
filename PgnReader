import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    private static char[][] chessboard = {
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    private static boolean whiteMove = true;

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        int valueBeg = game.indexOf(tagName) + tagName.length() + 1;
        if (valueBeg > tagName.length()) {
            String value = "";
            for (int i = valueBeg + 1; game.charAt(i) != '"'; i++) {
                value += game.charAt(i);
            }
            return value;
        } else {
            return "NOT GIVEN";
        }
    }

    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `String` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {

        int startingPos = game.indexOf("1. ", game.lastIndexOf("]"));

        String[] moves = game.substring(startingPos).split("\\s+");

        for (String move : moves) {
            if (!Character.isDigit(move.charAt(0))) {
                char moveInit = move.charAt(0);

                int moveLen = move.length();
                String movedPieceTrim = move.replaceAll("[^a-zA-Z0-9]", "");

                if (!Character.isUpperCase(moveInit)) {
                    pawnMovement(movedPieceTrim);
                } else if (moveInit == 'N') {
                    knightMovement(movedPieceTrim);
                } else if (moveInit == 'B') {
                    checkDiagonal(movedPieceTrim);
                } else if (moveInit == 'Q') {
                    checkDiagonal(movedPieceTrim);
                    checkRC(movedPieceTrim);
                } else if (moveInit == 'R') {
                    checkRC(movedPieceTrim);
                } else if (moveInit == 'O') {
                    castlePieces(movedPieceTrim);
                } else if (moveInit == 'K') {
                    kingMovement(movedPieceTrim);
                }

                whiteMove = !whiteMove;
            }
        }

        String resultFen = "";
        for (char[] row : chessboard) {
            int counter = 0;
            for (char c : row) {
                if (c != ' ') {
                    if (counter != 0) {
                        resultFen += counter;
                        counter = 0;
                    }
                    resultFen += c;
                } else {
                    counter++;
                }
            }
            if (counter != 0) {
                resultFen += counter;
            }
            resultFen += "/";
        }
        resultFen = resultFen.substring(0, resultFen.length() - 1);
        return resultFen;
    }

    public static void pawnMovement(String trimPiece) {
        int moveLen = trimPiece.length();
        if (!Character.isUpperCase(trimPiece.charAt(moveLen - 1))) {
            int moveRow = '8' - trimPiece.charAt(moveLen - 1);
            int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

            if (whiteMove) {
                chessboard[moveRow][moveCol] = 'P';
                if (trimPiece.contains("x")) {
                    chessboard[moveRow + 1][trimPiece.charAt(0) - 'a'] = ' ';
                    if (chessboard[moveRow + 1][moveCol] == 'p') {
                        chessboard[moveRow + 1][moveCol] = ' ';
                    }
                } else if (chessboard[moveRow + 1][moveCol] == 'P') {
                    chessboard[moveRow + 1][moveCol] = ' ';
                } else {
                    chessboard[moveRow + 2][moveCol] = ' ';
                }
            } else {
                chessboard[moveRow][moveCol] = 'p';
                if (trimPiece.contains("x")) {
                    chessboard[moveRow - 1][trimPiece.charAt(0) - 'a'] = ' ';
                    if (chessboard[moveRow - 1][moveCol] == 'P') {
                        chessboard[moveRow - 1][moveCol] = ' ';
                    }
                } else if (chessboard[moveRow - 1][moveCol] == 'p') {
                    chessboard[moveRow - 1][moveCol] = ' ';
                } else {
                    chessboard[moveRow - 2][moveCol] = ' ';
                }
            }
        } else {
            int moveRow = '8' - trimPiece.charAt(moveLen - 2);
            int moveCol = trimPiece.charAt(moveLen - 3) - 'a';
            char promote = trimPiece.charAt(moveLen - 1);

            if (whiteMove) {
                chessboard[moveRow][moveCol] = promote;
                if (trimPiece.contains("x")) {
                    chessboard[moveRow + 1][trimPiece.charAt(0) - 'a'] = ' ';
                } else if (chessboard[moveRow + 1][moveCol] == 'P') {
                    chessboard[moveRow + 1][moveCol] = ' ';
                } else {
                    chessboard[moveRow + 2][moveCol] = ' ';
                }
            } else {
                chessboard[moveRow][moveCol] = Character.toLowerCase(promote);
                if (trimPiece.contains("x")) {
                    chessboard[moveRow - 1][trimPiece.charAt(0) - 'a'] = ' ';
                } else if (chessboard[moveRow - 1][moveCol] == 'p') {
                    chessboard[moveRow - 1][moveCol] = ' ';
                } else {
                    chessboard[moveRow - 2][moveCol] = ' ';
                }
            }
        }
    }

    public static void knightMovement(String trimPiece) {

        trimPiece = trimPiece.replace("x", "");

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';
        if (moveLen > 3) {
            knightAmbiguity(trimPiece);
        } else {
            if (whiteMove) {
                chessboard[moveRow][moveCol] = 'N';
                if (moveRow - 2 < chessboard.length
                        && moveRow - 2 >= 0
                        && moveCol - 1 < chessboard[0].length
                        && moveCol - 1 >= 0
                        && chessboard[moveRow - 2][moveCol - 1] == 'N') {
                    chessboard[moveRow - 2][moveCol - 1] = ' ';
                } else if (moveRow - 2 < chessboard.length
                        && moveRow - 2 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && moveCol + 1 >= 0
                        && chessboard[moveRow - 2][moveCol + 1] == 'N') {
                    chessboard[moveRow - 2][moveCol + 1] = ' ';
                } else if (moveRow + 2 < chessboard.length
                        && moveRow + 2 >= 0
                        && moveCol - 1 < chessboard[0].length
                        && moveCol - 1 >= 0
                        && chessboard[moveRow + 2][moveCol - 1] == 'N') {
                    chessboard[moveRow + 2][moveCol - 1] = ' ';
                } else if (moveRow + 2 < chessboard.length
                        && moveRow + 2 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && moveCol + 1 >= 0
                        && chessboard[moveRow + 2][moveCol + 1] == 'N') {
                    chessboard[moveRow + 2][moveCol + 1] = ' ';
                } else if (moveRow - 1 < chessboard.length
                        && moveRow - 1 >= 0
                        && moveCol - 2 < chessboard[0].length
                        && moveCol - 2 >= 0
                        && chessboard[moveRow - 1][moveCol - 2] == 'N') {
                    chessboard[moveRow - 1][moveCol - 2] = ' ';
                } else if (moveRow - 1 < chessboard.length
                        && moveRow - 1 >= 0
                        && moveCol + 2 < chessboard[0].length
                        && moveCol + 2 >= 0
                        && chessboard[moveRow - 1][moveCol + 2] == 'N') {
                    chessboard[moveRow - 1][moveCol + 2] = ' ';
                } else if (moveRow + 1 < chessboard.length
                        && moveRow + 1 >= 0
                        && moveCol - 2 < chessboard[0].length
                        && moveCol - 2 >= 0
                        && chessboard[moveRow + 1][moveCol - 2] == 'N') {
                    chessboard[moveRow + 1][moveCol - 2] = ' ';
                } else {
                    chessboard[moveRow + 1][moveCol + 2] = ' ';
                }
            } else {
                chessboard[moveRow][moveCol] = 'n';
                if (moveRow - 2 < chessboard.length
                        && moveRow - 2 >= 0
                        && moveCol - 1 < chessboard[0].length
                        && moveCol - 1 >= 0
                        && chessboard[moveRow - 2][moveCol - 1] == 'n') {
                    chessboard[moveRow - 2][moveCol - 1] = ' ';
                } else if (moveRow - 2 < chessboard.length
                        && moveRow - 2 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && moveCol + 1 >= 0
                        && chessboard[moveRow - 2][moveCol + 1] == 'n') {
                    chessboard[moveRow - 2][moveCol + 1] = ' ';
                } else if (moveRow + 2 < chessboard.length
                        && moveRow + 2 >= 0
                        && moveCol - 1 < chessboard[0].length
                        && moveCol - 1 >= 0
                        && chessboard[moveRow + 2][moveCol - 1] == 'n') {
                    chessboard[moveRow + 2][moveCol - 1] = ' ';
                } else if (moveRow + 2 < chessboard.length
                        && moveRow + 2 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && moveCol + 1 >= 0
                        && chessboard[moveRow + 2][moveCol + 1] == 'n') {
                    chessboard[moveRow + 2][moveCol + 1] = ' ';
                } else if (moveRow - 1 < chessboard.length
                        && moveRow - 1 >= 0
                        && moveCol - 2 < chessboard[0].length
                        && moveCol - 2 >= 0
                        && chessboard[moveRow - 1][moveCol - 2] == 'n') {
                    chessboard[moveRow - 1][moveCol - 2] = ' ';
                } else if (moveRow - 1 < chessboard.length
                        && moveRow - 1 >= 0
                        && moveCol + 2 < chessboard[0].length
                        && moveCol + 2 >= 0
                        && chessboard[moveRow - 1][moveCol + 2] == 'n') {
                    chessboard[moveRow - 1][moveCol + 2] = ' ';
                } else if (moveRow + 1 < chessboard.length
                        && moveRow + 1 >= 0
                        && moveCol - 2 < chessboard[0].length
                        && moveCol - 2 >= 0
                        && chessboard[moveRow + 1][moveCol - 2] == 'n') {
                    chessboard[moveRow + 1][moveCol - 2] = ' ';
                } else {
                    chessboard[moveRow + 1][moveCol + 2] = ' ';
                }
            }
        }

    }

    public static void checkDiagonal(String trimPiece) {

        trimPiece = trimPiece.replace("x", "");

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);

        boolean match = false;
        if (moveLen > 3) {
            checkDiagonalDisambiguation(trimPiece);
        } else {
            if (whiteMove) {
                for (int i = moveRow - 1, j = moveCol - 1;
                        i >= 0 && j >= 0;
                        i--, j--) {
                    if (chessboard[i][j] != ' '
                            && chessboard[i][j] != piece) {
                        i = -1;
                        j = -1;
                    } else if (chessboard[i][j] == piece) {
                        chessboard[i][j] = ' ';
                        i = -1;
                        j = -1;
                        match = true;
                    }
                }
                if (!match) {
                    for (int i = moveRow + 1, j = moveCol - 1;
                            i < chessboard.length && j >= 0;
                            i++, j--) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != piece) {
                            i = chessboard.length;
                            j = -1;
                        } else if (chessboard[i][j] == piece) {
                            chessboard[i][j] = ' ';
                            i = chessboard.length;
                            j = -1;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int i = moveRow + 1, j = moveCol + 1;
                            i < chessboard.length && j < chessboard[0].length;
                            i++, j++) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != piece) {
                            i = chessboard.length;
                            j = chessboard[0].length;
                        } else if (chessboard[i][j] == piece) {
                            chessboard[i][j] = ' ';
                            i = chessboard.length;
                            j = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int i = moveRow - 1, j = moveCol + 1;
                            i >= 0 && j < chessboard[0].length;
                            i--, j++) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != piece) {
                            i = -1;
                            j = chessboard[0].length;
                        } else if (chessboard[i][j] == piece) {
                            chessboard[i][j] = ' ';
                            i = -1;
                            j = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                chessboard[moveRow][moveCol] = piece;
            } else {
                for (int i = moveRow - 1, j = moveCol - 1;
                        i >= 0 && j >= 0;
                        i--, j--) {
                    if (chessboard[i][j] != ' '
                            && chessboard[i][j] != pieceL) {
                        i = -1;
                        j = -1;
                    } else if (chessboard[i][j] == pieceL) {
                        chessboard[i][j] = ' ';
                        i = -1;
                        j = -1;
                        match = true;
                    }
                }
                if (!match) {
                    for (int i = moveRow + 1, j = moveCol - 1;
                            i < chessboard.length && j >= 0;
                            i++, j--) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != pieceL) {
                            i = chessboard.length;
                            j = -1;
                        } else if (chessboard[i][j] == pieceL) {
                            chessboard[i][j] = ' ';
                            i = chessboard.length;
                            j = -1;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int i = moveRow + 1, j = moveCol + 1;
                            i < chessboard.length && j < chessboard[0].length;
                            i++, j++) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != pieceL) {
                            i = chessboard.length;
                            j = chessboard[0].length;
                        } else if (chessboard[i][j] == pieceL) {
                            chessboard[i][j] = ' ';
                            i = chessboard.length;
                            j = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int i = moveRow - 1, j = moveCol + 1;
                            i >= 0 && j < chessboard[0].length;
                            i--, j++) {
                        if (chessboard[i][j] != ' '
                                && chessboard[i][j] != pieceL) {
                            i = -1;
                            j = chessboard[0].length;
                        } else if (chessboard[i][j] == pieceL) {
                            chessboard[i][j] = ' ';
                            i = -1;
                            j = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                chessboard[moveRow][moveCol] = pieceL;
            }
        }

    }

    public static void castlePieces(String trimPiece) {
        if (trimPiece.length() == 2) {
            if (whiteMove) {
                chessboard[7][7] = ' ';
                chessboard[7][6] = 'K';
                chessboard[7][5] = 'R';
                chessboard[7][4] = ' ';
            } else {
                chessboard[0][7] = ' ';
                chessboard[0][6] = 'k';
                chessboard[0][5] = 'r';
                chessboard[0][4] = ' ';
            }
        } else {
            if (whiteMove) {
                chessboard[7][0] = ' ';
                chessboard[7][2] = 'K';
                chessboard[7][3] = 'R';
                chessboard[7][4] = ' ';
            } else {
                chessboard[0][0] = ' ';
                chessboard[0][2] = 'k';
                chessboard[0][3] = 'r';
                chessboard[0][4] = ' ';
            }
        }
    }

    public static void kingMovement(String trimPiece) {

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

        if (whiteMove) {
            chessboard[moveRow][moveCol] = 'K';
            if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && chessboard[moveRow + 1][moveCol] == 'K') {
                chessboard[moveRow + 1][moveCol] = ' ';
            } else if (moveRow - 1 < chessboard.length
                    && moveRow - 1 >= 0
                    && chessboard[moveRow - 1][moveCol] == 'K') {
                chessboard[moveRow - 1][moveCol] = ' ';
            } else if (moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow][moveCol + 1] == 'K') {
                chessboard[moveRow][moveCol + 1] = ' ';
            } else if (moveCol - 1 < chessboard[0].length
                    && moveCol - 1 >= 0
                    && chessboard[moveRow][moveCol - 1] == 'K') {
                chessboard[moveRow][moveCol - 1] = ' ';
            } else if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow + 1][moveCol + 1] == 'K') {
                chessboard[moveRow + 1][moveCol + 1] = ' ';
            } else if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && moveCol - 1 < chessboard[0].length
                    && moveCol - 1 >= 0
                    && chessboard[moveRow + 1][moveCol - 1] == 'K') {
                chessboard[moveRow + 1][moveCol - 1] = ' ';
            } else if (moveRow - 1 < chessboard.length
                    && moveRow - 1 >= 0
                    && moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow - 1][moveCol + 1] == 'K') {
                chessboard[moveRow - 1][moveCol + 1] = ' ';
            } else {
                chessboard[moveRow - 1][moveCol - 1] = ' ';
            }
        } else {
            chessboard[moveRow][moveCol] = 'k';
            if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && chessboard[moveRow + 1][moveCol] == 'k') {
                chessboard[moveRow + 1][moveCol] = ' ';
            } else if (moveRow - 1 < chessboard.length
                    && moveRow - 1 >= 0
                    && chessboard[moveRow - 1][moveCol] == 'k') {
                chessboard[moveRow - 1][moveCol] = ' ';
            } else if (moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow][moveCol + 1] == 'k') {
                chessboard[moveRow][moveCol + 1] = ' ';
            } else if (moveCol - 1 < chessboard[0].length
                    && moveCol - 1 >= 0
                    && chessboard[moveRow][moveCol - 1] == 'k') {
                chessboard[moveRow][moveCol - 1] = ' ';
            } else if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow + 1][moveCol + 1] == 'k') {
                chessboard[moveRow + 1][moveCol + 1] = ' ';
            } else if (moveRow + 1 < chessboard.length
                    && moveRow + 1 >= 0
                    && moveCol - 1 < chessboard[0].length
                    && moveCol - 1 >= 0
                    && chessboard[moveRow + 1][moveCol - 1] == 'k') {
                chessboard[moveRow + 1][moveCol - 1] = ' ';
            } else if (moveRow - 1 < chessboard.length
                    && moveRow - 1 >= 0
                    && moveCol + 1 < chessboard[0].length
                    && moveCol + 1 >= 0
                    && chessboard[moveRow - 1][moveCol + 1] == 'k') {
                chessboard[moveRow - 1][moveCol + 1] = ' ';
            } else {
                chessboard[moveRow - 1][moveCol - 1] = ' ';
            }
        }
    }

    public static void checkRC(String trimPiece) {

        trimPiece = trimPiece.replace("x", "");

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);

        boolean match = false;

        if (moveLen > 3) {
            checkRCDisambiguation(trimPiece);
        } else {
            if (whiteMove) {
                for (int i = moveRow - 1; i >= 0; i--) {
                    if (chessboard[i][moveCol] != ' '
                            && chessboard[i][moveCol] != piece) {
                        i = -1;
                    } else if (chessboard[i][moveCol] == piece) {
                        chessboard[i][moveCol] = ' ';
                        i = -1;
                        match = true;
                    }
                }
                if (!match) {
                    for (int j = moveRow + 1; j < chessboard.length; j++) {
                        if (chessboard[j][moveCol] != ' '
                                && chessboard[j][moveCol] != piece) {
                            j = chessboard.length;
                        } else if (chessboard[j][moveCol] == piece) {
                            chessboard[j][moveCol] = ' ';
                            j = chessboard.length;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int m = moveCol - 1; m >= 0; m--) {
                        if (chessboard[moveRow][m] != ' '
                                && chessboard[moveRow][m] != piece) {
                            m = -1;
                        } else if (chessboard[moveRow][m] == piece) {
                            chessboard[moveRow][m] = ' ';
                            m = -1;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int n = moveCol + 1; n < chessboard[0].length; n++) {
                        if (chessboard[moveRow][n] != ' '
                                && chessboard[moveRow][n] != piece) {
                            n = chessboard[0].length;
                        } else if (chessboard[moveRow][n] == piece) {
                            chessboard[moveRow][n] = ' ';
                            n = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                chessboard[moveRow][moveCol] = piece;
            } else {
                for (int i = moveRow - 1; i >= 0; i--) {
                    if (chessboard[i][moveCol] != ' '
                            && chessboard[i][moveCol] != pieceL) {
                        i = -1;
                    } else if (chessboard[i][moveCol] == pieceL) {
                        chessboard[i][moveCol] = ' ';
                        i = -1;
                        match = true;
                    }
                }
                if (!match) {
                    for (int j = moveRow + 1; j < chessboard.length; j++) {
                        if (chessboard[j][moveCol] != ' '
                                && chessboard[j][moveCol] != pieceL) {
                            j = chessboard.length;
                        } else if (chessboard[j][moveCol] == pieceL) {
                            chessboard[j][moveCol] = ' ';
                            j = chessboard.length;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int m = moveCol - 1; m >= 0; m--) {
                        if (chessboard[moveRow][m] != ' '
                                && chessboard[moveRow][m] != pieceL) {
                            m = -1;
                        } else if (chessboard[moveRow][m] == pieceL) {
                            chessboard[moveRow][m] = ' ';
                            m = -1;
                            match = true;
                        }
                    }
                }
                if (!match) {
                    for (int n = moveCol + 1; n < chessboard[0].length; n++) {
                        if (chessboard[moveRow][n] != ' '
                                && chessboard[moveRow][n] != pieceL) {
                            n = chessboard[0].length;
                        } else if (chessboard[moveRow][n] == pieceL) {
                            chessboard[moveRow][n] = ' ';
                            n = chessboard[0].length;
                            match = true;
                        }
                    }
                }
                chessboard[moveRow][moveCol] = pieceL;
            }
        }
    }

    public static void checkRCDisambiguation(String trimPiece) {

        trimPiece = trimPiece.replace("x", "");

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);

        boolean match = false;

        if (whiteMove) {
            if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
                int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';

                chessboard[moveRow][moveColAlt] = ' ';
            } else {
                int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
                chessboard[moveRowAlt][moveCol] = ' ';
            }
            chessboard[moveRow][moveCol] = piece;
        } else {
            if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
                int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';
                chessboard[moveRow][moveColAlt] = ' ';
            } else {
                int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
                chessboard[moveRowAlt][moveCol] = ' ';
            }
            chessboard[moveRow][moveCol] = pieceL;
        }
    }

    public static void checkDiagonalDisambiguation(String trimPiece) {

        trimPiece = trimPiece.replace("x", "");

        int moveLen = trimPiece.length();

        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';

        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);

        boolean match = false;

        if (whiteMove) {
            if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
                int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';
                int difference = Math.abs(moveColAlt - moveCol);
                int moveRowAlt = moveRow + difference;
                if (moveRowAlt >= 0
                        && moveRowAlt < chessboard.length
                        && chessboard[moveRowAlt][moveColAlt] == piece) {
                    chessboard[moveRowAlt][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - difference][moveColAlt] = ' ';
                }
            } else {
                int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
                int difference = Math.abs(moveRowAlt - moveCol);
                int moveColAlt = moveCol + difference;
                if (moveColAlt >= 0
                        && moveColAlt < chessboard[0].length
                        && chessboard[moveRowAlt][moveColAlt] == piece) {
                    chessboard[moveRowAlt][moveColAlt] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - difference] = ' ';
                }
            }
            chessboard[moveRow][moveCol] = piece;
        } else {
            if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
                int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';
                int difference = Math.abs(moveColAlt - moveCol);
                int moveRowAlt = moveRow + difference;
                if (moveRowAlt >= 0
                        && moveRowAlt < chessboard.length
                        && chessboard[moveRowAlt][moveColAlt] == pieceL) {
                    chessboard[moveRowAlt][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - difference][moveColAlt] = ' ';
                }
            } else {
                int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
                int difference = Math.abs(moveRowAlt - moveCol);
                int moveColAlt = moveCol + difference;
                if (moveColAlt >= 0
                        && moveColAlt < chessboard[0].length
                        && chessboard[moveRowAlt][moveColAlt] == pieceL) {
                    chessboard[moveRowAlt][moveColAlt] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - difference] = ' ';
                }
            }
            chessboard[moveRow][moveCol] = pieceL;
        }
    }

    public static void knightAmbiguity(String trimPiece) {
        trimPiece = trimPiece.replace("x", "");
        int moveLen = trimPiece.length();
        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';
        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);
        boolean match = false;
        if (whiteMove) {
            if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
                int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';
                if (moveColAlt - moveCol == 1) {
                    if (moveRow + 2 >= 0
                            && moveRow + 2 < chessboard.length
                            && chessboard[moveRow + 2][moveColAlt] == piece) {
                        chessboard[moveRow + 2][moveColAlt] = ' ';
                    } else {
                        chessboard[moveRow - 2][moveColAlt] = ' ';
                    }
                } else if (moveColAlt - moveCol == 2) {
                    if (moveRow + 1 >= 0
                            && moveRow + 1 < chessboard.length
                            && chessboard[moveRow + 1][moveColAlt] == piece) {
                        chessboard[moveRow + 1][moveColAlt] = ' ';
                    } else {
                        chessboard[moveRow - 1][moveColAlt] = ' ';
                    }
                } else if (moveColAlt - moveCol == -1) {
                    if (moveRow + 2 >= 0
                            && moveRow + 2 < chessboard.length
                            && chessboard[moveRow + 2][moveColAlt] == piece) {
                        chessboard[moveRow + 2][moveColAlt] = ' ';
                    } else {
                        chessboard[moveRow - 2][moveColAlt] = ' ';
                    }
                } else {
                    if (moveRow + 1 >= 0
                            && moveRow + 1 < chessboard.length
                            && chessboard[moveRow + 1][moveColAlt] == piece) {
                        chessboard[moveRow + 1][moveColAlt] = ' ';
                    } else {
                        chessboard[moveRow - 1][moveColAlt] = ' ';
                    }
                }
            } else {
                int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
                if (moveRowAlt - moveRow == 1) {
                    if (moveCol + 2 >= 0
                            && moveCol + 2 < chessboard[0].length
                            && chessboard[moveRowAlt][moveCol + 2] == piece) {
                        chessboard[moveRowAlt][moveCol + 2] = ' ';
                    } else {
                        chessboard[moveRowAlt][moveCol - 2] = ' ';
                    }
                } else if (moveRowAlt - moveRow == 2) {
                    if (moveCol + 1 >= 0
                            && moveCol + 1 < chessboard[0].length
                            && chessboard[moveRowAlt][moveCol + 1] == piece) {
                        chessboard[moveRowAlt][moveCol + 1] = ' ';
                    } else {
                        chessboard[moveRowAlt][moveCol - 1] = ' ';
                    }
                } else if (moveRowAlt - moveRow == -1) {
                    if (moveCol + 2 >= 0
                            && moveCol + 2 < chessboard[0].length
                            && chessboard[moveRowAlt][moveCol + 2] == piece) {
                        chessboard[moveRowAlt][moveCol + 2] = ' ';
                    } else {
                        chessboard[moveRowAlt][moveCol - 2] = ' ';
                    }
                } else {
                    if (moveCol + 1 >= 0
                            && moveCol + 1 < chessboard[0].length
                            && chessboard[moveRowAlt][moveCol + 1] == piece) {
                        chessboard[moveRowAlt][moveCol + 1] = ' ';
                    } else {
                        chessboard[moveRowAlt][moveCol - 1] = ' ';
                    }
                }
            }
            chessboard[moveRow][moveCol] = piece;
        } else {
            knightAmbiguityB(trimPiece);
        }
    }

    public static void knightAmbiguityB(String trimPiece) {
        trimPiece = trimPiece.replace("x", "");
        int moveLen = trimPiece.length();
        int moveRow = '8' - trimPiece.charAt(moveLen - 1);
        int moveCol = trimPiece.charAt(moveLen - 2) - 'a';
        char piece = trimPiece.charAt(0);
        char pieceL = Character.toLowerCase(piece);
        boolean match = false;

        if (Character.isAlphabetic(trimPiece.charAt(moveLen - 3))) {
            int moveColAlt = trimPiece.charAt(moveLen - 3) - 'a';
            if (moveColAlt - moveCol == 1) {
                if (moveRow + 2 >= 0
                        && moveRow + 2 < chessboard.length
                        && chessboard[moveRow + 2][moveColAlt] == pieceL) {
                    chessboard[moveRow + 2][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - 2][moveColAlt] = ' ';
                }
            } else if (moveColAlt - moveCol == 2) {
                if (moveRow + 1 >= 0
                        && moveRow + 1 < chessboard.length
                        && chessboard[moveRow + 1][moveColAlt] == pieceL) {
                    chessboard[moveRow + 1][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - 1][moveColAlt] = ' ';
                }
            } else if (moveColAlt - moveCol == -1) {
                if (moveRow + 2 >= 0
                        && moveRow + 2 < chessboard.length
                        && chessboard[moveRow + 2][moveColAlt] == pieceL) {
                    chessboard[moveRow + 2][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - 2][moveColAlt] = ' ';
                }
            } else {
                if (moveRow + 1 >= 0
                        && moveRow + 1 < chessboard.length
                        && chessboard[moveRow + 1][moveColAlt] == pieceL) {
                    chessboard[moveRow + 1][moveColAlt] = ' ';
                } else {
                    chessboard[moveRow - 1][moveColAlt] = ' ';
                }
            }
        } else {
            int moveRowAlt = '8' - trimPiece.charAt(moveLen - 3);
            if (moveRowAlt - moveRow == 1) {
                if (moveCol + 2 >= 0
                        && moveCol + 2 < chessboard[0].length
                        && chessboard[moveRowAlt][moveCol + 2] == pieceL) {
                    chessboard[moveRowAlt][moveCol + 2] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - 2] = ' ';
                }
            } else if (moveRowAlt - moveRow == 2) {
                if (moveCol + 1 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && chessboard[moveRowAlt][moveCol + 1] == pieceL) {
                    chessboard[moveRowAlt][moveCol + 1] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - 1] = ' ';
                }
            } else if (moveRowAlt - moveRow == -1) {
                if (moveCol + 2 >= 0
                        && moveCol + 2 < chessboard[0].length
                        && chessboard[moveRowAlt][moveCol + 2] == pieceL) {
                    chessboard[moveRowAlt][moveCol + 2] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - 2] = ' ';
                }
            } else {
                if (moveCol + 1 >= 0
                        && moveCol + 1 < chessboard[0].length
                        && chessboard[moveRowAlt][moveCol + 1] == pieceL) {
                    chessboard[moveRowAlt][moveCol + 1] = ' ';
                } else {
                    chessboard[moveRowAlt][moveCol - 1] = ' ';
                }
            }
        }
        chessboard[moveRow][moveCol] = pieceL;
    }
    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the4 file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));

    }
}

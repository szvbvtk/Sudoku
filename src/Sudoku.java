import sac.graph.*;

import java.util.ArrayList;
import java.util.List;

public class Sudoku extends GraphStateImpl {
    protected int[][] board;
    protected int n;

    public Sudoku(int n) {
        this.board = new int[n * n][n * n];
        this.n = n;
    }

    public Sudoku(Sudoku s) {
        this.n = s.n;
        this.board = new int[n * n][n * n];

        for (int i = 0; i < n * n; i++)
            this.board[i] = java.util.Arrays.copyOf(s.board[i], s.board[i].length);
    }

    public Sudoku(String str, int n) {
        this.n = n;
        this.board = new int[n * n][n * n];

        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                int pos = i * n * n + j;

                if (str.charAt(pos) == '.') {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Character.getNumericValue(str.charAt(pos));
                }
            }
        }
    }

    public String toString() {
        StringBuilder sudokuString = new StringBuilder();

        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                sudokuString.append(board[i][j]).append(' ');

                if ((j + 1) % n == 0 && (j + 1) != n * n) {
                    sudokuString.append("| ");
                }
            }
            sudokuString.append('\n');

            if ((i + 1) % (n) == 0 && (i + 1) != n * n) {

                sudokuString.append(String.valueOf('-').repeat(board[i].length + board[i].length - 1 + (n - 1) * 2));
                sudokuString.append('\n');
            }
        }

        return sudokuString.toString();
    }

    public boolean isSolution() {
        for (int i = 0; i < n * n; i++)
            for (int j = 0; j < n * n; j++)
                if (board[i][j] == 0) return false;

        return true;
    }

    public List<GraphState> generateChildren() {
        List<GraphState> children = new ArrayList<>();

        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                if (board[i][j] == 0) {
                    boolean[] crossed = new boolean[n * n + 1];

                    for (int k = 0; k < n * n; k++) {
                        crossed[board[i][k]] = true;
                        crossed[board[k][j]] = true;
                    }

                    int I = (i / n) * n;
                    int J = (j / n) * n;
//                    int J = (j - j % n); // to samo

                    for (int k = 0; k < n; k++)
                        for (int m = 0; m < n; m++)
                            crossed[board[I + k][J + m]] = true;

                    for (int k = 1; k <= n * n; k++) {
                        if (!crossed[k]) {
                            Sudoku child = new Sudoku(this);
                            child.board[i][j] = k;

                            children.add(child);
                        }
                    }

                    return children;
                }
            }
        }
        return children;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }
}


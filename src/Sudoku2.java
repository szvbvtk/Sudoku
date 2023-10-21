import sac.graph.GraphState;

import java.util.ArrayList;
import java.util.List;

public class Sudoku2 extends Sudoku {
    public Sudoku2(String str, int n) {
        super(str, n);
    }

    public Sudoku2(int n) {
        super(n);
    }

    public Sudoku2(Sudoku s) {
        super(s);
    }

    private List<Integer> possibleValues(int i, int j) {
        boolean[] crossed = new boolean[n * n + 1];
        List<Integer> possibleValuesArr = new ArrayList<>();

        for (int k = 0; k < n * n; k++) {
            crossed[board[k][j]] = true;
            crossed[board[i][k]] = true;
        }

        int I = (i / n) * n;
        int J = (j / n) * n;

        for (int k = 0; k < n; k++) {
            for (int m = 0; m < n; m++) {
                crossed[board[I + k][J + m]] = true;
            }
        }

        for (int k = 1; k <= n * n; k++) {
            if (!crossed[k]) {
                possibleValuesArr.add(k);
            }
        }

        return possibleValuesArr;
    }

    public List<GraphState> generateChildren() {
        List<GraphState> children = new ArrayList<>();
        int minNumberOfPossibilities = n * n + 1;
        int numberOfPossibilities = 0;

        int minPosI = -1;
        int minPosJ = -1;

        List<Integer> minPossibleValuesArr = null;
        List<Integer> possibleValuesArr;

        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                if (board[i][j] == 0) {
                    possibleValuesArr = possibleValues(i, j);
                    numberOfPossibilities = possibleValuesArr.size();

                    if (minNumberOfPossibilities > numberOfPossibilities) {
                        minNumberOfPossibilities = numberOfPossibilities;
                        minPossibleValuesArr = possibleValuesArr;
                        minPosI = i;
                        minPosJ = j;
                    }
                }
            }
        }

        if (minPosI > -1 && minPosJ > -1) {
            for (int k = 1; k <= n * n; k++) {
                if (minPossibleValuesArr.contains(k)) {
                    Sudoku2 child = new Sudoku2(this);
                    child.board[minPosI][minPosJ] = k;
                    children.add(child);
                }
            }
        }

        return children;
    }
}

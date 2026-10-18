/**
 * Board class — manages the 3x3 grid
 * Demonstrates: Encapsulation, Static attributes/methods
 */
public class Board {

    private String[][] grid;
    private int size;

    // STATIC — shared across all Board objects, tracks total moves ever made
    private static int totalMovesPlayed = 0;

    public Board(int size) {
        this.size = size;
        grid = new String[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j] = "";
    }

    public void placeMark(int row, int col, String symbol) throws InvalidMoveException {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new InvalidMoveException("Position out of bounds!");
        }
        if (!grid[row][col].isEmpty()) {
            throw new InvalidMoveException("Cell already occupied!");
        }
        grid[row][col] = symbol;
        totalMovesPlayed++;
    }

    public String getMark(int row, int col) { return grid[row][col]; }
    public int getSize() { return size; }

    public static int getTotalMovesPlayed() { return totalMovesPlayed; }

    public boolean isFull() {
        for (String[] row : grid)
            for (String cell : row)
                if (cell.isEmpty()) return false;
        return true;
    }

    public void reset() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j] = "";
    }

    // Checks rows, columns, diagonals for a winner
    public String checkWinner() {
        for (int i = 0; i < size; i++) {
            if (!grid[i][0].isEmpty() && rowEqual(grid[i])) return grid[i][0];

            String[] col = new String[size];
            for (int j = 0; j < size; j++) col[j] = grid[j][i];
            if (!col[0].isEmpty() && rowEqual(col)) return col[0];
        }

        String[] diag1 = new String[size];
        String[] diag2 = new String[size];
        for (int i = 0; i < size; i++) {
            diag1[i] = grid[i][i];
            diag2[i] = grid[i][size - 1 - i];
        }
        if (!diag1[0].isEmpty() && rowEqual(diag1)) return diag1[0];
        if (!diag2[0].isEmpty() && rowEqual(diag2)) return diag2[0];

        return null;   // no winner yet
    }

    private boolean rowEqual(String[] arr) {
        for (String s : arr)
            if (!s.equals(arr[0])) return false;
        return true;
    }
}
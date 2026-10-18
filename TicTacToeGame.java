import javax.swing.*;
import java.awt.*;

/**
 * Main GUI Game class
 * Demonstrates: Interface implementation, Polymorphism, GUI (Swing)
 */
public class TicTacToeGame extends JFrame implements Playable {

    private Board board;
    private JButton[][] buttons;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private JLabel statusLabel;

    private static int gamesPlayed = 0;   // static — tracks across games

    public TicTacToeGame() {
        board = new Board(3);
        player1 = new HumanPlayer("Player 1", "X");
        player2 = new HumanPlayer("Player 2", "O");
        currentPlayer = player1;

        setTitle("DCST Tic-Tac-Toe — OOP Game");
        setSize(420, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Turn: " + currentPlayer.introduce(), SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 40));
                final int row = i, col = j;
                btn.addActionListener(e -> playTurn(row, col));
                buttons[i][j] = btn;
                gridPanel.add(btn);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        JButton resetBtn = new JButton("Reset Game");
        resetBtn.addActionListener(e -> resetGame());
        add(resetBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void playTurn(int row, int col) {
        try {
            board.placeMark(row, col, currentPlayer.getSymbol());
            buttons[row][col].setText(currentPlayer.getSymbol());

            if (board.checkWinner() != null) {
                currentPlayer.incrementScore();
                gamesPlayed++;
                statusLabel.setText(currentPlayer.getName() + " Wins!  (Games played: " + gamesPlayed + ")");
                JOptionPane.showMessageDialog(this, currentPlayer.getName() + " wins the game!");
                disableAllButtons();
                return;
            }

            if (board.isFull()) {
                gamesPlayed++;
                statusLabel.setText("It's a Draw!  (Games played: " + gamesPlayed + ")");
                JOptionPane.showMessageDialog(this, "Game Draw!");
                return;
            }

            switchPlayer();
            statusLabel.setText("Turn: " + currentPlayer.introduce());

        } catch (InvalidMoveException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());   // exception handling
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    @Override
    public boolean isGameOver() {
        return board.checkWinner() != null || board.isFull();
    }

    private void disableAllButtons() {
        for (JButton[] row : buttons)
            for (JButton b : row)
                b.setEnabled(false);
    }

    private void resetGame() {
        board.reset();
        for (JButton[] row : buttons)
            for (JButton b : row) {
                b.setText("");
                b.setEnabled(true);
            }
        currentPlayer = player1;
        statusLabel.setText("Turn: " + currentPlayer.introduce());
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
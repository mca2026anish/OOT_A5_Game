/**
 * Interface defining game behaviour contract
 * Demonstrates: Interface / Abstraction
 */
public interface Playable {
    void playTurn(int row, int col);
    boolean isGameOver();
}
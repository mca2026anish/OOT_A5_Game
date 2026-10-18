/**
 * Abstract Player class
 * Demonstrates: Abstraction, Encapsulation
 */
public abstract class Player {

    private String name;     // private fields = encapsulation
    private String symbol;   // "X" or "O"
    private int score;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public int getScore() { return score; }
    public void incrementScore() { score++; }

    // Abstract method — child class decides how to implement it
    public abstract String introduce();
}
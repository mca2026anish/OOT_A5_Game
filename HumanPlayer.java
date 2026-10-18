/**
 * HumanPlayer extends abstract Player
 * Demonstrates: Inheritance, Method Overriding (Polymorphism)
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, String symbol) {
        super(name, symbol);   // calls Player's constructor
    }

    @Override
    public String introduce() {
        return getName() + " (playing as " + getSymbol() + ")";
    }
}
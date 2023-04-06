package classpack;

public class RollDice {
    public int die1 = (int) (Math.random() * 6) + 1;
    public int die2 = (int) (Math.random() * 6) + 1;

    public void roll() {
        die1 = (int) (Math.random() * 6) + 1;
        die2 = (int) (Math.random() * 6) + 1;
    }
}

package classpack;

public class student {
    public String name;
    public double test1, test2, test3;

    public double getAverage() {
        return (test1 + test2 + test3) / 3;
    }
}

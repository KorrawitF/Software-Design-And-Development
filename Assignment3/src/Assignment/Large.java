package Assignment;

public class Large extends Pizza {
    protected String base;
    protected String toppings;
    protected double cost = 499.0;

    public Large(String base, String toppings) {
        this.base = base;
        this.toppings = toppings;
    }

    public String getToppings() {
        return toppings;
    }

    public String getBase() {
        return base;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public double Cost() {
        return getCost();
    }

    @Override
    public String Base() {
        return getBase();
    }

    @Override
    public String toppings() {
        return getToppings();
    }
}

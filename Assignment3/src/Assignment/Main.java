package Assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double totalprice = 0;
        System.out.print("Number of pizzas: ");
        int n = TextIO.getInt();
        int i;
        Pizza[] pizzas = new Pizza[n];
        for (i = 0; i < n; i++) {
            System.out.print("Choose size for pizza(Small, Medium, Large): ");
            Scanner input = new Scanner(System.in);
            String Size = input.nextLine();
            if (Size.equalsIgnoreCase("Small")) {
                System.out.print("Enter pizza's base: ");
                Scanner input2 = new Scanner(System.in);
                String base = input2.nextLine();
                System.out.print("Enter pizza's topping: ");
                Scanner input3 = new Scanner(System.in);
                String topping = input3.nextLine();
                pizzas[i] = new Small(base, topping);
            } else if (Size.equalsIgnoreCase("Medium")) {
                System.out.print("Enter pizza's base: ");
                Scanner input2 = new Scanner(System.in);
                String base = input2.nextLine();
                System.out.print("Enter pizza's topping: ");
                Scanner input3 = new Scanner(System.in);
                String topping = input3.nextLine();
                pizzas[i] = new Medium(base, topping);
            } else if (Size.equalsIgnoreCase("Large")) {
                System.out.print("Enter pizza's base: ");
                Scanner input2 = new Scanner(System.in);
                String base = input2.nextLine();
                System.out.print("Enter pizza's topping: ");
                Scanner input3 = new Scanner(System.in);
                String topping = input3.nextLine();
                pizzas[i] = new Medium(base, topping);
            } else {
                System.out.println(Size + " doesn't exists.");
            }
            if (i + 1 == n) {
                input.close();
            }
        }
        for (Pizza pizza : pizzas) {
            System.out.println("Base: " + pizza.Base() + " Topping: " + pizza.toppings() + " Price: " + pizza.Cost());
            totalprice = totalprice + pizza.Cost();
        }
        System.out.println("Total price: " + totalprice);

    }
}

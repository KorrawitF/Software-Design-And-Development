package Assignment;
import java.lang.Math;
import java.text.DecimalFormat;

public class main {
    public static void main(String[] args){
        System.out.println("Enter initial investment cost, interest and number of years: ");
        double IC = TextIO.getDouble();
        float initiali = TextIO.getFloat();
        float  i = initiali / 100;
        int N = TextIO.getInt();
        double initialAP = (i * Math.pow((1 + i), N)) / (Math.pow((1 + i), N) - 1);
        double AP = Math.round(initialAP * 10000.0)/10000.0;
        int UAC = (int) (IC * AP);
        System.out.println("UAC: $" + UAC + "/yr");
    }
}

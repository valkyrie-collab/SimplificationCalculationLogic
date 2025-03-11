import com.infix_to_postfix_summation.calculationOptimize;

public class Main{
    public static void main(String args[]){
        String value = "5*5/2+11-3";
        calculationOptimize cal = new calculationOptimize(value);
        System.out.println("The summation is: " + cal.getCalculationValue());
    }
}
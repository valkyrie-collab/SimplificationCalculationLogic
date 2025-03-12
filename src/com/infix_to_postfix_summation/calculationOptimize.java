package com.infix_to_postfix_summation;
import java.util.Arrays;

public class calculationOptimize {
    private final double[] stackNumber = new double[50];
    private final char[] stackSymbol = new char[50];
    private int topNumber = -1, topSymbol = -1;
    private final String value;
    private double sum; private int startSign = 0;
    private double numberOne, numberTwo;
    private char symbol;

    public calculationOptimize(String value){
        this.value = value;
    }

    public String getCalculationValue(){
        int j = 0;
        String[] valueArray = value.split("");
        int[] symbolPriorityArray = {3, 2, 0, 1, 0, 4};

        for(int i = 0; i < valueArray.length; i++){
            char checkValue = valueArray[i].charAt(0);
            //combining digits to get the full digit because after split it will look like[1,2,+,4,-,8,9,*,2]
            if(Character.isDigit(checkValue)){
                if(j == 0){
                    //at first it is checking for starting digit
                    j = i + 1;
                    valueArray[j - 1] = "" + checkValue;
                }
                else{
                    //here it is perfectly aligning the digits to make one single digit like 1,2 is 12
                    valueArray[i] = "null";
                    valueArray[j - 1] = valueArray[j - 1] + checkValue;
                }
            }
            else{
                j = 0;
                //here storing the first symbol index like for * is 1
                if(startSign == 0){
                    startSign = i;
                }
            }
        }
        System.out.println("Given Value: " + value);
        System.out.println("newString: "+Arrays.toString(valueArray));

        int i = startSign;

        //here storing the symbol and numbers seperately in stackNumber and stackSymbol respectively
        topNumber++;
        stackNumber[topNumber] = Integer.parseInt(valueArray[0]);

        topNumber++;
        stackNumber[topNumber] = Integer.parseInt(valueArray[i+1]);

        topSymbol++;
        stackSymbol[topSymbol] = valueArray[i].charAt(0);
        i = i + 2;

        //System.out.println(topNumber);
        
        try{
            while(i < valueArray.length){
                //System.out.println("stackNumber: " + Arrays.toString(stackNumber));
                //System.out.println("stackSymbol: " + Arrays.toString(stackSymbol));

                //System.out.println("value of i = " + i);
                String stackValue = valueArray[i];
                //System.out.println("stackValue: " + stackValue);

                //System.out.println(stackValue.equals("*"));

                if(stackValue.equals("/") || stackValue.equals("*") || 
                    stackValue.equals("+") || stackValue.equals("-")){
                        //here i am simply checking its priority to do the operation in that way like 
                        //low priority can remove high priority by not vice versa
                        topSymbol++;
                        stackSymbol[topSymbol] = stackValue.charAt(0);

                        int valueOne = symbolPriorityArray[stackSymbol[topSymbol--] - '*'];
                        //System.out.println("stackSymbol[topSymbol--]: " + 
                        //stackSymbol[topSymbol + 1] + "value of symbolPriorityArray[stackSymbol[topSymbol--] - '*']: " + 
                        //valueOne);

                        int valueTwo = symbolPriorityArray[stackSymbol[topSymbol] - '*'];
                        //System.out.println("stackSymbol[topSymbol]: " + 
                        //stackSymbol[topSymbol] + "value of symbolPriorityArray[stackSymbol[topSymbol] - '*']: " + 
                        //valueTwo);

                        if(valueTwo <= valueOne){

                            //topNumber++; 
                            symbol = stackValue.charAt(0);
                            //stackValue = valueArray[++i];
                            //stackNumber[topNumber] = Integer.parseInt(stackValue);
                                
                                //numberTwo = stackNumber[topNumber--];
                                numberTwo = Integer.parseInt(valueArray[++i]);
                                numberOne = stackNumber[topNumber];

                                //System.out.println("value of symbol: " + symbol);
                                //System.out.println("numberOne: " + numberOne);
                                //System.out.println("numberTwo: " + numberTwo);
                                
                        }
                        else{
                            numberTwo = stackNumber[topNumber--];
                            numberOne = stackNumber[topNumber];
                            symbol = stackSymbol[topSymbol];
                            stackSymbol[topSymbol] = stackSymbol[topSymbol + 1];
                        }
                        if(numberTwo == 0 && symbol == '/'){
                            return "Invalid Input";
                        }
                        else{
                            performCalculation();
                        }
                    }
                else if(!stackValue.equals("null")){
                    topNumber++;
                    stackNumber[topNumber] = Integer.parseInt(stackValue);
                }
                i++;
                //System.out.println();
            }

            //doing the last part of the calculation
            numberOne = stackNumber[0];
            numberTwo = stackNumber[1];
            symbol = stackSymbol[0];

            if(numberTwo == 0 && symbol == '/'){
                return "Invalid Input";
            }
            else{
                performCalculation();
                return Double.toString(sum);
            }
        }
        catch(NumberFormatException e){
            System.out.println(e);
            return "Invalid Input";
        }
    }

    public void performCalculation(){
        switch(symbol){
            case '*' ->{
                sum = numberOne * numberTwo;
                stackNumber[topNumber] = sum;
                //System.out.printf("value of i in *: %d sum value: %d \n", i, sum);
            }
            case '/' ->{
                sum = numberOne / numberTwo;
                stackNumber[topNumber] = sum;
                //System.out.printf("value of i in /: %d sum value: %d \n", i, sum);
            }
            case '+' ->{
                sum = numberOne + numberTwo;
                stackNumber[topNumber] = sum;
                //System.out.printf("value of i in +: %d sum value: %d \n", i, sum);
            }
            case '-' ->{
                sum = numberOne - numberTwo;
                stackNumber[topNumber] = sum;
                //System.out.printf("value of i in -: %d sum value: %d \n", i, sum);
            }
        }
        //System.out.println("value of sum: " + sum);
    }
}
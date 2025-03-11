package com.infix_to_postfix_summation;
import java.util.Arrays;

public class calculation {
    private final int[] stackNumber = new int[50];
    private final char[] stackSymbol = new char[50];
    private int topNumber = -1, topSymbol = -1;
    private final String value;
    private int sum, startSign = 0;

    public calculation(String value){
        this.value = value;
    }

    public String getCalculationValue(){
        int j = 0; char symbol;
        String[] valueArray = value.split("");
        int[] symbolPriorityArray = {3, 2, 0, 1, 0, 4};
        int numberOne, numberTwo;

        for(int i = 0;i < valueArray.length; i++){
            char checkValue = valueArray[i].charAt(0);
            if(Character.isDigit(checkValue)){
                if(j == 0){
                    j = i + 1;
                    valueArray[j - 1] = "" + checkValue;
                }
                else{
                    valueArray[i] = "NaN";
                    valueArray[j - 1] = valueArray[j - 1] + checkValue;
                }
            }
            else{
                j = 0;
                if(startSign == 0){
                    startSign = i;
                }
            }
        }
        System.out.println("hello"+Arrays.toString(valueArray));

        int i = startSign;

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

                            topNumber++; 
                            symbol = stackValue.charAt(0);
                            stackValue = valueArray[++i];
                            stackNumber[topNumber] = Integer.parseInt(stackValue);
                                
                                numberTwo = stackNumber[topNumber--];
                                numberOne = stackNumber[topNumber];

                                //System.out.println("value of symbol: " + symbol);
                                //System.out.println("numberOne: " + numberOne);
                                //System.out.println("numberTwo: " + numberTwo);
                                
                                switch(symbol){
                                    case '*' ->{
                                        sum = numberOne * numberTwo;
                                        stackNumber[topNumber] = sum;
                                        //System.out.printf("value of i in *: %d sum value: %d \n", i, sum);
                                    }
                                    case '/' ->{
                                        if(numberTwo == 0){
                                            return "Null";
                                        }
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
                        if(valueTwo == valueOne){
                            stackSymbol[topSymbol + 1] = '.';
                        }
                        if(valueTwo > valueOne){
                            numberTwo = stackNumber[topNumber--];
                            numberOne = stackNumber[topNumber];
                            symbol = stackSymbol[topSymbol];
                            stackSymbol[topSymbol] = stackSymbol[topSymbol + 1];

                            switch(symbol){
                                case '*' ->{
                                    sum = numberOne * numberTwo;
                                    stackNumber[topNumber] = sum;
                                    //System.out.printf("value of i in *: %d sum value: %d \n", i, sum);
                                }
                                case '/' ->{
                                    if(numberTwo == 0){
                                        return "Null";
                                    }
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
                else if(!stackValue.equals("NaN")){
                    topNumber++;
                    stackNumber[topNumber] = Integer.parseInt(stackValue);
                }
                i++;
                //System.out.println();
            }
            
            numberOne = stackNumber[0];
            numberTwo = stackNumber[1];
            symbol = stackSymbol[0];

            switch(symbol){
                case '*' ->{
                    sum = numberOne * numberTwo;
                    stackNumber[topNumber] = sum;
                    //System.out.printf("value of i in *: %d sum value: %d \n", i, sum);
                }
                case '/' ->{
                    if(numberTwo == 0){
                        return "Null";
                    }
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

        }
        catch(NumberFormatException E){
            System.out.println(E);
        }
        return Integer.toString(sum);
    }
}

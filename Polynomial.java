//lab 2 version
import java.lang.Math;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial{
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        this.coefficients = new double[0];
        this.exponents = new int[0];
    }
    
    public Polynomial(double[] newCoefs, int[] newExpos){
        this.coefficients = newCoefs;
        this.exponents = newExpos;
    }

    public Polynomial(File inputFile){        
        try{
            Scanner scan = new Scanner(inputFile);
            String inputText = scan.nextLine();
            scan.close();
            double[] tempCoefs = new double[inputText.length()];
            int[] tempExpos = new int[inputText.length()];            
            int[] signs = getSigns(inputText);            
            String[] terms = inputText.split("[-+]");               
            for (int i =0; i<terms.length; i++){
                int xIndex = terms[i].indexOf('x');
                if (xIndex == -1){
                    double coef = Double.valueOf(terms[i].substring(0));   
                    if(signs[i] == 0)       
                        coef=(-1.0)*coef; 
                    tempCoefs[i] = coef;
                    tempExpos[i] = 0;   
                }
                else{
                    double coef = Double.valueOf(terms[i].substring(0,xIndex));     
                    if(signs[i] == 0)       
                        coef=(-1.0)*coef;
                    if(terms[i].substring(xIndex+1) == ""){
                        tempExpos[i] = 1;   
                    }
                    else{
                        int expo = Integer.valueOf(terms[i].substring(xIndex+1));
                        tempExpos[i] = expo;   
                    }
                    tempCoefs[i] = coef;         
                }
            }
            int length = countNonZeroCoefs(tempCoefs);
            double[] newCoefs = new double[length];
            int[] newExpos = new int[length];
            int index = 0;
            for (int i =0; i< tempExpos.length; i++){
                if(tempCoefs[i] != 0 && tempCoefs[i] != 0.0){
                    newCoefs[index] = tempCoefs[i];
                    newExpos[index] = tempExpos[i];
                    index = index +1;
                }
            }
            this.coefficients = newCoefs;
            this.exponents = newExpos;
        } catch(IOException e){
            System.out.println("Exception encountered loading file");
            e.printStackTrace();
        }
    }

    protected int[] getSigns(String inputText){
        int[] series = new int[inputText.length()-3];
        int index = 1;
        series[0] = 1;
        for (int i = 0; i<inputText.length()-3;i++){
            if (inputText.charAt(i) == '-'){
                series[index] = 0;
                index = index+1;
            }
            if (inputText.charAt(i) == '+'){
                series[index] = 1;
                index = index+1;
            }
        }
        return series;
    }
    public void saveToFile(String fileName){
        String output = "";
        for (int i = 0; i < this.exponents.length; i ++){
            if(this.coefficients[i] != 0)
                if (this.exponents[i] == 0){
                    output = (String)(output+this.coefficients[i]);
                }
                else if(this.exponents[i] == 1){
                    output = (String)(output+this.coefficients[i]+"x");
                }
                else{
                output = (String)(output+this.coefficients[i]+"x"+this.exponents[i]);
                }
            if(i+1 < this.exponents.length){
                if(this.coefficients[i+1] > 0){
                    output = (String)(output+"+");
                }
            }
        }

        try{
            FileWriter writer = new FileWriter((String)fileName);            
            writer.write(output);
            writer.close();
        } catch(IOException e){
            System.out.println("Exception encountered while saving to file");
            e.printStackTrace();
        }
    }

    public Polynomial add(Polynomial other){
        int largest = getLargest(this.exponents, other.exponents);
        int smallest = getSmallest(this.exponents, other.exponents);
        int totalExps =  largest - smallest +1;     
        double[] tempCoefs = new double[totalExps];
        int[] tempExpos = new int[totalExps];
        for (int i = smallest; i < largest +1; i ++){
        	tempExpos[i] = i;
        }        
        for (int i = 0; i < this.exponents.length; i ++){
		    int exp = this.exponents[i];
        	tempCoefs[exp] = tempCoefs[exp] + this.coefficients[i]; 
        }
        for (int i = 0; i < other.exponents.length; i ++){
		    int exp = other.exponents[i];
        	tempCoefs[exp] = tempCoefs[exp] + other.coefficients[i]; 
        } 
        int newLength = countNonZeroCoefs(tempCoefs);
        double[] newCoefs = new double[newLength];
        int[] newExpos = new int[newLength];        
        int newIndex = 0;
        for (int i =0; i <tempExpos.length; i++){
            if (tempCoefs[i] != 0 || tempCoefs[i] != 0.0){
                newCoefs[newIndex] = tempCoefs[i];
                newExpos[newIndex] = tempExpos[i];
                newIndex = newIndex+1;
            }
        }
        return new Polynomial(newCoefs, newExpos);
    }

    public Polynomial multiply(Polynomial other){
        int maxLength = this.exponents.length * other.exponents.length;
        double[] newCoefs = new double[maxLength];
        int[] newExpos = new int[maxLength];
        for (int i = 0; i < this.exponents.length; i++){
            for (int j = 0; j < other.exponents.length; j++){
                newExpos[i+j] = this.exponents[i]+other.exponents[j];
                newCoefs[i+j] = this.coefficients[i]*other.coefficients[j];
            }
        }
        double[]c = {0};
        int[]e = {0};
        Polynomial dumbPoly = new Polynomial(c,e);
        Polynomial newPoly = new Polynomial(newCoefs, newExpos);
        return newPoly.add(dumbPoly);
    }

    public double evaluate(double value){
        double result = 0.0;
        for(int i =0; i < this.coefficients.length; i ++){
              result = result + this.coefficients[i]*(Math.pow(value, exponents[i]));
        }
        return result;
    }

    public boolean hasRoot(double value){
        return this.evaluate(value) == 0.0;
    }

    protected int getLargest(int[] arr1, int[] arr2){
        int largest = 0;
        for (int num: arr1)
            if (num > largest) largest = num;
        for (int num: arr2)
            if (num > largest) largest = num;
        return largest;
    }
    
    protected int getSmallest(int[] arr1, int[] arr2){
        int smallest = 0;
        for (int num: arr1)
            if (num < smallest) smallest = num;
        for (int num: arr2)
            if (num < smallest) smallest = num;
        return smallest;
    }

    protected int countNonZeroCoefs(double[] coefs){
        int nonZeros = 0;
        for (int i = 0; i<coefs.length; i++){
            if(coefs[i] != 0.0){
                nonZeros = nonZeros+1;
            }
        }
        return nonZeros;
    }

    protected void print(){
        String output = asString();
        System.out.println(output);
    }

    protected String asString(){
        String output = "";
        for (int i = 0; i< coefficients.length; i++){
            output = output +" "+coefficients[i]+"x^("+exponents[i]+")";
        }
        return output;
    }

    protected void oneFactorMultiply(double coefFactor, int coefExp, double[] coefArray, int[] expoArray){
        for (int i = 0; i<coefArray.length; i++){
            coefArray[i] = coefArray[i] * coefFactor;
            expoArray[i] = expoArray[i] + coefExp;
        }
    }

    protected int[] copyArray(int[] arrayToCopy){
        int[] newArray = new int[arrayToCopy.length];
        for (int i = 0; i < arrayToCopy.length; i++)
            newArray[i] = arrayToCopy[i];
        return newArray;
    }

    protected double[] copyArray(double[] arrayToCopy){
        double[] newArray = new double[arrayToCopy.length];
        for (int i = 0; i < arrayToCopy.length; i++)
            newArray[i] = arrayToCopy[i];
        return newArray;
    }
}

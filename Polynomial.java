import java.lang.Math;
public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[0];
    }

    public Polynomial(double[] newCoefs){
        this.coefficients = newCoefs;
    }

    public Polynomial add(Polynomial other){
        int thisLength = this.coefficients.length;
        int otherLength = other.coefficients.length;
        int newCoefsLength = Math.max(thisLength, otherLength);
        double[] newCoefs = new double[newCoefsLength];

        for (int i = 0; i < thisLength; i++){
            newCoefs[i] = this.coefficients[i];
        }
        for (int i = 0; i < otherLength; i++){
            newCoefs[i] = newCoefs[i] + other.coefficients[i];
        }
        return new Polynomial(newCoefs);
    }

    public double evaluate(double value){
        double result = 0.0;
        for(int i =0; i < this.coefficients.length; i ++){
              result = result + this.coefficients[i]*(Math.pow(value, i));
        }
        return result;
    }

    public boolean hasRoot(double value){
        return this.evaluate(value) == 0.0;
    }
}
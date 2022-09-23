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
        int loopRuns = 0;
        int newCoefsLength =0;
        if (thisLength > otherLength){
            loopRuns = otherLength;
            newCoefsLength = thisLength;
        }
        else{
            loopRuns = thisLength;
            newCoefsLength = otherLength;
        }

        for (int i = 0; i < loopRuns; i++){

        }

        Polynomial newPoly = new Polynomial(newCoefs);

        return newPoly;
    }



}
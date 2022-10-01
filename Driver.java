import java.io.*;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) {

	System.out.println("\n\n");
        double [] c1 = {6,0,0,5};
        int[] e1 = {1,2,4,3};
        Polynomial p1 = new Polynomial(c1, e1);
        //p1.print();

        double [] c2 = {1,-2,0,-9};
        int[] e2 = {1,2,3,7};
        Polynomial p2 = new Polynomial(c2, e2);
	    //p2.print();

        double [] c3 = {1,2,0,4,5,2};
        int [] e3 = {3,4,5,5,5,5};
        Polynomial p3 = new Polynomial(c3, e3);
        //p3.print();

        double [] c4 = {1};
        int [] e4 = {2};
        Polynomial p4 = new Polynomial(c4, e4);

        double [] c5 = {1, 2};
        int [] e5 = {1, 2};
        Polynomial p5 = new Polynomial(c5, e5);

        double [] c6 = {4, 2};
        int [] e6 = {0, 1};
        Polynomial p6 = new Polynomial(c6, e6);

        Polynomial sum1 = p4.add(p5);
        System.out.println("Added " + p4.asString());
        System.out.println("and "+ p5.asString());
        System.out.println("and got: "+sum1.asString());
        System.out.println();

        Polynomial sum2 = p1.add(p2);
        System.out.println("Added " + p1.asString());
        System.out.println("and "+ p2.asString());
        System.out.println("and got: "+sum2.asString());
        System.out.println();
        
        Polynomial prod1 = p3.multiply(p5);
        System.out.println("Multiplied: "+p3.asString());
        System.out.println("With: "+p5.asString());
        System.out.println("and got: "+prod1.asString());
        System.out.println();
        
        Polynomial prod2 = p2.multiply(p4);
        System.out.println("Multiplied: "+p2.asString());
        System.out.println("With: "+p4.asString());
        System.out.println("and got: "+prod2.asString());
        System.out.println();
        
        Polynomial sum3 = prod2.add(p6);
        System.out.println("Added " + p1.asString());
        System.out.println("and "+ p2.asString());
        System.out.println("and got: "+sum2.asString());
        System.out.println();

		File directory = new File("./");
		String path = directory.getAbsolutePath();
        path = path.substring(0,path.length()-1)+"testOutput01.txt";
        p6.saveToFile(path);
        System.out.println("Wrote: "+p6.asString()+" to file: "+path);

        File p6File = new File(path);
        Polynomial p7 = new Polynomial(p6File);
        System.out.println("Read from file: "+p7.asString());
        p6File.delete();
        System.out.println("File deleted. Please check around line 74 of Driver.java inspect code and or comment out if you want to inspect the file.");

    }
}

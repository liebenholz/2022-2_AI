package univ_lecture.knu22_AI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MLP {

    public static int label;
    public static int epoch = 100;
    public static int hidden_node = 100;
    public static double learning_rate = 0.1;
    public static int[][] test_data = new int[28][28];
    public static int[][] train_data = new int[28][28];
    public static int train_repeat = 70000;

    /*
    Specify the node threshold and weight level as an arbitrary number that
    follows the even distribution
    */  
    public static double initA(double fl) {

        return (Math.random()*((4.8/fl)))-(2.4/fl);
    }

    // Sigmoid Activation Function
    public static double activ(double rv) {
        
        return 1 / (1 + Math.exp(-rv));
    }

    public static void main(String[] args) throws FileNotFoundException {

        // file call
        File data = new File("MNIST.txt");
        Scanner scan = new Scanner(data);

        // read label variable
        label = scan.nextInt(); // 5

        // read test data 
        for(int i=0; i<28; i++) {
            for(int j=0; j<28; j++) {
                test_data[i][j] = scan.nextInt();
            }
        }

        scan.close();

        // 1 step : initialize method call(hidden & output layer)


        while(hasNext()) {
            /*
            initialize variable(input array, hidden layer value array, output array,
            output layer value array, error gradient array…)
            */
            // One-hot encoding for output


            // 2 step : activation method call(hidden & output layer)


            // 3 step : training weight value


            // observation loss rate

            if(all training data is calculated) {
                // calculate loss rate
                // epoch increase
                if(filled in the number of training iterations) {
                // start the test
                // initialize fail count and use test data fail count
                }
            }
        }
    }
}

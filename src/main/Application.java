import algorithms.MBase;
import utils.MatrixParser;

import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        
        var line = "C:\\Users\\mmich\\Desktop\\VS_workspace\\Algoritmi-2020-2021\\src\\main\\resources\\74L85.000.matrix";
        
        var mbase = new MBase();
        var matrix = MatrixParser.parse(line);

        var runtime = Runtime.getRuntime();
        var inizio = System.currentTimeMillis();
        var res = mbase.mbase(matrix);
        var fine = System.currentTimeMillis();
        var memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        
        System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n", matrix.length, matrix[0].length);
        System.out.printf("I tempi di esecuzione dell'algoritmo per questa istanza sono: %d millisecondi \n", (fine-inizio));
        System.out.printf("La memoria usata è: %d KB", memoryUsed/1024);
           
    }
}

import algorithms.MBase;
import utils.MatrixParser;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        
        var line = "C:\\Users\\mmich\\Desktop\\VS_workspace\\Algoritmi-2020-2021\\src\\main\\resources\\74L85.000.matrix";
        
        var mbase = new MBase();
        var matrix = MatrixParser.parse(line);

        var runtime = Runtime.getRuntime();
        var start = System.currentTimeMillis();
        var res = mbase.mbase(matrix);
        var end = System.currentTimeMillis();
        var memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        
        System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n", matrix.length, matrix[0].length);
        System.out.printf("I tempi di esecuzione dell'algoritmo per questa istanza sono: %d millisecondi \n", (end-start));
        System.out.printf("La memoria usata è: %d KB \n", memoryUsed/1024);
        System.out.printf("Numero MHS trovati %d: \n", res.size());
        res.forEach(mhs -> System.out.println(Arrays.asList(mhs)));
    }
}

import algorithms.MBase;
import utils.MatrixParser;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {

        var line = "/Users/michelafarruggia/IdeaProjects/Algoritmi-2020-2021/src/main/resources/74L85.000.matrix";

        var mbase = new MBase();
        // var mvariant1 = new MVariant1();
        var matrix = MatrixParser.parse(line);

        // Risultati con algoritmo Mbase
        var runtime = Runtime.getRuntime();
        var start = System.currentTimeMillis();
        var res = mbase.mbase(matrix);
        var end = System.currentTimeMillis();
        var memoryUsed = runtime.totalMemory() - runtime.freeMemory();

        // Risultati con algoritmo Mvariant1
        /*
         * var runtime1 = Runtime.getRuntime();
         * var start1 = System.currentTimeMillis();
         * var res1 = mvariant1.mvariant1(matrix);
         * var end1 = System.currentTimeMillis();
         * var memoryUsed1 = runtime1.totalMemory() - runtime1.freeMemory();
         */

        System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n\n", matrix.length, matrix[0].length);
        System.out.println("------ MBASE ------");
        System.out.printf("I tempi di esecuzione dell'algoritmo MBase per questa istanza sono: %d millisecondi \n",
                (end - start));
        System.out.printf("La memoria usata è: %d KB \n", memoryUsed / 1024);
        System.out.printf("Numero MHS trovati %d: \n", res.size());
        res.forEach(mhs -> System.out.println(Arrays.asList(mhs)));

    }
}

import algorithms.MVariant1;
import utils.MatrixParser;

import java.io.FileNotFoundException;

public class Application2 {
    public static void main(String[] args) throws FileNotFoundException {

        var line = "C:\\Users\\mmich\\Desktop\\VS_workspace\\Algoritmi-2020-2021\\src\\main\\resources\\74L85.000.matrix";

        var mVariant1Refactor = new MVariant1();
        var matrix = MatrixParser.parse(line);

        // Risultati con algoritmo MBaseRefactor
        var runtime1 = Runtime.getRuntime();
        var start1 = System.currentTimeMillis();
        var res1 = mVariant1Refactor.mVariant1(matrix);
        var end1 = System.currentTimeMillis();
        var memoryUsed1 = runtime1.totalMemory() - runtime1.freeMemory();

        System.out.println("------ MBaseRefactor ------");
        System.out.printf("I tempi di esecuzione dell'algoritmo MVariant1 per questa istanza sono: %d millisecondi \n",
                (end1 - start1));
        System.out.printf("La memoria usata è: %d KB \n", memoryUsed1 / 1024);
        System.out.printf("Numero MHS trovati %d: \n", res1.size());
        res1.forEach(mhs -> System.out.println(mhs));
    }
}

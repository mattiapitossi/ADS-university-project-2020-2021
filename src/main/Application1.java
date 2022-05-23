
import algorithms.MBaseRefactorprova1;
import utils.MatrixParser;

import java.io.FileNotFoundException;

public class Application1 {
    public static void main(String[] args) throws FileNotFoundException {

        var line = "/Users/michelafarruggia/IdeaProjects/Algoritmi-2020-2021/src/main/resources/74L85.000.matrix";

        var mbaseRefactor = new MBaseRefactorprova1();
        var matrix = MatrixParser.parse(line);

        // Risultati con algoritmo MBaseRefactor
        var runtime1 = Runtime.getRuntime();
        var start1 = System.currentTimeMillis();
        var res1 = mbaseRefactor.mBaseRefactorprova1(matrix.stream().map(row -> row.toArray(String[]::new)).toArray(String[][]::new));
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

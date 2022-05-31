import algorithms.MBase;
import utils.MatrixParser;
import utils.MatrixPreElaboration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {

        var scanner = new Scanner(System.in);

        System.out.println("Insert the path of the folder that contains .matrix files or a single .matrix file: ");
        var path = scanner.nextLine();

        System.out.println("Insert the maximum time of elaboration (in seconds): ");
        var time = scanner.nextLong();

        var dir = new File(path);

        if (!dir.isDirectory()) {
            var mbase = new MBase();
            var preElab = new MatrixPreElaboration();
            // var mvariant1 = new MVariant1();
            var matrix = MatrixParser.parse(dir);
            var size = matrix.size();
            var size2 = matrix.get(0).size();

            // Risultati senza pre-elaborazione
            var runtime1 = Runtime.getRuntime();
            var start1 = System.currentTimeMillis();
            var res3 = mbase.mbase(preElab.convertFromListToArray(matrix), dir.getName(), time);
            var end1 = System.currentTimeMillis();
            var memoryUsed1 = runtime1.totalMemory() - runtime1.freeMemory();

            var res1 = preElab.convertFromListToArray(preElab.removeColumns(preElab.removeRows(matrix)));

            // Risultati con pre-elaborazione
            var runtime = Runtime.getRuntime();
            var start = System.currentTimeMillis();
            var res = mbase.mbase(res1, dir.getName(), time);
            var end = System.currentTimeMillis();
            var memoryUsed = runtime.totalMemory() - runtime.freeMemory();


            System.out.println("------ MBASE + PRE-ELABORAZIONE------");
            System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n\n", res1.length, res1[0].length);
            System.out.printf("I tempi di esecuzione dell'algoritmo MBase per questa istanza sono: %d millisecondi \n",
                    (end - start));
            System.out.printf("La memoria usata è: %d KB \n", memoryUsed / 1024);
            System.out.printf("Numero MHS trovati %d: \n", res.size());
            res.forEach(mhs -> System.out.println(Arrays.asList(mhs)));


            System.out.println("------ MBASE SENZA PRE-ELABORAZIONE------");
            System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n\n", size, size2);
            System.out.printf("I tempi di esecuzione dell'algoritmo MBase per questa istanza sono: %d millisecondi \n",
                    (end1 - start1));
            System.out.printf("La memoria usata è: %d KB \n", memoryUsed1 / 1024);
            System.out.printf("Numero MHS trovati %d: \n", res3.size());
            res3.forEach(mhs -> System.out.println(Arrays.asList(mhs)));
        } else {
            for (var file : dir.listFiles()) {
                if (file.isFile() && file.toString().endsWith(".matrix")) {
                    var mbase = new MBase();
                    var preElab = new MatrixPreElaboration();
                    var matrix = MatrixParser.parse(file);
                    var size = matrix.size();
                    var size2 = matrix.get(0).size();

                    // Risultati senza pre-elaborazione
                    var runtime1 = Runtime.getRuntime();
                    var start1 = System.currentTimeMillis();
                    var res3 = mbase.mbase(preElab.convertFromListToArray(matrix), file.getName(), time);
                    var end1 = System.currentTimeMillis();
                    var memoryUsed1 = runtime1.totalMemory() - runtime1.freeMemory();

                    var res1 = preElab.convertFromListToArray(preElab.removeColumns(preElab.removeRows(matrix)));

                    // Risultati con pre-elaborazione
                    var runtime = Runtime.getRuntime();
                    var start = System.currentTimeMillis();
                    var res = mbase.mbase(res1, file.getName(), time);
                    var end = System.currentTimeMillis();
                    var memoryUsed = runtime.totalMemory() - runtime.freeMemory();


                    System.out.println("------ MBASE + PRE-ELABORAZIONE------");
                    System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n\n", res1.length, res1[0].length);
                    System.out.printf("I tempi di esecuzione dell'algoritmo MBase per questa istanza sono: %d millisecondi \n",
                            (end - start));
                    System.out.printf("La memoria usata è: %d KB \n", memoryUsed / 1024);
                    System.out.printf("Numero MHS trovati %d: \n", res.size());
                    res.forEach(mhs -> System.out.println(Arrays.asList(mhs)));


                    System.out.println("------ MBASE SENZA PRE-ELABORAZIONE------");
                    System.out.printf("Le dimensioni dell'istanza in ingresso sono: %d x %d \n\n", size, size2);
                    System.out.printf("I tempi di esecuzione dell'algoritmo MBase per questa istanza sono: %d millisecondi \n",
                            (end1 - start1));
                    System.out.printf("La memoria usata è: %d KB \n", memoryUsed1 / 1024);
                    System.out.printf("Numero MHS trovati %d: \n", res3.size());
                    res3.forEach(mhs -> System.out.println(Arrays.asList(mhs)));
                }
            }
        }


    }
}

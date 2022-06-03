package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MatrixParser {

    private static final String PREFIX = ";;;";

    private MatrixParser() {
    }

    public static List<List<String>> parse(File file) throws FileNotFoundException {
        var sc = new Scanner(new BufferedReader(new FileReader(file)));
        var rowsCtx = new ArrayList<String>();
        var m = 0;

        while (sc.hasNext()) {
            var line = sc.nextLine();
            // exclude commented rows
            if (!line.startsWith(PREFIX)) {
                m = line.replace(" ", "").length();
                rowsCtx.add(line.substring(0, line.length() - 1));
            }
        }

        return processMatrix(rowsCtx, m);
    }

    private static List<List<String>> processMatrix(ArrayList<String> ctx, int columns) {
        String[][] array = new String[ctx.size()][columns];
        for (var i = 0; i < array.length; i++) {
            array[i] = ctx.get(i).split("\\s+");
        }
        for (var i = 0; i < array.length; i++) {
            for (var j = 0; j < array[0].length; j++) {
                if (array[i][j].equals("1")) {
                    array[i][j] = "c" + j;
                }
            }
        }
        var list = new ArrayList<List<String>>();

        for (var i = 0; i < array.length; i++) {
            list.add(new ArrayList<>());
            for (var j = 0; j < array[0].length; j++) {
                list.get(i).add(array[i][j]);
            }
        }

        return list;
    }

}
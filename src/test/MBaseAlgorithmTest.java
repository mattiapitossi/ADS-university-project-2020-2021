import algorithms.MBase;
import org.junit.jupiter.api.Test;
import utils.MatrixPreElaboration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MBaseAlgorithmTest {

    @Test
    void testMBaseAlgorithm() {
        var mbase = new MBase();
        String[][] arr = {
                {"c0", "c1", "c2", "0", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "c1", "c2", "0", "0", "0"},
                {"c0", "0", "c2", "0", "0", "c5"},
                {"c0", "c1", "c2", "c3", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "0", "c2", "c3", "0", "0"}
        };

        var output = mbase.mbase(arr, "test", 10000L);

        var myArrayList = new ArrayList<List<String>>();

        var mhs0 = new ArrayList<>(List.of("c2"));
        var mhs1 = new ArrayList<>(List.of("c1", "c3", "c5"));
        var mhs2 = new ArrayList<>(List.of("c0", "c1", "c3"));
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmPreElaboration() {
        var mbase = new MBase();
        var preelaborated = new MatrixPreElaboration();
        String[][] arr = {
                {"c0", "c1", "c2", "0", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "c1", "c2", "0", "0", "0"},
                {"c0", "0", "c2", "0", "0", "c5"},
                {"c0", "c1", "c2", "c3", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "0", "c2", "c3", "0", "0"}
        };
        var matrixList = new ArrayList<List<String>>();
        for (String[] strings : arr) {
            var rows = new ArrayList<String>();
            Collections.addAll(rows, strings);
            matrixList.add(rows);
        }
        var noRows = preelaborated.removeRows(matrixList);
        var noCols = preelaborated.removeColumns(noRows);
        var toArray = preelaborated.convertFromListToArray(noCols);
        var output = mbase.mbase(toArray, "test", 8L);

        var myArrayList = new ArrayList<List<String>>();

        var mhs0 = new ArrayList<>(List.of("c2"));
        var mhs1 = new ArrayList<>(List.of("c1", "c3", "c5"));
        var mhs2 = new ArrayList<>(List.of("c0", "c1", "c3"));
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmMat1() {
        var mbase = new MBase();
        String[][] arr = {
                {"0", "0", "c2", "c3", "0", "0"},
                {"0", "c1", "0", "c3", "0", "c5"}
        };

        var output = mbase.mbase(arr, "test", 8L);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[]{"c3", "c3"};
        var mhs1 = new String[]{"c2", "c1"};
        var mhs2 = new String[]{"c2", "c5"};
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmMat2() {
        var mbase = new MBase();
        String[][] arr = {
                {"0", "c1", "c2", "0"},
                {"c0", "c1", "c2", "c3"},
                {"c0", "0", "c2", "c3"}
        };

        var output = mbase.mbase(arr, "test", 8L);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[]{"c2", "c2", "c2"};
        var mhs1 = new String[]{"c1", "x", "c0"};
        var mhs2 = new String[]{"c1", "x", "c3"};
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

}

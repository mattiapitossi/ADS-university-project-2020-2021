import algorithms.MBase;
import org.junit.jupiter.api.Test;
import utils.MatrixPreElaboration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MBaseAlgorithmTest {

    @Test
    void testMBaseAlgorithm() throws Exception {
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

        var output = mbase.mbase(arr);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[]{null, null, "c2", null, null, null};
        var mhs1 = new String[]{null, "c1", null, "c3", null, "c5"};
        var mhs2 = new String[]{"c0", "c1", null, "c3", null, null};
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmPre() throws Exception {
        var mbase = new MatrixPreElaboration();
        var row1 = Arrays.asList("c0","c1","c2");
        var row2 = Arrays.asList("0","c1","c2");
        var matrix = new ArrayList<List<String>>();
        matrix.add(row1);
        matrix.add(row2);

        //we should be careful to not input an immutable list
        var res = mbase.removeRows(matrix);
    }

    @Test
    void testMBaseAlgorithmMat1() throws Exception {
        var mbase = new MBase();
        String[][] arr = {
                {"0", "0", "c2", "c3", "0", "0"},
                {"0", "c1", "0", "c3", "0", "c5"}
        };

        var output = mbase.mbase(arr);

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
    void testMBaseAlgorithmMat2() throws Exception {
        var mbase = new MBase();
        String[][] arr = {
                {"0", "c1", "c2", "0"},
                {"c0", "c1", "c2", "c3"},
                {"c0", "0", "c2", "c3"}
        };

        var output = mbase.mbase(arr);

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

import algorithms.MBase;
import org.junit.jupiter.api.Test;
import utils.MatrixPreElaboration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MBaseAlgorithmTest {

    @Test
    void testMBaseAlgorithm() {
        var mbase = new MBase();
        String[][] arr = {{"c0", "c1", "c2", "0", "0", "0"}, {"0", "c1", "c2", "c3", "0", "c5"}, {"0", "c1", "c2", "0", "0", "0"}, {"c0", "0", "c2", "0", "0", "c5"}, {"c0", "c1", "c2", "c3", "0", "0"}, {"0", "c1", "c2", "c3", "0", "c5"}, {"0", "0", "c2", "c3", "0", "0"}};

        var output = mbase.mbase(arr, "test", 8L);

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
    void testMBaseAlgorithmPre() {
        var preElaboration = new MatrixPreElaboration();
        var mbase = new MBase();
        var row1 = new ArrayList<String>();
        row1.add("0");
        row1.add("0");
        row1.add("c2");
        row1.add("c3");
        row1.add("0");
        row1.add("0");
        var row2 = new ArrayList<String>();
        row2.add("0");
        row2.add("c1");
        row2.add("0");
        row2.add("c3");
        row2.add("0");
        row2.add("c5");

        var matrix = new ArrayList<List<String>>();
        matrix.add(row1);
        matrix.add(row2);

        //we should be careful to not input an immutable list
        var res = preElaboration.convertFromListToArray(preElaboration.removeColumns(preElaboration.removeRows(matrix)));

        var output = mbase.mbase(res, "test", 8L);

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
    void testMBaseAlgorithmMat1() {
        var mbase = new MBase();
        String[][] arr = {{"0", "0", "c2", "c3", "0", "0"}, {"0", "c1", "0", "c3", "0", "c5"}};

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
        String[][] arr = {{"0", "c1", "c2", "0"}, {"c0", "c1", "c2", "c3"}, {"c0", "0", "c2", "c3"}};

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

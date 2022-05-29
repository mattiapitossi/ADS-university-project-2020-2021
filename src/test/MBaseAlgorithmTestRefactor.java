import algorithms.MBaseRefactorprova1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MBaseAlgorithmTestRefactor {
    @Test
    void testMVariant1Algorithm() {
        var mbase = new MBaseRefactorprova1();
        String[][] arr = {
                {"c0", "c1", "c2", "0", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "c1", "c2", "0", "0", "0"},
                {"c0", "0", "c2", "0", "0", "c5"},
                {"c0", "c1", "c2", "c3", "0", "0"},
                {"0", "c1", "c2", "c3", "0", "c5"},
                {"0", "0", "c2", "c3", "0", "0"}
        };

        var output = mbase.mBaseRefactorprova1(arr);

        var myArrayList = new ArrayList<ArrayList<String>>();

        var mhs0 = new ArrayList<>(List.of("c2", "c2", "c2", "c2", "c2", "c2", "c2"));
        var mhs1 = new ArrayList<>(List.of("c1", "x", "c1", "c5", "x", "x", "c3"));
        var mhs2 = new ArrayList<>(List.of("x", "x", "c1", "c0", "x", "x", "c3"));
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmMat1() {
        var mbase = new MBaseRefactorprova1();
        String[][] arr = {
                {"0", "0", "c2", "c3", "0", "0"},
                {"0", "c1", "0", "c3", "0", "c5"}
        };

        var output = mbase.mBaseRefactorprova1(arr);

        var myArrayList = new ArrayList<ArrayList<String>>();

        var mhs0 = new ArrayList<>(List.of("c3", "c3"));
        var mhs1 = new ArrayList<>(List.of("c2", "c1"));
        var mhs2 = new ArrayList<>(List.of("c2", "c5"));
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMBaseAlgorithmMat2() {
        var mbase = new MBaseRefactorprova1();
        String[][] arr = {
                {"0", "c1", "c2", "0"},
                {"c0", "c1", "c2", "c3"},
                {"c0", "0", "c2", "c3"}
        };

        var output = mbase.mBaseRefactorprova1(arr);

        var myArrayList = new ArrayList<ArrayList<String>>();

        var mhs0 = new ArrayList<>(List.of("c2", "c2", "c2"));
        var mhs1 = new ArrayList<>(List.of("c1", "x", "c0"));
        var mhs2 = new ArrayList<>(List.of("c1", "x", "c3"));
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

}

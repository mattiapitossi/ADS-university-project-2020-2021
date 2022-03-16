import algorithms.MVariant1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

public class MVariant1AlgorithmTest {
    
    @Test
    void testMVariant1Algorithm() throws Exception {
        var mbase = new MVariant1();
        String[][] arr = {
                { "c0", "c1", "c2", "0", "0", "0" },
                { "0", "c1", "c2", "c3", "0", "c5" },
                { "0", "c1", "c2", "0", "0", "0" },
                { "c0", "0", "c2", "0", "0", "c5" },
                { "c0", "c1", "c2", "c3", "0", "0" },
                { "0", "c1", "c2", "c3", "0", "c5" },
                { "0", "0", "c2", "c3", "0", "0" }
        };

        var output = mbase.mvariant1(arr);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[] { "c2", "c2", "c2", "c2", "c2", "c2", "c2" };
        var mhs1 = new String[] { "c1", "x", "c1", "c5", "x", "x", "c3" };
        var mhs2 = new String[] { "x", "x", "c1", "c0", "x", "x", "c3" };
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMVariant1AlgorithmMat1() throws Exception {
        var mbase = new MVariant1();
        String[][] arr = { { "0", "0", "c2", "c3", "0", "0" },
                { "0", "c1", "0", "c3", "0", "c5" }
        };

        var output = mbase.mvariant1(arr);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[] { "c3", "c3" };
        var mhs1 = new String[] { "c2", "c1" };
        var mhs2 = new String[] { "c2", "c5" };
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

    @Test
    void testMVariant1AlgorithmMat2() throws Exception {
        var mbase = new MVariant1();
        String[][] arr = {
                { "0", "c1", "c2", "0" },
                { "c0", "c1", "c2", "c3" },
                { "c0", "0", "c2", "c3" }
        };

        var output = mbase.mvariant1(arr);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[] { "c2", "c2", "c2" };
        var mhs1 = new String[] { "c1", "x", "c0" };
        var mhs2 = new String[] { "c1", "x", "c3" };
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

}
